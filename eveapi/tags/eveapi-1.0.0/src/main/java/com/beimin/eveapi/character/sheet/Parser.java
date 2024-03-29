package com.beimin.eveapi.character.sheet;

import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.beimin.eveapi.AbstractApiParser;
import com.beimin.eveapi.ApiAuth;

public class Parser extends AbstractApiParser<Response> {
	protected static final String CHARACTER_SHEET_URL = Path.CHARACTER.getPath() + "/CharacterSheet.xml.aspx";

	public Parser() {
		super(Response.class, 1, CHARACTER_SHEET_URL);
	}

	public Response getCharacterSheet(ApiAuth auth) throws IOException, SAXException {
		return getResponse(auth);
	}

	@Override
	protected Digester getDigester() {
		Digester digester = super.getDigester();
		digester.addBeanPropertySetter("eveapi/result/characterID");
		digester.addBeanPropertySetter("eveapi/result/name");
		digester.addBeanPropertySetter("eveapi/result/race");
		digester.addBeanPropertySetter("eveapi/result/bloodLine");
		digester.addBeanPropertySetter("eveapi/result/gender");
		digester.addBeanPropertySetter("eveapi/result/corporationName");
		digester.addBeanPropertySetter("eveapi/result/corporationID");
		digester.addBeanPropertySetter("eveapi/result/balance");
		digester.addObjectCreate("eveapi/result/attributeEnhancers/intelligenceBonus", IntelligenceBonus.class);
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/intelligenceBonus/augmentatorName");
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/intelligenceBonus/augmentatorValue");
		digester.addSetNext("eveapi/result/attributeEnhancers/intelligenceBonus", "addAttributeEnhancer");

		digester.addObjectCreate("eveapi/result/attributeEnhancers/memoryBonus", MemoryBonus.class);
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/memoryBonus/augmentatorName");
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/memoryBonus/augmentatorValue");
		digester.addSetNext("eveapi/result/attributeEnhancers/memoryBonus", "addAttributeEnhancer");

		digester.addObjectCreate("eveapi/result/attributeEnhancers/charismaBonus", CharismaBonus.class);
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/charismaBonus/augmentatorName");
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/charismaBonus/augmentatorValue");
		digester.addSetNext("eveapi/result/attributeEnhancers/charismaBonus", "addAttributeEnhancer");

		digester.addObjectCreate("eveapi/result/attributeEnhancers/perceptionBonus", PerceptionBonus.class);
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/perceptionBonus/augmentatorName");
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/perceptionBonus/augmentatorValue");
		digester.addSetNext("eveapi/result/attributeEnhancers/perceptionBonus", "addAttributeEnhancer");

		digester.addObjectCreate("eveapi/result/attributeEnhancers/willpowerBonus", WillpowerBonus.class);
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/willpowerBonus/augmentatorName");
		digester.addBeanPropertySetter("eveapi/result/attributeEnhancers/willpowerBonus/augmentatorValue");
		digester.addSetNext("eveapi/result/attributeEnhancers/willpowerBonus", "addAttributeEnhancer");

		digester.addBeanPropertySetter("eveapi/result/attributes/intelligence");
		digester.addBeanPropertySetter("eveapi/result/attributes/memory");
		digester.addBeanPropertySetter("eveapi/result/attributes/charisma");
		digester.addBeanPropertySetter("eveapi/result/attributes/perception");
		digester.addBeanPropertySetter("eveapi/result/attributes/willpower");

		digester.addObjectCreate("eveapi/result/rowset/row", ApiSkill.class);
		digester.addSetProperties("eveapi/result/rowset/row");
		digester.addSetNext("eveapi/result/rowset/row", "addSkill");
		return digester;
	}

	public static Parser getInstance() {
		return new Parser();
	}
}