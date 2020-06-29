package com.talkdesk.mule;

import static org.junit.Assert.*;

import com.talkdesk.mule.internal.ClientCredentialsState;
import com.talkdesk.mule.internal.TalkdeskConfiguration;
import org.junit.Test;

import com.talkdesk.mule.internal.TalkdeskConnection;

import java.io.IOException;

public class TalkdeskConnectionTestCase {
	
	private String clientId = "CLIENT_ID";
	private String clientSecret = "CLIENT_ID";
	private String tokenUrl = "https://ACCOUNT_NAME.talkdeskqaid.com/oauth/token";;

	@Test
	public void RequestToken() {
		try {
			
			ClientCredentialsState tokenInfo = TalkdeskConnection.getClientCredentials(tokenUrl, clientId, clientSecret);
			assertNotNull(tokenInfo);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	
}
