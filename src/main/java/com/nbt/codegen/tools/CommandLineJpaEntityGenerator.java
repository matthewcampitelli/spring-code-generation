package com.nbt.codegen.generators;

import com.nbt.codegen.FileSystemUtils;

/**
 * Command line utility used to generate Java Source files for JPA entities. 
 *  
 * @author jefe
 *
 */
public class CommandLineJpaEntityGenerator {

	
	public static void printUsage(){
		System.out.println("Usage:");
		System.out.println("	CommandLineEntityGenerator [command]");
		System.out.println("Where [command] is one of tthe following");
		System.out.println("	generate-entity [file name] [destination directory]");
		System.out.println("		Generates a single entity from the specified file.");
		System.out.println("	generate-entity [directory] [destination directory]");
		System.out.println("		Generates all entities within the specified directory.");
		System.out.println("And [destination directory] is an optional argument specifying where artifacts are to be published. Default destination is 'publish'.");
		
	}
	
	public static void publishEntityArtifact(String[] args)throws Exception{
		if(args.length<2){
			
		}
		
		String entityJson = FileSystemUtils.loadFileContents(args[1]);
		if(entityJson==null){
			throw new Exception("No content present for entity '"+ args[1] +"'.");
		}
		
		
				
	}
	
	public static void publishEntityArtifacts(String[] args)throws Exception{
		
	}
	
	public static void publishJpaRepoArtifact()throws Exception{
		
	}
	
	public static void main(String[] args){
		try{
			if(args.length<1){
				CommandLineEntityGenerator.printUsage();
				return;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
