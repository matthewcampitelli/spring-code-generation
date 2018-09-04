package com.nbt.codegen.repo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.nbt.codegen.model.EntityDefinition;

public class LocalFileSystemRepoTest {

	LocalFileSystemRepo repo;
	
	@Test
	public void shouldCorrectlyIdentifyIfRepoInitialized()throws Exception{
		String rootRepoDir = "/tmp/repo-test/"+ getUniqueRootDirectoryName();
		repo = new LocalFileSystemRepo(rootRepoDir);
		assertFalse(repo.repoInititalized());
		Files.createDirectories(Paths.get(rootRepoDir));
		Files.createDirectories(Paths.get(rootRepoDir +"/projects"));
		assertTrue(rootRepoDir, repo.repoInititalized());
	}
	
	@Test
	public void shouldInitializeRepoCorrectly()throws Exception{
		String rootRepoDir = "/tmp/repo-test/"+ getUniqueRootDirectoryName();
		repo = new LocalFileSystemRepo(rootRepoDir);
		repo.initRepo();
		assertTrue(Files.exists(Paths.get(rootRepoDir +"/projects")));
	}
	
	@Test
	public void shouldAssertProjectDirectoryExists()throws Exception{
		String rootRepoDir = "/tmp/repo-test/"+ getUniqueRootDirectoryName();
		repo = new LocalFileSystemRepo(rootRepoDir);
		repo.initRepo();
		repo.assertProjectDirectoryExists("sampleProject1");
		assertTrue(rootRepoDir, Files.exists(Paths.get(rootRepoDir +"/projects/sampleProject1")));
	}
	
	@Test
	public void shouldPersistEntityFile()throws Exception{
		String rootRepoDir = "/tmp/repo-test/"+ getUniqueRootDirectoryName();
		repo = new LocalFileSystemRepo(rootRepoDir);
		EntityDefinition entity = new EntityDefinition("SampleEntity", "sample1.sample2");
		repo.importEntityDefinition("project1", entity);
		String expDir = rootRepoDir +"/projects/project1/sample1.sample2.SampleEntity.json";
		assertTrue("File '"+ expDir +"'  does not exist", Files.exists(Paths.get(expDir)));
	}
	
	/**
	 * Assert that an insertion on an existing entity results in an update operation and not an insertion. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldPersistExistingEntityFile()throws Exception{
		String rootRepoDir = "/tmp/repo-test/"+ getUniqueRootDirectoryName();
		repo = new LocalFileSystemRepo(rootRepoDir);
		EntityDefinition entity = new EntityDefinition("SampleEntity", "sample1.sample2");
		entity.setNotes("v1");
		repo.importEntityDefinition("project1", entity);
		entity.setNotes("v2");
		repo.importEntityDefinition("project1", entity);
		String expDir = rootRepoDir +"/projects/project1/sample1.sample2.SampleEntity.json";
		assertTrue("File '"+ expDir +"'  does not exist", Files.exists(Paths.get(expDir)));
		entity = repo.getEntityDefinitionById("sample1.sample2.SampleEntity", "project1");
		assertTrue(entity!=null);
		assertTrue(entity.getNotes().equals("v2"));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldLoadEntityCorrectly()throws Exception{
		String rootRepoDir = "/tmp/repo-test/"+ getUniqueRootDirectoryName();
		repo = new LocalFileSystemRepo(rootRepoDir);
		Files.createDirectories(Paths.get(rootRepoDir +"/projects"));
		Files.createDirectories(Paths.get(rootRepoDir +"/projects/sampleProject"));
		Files.write(Paths.get(rootRepoDir +"/projects/sampleProject/Test.json"), "{\"entityName\":\"Test\"}".getBytes());
		EntityDefinition entity = repo.getEntityDefinitionById("Test", "sampleProject");
	}
	
	@Test
	public void shouldListEntitiesCorrectly()throws Exception{
		String rootRepoDir = "/tmp/repo-test/"+ getUniqueRootDirectoryName();
		repo = new LocalFileSystemRepo(rootRepoDir);
		Files.createDirectory(Paths.get(rootRepoDir));
		Files.createDirectory(Paths.get(rootRepoDir +"/projects"));
		Files.createDirectory(Paths.get(rootRepoDir +"/projects/project1"));
		Files.write(Paths.get(rootRepoDir +"/projects/project1/Test1.json"), "{\"entityName\":\"Test1\"}".getBytes());
		Files.write(Paths.get(rootRepoDir +"/projects/project1/Test2.json"), "{\"entityName\":\"Test2\"}".getBytes());
		Files.write(Paths.get(rootRepoDir +"/projects/project1/Test3.json"), "{\"entityName\":\"Test3\"}".getBytes());
		List<EntityDefinition> entities = repo.listEntities("project1");
		assertTrue("Expected 3 entities, actual "+ entities.size(), entities.size()==3);
	}
	
	@Test
	public void shouldDeleteEntity(){
		
	}

	String getUniqueRootDirectoryName(){
		return System.currentTimeMillis() +""+ (int) (Math.random() * (100 - 1)) + 1;
	}
}
