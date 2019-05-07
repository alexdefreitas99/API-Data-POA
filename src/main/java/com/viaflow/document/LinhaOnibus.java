package com.viaflow.document;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document          
public class LinhaOnibus {
	private String id;
	private String codigo;
	private String nome;
}
