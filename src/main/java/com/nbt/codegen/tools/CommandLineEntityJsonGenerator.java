package com.nbt.codegen.tools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import com.nbt.codegen.EntityParser;
import com.nbt.codegen.model.EntityDefinition;
import com.nbt.codegen.utils.CommandLineArgsParser;

/**
 * Provides method for generating entity JSON files via the command line. By default, a minimal skeleton of the object 
 * definition format is generated. Additionally, entities may be generated off of stereotypes. 
 *  
 * 
 * 
 * @author jefe
 *
 */
public class CommandLineEntityJsonGenerator {

	public static void printUsage(){
		System.out.println("");
		System.out.println("");
		
		StringBuilder output = new StringBuilder();
		output.append("Usage:\n");
		output.append("	CommandLineEntityJsonGenerator [args]\n");
		output.append("Where\n");
		output.append("	--name\n"); 
		output.append("		Name (required) of the Entity.class Either a name, or a fully qualified entity name.\n");
		output.append("	--dir\n");
		output.append("		Optional output directory for file.\n");
		output.append("	--type\n");
		output.append("		Optional stereotype for object. Legal values are:\n");
		output.append("			'person'\n");
		output.append("				Creates a full person entity with contact information (addres, phone, and email)");
		output.append("				");
		output.append("			'address'\n");
		output.append("			'simple-dict'\n");
		output.append("				Generates a basic dictionary entity, int based pk, simple 'name' field, and an 'active' attribute");
		output.append("			'phone'\n");
					
		System.out.println(output.toString());
	}
	
	public static void generateEntitySkeleton(String entityName, String packageRef, Path destinationDir)throws Exception{
		EntityDefinition entity = new EntityDefinition(entityName, packageRef);
		EntityParser parser = new EntityParser();
		String json = new EntityParser().toJson(entity);
		
		Path destinationFile = Paths.get(destinationDir.toString() + File.separator + entity.getUniqueIdentifier() +".json");
		
		Files.write(destinationFile, json.getBytes());
	}
	
	static String parsePackageName(String entityName){
		if(!entityName.contains(".")){
			return null;
		}
		
		return entityName.replace( "."+ parseEntityName(entityName), "");
	}
	
	static String parseEntityName(String name){
		if(!name.contains(".")){
			return name;
		}
		
		String[] tokens = name.split("\\.");
		return tokens[tokens.length-1];
	}
	
	public static void main(String[] args){
		if(args.length<1||args[0].equalsIgnoreCase("--help")){
			CommandLineEntityJsonGenerator.printUsage();
			System.exit(1);
		}
		
		try{
			Map<String, String>parsedArgs = CommandLineArgsParser.parseArgs(args);
			Path targetDir = Paths.get(parsedArgs.get("dir"));
			if(!Files.exists(targetDir)){
				throw new Exception("Target directory '"+ parsedArgs.get("dir") +"' does not exist"); 
			}
			
			String entityName = parsedArgs.get("name");
			//if(entityName.contains(".")){
				generateEntitySkeleton(parseEntityName(entityName), parsePackageName(entityName), targetDir);
			//}else{
			//	generateEntitySkeleton(entityName, null, targetDir);
			//}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
