package com.talkdesk.mule.internal;

import com.google.gson.Gson;
import com.talkdesk.mule.internal.contact.Contact;

import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;

import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.request.HttpRequestBuilder;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeoutException;


/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public final class TalkdeskConnection {

	private TalkdeskConfiguration configuration;
	private int connectionTimeout;
	private HttpClient httpClient;
	private HttpRequestBuilder httpRequestBuilder;
	private ClientCredentialsState tokenInfo;

	/**
	 * 
	 * @param httpService
	 * @param gConfig
	 * @param cTimeout
	 */
	public TalkdeskConnection(HttpService httpService, TalkdeskConfiguration gConfig, int cTimeout) {
		configuration = gConfig;
		connectionTimeout = cTimeout;
		initHttpClient(httpService);
	}

	public void initHttpClient(HttpService httpService) {
		HttpClientConfiguration.Builder builder = new HttpClientConfiguration.Builder();
		builder.setName("TalkdeskAPI");
		httpClient = httpService.getClientFactory().create(builder.build());

		httpRequestBuilder = HttpRequest.builder();

		httpClient.start();
	}

	public void invalidate() {
		httpClient.stop();
	}

	private void SetAuthentication() throws IOException {
		if(tokenInfo == null || !tokenInfo.isValid()) {
			String tokenUrl = "https://" + configuration.getAccountName() + ".talkdeskqaid.com/oauth/token";
			tokenInfo = getClientCredentials(tokenUrl, configuration.getClientId(), configuration.getClientSecret());
		}
	}
	
	
	public InputStream getContacts() {
		try {
			return get("https://api.talkdeskdev.com/callbar/contacts/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Contact getContact(String contactId) {
		try {
			InputStream response = get("https://api.talkdeskdev.com/callbar/contacts/"+contactId);
			
			Reader reader = new InputStreamReader(response, "UTF-8");
			Contact result  = new Gson().fromJson(reader, Contact.class);
			  
			
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private InputStream get(String uri) throws IOException, TimeoutException {
		SetAuthentication();
		HttpRequest request = httpRequestBuilder.method(HttpConstants.Method.GET).uri(uri).addHeader("Authorization", tokenInfo.getToken_type()+" "+tokenInfo.getAccess_token()).build();
		System.out.println("Request is "+request);
		
		
		
		HttpResponse response = httpClient.send(request, connectionTimeout, false, null);
		System.out.println(response);
        return response.getEntity().getContent();
	}

	//private static final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
	//private static final String clientId = "59e27b4fa19a482a86046893c625de66";// clientId
	//private static final String clientSecret = "xspnZFKmbwS2qXS9BXfC9sg6WQ1mpQq8QSEq2UPx3sCsTTPTAioPjfJSHzYnFfCwz3TB6aRQ696PdIuU1WDVvQ";// client secret
	//private static final String tokenUrl = "https://integrations2.talkdeskqaid.com/oauth/token";
	//private static final String auth = clientId + ":" + clientSecret;
	//private static final String authentication = Base64.getEncoder().encodeToString(auth.getBytes());


	public static ClientCredentialsState getClientCredentials(String tokenUrl, String clientId, String clientSecret) throws IOException {
		String auth = clientId + ":" + clientSecret;
		String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
		String content = "grant_type=client_credentials";
		BufferedReader reader = null;
		HttpsURLConnection connection = null;
		try {
			URL url = new URL(tokenUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authentication);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept", "application/json");
			PrintStream os = new PrintStream(connection.getOutputStream());
			os.print(content);
			os.close();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(
					connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}

			String response = out.toString();
			System.out.println("Response : " + response);

			Gson g = new Gson();
			ClientCredentialsState tokenInfo = g.fromJson(response, ClientCredentialsState.class);

			System.out.println("Token : " + tokenInfo.getAccess_token());
			return tokenInfo;

		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
			connection.disconnect();
		}
	}

	 
	
}
