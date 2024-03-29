package com.beimin.eveapi.eve.factwar.stats;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

public class EveFacWarStatsParserTest {

	@Test
	public void facWarStats() throws IOException, SAXException {
		EveFacWarStatsParser parser = EveFacWarStatsParser.getInstance();
		InputStream input = EveFacWarStatsParserTest.class.getResourceAsStream("/eve/FacWarStats.xml");
		EveFacWarStats facWarStats = parser.getResponse(input);
		assertEquals(677, facWarStats.getKillsYesterday());
		assertEquals(3246, facWarStats.getKillsLastWeek());
		assertEquals(232772, facWarStats.getKillsTotal());
		assertEquals(55087, facWarStats.getVictoryPointsYesterday());
		assertEquals(414049, facWarStats.getVictoryPointsLastWeek());
		assertEquals(44045189, facWarStats.getVictoryPointsTotal());
		List<ApiFactionStats> factions = facWarStats.getFactions();
		assertEquals(4, factions.size());
		ApiFactionStats factionStats = factions.iterator().next();
		assertEquals(500001, factionStats.getFactionID());
		assertEquals("Caldari State", factionStats.getFactionName());
		assertEquals(5324, factionStats.getPilots());
		assertEquals(61, factionStats.getSystemsControlled());
		assertEquals(115, factionStats.getKillsYesterday());
		assertEquals(627, factionStats.getKillsLastWeek());
		assertEquals(59239, factionStats.getKillsTotal());
		assertEquals(9934, factionStats.getVictoryPointsYesterday());
		assertEquals(64548, factionStats.getVictoryPointsLastWeek());
		assertEquals(4506493, factionStats.getVictoryPointsTotal());
		List<ApiFactionWar> wars = facWarStats.getWars();
		assertEquals(8, wars.size());
		ApiFactionWar factionWar = wars.iterator().next();
		assertEquals(500001, factionWar.getFactionID());
		assertEquals("Caldari State", factionWar.getFactionName());
		assertEquals(500002, factionWar.getAgainstID());
		assertEquals("Minmatar Republic", factionWar.getAgainstName());
	}
}