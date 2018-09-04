package com.nbt.codegen.utils;

import java.util.Map;
import java.util.HashMap;

public class CommandLineArgsParser {

	public static Map<String, String> parseArgs(String[] args){
		Map<String, String> parsedArgs = new HashMap();
		
		String[]tokens;
		for(String arg:args){
			if(arg.startsWith("--") && arg.indexOf("=")>-1){
				tokens = arg.split("=");
				parsedArgs.put(tokens[0].substring(2, tokens[0].length()), tokens[1]);
			}
		}
		
		return parsedArgs;
	}
	
}
