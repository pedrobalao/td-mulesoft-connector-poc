package com.talkdesk.mule.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

/**
 * This class represents an extension configuration, values set in this class
 * are commonly used across multiple operations since they represent something
 * core from the extension.
 */
@Operations(TalkdeskOperations.class)
@ConnectionProviders(TalkdeskConnectionProvider.class)
public class TalkdeskConfiguration {
	private static final String GENL = "General";

	@Parameter
	private String configId;

	public String getConfigId() {
		return configId;
	}



	@Parameter
	@Placement(tab = GENL)
	@DisplayName("Account Name")
	private String accountName;

	public String getAccountName() {
		return accountName;
	}

	@Parameter
	@Placement(tab = GENL)
	@DisplayName("oAuth Client Id")
	private String clientId;

	public String getClientId() {
		return clientId;
	}

	@Parameter
	@Placement(tab = GENL)
	@DisplayName("oAuth Client Secret")
	private String clientSecret;

	public String getClientSecret() {
		return clientSecret;
	}


	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}
