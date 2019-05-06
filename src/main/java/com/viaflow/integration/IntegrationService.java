package com.viaflow.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.viaflow.document.LinhaOnibus;

@Service
public class IntegrationService {

	public List<LinhaOnibus> findAll() {
		String UrlTodasAsLinhas = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		JsonArray arrayFromString = new JsonParser()
				.parse(new RestTemplate().exchange(UrlTodasAsLinhas, HttpMethod.GET, entity, String.class).getBody())
				.getAsJsonArray();

		List<LinhaOnibus> linhas = new ArrayList<>();
		Gson gson = new Gson();
		for (int i = 0; i < arrayFromString.size(); i++) {
			linhas.add(gson.fromJson(arrayFromString.get(i), LinhaOnibus.class));
		}
		return linhas;
	}
}
