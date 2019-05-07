package com.viaflow.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.viaflow.document.Itinerario;

@Repository
public interface ItinerarioRepository extends MongoRepository<Itinerario, String>{

	Itinerario findByidlinha(String idlinha);
	
}
