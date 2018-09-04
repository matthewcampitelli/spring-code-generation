package com.nbt.codegen.repo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.nbt.codegen.EntityParser;
import com.nbt.codegen.model.EntityDefinition;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * <p>
 * Storage container for managing entity data that makes use the local file system. Physical location will exist under the current user's home directory. It is important to note that 
 * this is the most basic storage implementation where dependencies are minimized (a.k.a no data base is required). The intent is to provide a basic implementation, however as a 
 * side effect this implementation should be used for single user; there is no mechanism to synchronize changes aross multiple users. 
 * </p>
 * 
 * 
 * @author jefe
 *
 */
public class LocalFileSystemRepo implements EntityRepo{

	String baseDirectory; 
	
	private EntityParser parser = new EntityParser();
	
	/**
	 * Default constructor, persistent directory located under user's home directory.
	 */
	public LocalFileSystemRepo(){
		
	}
	
	/**
	 * Alternate constructor, where folder for persistent store is located.
	 * 
	 * @param baseDirectory
	 */
	public LocalFileSystemRepo(String baseDirectory){
		this.baseDirectory = baseDirectory;
	}
	
	@Override
	public void initRepo() throws Exception {
		Path repoDirRef = getRootRepoDirectory();
		
		//Files.deleteIfExists(repoDirRef);
		
		if(Files.exists(repoDirRef)){
			final List<Path> pathsToDelete = Files.walk(repoDirRef).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			for(Path path : pathsToDelete) {
			    Files.deleteIfExists(path);
			}
		}
		
		Files.createDirectories(repoDirRef);
		Files.createDirectories(getProjectsDirectory());
	}

	
	
	@Override
	public boolean repoInititalized() throws Exception {
		return Files.exists(getProjectsDirectory());
	}

	@Override
	public void importEntityDefinition(String projectName, EntityDefinition entity) throws Exception {
		assertProjectDirectoryExists(projectName);
		try{
			Path targetFile = getEntityFile(entity.getUniqueIdentifier(), projectName);
			if(Files.exists(targetFile)){
				Files.delete(targetFile);
			}
			Files.write(targetFile, parser.toJson(entity).getBytes());
		}
		catch(Exception e){
			throw new RuntimeException("Unable to parse entity '"+ entity.getUniqueIdentifier() +"' for project '"+ projectName +"'");
		}
	}

	@Override
	public List<EntityDefinition> listEntities(String projectName) throws Exception {
		assertProjectDirectoryExists(projectName);
		List<EntityDefinition>results = new ArrayList();
		Files.list(getProjectDirectory(projectName)).filter( p->{ return p.toString().endsWith(".json"); } ).forEach( p->{  
			try{
				String json = new String(Files.readAllBytes(p));
				results.add(parser.parserEntityFromJson(json));
			}
			catch(IOException e){
				throw new RuntimeException("Exception ocurred while parsing file '"+ p.getFileName() +"'");
			}
		});
		return results;
	}
	
	@Override
	public EntityDefinition getEntityDefinitionById(String uniqueId, String projectName){
		try{
			assertProjectDirectoryExists(projectName);
			Path selected = getEntityFile(uniqueId, projectName);
			if(!Files.exists(selected)){
				return null;
			}
			return parser.parserEntityFromJson(new String(Files.readAllBytes(selected)));
		}
		catch(Exception e){
			throw new RuntimeException("Unable to read referenced entity '"+ uniqueId +"' for project '"+ projectName +"'");
		}
	}

	@Override
	public void deleteEntityDefintion(String uniqueId, String projectName) throws Exception {
		assertProjectDirectoryExists(projectName);
		//Files.delete(Paths.get(getProjectDirectory(projectName) + File.separator + uniqueId));
		Files.delete(getEntityFile(uniqueId, projectName));
	}

	public boolean projectExists(String projectName) throws Exception {
		return Files.exists(getProjectDirectory(projectName));
	}
	
	Path getRootRepoDirectory(){
		if(this.baseDirectory!=null){
			return Paths.get(this.baseDirectory);
		}
		return Paths.get(System.getProperty("user.home") + File.separator +".entity-gen");
	}
	
	Path getProjectsDirectory()throws IOException{
		return Paths.get(getRootRepoDirectory().toString() + File.separator + "/projects");
	}
	
	Path getProjectDirectory(String projectName)throws IOException{
		return Paths.get(getProjectsDirectory().toString() + File.separator + projectName);
	}
	
	Path createProjectDirectory(String projectName)throws Exception{
		return Files.createDirectories(Paths.get(getProjectsDirectory().toString() + File.separator + projectName));
	}
	
	Path getEntityFile(String id, String projectName)throws Exception{
		return Paths.get(getProjectDirectory(projectName).toString() + File.separator + id +".json");
	}
	
	boolean projectDirectoryExists(String projectName)throws Exception{
		return Files.exists(getProjectDirectory(projectName));
	}

	void assertProjectDirectoryExists(String projectName)throws Exception{
		if(!projectExists(projectName)){
			createProjectDirectory(projectName);
		}
	}
	
	public List<String>listProjects(){
		List<String>projects = new ArrayList();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getProjectsDirectory())) {
            for (Path path : directoryStream) {
            	projects.add(path.getFileName().toString());
            }
        } catch (IOException ex) {
        	throw new RuntimeException("Unable to list projects");
        }
		return projects;
	}
}
