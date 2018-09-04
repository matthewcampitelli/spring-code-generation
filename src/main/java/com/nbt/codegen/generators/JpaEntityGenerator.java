package com.nbt.codegen.generators;

import com.nbt.codegen.model.EntityDefinition;
import com.nbt.codegen.template.TemplateEngine;
import com.nbt.codegen.template.TemplateEngineFactory;

public class JpaEntityGenerator {

	private TemplateEngine templateEngine = new TemplateEngineFactory().getTemplateEngine();
	
	public String generateJpaEntitySource(EntityDefinition entity){
		return templateEngine.renderTemplate("jpa-entity", entity);
	}
}
