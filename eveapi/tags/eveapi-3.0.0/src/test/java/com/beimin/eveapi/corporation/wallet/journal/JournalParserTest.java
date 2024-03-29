package com.beimin.eveapi.corporation.wallet.journal;

import static com.beimin.eveapi.utils.Assert.assertDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;


import org.junit.Test;


import com.beimin.eveapi.core.ApiException;
import com.beimin.eveapi.core.ApiPage;
import com.beimin.eveapi.core.ApiPath;
import com.beimin.eveapi.shared.wallet.RefType;
import com.beimin.eveapi.shared.wallet.journal.AbstractWalletJournalParser;
import com.beimin.eveapi.shared.wallet.journal.ApiJournalEntry;
import com.beimin.eveapi.shared.wallet.journal.WalletJournalResponse;
import com.beimin.eveapi.utils.FullAuthParserTest;
import java.util.Map;

public class JournalParserTest extends FullAuthParserTest {
	public JournalParserTest() {
		super(ApiPath.CORPORATION, ApiPage.WALLET_JOURNAL);
	}

	@Test
	public void getResponse() throws ApiException {
		AbstractWalletJournalParser parser = WalletJournalParser.getInstance();
		WalletJournalResponse response = parser.getResponse(auth, 1000);
		assertNotNull(response);
		Collection<ApiJournalEntry> entries = response.getJournalEntries();
		assertEquals(5, entries.size());
		boolean found = false;
		for (ApiJournalEntry journalEntry : entries) {
			if (journalEntry.getRefID() == 2566012762L) {
				found = true;
				assertDate(2010, 4, 9, 15, 53, 0, journalEntry.getDate());
				assertEquals(RefType.MARKET_ESCROW, journalEntry.getRefType());
				assertEquals("corpslave", journalEntry.getOwnerName1());
				assertEquals(150337897, journalEntry.getOwnerID1());
				assertEquals("Secure Commerce Commission", journalEntry.getOwnerName2());
				assertEquals(1000132, journalEntry.getOwnerID2());
				assertEquals("", journalEntry.getArgName1());
				assertEquals(0, journalEntry.getArgID1());
				assertEquals(-589996.29, journalEntry.getAmount(), 0.00001);
				assertEquals(337405445.52, journalEntry.getBalance(), 0.00001);
				assertEquals("", journalEntry.getReason());
				assertEquals(0L, journalEntry.getTaxReceiverID());
				assertEquals(0.0, journalEntry.getTaxAmount(), 0.00001);
			}
		}
		assertTrue("test journal entry wasn't found.", found);
	}

	@Override
	public void extraAsserts(Map<String, String> req) {
		super.extraAsserts(req);
		assertEquals("1000", req.get("accountKey"));
	}
}