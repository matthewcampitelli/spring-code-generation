package com.nbt.codegen.tools;

import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;

import com.nbt.codegen.EntityParser;
import com.nbt.codegen.model.EntityDefinition;
//import com.github.jknack.handlebars.internal.Files;
//import com.github.jknack.handlebars.internal.Files;
import com.nbt.codegen.repo.EntityRepo;
import com.nbt.codegen.repo.RepositoryFactory;
import com.nbt.codegen.utils.CommandLineArgsParser;

/**
 * <p>Command line utility for importing entity definitions in JSON format into the storage engine. The generate process 
 * for performing code generation tasks is to initially either define an entity in an JSON file from scratch or to generate one using the 
 * 'CommandLineEntityJsonGenerator' class. Next the entity is imported into a storage engine, and then finally generation related operations 
 * are performed against the data stored in the repository.</p>
 * 
 * @author jefe
 *
 */
public class CommandLineEntityImporter {

	RepositoryFactory repoFactory = new RepositoryFactory();
	EntityParser parser = new EntityParser();
	
	public static void printUsage(){
		
		StringBuilder output = new StringBuilder();
		System.out.println(output.toString());
		
		output.append("Usage:\n");
		output.append("	CommandLineEntityImporter [file] [dir] [project]\n");
		output.append("Where\n");
		output.append("	--file=filename\n"); 
		output.append("		Name of the file that contains the entity definition.");
		output.append("	--dir=dirname\n"); 
		output.append("		Name of the directory containing one or more entity definitions to import.");
		output.append("	--project\n"); 
		output.append("		Name of the project (required) that entity is to be imported into.");
		
	}
	
	EntityRepo getRepo(){
		return repoFactory.getRepo();
	}
	
	void importEntity(File file, String projectName)throws Exception{
		if(!file.exists()){
			System.out.println("Target file '"+ file.getName() +"' does not exist, skipping import operation");
		}
		
		System.out.println("Importing target file '"+ file.getName() +"'");
		
		String json = new String(Files.readAllBytes(Paths.get(file.getName())));
		EntityDefinition entity = parser.parserEntityFromJson(json);
		getRepo().importEntityDefinition(projectName, entity);
	}
	
	void importEntity(String fileName, String projectName)throws Exception{
		importEntity(new File(fileName), projectName);
	}
	
	void importDirectory(String directoryName, String projectName)throws Exception{
		File targetDir = new File(directoryName);
		if(!targetDir.isDirectory()){
			System.out.println("Target directory '"+ directoryName +"' does not refer to a valid directory.");
			return;
		}
		
		File[] files = targetDir.listFiles();
		for(File file:files){
			if(!file.isFile()){
				continue;
			}
			if(file.getName().endsWith(".json")){
				importEntity(file, projectName);
			}
		}
	}
	
	void performImportOperation(Map<String, String>args)throws Exception{
		if(args.containsKey("file")){
			
		}
		else if(args.containsKey("directory")){
			
		}
		else{
			System.out.println("No argument for targt file or directory provided.");
		}
	}
	
	public static void main(String[] args){
		if(args.length<1||args[0].equalsIgnoreCase("--help")){
			CommandLineEntityJsonGenerator.printUsage();
			System.exit(1);
		}
		
		try{
			Map<String, String>parsedArgs = CommandLineArgsParser.parseArgs(args);
			CommandLineEntityImporter parser = new CommandLineEntityImporter();
			parser.performImportOperation(parsedArgs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
