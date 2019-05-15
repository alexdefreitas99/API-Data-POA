package com.viaflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viaflow.document.LinhaOnibus;
import com.viaflow.response.Response;
import com.viaflow.service.LinhaOnibusService;

@RestController
@RequestMapping("/api/linha")
@CrossOrigin(origins = "*")
public class LinhaOnibusController {

	@Autowired
	private LinhaOnibusService linhaService;

	@GetMapping(value = "{page}/{count}")
	public ResponseEntity<Response<Page<LinhaOnibus>>> findAll(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Response<Page<LinhaOnibus>> response = new Response<>();
		Page<LinhaOnibus> list = linhaService.findAndSave(page, count);
		response.setData(list);
		return ResponseEntity.ok(response);
	}

	
	@GetMapping(value="{idlinha}")
	public ResponseEntity<Response<LinhaOnibus>> findByIdLinha(@PathVariable("idlinha") String idlinha){
		Response<LinhaOnibus> response = new Response<>();
		response.setData(linhaService.findByIdLinha(idlinha));
		return ResponseEntity.ok(response);
	}
}
