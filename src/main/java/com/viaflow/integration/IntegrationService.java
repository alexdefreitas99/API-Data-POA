package com.viaflow.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.viaflow.document.Itinerario;
import com.viaflow.document.LinhaOnibus;

public class IntegrationService {

	public static void getRestTemplate() {

		String UrlTodasAsLinhas = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
		String UrlItinerario = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		JsonArray arrayFromString = new JsonParser()
				.parse(restTemplate.exchange(UrlTodasAsLinhas, HttpMethod.GET, entity, String.class).getBody())
				.getAsJsonArray();

		List<LinhaOnibus> linhas = new ArrayList<>();
		List<Itinerario> itinerarios = new ArrayList<>();
		Gson gson = new Gson();
		
		try {
			for (int i = 0; i < arrayFromString.size(); i++) {
				linhas.add(gson.fromJson(arrayFromString.get(i), LinhaOnibus.class));
				Thread.sleep(500);
					JsonArray objectFromString = new JsonParser()
							.parse(restTemplate.exchange(UrlItinerario + 94, HttpMethod.GET, entity, String.class).getBody())
							.getAsJsonArray();
					itinerarios.add(gson.fromJson(objectFromString.get(i), Itinerario.class));
			}
			
//			JsonObject itinerarioJson = new JsonParser()
//					.parse(restTemplate.exchange(UrlItinerario + 93, HttpMethod.GET, entity, String.class).getBody())
//					.getAsJsonObject();
//			itinerarios.add(gson.fromJson(itinerarioJson, Itinerario.class));
//			System.out.println(itinerarios.get(0));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
