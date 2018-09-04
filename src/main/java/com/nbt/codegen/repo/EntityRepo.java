package com.nbt.codegen.repo;

import java.util.List;

import com.nbt.codegen.model.EntityDefinition;

/**
 * Representation of an storage for entity and project related operations. Abstraction implemented so that different 
 * mechanisms may be implemented for storage (File System, Database...). 
 * 
 * @author jefe
 *
 */
public interface EntityRepo {
	
	/**
	 * Initializes the data store. Will 'wipe out' entire contents, so a check should be performed prior to 
	 * triggering this action. A data store must be initialized prior to any otther operations. 
	 * 
	 * 
	 * @throws Exception
	 */
	public void initRepo()throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean repoInititalized()throws Exception;
	
	/**
	 * Import an entity into the store. Should the entity exist, the end result will be an update instead of an insert operation.
	 * 
	 * @param projectName
	 * @throws Exception
	 */
	public void importEntityDefinition(String projectName, EntityDefinition entity)throws Exception;
	
	/**
	 * 
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public List<EntityDefinition> listEntities(String projectName)throws Exception;
	
	/**
	 * 
	 * @param id
	 * @param projectName
	 * @return
	 */
	public EntityDefinition getEntityDefinitionById(String id, String projectName);
	
	/**
	 * 
	 * @param uniqueId
	 * @param projectName
	 * @throws Exception
	 */
	public void deleteEntityDefintion(String uniqueId, String projectName)throws Exception;
	
	public boolean projectExists(String projectName)throws Exception;
	
	public List<String>listProjects();
	
}
