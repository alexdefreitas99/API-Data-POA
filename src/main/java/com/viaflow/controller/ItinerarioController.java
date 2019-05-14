package com.viaflow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Response<Itinerario>> findAndCreate(@PathVariable("idlinha") String idlinha) {
		Response<Itinerario> response = new Response<>();
		Itinerario obj = itinerarioService.getByIdFromApiAndSave(idlinha);
		if (obj == null) {
			response.getErrors().add("Register not found " + idlinha);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(obj);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Itinerario>> create(HttpServletRequest req, @RequestBody Itinerario object,
			BindingResult result) {
		Response<Itinerario> response = new Response<>();
		Itinerario persistedObject = itinerarioService.createOrUpdate(object);
		response.setData(persistedObject);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{lng}/{lat}/{distance}")
	public ResponseEntity<Response<GeoResults<Itinerario>>> findByLocationNear(@PathVariable("lng") Double lng,
			@PathVariable("lat") Double lat, @PathVariable("distance") Double distance) {
		Response<GeoResults<Itinerario>> response = new Response<>();
		GeoResults<Itinerario> obj = itinerarioService.findByLocationNear(lat, lng, distance);
		response.setData(obj);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Itinerario> delete(@PathVariable("id") String id) {
		itinerarioService.delete(id);
		return null;
	}
}
