package com.beimin.eveapi.corporation.starbase.detail;

import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.beimin.eveapi.AbstractApiParser;
import com.beimin.eveapi.ApiAuth;
import com.beimin.eveapi.ApiPage;
import com.beimin.eveapi.ApiPath;

public class StarbaseDetailParser extends AbstractApiParser<StarbaseDetailResponse> {
	public StarbaseDetailParser() {
		super(StarbaseDetailResponse.class, 2, ApiPath.CORPORATION, ApiPage.STARBASE_DETAIL);
	}

	@Override
	protected Digester getDigester() {
		Digester digester = super.getDigester();
		digester.addBeanPropertySetter("eveapi/result/generalSettings/usageFlags");
		digester.addBeanPropertySetter("eveapi/result/generalSettings/deployFlags");
		digester.addBeanPropertySetter("eveapi/result/generalSettings/allowCorporationMembers");
		digester.addBeanPropertySetter("eveapi/result/generalSettings/allowAllianceMembers");
		digester.addBeanPropertySetter("eveapi/result/generalSettings/claimSovereignty");

		digester.addObjectCreate("eveapi/result/combatSettings/onStandingDrop", ApiCombatSetting.class);
		digester.addSetProperties("eveapi/result/combatSettings/onStandingDrop");
		digester.addSetNext("eveapi/result/combatSettings/onStandingDrop", "setOnStandingDrop");

		digester.addObjectCreate("eveapi/result/combatSettings/onStatusDrop", ApiCombatSetting.class);
		digester.addSetProperties("eveapi/result/combatSettings/onStatusDrop");
		digester.addSetNext("eveapi/result/combatSettings/onStatusDrop", "setOnStatusDrop");

		digester.addObjectCreate("eveapi/result/combatSettings/onAggression", ApiCombatSetting.class);
		digester.addSetProperties("eveapi/result/combatSettings/onAggression");
		digester.addSetNext("eveapi/result/combatSettings/onAggression", "setOnAggression");

		digester.addObjectCreate("eveapi/result/combatSettings/onCorporationWar", ApiCombatSetting.class);
		digester.addSetProperties("eveapi/result/combatSettings/onCorporationWar");
		digester.addSetNext("eveapi/result/combatSettings/onCorporationWar", "setOnCorporationWar");

		digester.addObjectCreate("eveapi/result/rowset/row", FuelLevel.class);
		digester.addSetProperties("eveapi/result/rowset/row");
		digester.addSetNext("eveapi/result/rowset/row", "addFuelLevel");
		return digester;
	}

	public static StarbaseDetailParser getInstance() {
		return new StarbaseDetailParser();
	}

	public StarbaseDetailResponse getResponse(ApiAuth auth, int itemID) throws IOException, SAXException {
		return super.getResponse(auth, "itemID", Integer.toString(itemID));
	}
}
