package com.beimin.eveapi.character.mail.lists;

import java.util.HashSet;
import java.util.Set;

import com.beimin.eveapi.core.ApiResponse;

public class MailingListsResponse extends ApiResponse {
	private static final long serialVersionUID = 1L;
	private final Set<ApiMailingList> mailingLists = new HashSet<ApiMailingList>();

	public void addMailingList(ApiMailingList mailingList) {
		mailingLists.add(mailingList);
	}

	public Set<ApiMailingList> getMailingLists() {
		return mailingLists;
	}
}