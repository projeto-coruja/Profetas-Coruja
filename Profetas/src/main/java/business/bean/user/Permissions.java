package business.bean.user;

/**
 * Enum contendo todas as permissões de usuário.
 */
public enum Permissions {
    userInfoUpdatePermission, // Permissão para atualizar informações de outras
			      // contas.
    userRemovalPermission, // Permissão para remover um usuário do banco de
			   // dados.
    userApprovalPermission, // Permissão para aprovar um usuário e atribuir um
			    // profile novo para o mesmo.
    addNewUserPermission // Permissão para adicionar um novo usuário
			 // pré-aprovado.
}
