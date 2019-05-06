package com.viaflow.response;

import java.util.List;

import lombok.Data;

@Data
public class Response<T> {
	private T data;
	private List<String> errors;
}
