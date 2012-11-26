package persistence.util;

import general.Tuple;

import java.beans.ConstructorProperties;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jdto.DTOBinder;
import org.springframework.beans.factory.annotation.Autowired;

import persistence.dto.DTO;
import persistence.model.EntityModel;
import persistence.util.UpdateEntityException;


/**
 * Classe utilitária para ajudar na manutenção dos DTOs.<br>
 * Originalmente funcionava por convenção, mas adaptada para funcionar por configuração, para integrar melhor ao Spring
 */

@SuppressWarnings("rawtypes")
public class DTOUtility {

	private String dtoPrefix;
	private String entityPrefix;
	private String dtoSuffix;
	private String entitySuffix;

	@Autowired
	private DTOBinder binder;

	@Autowired
	private SessionFactory sf;

	/**
	 * Construtor para a classe, criado especificamente para uso pelo Spring.<br>
	 * Define prefixos de classe para as entidades e os DTOs
	 * @param dtoPrefix
	 * @param entityPrefix
	 */
	@ConstructorProperties({"dtoPrefix", "entityPrefix", "dtoSuffix", "entitySuffix"})
	public DTOUtility(String dtoPrefix, String entityPrefix, String dtoSuffix, String entitySuffix){
		this.dtoPrefix = dtoPrefix;
		this.entityPrefix = entityPrefix;
		this.dtoSuffix = dtoSuffix;
		this.entitySuffix = entitySuffix;
	}

	public DTOUtility(){

	}

	/**
	 * Função utilitária para encontrar a classe de entidade correspondente a instância de<br>
	 * DTO passada como parâmetro. 
	 * @param dto a instância do DTO de onde queremos derivar a entidade
	 * @return o objeto Class representando a entidade desejada
	 */
	public Class findEntityClassForDTO(DTO dto){
		Class clazz = dto.getClass();
		String name = clazz.getSimpleName();
		try {
			return Class.forName(entityPrefix + "." + name + entitySuffix);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Função utilitária para encontrar a classe de DTO correspondente a instância de<br>
	 * entidade passada como parâmetro. 
	 * @param ent a instância de entidade de onde queremos derivar a DTO
	 * @return o objeto Class representando o DTO desejada
	 */
	public Class findDTOClassForEntity(Object ent){
		Class clazz = ent.getClass();
		String name = clazz.getSimpleName().replace(entitySuffix, dtoSuffix);
		try {
			return Class.forName(dtoPrefix + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** 
	 * Função que atualiza uma entidade com base em um DTO. A função utiliza reflexão para derivar dinamicamente<br>
	 * os getters e setters dos objetos, e então executá-los de acordo. Quando for necessário atualizar um campo<br>
	 * de entidade, a função é chamada recursivamente utilizando como parâmetros a entidade e o DTO embutidos.
	 * @param ent a instância de entidade
	 * @param dto a instância de DTO
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException 
	 */
	@SuppressWarnings("unchecked")
	public void updateEntityFromDTO(EntityModel ent, DTO dto) throws IllegalArgumentException {
		//Antes criamos uma sessão no Hibernate para a função utilizar
		Session ss = sf.openSession();

		//Primeiro verificando se os argumentos são nulos (e portanto inválidos)
		if(ent == null) throw new IllegalArgumentException("Entity argument is null");
		if(dto == null) throw new IllegalArgumentException("DTO argument is null");

		//Selecionamos a classe de cada objeto-argumento.
		//Em tese, ent_class será sempre uma classe de entidade (com sufixo MO no nome) e dto_class será sempre
		//a classe de DTO correspondente (sem sufixo no nome)
		Class ent_class = ent.getClass();
		Class dto_class = dto.getClass();

		//Agora selecionando todos os métodos declarados de cada classe.
		//ec_methods possuí os métodos da entidade, e dc_methods, os do DTO
		Method[] ec_methods = ent_class.getDeclaredMethods();
		Method[] dc_methods = dto_class.getDeclaredMethods();

		//Aqui selecionamos os getters da classe DTO e os setters da classe entidade
		//Por causa disso é vital que as classes estejam bem construídas, com nomes de campos e métodos
		//iguais.
		//Descartamos todos os getters/setters de id, que não nos interessam:
		// - Para o caso de atualizar entidade, pegamos ela antes no banco de dados, e não podemos mudar o id dela
		// - Para o caso de criar entidade, deixamos que o Hibernate gere um id automaticamente
		String em_name = null, dm_name = null;
		HashSet<Tuple<Method, Method>> sorted_methods = new HashSet<Tuple<Method, Method>>();
		for(Method em : ec_methods) {
			em_name = em.getName();
			if(!em_name.substring(3).equals("Id")) {
				for(Method dm : dc_methods) {
					dm_name = dm.getName();
					if(!dm_name.substring(3).equals("Id") && em_name.startsWith("set") && dm_name.startsWith("get") && em_name.substring(3).equals(dm_name.substring(3))) {
						sorted_methods.add(new Tuple(dm, em));
					}
				}
			}
		}		

		for(Tuple<Method, Method> pair : sorted_methods) {	
			Method get = pair.fst;
			Method set = pair.snd;
			
			//Aqui começa a ação - invocamos o getter para retornar o valor do campo,
			//armazenamos num Object e depois verificamos seu tipo para decidir o curso de ação
			Object arg = null;
			try {
				arg = get.invoke(dto, (Object[]) null);
				//Se é nulo, simplesmente setamos o valor na entidade
				if(arg == null) {
					set.invoke(ent, arg);
				}
				//Se ele é uma lista, precisamos verificar o tipo de lista
				if(arg instanceof List){
					Object test_type = ((List) arg).get(0);
					if(test_type instanceof DTO) {
						/*
						 * Sabemos que arg é uma lista de DTOs
						 * Se executar o getter equivalente da entidade, o resultado também será uma lista 
						 * ,só que de entidades. Então vamos selecionar tal lista.
						 */
						Method ent_list_getter = ent_class.getMethod(get.getName(), (Class[]) null);
						List<EntityModel> ent_list = (List<EntityModel>) ent_list_getter.invoke(ent, (Object[]) null);
						List<DTO> dto_list = (List<DTO>) arg;
						//Se a lista de entidades não for nula, temos que modificar ela
						if(ent_list != null) {
							/*
							 * Para cada DTO, itera pelas entidades que já existem na lista.
							 * Se o DTO não possuir id em comum com qualquer entidade, busque
							 * a entidade relacionada ao DTO do banco de dados. Se não existir, 
							 * taca exceção porque setaram o Id do DTO manualmente, o que é ERRADO.
							 */
							
							for(DTO dto_e : dto_list) {
								//Se o DTO não possuir id setado, é porque é um novo DTO, e será adicionado como uma nova
								//entidade no banco de dados. Do contrário, tratamos como entidade já existente
								boolean check = true;
								if(dto_e.getId() != null) {
									for(EntityModel ent_e : ent_list) {
										//Aqui nós supomos que o DTO e a entidade são referentes a mesma entrada no banco de dados, e atualizamos recursivamente. 
										//Com isso dito, recomenda-se sempre atualizar entidades que façam parte de listas separadamente.
										if(dto_e.getId() == ent_e.getId()) {
											updateEntityFromDTO(ent_e, dto_e);
											ss.update(ent_e);
											check = false;
											break;
										}
									}
									if(check) {
										//Aqui buscamos a entidade que possuí o mesmo id do DTO da lista. O mesmo príncipio descrito acima vale aqui.
										//Sempre modifique as entidades de menor nível antes de manipular entidades com listas.
										EntityModel new_ent = (EntityModel) ss.get(findEntityClassForDTO(dto_e), dto_e.getId());
										if(new_ent != null) {
											updateEntityFromDTO(new_ent, dto_e);
											ss.update(new_ent);
											ent_list.add(new_ent);
										}
										else {
											throw new UpdateEntityException("DO NOT SET ID ON NEWLY CREATED DTO INSTANCES");
										}
									}
								}
								else {
									//Criamos uma entidade vazia, re-executamos a função nela e no DTO da lista, depois salvamos no banco de dados e adicionamos na lista
									EntityModel new_ent = createEmptyEntityInstanceFromDTOType(dto_e);
									updateEntityFromDTO(new_ent, dto_e);
									ss.save(new_ent);
									ent_list.add(new_ent);
								}
							}
						}
						//Se ent_list for nulo, então criaremos uma nova lista. O resto do processo é análogo a quando existe uma lista:
						//Se o id do DTO é nulo, cria-se nova entidade. Do contrário, busca-se no banco de dados.
						else {
							ent_list = new ArrayList<EntityModel>();
							for(DTO dto_e : dto_list) {
								if(dto_e.getId() != null) {
									EntityModel new_ent = (EntityModel) ss.get(findEntityClassForDTO(dto_e), dto_e.getId());
									if(new_ent != null) {
										ent_list.add(new_ent);
									}
									else {
										throw new UpdateEntityException("DO NOT SET ID ON NEWLY CREATED DTO INSTANCES");
									}
								}
								else {
									EntityModel new_ent = createEmptyEntityInstanceFromDTOType(dto_e);
									updateEntityFromDTO(new_ent, dto_e);
									ss.save(new_ent);
									ent_list.add(new_ent);
								}
							}
						}
						//Independente do que acontecer, terminamos setando a lista de entidades gerada ou atualizada na entidade com a qual estamos trabalhando
						set.invoke(ent, ent_list);
					}
					//Se não é uma lista de entidades... Bem, o resto é simples: só criar uma lista dos objetos novos e substituir a lista antiga na entidade
					else {
						List<Object> ent_list = new ArrayList<Object>();
						for(Object obj : (List<Object>) arg) {
							ent_list.add(obj);
						}
						set.invoke(ent, ent_list);
					}
				}
				//Se arg é um DTO, precisamos atualizar a entidade embutida dentro da entidade com a qual estamos trabalhando
				else if(arg instanceof DTO){
					Method ent_getter = ent_class.getMethod(get.getName(), (Class[]) null);
					Object embedded_ent = null;
					//Se o DTO é novo e sem id, criamos uma nova entidade para ele, chamando recursivamente esta função passando
					//uma entidade vazia, salvando a entidade resultante, e setando o valor na entidade-mãe
					if(((DTO) arg).getId() == null){
						Class embedded_ent_class = ent_getter.getReturnType();
						embedded_ent = embedded_ent_class.newInstance();
						updateEntityFromDTO((EntityModel) embedded_ent, (DTO) arg);
						ss.save((EntityModel) embedded_ent);
						set.invoke(ent, embedded_ent);
					}
					//Do contrário, aí sim nós atualizamos a entidade embutida, primeiro buscando a entidade no banco de dados para garantir
					// e depois atualizando-a, chamando esta função recursivamente, mandando um comando de update no banco e setando o valor
					else{
						embedded_ent = ss.get(findEntityClassForDTO((DTO) arg), ((DTO) arg).getId());
						//Se não encontrarmos a entidade original, alguém foi idiota e setou id em DTO manualmente
						if(embedded_ent != null) {
							updateEntityFromDTO((EntityModel) embedded_ent, (DTO) arg);
							ss.update((EntityModel) embedded_ent);
							set.invoke(ent, embedded_ent);
						}
						else {
							throw new UpdateEntityException("DO NOT SET ID ON NEWLY CREATED DTO INSTANCES");
						}
					}
					//Finalmente, se arg for apenas um humilde objeto sem relação com o banco de dados (isto é, não é DTO nem nada), apenas executar o setter sem rodeios
				} else set.invoke(ent, arg);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (UpdateEntityException e) {
				e.printStackTrace();
			}
		}
	}


	public EntityModel createEmptyEntityInstanceFromDTOType(DTO dto) throws InstantiationException, IllegalAccessException {
		return (EntityModel) findEntityClassForDTO(dto).newInstance();
	}

}
