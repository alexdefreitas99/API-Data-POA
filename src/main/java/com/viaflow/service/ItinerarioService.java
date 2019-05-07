package com.viaflow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
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
			
			List<Point> points = new ArrayList<Point>(); 
			
			JsonObject jsonElement = itinerarioJson.getAsJsonObject(Integer.toString(1));
			GeoJsonPoint geoJsonPoint = new GeoJsonPoint(jsonElement.get("lng").getAsDouble(), Double.valueOf(jsonElement.get("lat").getAsDouble()));
			
			System.out.println(geoJsonPoint.getCoordinates());
			//System.out.println(geoJsonPoint);
			
			itinerario.getLocation().getX();
			
			
			
			itinerario.getLocation().getCoordinates().add(jsonElement.get("lng").getAsDouble());
			
			itinerario.getLocation().getCoordinates().addAll(geoJsonPoint.getCoordinates());
		//	itinerario.setLocation(geoJsonPoint);
			
			//for (String chave : chaves) {
			//	try {
					//int val = 0;
					//val = Integer.parseInt(chave);
				//	JsonObject jsonElement = itinerarioJson.getAsJsonObject(Integer.toString(1));
					
				//	GeoJsonPoint lineString = new GeoJsonLineString();
					
					
					//Point point = new Point(jsonElement.get("lng").getAsDouble(), Double.valueOf(jsonElement.get("lat").getAsDouble()));
//					Multi
					
//					GeoJsonMultiPoint jsonMultiPoint = new GeoJsonMultiPoint(point);
					
//					itinerario.setLocation(point);
					
					
					
					
					//itinerario.getLocation().getCoordinates().add(point);
					//System.out.println(itinerario.getLocation().getCoordinates());
					//GeoJsonLineString
					
					
//					jsonElement.get("lat").getAsDouble();
//					Point point = new Point(Double.valueOf(jsonElement.get("lng").getAsDouble()), Double.valueOf(jsonElement.get("lat").getAsDouble()));
//					points.add(point);
//					System.out.println(points);
//					GeoJsonPoint location = new GeoJsonPoint();
//					GeoJsonMultiPoint location2 = new GeoJsonMultiPoint((List<Point>) location);  
//					itinerario.setLocation(location);		
//					itinerario.getLocation().add(location);
//					itinerario.setLocation(location);
//					Coordenadas coor = new Coordenadas();
//					coor.setLat(jsonElement.get("lat").getAsString());
//					coor.setLng(jsonElement.get("lng").getAsString());
//					itinerario.getCoordenadas().add(coor);
			//	} catch (NumberFormatException e) {
				//}
			//}
			//itinerario.setLocation(new GeoJsonMultiPoint(points));
			
			this.itinerarioRepository.save(itinerario);
			return itinerario;
		}
		return itinerario;

	}

//	public Itinerario createOrUpdate(String idlinha) {
//		return this.itinerarioRepository.save(this.findById(idlinha));
//	}
}
