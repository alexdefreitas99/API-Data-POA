package com.viaflow.document;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Itinerario {
	private String idlinha;
	private String nome;
	private String codigo;
//	private Map<lat,lng> int;
	private List<Coordenadas> coordenadas = new ArrayList<Coordenadas>();
}
