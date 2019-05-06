package com.viaflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viaflow.document.LinhaOnibus;
import com.viaflow.integration.IntegrationService;
import com.viaflow.response.Response;

@RestController
public class IntegrationController {

	@Autowired
	private IntegrationService integrationService;

	@GetMapping()
	public ResponseEntity<Response<List<LinhaOnibus>>> findAll() {
		Response<List<LinhaOnibus>> response = new Response<>();
		List<LinhaOnibus> list = integrationService.findAll();
		response.setData(list);
		return ResponseEntity.ok(response);
	}
}
