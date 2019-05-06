package com.viaflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viaflow.document.Itinerario;
import com.viaflow.integration.ItinerarioService;
import com.viaflow.response.Response;

@RestController
@RequestMapping("/api/itinerario")
public class ItinerarioController {

	@Autowired
	private ItinerarioService itinerarioService;

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Itinerario>> findItinerario(@PathVariable("id") String id) {
		Response<Itinerario> response = new Response<>();
		Itinerario obj = itinerarioService.findByid(id);
		if (obj == null) {
			response.getErrors().add("Register not found " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(obj);
		return ResponseEntity.ok(response);
	}

}
