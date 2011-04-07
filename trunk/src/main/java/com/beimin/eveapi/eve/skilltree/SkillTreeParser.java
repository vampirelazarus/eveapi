package com.beimin.eveapi.eve.skilltree;


import org.apache.commons.digester.AbstractObjectCreationFactory;
import org.apache.commons.digester.Digester;
import org.xml.sax.Attributes;

import com.beimin.eveapi.core.AbstractListParser;
import com.beimin.eveapi.core.ApiException;
import com.beimin.eveapi.core.ApiPage;
import com.beimin.eveapi.core.ApiPath;

public class SkillTreeParser extends AbstractListParser<SkillTreeResponse, ApiSkillGroup> {
	public SkillTreeParser() {
		super(SkillTreeResponse.class, 2, ApiPath.EVE, ApiPage.SKILL_TREE, ApiSkillGroup.class);
	}

	@Override
	protected Digester getDigester() {
		Digester digester = super.getDigester();
		digester.addObjectCreate("eveapi/result/rowset/row/rowset/row", ApiSkill.class);
		digester.addSetProperties("eveapi/result/rowset/row/rowset/row");
		digester.addBeanPropertySetter("eveapi/result/rowset/row/rowset/row/description");
		digester.addBeanPropertySetter("eveapi/result/rowset/row/rowset/row/rank");
		digester.addFactoryCreate("eveapi/result/rowset/row/rowset/row/rowset/row",
				new AbstractObjectCreationFactory() {
					@Override
					public Object createObject(Attributes attributes) throws Exception {
						if (attributes.getValue("typeID") != null)
							return new ApiRequirement();
						if (attributes.getValue("bonusType") != null)
							return new ApiBonus();
						return null;
					}
				});
		digester.addSetProperties("eveapi/result/rowset/row/rowset/row/rowset/row");
		digester.addSetNext("eveapi/result/rowset/row/rowset/row/rowset/row", "add");
		digester.addSetNext("eveapi/result/rowset/row/rowset/row", "add");
		return digester;
	}

	public static SkillTreeParser getInstance() {
		return new SkillTreeParser();
	}

	@Override
	public SkillTreeResponse getResponse() throws ApiException {
		return super.getResponse();
	}
}