package com.nbt.codegen.template;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nbt.codegen.model.EntityDefinition;

public class HandlebarsTemplateEngineTest {

	HandlebarsTemplateEngine engine;
		
	@Test
	public void shouldInitializeCorrectly() {
		engine = new HandlebarsTemplateEngine();
		engine.init();
	}
	
	@Test
	public void shouldPerformParse()throws Exception{
		EntityDefinition entity = new EntityDefinition("SampleEntity");
		
		engine = new HandlebarsTemplateEngine();
		engine.init();
		engine.loadTemplates();
		String result = engine.renderTemplate("test", entity);
		assertNotNull(result);
		assertTrue(result,result.equals("<h1>SampleEntity</h1>"));
	}

}
