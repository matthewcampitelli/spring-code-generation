package com.nbt.codegen.utils;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class CommandLineArgsParserTest {
	
	@Test
	public void shouldParseEmptyArgs(){
		String[] args = {};
		Map<String, String>results = CommandLineArgsParser.parseArgs(args);
		assertTrue(results.size()==0);
	}
	
	@Test
	public void shouldIgnoreInvalidArgs(){
		String[] args = {"--arg1=value1","arg2=value2","--arg3","--arg4=value4"};
		Map<String, String>results = CommandLineArgsParser.parseArgs(args);
		assertTrue(""+ results.size(),results.size()==2);
	}
	
	@Test
	public void shouldParseArgsCorrectly(){
		String[] args = {"--arg1=value1","--arg2=value2","--arg3=value3","--arg4=value4"};
		Map<String, String>results = CommandLineArgsParser.parseArgs(args);
		assertTrue(results.size()==4);
	}

}
