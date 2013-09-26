package persistence.model;

/**
 * Interface de marcação para todos as entidades que possuem ID numérico.<br>
 */
public interface IdentifiedEntity extends EntityModel{

	Long getId();
	
}
