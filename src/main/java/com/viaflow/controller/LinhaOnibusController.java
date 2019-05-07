package com.viaflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viaflow.document.LinhaOnibus;
import com.viaflow.response.Response;
import com.viaflow.service.LinhaOnibusService;

@RestController
@RequestMapping("/api/linha")
public class LinhaOnibusController {

	@Autowired
	private LinhaOnibusService linhaService;

	@GetMapping()
	public ResponseEntity<Response<List<LinhaOnibus>>> findAll() {
		Response<List<LinhaOnibus>> response = new Response<>();
		List<LinhaOnibus> list = linhaService.findAll();
		response.setData(list);
		return ResponseEntity.ok(response);
	}
	
//	@PutMapping()
//	public ResponseEntity<Response<List<LinhaOnibus>>> createOrUpdate(){
//		
//	}
}
