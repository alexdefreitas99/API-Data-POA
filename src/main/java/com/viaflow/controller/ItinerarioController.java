package com.viaflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viaflow.document.Itinerario;
import com.viaflow.response.Response;
import com.viaflow.service.ItinerarioService;

@RestController
@RequestMapping("/api/itinerario")
public class ItinerarioController {

	@Autowired
	private ItinerarioService itinerarioService;

	@GetMapping(value = "{idlinha}")
	public ResponseEntity<Response<Itinerario>> findById(@PathVariable("idlinha") String idlinha) {
		Response<Itinerario> response = new Response<>();
		Itinerario obj = itinerarioService.findByIdAndCreate(idlinha);
		if (obj == null) {
			response.getErrors().add("Register not found " + idlinha);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(obj);
		return ResponseEntity.ok(response);
	}
	
//	@PostMapping(value = "{idlinha}")
//	public ResponseEntity<Response<Itinerario>> createOrUpdate(@PathVariable("idlinha") String idlinha){
//		Response<Itinerario> response = new Response<>();
//		response.setData(itinerarioService.createOrUpdate(idlinha));
//		return ResponseEntity.ok(response);
//	}
}
