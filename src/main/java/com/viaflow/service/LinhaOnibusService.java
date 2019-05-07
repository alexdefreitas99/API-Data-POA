package com.viaflow.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.viaflow.document.LinhaOnibus;
import com.viaflow.repository.LinhaOnibusRepository;

@Service
public class LinhaOnibusService {

	@Autowired
	LinhaOnibusRepository linhaOnibusRepository;

	public Page<LinhaOnibus> findAndSave(int page, int count) {
		String UrlTodasAsLinhas = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		JsonArray arrayFromString = new JsonParser()
				.parse(new RestTemplate().exchange(UrlTodasAsLinhas, HttpMethod.GET, entity, String.class).getBody())
				.getAsJsonArray();
		Gson gson = new Gson();
		for (int i = 0; i < arrayFromString.size(); i++) {

			if (linhaOnibusRepository
					.findByidlinha(gson.fromJson(arrayFromString.get(i), LinhaOnibus.class).getId()) != null) {

			} else {
				LinhaOnibus linhaOnibus = new LinhaOnibus();
				linhaOnibus.setIdlinha(gson.fromJson(arrayFromString.get(i), LinhaOnibus.class).getId());
				linhaOnibus.setCodigo(gson.fromJson(arrayFromString.get(i), LinhaOnibus.class).getCodigo());
				linhaOnibus.setNome(gson.fromJson(arrayFromString.get(i), LinhaOnibus.class).getNome());
				linhaOnibusRepository.save(linhaOnibus);
			}
		}
		Pageable pageable = PageRequest.of(page, count);
		return this.linhaOnibusRepository.findAll(pageable);
	}
	
	public LinhaOnibus findByIdLinha(String idlinha) {		
		return this.linhaOnibusRepository.findByidlinha(idlinha);
	}
}
