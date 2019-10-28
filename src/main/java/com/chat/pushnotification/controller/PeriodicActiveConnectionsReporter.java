package com.chat.pushnotification.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chat.connectionmanager.model.ReportActiveConnectionsRequest;
import com.chat.connectionmanager.model.ReportActiveConnectionsResponse;
import com.chat.connectionmanager.model.ServerDetails;
import com.chat.pushnotification.state.ActiveConnections;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PeriodicActiveConnectionsReporter {
	private static final String CONNECTION_MANAGER_ENDPOINT = "http://localhost:9000";

	private static final String REPORT_ACTIVE_CONNECTIONS = "/reportActiveConnections";

	@Inject // httpClient = HttpClients.createDefault();
	private HttpClient httpClient;

	@Inject
	private ObjectMapper objectMapper;

	@Inject
	ActiveConnections activeConnections;

	@Scheduled(fixedRate = 1000)
	public void reportActiveConnections() throws ClientProtocolException, IOException {
		ReportActiveConnectionsRequest reportActiveConnectionsRequest = new ReportActiveConnectionsRequest(
				activeConnections.getAllConnections(), new ServerDetails("serverId1", "http://localhost:9001"));
		HttpPost post = new HttpPost(CONNECTION_MANAGER_ENDPOINT + REPORT_ACTIVE_CONNECTIONS);

		post.setEntity(
				new StringEntity(objectMapper.writeValueAsString(reportActiveConnectionsRequest), ContentType.APPLICATION_JSON));

		HttpResponse response = httpClient.execute(post);
		ReportActiveConnectionsResponse reportActiveConnectionsResponse = objectMapper.readValue(EntityUtils.toString(response.getEntity()),
				ReportActiveConnectionsResponse.class);
		System.out.println("reportActiveConnectionsResponse = "+ reportActiveConnectionsResponse);
	}
}
