package com.viaflow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.viaflow.document.LinhaOnibus;

@Repository
public interface LinhaOnibusRepository extends MongoRepository<LinhaOnibus, String>{
	
	LinhaOnibus findByidlinha(String idlinha);

}
