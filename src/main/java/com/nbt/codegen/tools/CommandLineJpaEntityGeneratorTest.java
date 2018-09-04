package com.nbt.codegen.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandLineJpaEntityGeneratorTest {

	CommandLineJpaEntityGenerator generator = new CommandLineJpaEntityGenerator();
	
	@Test
	public void testConvertNamespaceToDirectoryStructure() {
		String namespace = "com.something.another.andanother";
		String response = generator.convertNamespaceToDirectoryStructure(namespace);
		assertTrue(response, response.equals("com/something/another/andanother"));
	}

}
