package com.nbt.codegen.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.nbt.codegen.generators.JpaEntityGenerator;
import com.nbt.codegen.model.EntityDefinition;
import com.nbt.codegen.repo.EntityRepo;
import com.nbt.codegen.repo.RepositoryFactory;
import com.nbt.codegen.template.TemplateEngine;
import com.nbt.codegen.template.TemplateEngineFactory;
import com.nbt.codegen.utils.CommandLineArgsParser;

/**
 * Command line utility used to generate Java Source files for JPA entities. 
 *  
 * @author jefe
 *
 */
public class CommandLineJpaEntityGenerator {

	private EntityRepo entityRepo = new RepositoryFactory().getRepo();
	JpaEntityGenerator jpaEntityGenerator = new JpaEntityGenerator();
	
	String convertNamespaceToDirectoryStructure(String namespace){
		return namespace.replace(".", "/");
	}
	
	Path getTargetDirectory(String rootPublishDir, String namespace){
		if(namespace==null){
			return Paths.get(rootPublishDir);
		}
		else{
			return Paths.get(rootPublishDir +"/"+ convertNamespaceToDirectoryStructure(namespace));
		}
	}

	public void publishEntityToFileSystem(EntityDefinition entity, String rootPublishDir) throws IOException{
		String source = jpaEntityGenerator.generateJpaEntitySource(entity);
		Path targetDirectory = getTargetDirectory(rootPublishDir, entity.getNameSpace());
		if(!Files.exists(targetDirectory)){
			Files.createDirectories(targetDirectory);
		}
		Path targetFile = Paths.get(targetDirectory.toString() +"/"+ entity.getEntityName() +".java");
		Files.write(targetFile, source.getBytes());		
	}
	
	public void executeMainRoutine(String destinationDirectory)throws Exception{
		System.out.println("Please select a project from the list:");
		List<String> projects = entityRepo.listProjects();
		for(int x=0;x<projects.size();x++){
			System.out.println("\t["+ x +"] "+ projects.get(x));
		}
		System.out.println("\t [X] Exit");
		
		Scanner scanner = new Scanner(System.in);
		String response = scanner.next();
		
		if(response.equalsIgnoreCase("X")){
			System.out.println("Exiting...");
			System.exit(1);
		}
		
		String selectedProject = projects.get(Integer.parseInt(response));
		System.out.println("Please select an available action:");
		System.out.println("\t[1] Generate all entities");
		System.out.println("\t[2] Generate a selected entity");
		
		int selectedOption = Integer.parseInt(scanner.next());
		if(selectedOption==1){
			System.out.println("Generating all entities");
			List<EntityDefinition>entities = entityRepo.listEntities(selectedProject);
			for(EntityDefinition entity:entities){
				publishEntityToFileSystem(entity, destinationDirectory);
			}
		}
		else if(selectedOption==2){
			System.out.println("Please select an available entity");
			List<EntityDefinition>entities = entityRepo.listEntities(selectedProject);
			for(int x=0;x<entities.size();x++){
				System.out.println("\t["+ x +"] "+ entities.get(x).getUniqueIdentifier());
			}
			selectedOption = Integer.parseInt(scanner.next());
			
			System.out.println("Generating entity '"+ entities.get(selectedOption).getUniqueIdentifier() +"'");
			publishEntityToFileSystem(entities.get(selectedOption), destinationDirectory);
		}
		
	}
	
	/*
	public static void publishEntityArtifacts(String[] args)throws Exception{
		
	}*/
	
	/*
	public static void publishJpaRepoArtifact()throws Exception{
		
	}*/
	
	public static void main(String[] args){
		try{
			Map<String, String>parsedArgs = CommandLineArgsParser.parseArgs(args);
			if(!parsedArgs.containsKey("dir")){
				System.out.println("Please enter a value for output directory (Example: --dir=/path/to/output)");
				System.exit(0);
			}
			CommandLineJpaEntityGenerator generator = new CommandLineJpaEntityGenerator();
			generator.executeMainRoutine(parsedArgs.get("dir"));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
