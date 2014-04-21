package com.beimin.eveapi.corporation.standings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.model.shared.Standing;
import com.beimin.eveapi.model.shared.StandingsList;
import com.beimin.eveapi.parser.ApiPage;
import com.beimin.eveapi.parser.ApiPath;
import com.beimin.eveapi.parser.corporation.StandingsParser;
import com.beimin.eveapi.response.shared.StandingsResponse;
import com.beimin.eveapi.utils.FullAuthParserTest;

public class StandingsParserTest extends FullAuthParserTest {
	public StandingsParserTest() {
		super(ApiPath.CORPORATION, ApiPage.STANDINGS);
	}

	@Test
	public void getResponse() throws ApiException {
		StandingsParser parser = new StandingsParser();
		StandingsResponse response = parser.getResponse(auth);
		assertNotNull(response);

		StandingsList agentStandings = response.getAgentStandings();
		assertEquals("agents", agentStandings.getName());
		assertEquals(1871, agentStandings.size());
		Standing apiStanding = agentStandings.iterator().next();
		assertEquals(3008420, apiStanding.getFromID());
		assertEquals("Appi Intaa", apiStanding.getFromName());
		assertEquals(-0.33, apiStanding.getStanding(), 1E-15);

		StandingsList npcCorporations = response.getNpcCorporationStandings();
		assertEquals("NPCCorporations", npcCorporations.getName());
		assertEquals(154, npcCorporations.size());
		apiStanding = npcCorporations.iterator().next();
		assertEquals(1000002, apiStanding.getFromID());
		assertEquals("CBD Corporation", apiStanding.getFromName());
		assertEquals(0.52, apiStanding.getStanding(), 1E-15);

		StandingsList factionStandings = response.getFactionStandings();
		assertEquals("factions", factionStandings.getName());
		assertEquals(20, factionStandings.size());
		apiStanding = factionStandings.iterator().next();
		assertEquals(500001, apiStanding.getFromID());
		assertEquals("Caldari State", apiStanding.getFromName());
		assertEquals(1.47, apiStanding.getStanding(), 1E-15);
	}
}