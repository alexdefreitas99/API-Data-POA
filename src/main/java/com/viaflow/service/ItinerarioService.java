package com.viaflow.service;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viaflow.document.Coordenadas;
import com.viaflow.document.Itinerario;
import com.viaflow.repository.ItinerarioRepository;

@Service
public class ItinerarioService {

	@Autowired
	private ItinerarioRepository itinerarioRepository;

	public Itinerario findByIdAndCreate(String idlinha) {
		Itinerario itinerario = new Itinerario();
		itinerario = this.itinerarioRepository.findByidlinha(idlinha);
		if (itinerario == null) {
			String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + idlinha;
			Gson gson = new Gson();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			JsonObject itinerarioJson = new JsonParser()
					.parse(new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class).getBody())
					.getAsJsonObject();
			itinerario = gson.fromJson(itinerarioJson, Itinerario.class);
			Set<String> chaves = itinerarioJson.keySet();
			
			for (String chave : chaves) {
				try {
					int val = 0;
					val = Integer.parseInt(chave);
					JsonObject jsonElement = itinerarioJson.getAsJsonObject(Integer.toString(val));

					Coordenadas coor = new Coordenadas();
					coor.setLat(jsonElement.get("lat").getAsDouble());
					coor.setLng(jsonElement.get("lng").getAsDouble());
					itinerario.getCoordenadas().add(coor);
				} catch (NumberFormatException e) {
				}
			}			
			this.itinerarioRepository.save(itinerario);
			return itinerario;
		}
		return itinerario;

	}

//	public Itinerario createOrUpdate(String idlinha) {
//		return this.itinerarioRepository.save(this.findById(idlinha));
//	}
}
