package com.nbt.codegen.tools;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class CommandLineEntityJsonGeneratorTest {

	@Test
	public void shouldParseEntityNamsepace() {
		
		String result, entityName;
		
		//Ensure gold case passes.
		entityName = "com.SampleEntity";
		result = CommandLineEntityJsonGenerator.parseEntityName(entityName);
		assertNotNull(result);
		assertTrue(result, result.equals("SampleEntity"));
		
		result = CommandLineEntityJsonGenerator.parsePackageName(entityName);
		assertNotNull(result);
		assertTrue(result, result.equals("com"));
				
		//Ensure non existent package handled correctly.
		entityName = "SampleEntity";
		result = CommandLineEntityJsonGenerator.parseEntityName(entityName);
		assertNotNull(result);
		assertTrue(result, result.equals("SampleEntity"));
		
		result = CommandLineEntityJsonGenerator.parsePackageName(entityName);
		assertNull(result);
		
		
		//Ensure package with multiple declarations is parsed correctly. 
		entityName = "com.second.third.SampleEntity";
		result = CommandLineEntityJsonGenerator.parseEntityName(entityName);
		assertNotNull(result);
		assertTrue(result, result.equals("SampleEntity"));
		
		result = CommandLineEntityJsonGenerator.parsePackageName(entityName);
		assertNotNull(result);
		assertTrue(result, result.equals("com.second.third"));
	}
	
	@Test
	public void shouldGenerateEntityCorrectly()throws Exception{
		String dirName = "/tmp/entity-"+ System.currentTimeMillis();Files.createDirectories(Paths.get(dirName));
		Files.createDirectories(Paths.get(dirName));
		String[] args = {"--name=SampleEntity", "--dir="+ dirName};
		//CommandLineEntityJsonGenerator generator
		CommandLineEntityJsonGenerator.main(args);
		
		assertTrue(Files.exists(Paths.get(dirName +"/SampleEntity.json")));
		
	}
	
	@Test
	public void shouldGenerateEntityWithNamespaveCorrectly()throws Exception{
		String dirName = "/tmp/entity-"+ System.currentTimeMillis();Files.createDirectories(Paths.get(dirName));
		Files.createDirectories(Paths.get(dirName));
		String[] args = {"--name=com.somthing.SampleEntity", "--dir="+ dirName};
		//CommandLineEntityJsonGenerator generator
		CommandLineEntityJsonGenerator.main(args);
		
		assertTrue(Files.exists(Paths.get(dirName +"/com.somthing.SampleEntity.json")));
		
	}

}
