package com.talkdesk.mule.internal;



public class ClientCredentialsState {
	
	private String access_token;
	private String token_type;
	private int expires_in;
	private String scope;
	private long requestedDate;

	public ClientCredentialsState()
	{
		this.requestedDate = System.currentTimeMillis();
	}

	public boolean isValid() {
		if(access_token.isEmpty() || System.currentTimeMillis() > (requestedDate + expires_in*1000))
			return false;
		
		return true;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public long getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(long requestedDate) {
		this.requestedDate = requestedDate;
	}
}
