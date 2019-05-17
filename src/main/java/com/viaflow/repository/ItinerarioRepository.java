package com.viaflow.repository;


import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.viaflow.document.Itinerario;

@Repository
public interface ItinerarioRepository extends MongoRepository<Itinerario, String>{

	Itinerario findByidlinha(String idlinha);
	
	List<Itinerario> findByLocationNear(Point p, Distance distance);
	
	//List<Itinerario> findByLocationNear(Point p, Distance distance);
	
}