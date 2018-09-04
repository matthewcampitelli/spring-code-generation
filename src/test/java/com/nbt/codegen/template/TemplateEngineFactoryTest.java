package com.nbt.codegen.template;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemplateEngineFactoryTest {

	TemplateEngineFactory factory = new TemplateEngineFactory();
	
	@Test
	public void shouldSuccessfullLoadEnginet() {
		TemplateEngine engine = factory.getTemplateEngine();
		assertNotNull(engine);
	}

}
