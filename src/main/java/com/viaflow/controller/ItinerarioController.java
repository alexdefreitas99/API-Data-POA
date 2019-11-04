package com.viaflow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
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
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Response<Itinerario>> findById(@PathVariable("id") String id) {
		Response<Itinerario> response = new Response<>();
		Itinerario obj = this.itinerarioService.findById(id);
		if (obj == null) {
			response.getErrors().add("Register not found " + obj);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(obj);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Itinerario>> create(HttpServletRequest req, @RequestBody Itinerario itinerario,
			BindingResult result) {
		Response<Itinerario> response = new Response<>();
		Itinerario persistedObject = itinerarioService.createOrUpdate(itinerario);
		response.setData(persistedObject);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{lng}/{lat}/{distance}")
	public ResponseEntity<Response<List<Itinerario>>> findByLocationNear(@PathVariable("lng") Double lng,
			@PathVariable("lat") Double lat, @PathVariable("distance") Double distance) {
		Response<List<Itinerario>> response = new Response<>();
		List<Itinerario> obj = itinerarioService.findByLocationNear(lat, lng, distance);
		response.setData(obj);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Itinerario> delete(@PathVariable("id") String id) {
		itinerarioService.delete(id);
		return null;
	}
}
