package com.viaflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPoint;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viaflow.document.Itinerario;
import com.viaflow.repository.ItinerarioRepository;

@Service
public class ItinerarioService {

	@Autowired
	private ItinerarioRepository itinerarioRepository;

	public Itinerario getByIdFromApiAndSave(String idlinha) {
		Itinerario itinerario = new Itinerario();
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
		List<Point> points = new ArrayList<Point>();
		for (String chave : chaves) {
			try {
				int val = 0;
				val = Integer.parseInt(chave);
				JsonObject jsonElement = itinerarioJson.getAsJsonObject(Integer.toString(val));
				Point point = new Point(jsonElement.get("lat").getAsDouble(), jsonElement.get("lng").getAsDouble());
				points.add(point);
			} catch (NumberFormatException e) {
			}
		}
		itinerario.setLocation(new GeoJsonMultiPoint(points));
		this.createOrUpdate(itinerario);
		return itinerario;
	}

	public Itinerario createOrUpdate(Itinerario itinerario) {
		Itinerario itinerarioFind = new Itinerario();
		itinerarioFind = this.itinerarioRepository.findByidlinha(itinerario.getIdlinha());
		if (itinerarioFind != null && itinerario.getLocation().equals(itinerarioFind.getLocation())
				&& itinerario.getIdlinha().equals(itinerarioFind.getIdlinha())
				&& itinerario.getNome().equals(itinerarioFind.getNome())) {
			return itinerarioFind;
		} else if (itinerarioFind != null) {
			itinerario.setId(itinerarioFind.getId());
		}
		this.itinerarioRepository.save(itinerario);
		return itinerario;
	}

	public Itinerario findByIdLinha(String idlinha) {
		Itinerario itinerario = this.itinerarioRepository.findByidlinha(idlinha);
		return itinerario;
	}

	public List<Itinerario> findByLocationNear(Double lat, Double lng, Double distance) {
		return this.itinerarioRepository.findByLocationNear(new Point(lat, lng),
				new Distance(distance, Metrics.KILOMETERS));
	}

	public void delete(String id) {
		this.itinerarioRepository.deleteById(id);
	}
	
	public Itinerario findById(String id) {
		return this.itinerarioRepository.findById(id).get();
	}
}
