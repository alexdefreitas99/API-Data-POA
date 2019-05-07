package com.viaflow.integration;

import java.util.Arrays;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viaflow.document.Coordenadas;
import com.viaflow.document.Itinerario;

@Service
public class ItinerarioService {
	
	public Itinerario findByid(String id){
		String url= "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + id;
		Gson gson = new Gson();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);		
		JsonObject itinerarioJson = new JsonParser()
			.parse(new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class).getBody())
			.getAsJsonObject();	
		Itinerario itinerario = new Itinerario();
		itinerario =gson.fromJson(itinerarioJson, Itinerario.class);
		Set<String> chaves = itinerarioJson.keySet();
		System.out.println(chaves);
		for (String chave : chaves) {
			try {
				int val = 0;
				val = Integer.parseInt(chave);
				JsonObject jsonElement = itinerarioJson.getAsJsonObject(Integer.toString(val));
				Coordenadas coor = new Coordenadas();
				coor.setLat(jsonElement.get("lat").getAsString());
				coor.setLng(jsonElement.get("lng").getAsString());
				itinerario.getCoordenadas().add(coor);
			} catch (NumberFormatException e) {}			
		}		
		return itinerario;
	}
}
