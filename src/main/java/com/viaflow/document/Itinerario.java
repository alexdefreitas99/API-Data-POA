package com.viaflow.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viaflow.deserializer.GeoJsonDeserializer;

import lombok.Data;

@Data
@Document
public class Itinerario {
	
	@Id
	private String id;
	
	private String idlinha;
	private String nome;
	private String codigo;
	
	//@JsonDeserialize(using = GeoJsonDeserializer.class)
	
	private double lat;
	private double lng;
	
	@GeoSpatialIndexed
	private GeoJsonPoint location;
//	private List<GeoJsonPoint> location = new ArrayList<GeoJsonPoint>();
	
	//private List<Coordenadas> coordenadas = new ArrayList<Coordenadas>();
}
