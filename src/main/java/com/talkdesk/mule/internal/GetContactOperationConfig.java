package com.talkdesk.mule.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

@Operations({ TalkdeskOperations.class })
public class GetContactOperationConfig {

	@Parameter
	@DisplayName("Contact Id")
	private String contactId;

	public String getContactId() {
		return contactId;
	}

}
