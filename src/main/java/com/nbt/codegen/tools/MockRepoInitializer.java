package com.nbt.codegen.tools;

import com.nbt.codegen.model.AttributeDefinition;
import com.nbt.codegen.model.EntityDefinition;
import com.nbt.codegen.repo.EntityRepo;
import com.nbt.codegen.repo.RepositoryFactory;

public class MockRepoInitializer {
	
	public static void main(String[] args){
		
		try{
			System.out.println("Initializing repo with mock data set.");
			
			EntityRepo entityRepo = new RepositoryFactory().getRepo();
			EntityDefinition entity;
			
			entityRepo.initRepo();
			
			String projectName = "Project1";
			entity = new EntityDefinition("ExampleEntity1");
			AttributeDefinition attr = new AttributeDefinition("name");
			attr.setDescription("A sample attribute.");
			entity.addAttribute(attr);
			entity.setDescription("A sample attribute");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity2");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity3");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity4","example");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity5","example");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity6","example");
			entityRepo.importEntityDefinition(projectName, entity);
			
			projectName = "Project2";
			entity = new EntityDefinition("ExampleEntity7");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity8");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity9");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity10","example");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity11","example");
			entityRepo.importEntityDefinition(projectName, entity);
			entity = new EntityDefinition("ExampleEntity12","example");
			entityRepo.importEntityDefinition(projectName, entity);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
