package com.talkdesk.mule;

import static org.junit.Assert.*;

import com.talkdesk.mule.internal.ClientCredentialsState;
import com.talkdesk.mule.internal.TalkdeskConfiguration;
import org.junit.Test;

import com.talkdesk.mule.internal.TalkdeskConnection;

import java.io.IOException;

public class TalkdeskConnectionTestCase {

	@Test
	public void RequestToken() {
		try {
			String clientId = "59e27b4fa19a482a86046893c625de66";// clientId
			String clientSecret = "xspnZFKmbwS2qXS9BXfC9sg6WQ1mpQq8QSEq2UPx3sCsTTPTAioPjfJSHzYnFfCwz3TB6aRQ696PdIuU1WDVvQ";// client secret
			String tokenUrl = "https://integrations2.talkdeskqaid.com/oauth/token";

			ClientCredentialsState tokenInfo = TalkdeskConnection.getClientCredentials(tokenUrl, clientId, clientSecret);
			assertNotNull(tokenInfo);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGet() {
		TalkdeskConfiguration configuration = new TalkdeskConfiguration();
		configuration.setAccountName("integrations2");
		configuration.setClientId("59e27b4fa19a482a86046893c625de66");
		configuration.setClientSecret("xspnZFKmbwS2qXS9BXfC9sg6WQ1mpQq8QSEq2UPx3sCsTTPTAioPjfJSHzYnFfCwz3TB6aRQ696PdIuU1WDVvQ");

		//new TalkdeskConnection(new HttpService(),configuration, 5000 );
	}
}
