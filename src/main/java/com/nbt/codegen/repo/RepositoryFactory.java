package com.nbt.codegen.repo;

/**
 * Responsible for instantiating repository implementations. 
 * 
 * @author jefe
 *
 */
public class RepositoryFactory {

	LocalFileSystemRepo fileSystemRepo;
	
	public EntityRepo getRepo(){
		
		if(fileSystemRepo==null){
			fileSystemRepo = new LocalFileSystemRepo();
		}
		return fileSystemRepo;
	}
	
}
