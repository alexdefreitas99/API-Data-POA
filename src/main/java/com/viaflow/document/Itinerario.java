package com.viaflow.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Itinerario {
	
	@Id
	private String id;
	
	private String idlinha;
	private String nome;
	private String codigo;
	
	private List<Coordenadas> coordenadas = new ArrayList<Coordenadas>();
}
