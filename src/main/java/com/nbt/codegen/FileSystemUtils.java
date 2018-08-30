package com.nbt.codegen;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemUtils {

	public static String loadFileContents(String fileName)throws Exception{
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}
	
	public static void createPackage
}
