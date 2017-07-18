package com.eixox.usecases.instagram;

import com.eixox.usecases.UsecaseImplementation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class InstagramUsecase<TParams, TResult> extends UsecaseImplementation<TParams, TResult> {

	public static final ObjectMapper MAPPER;

	static {
		MAPPER = new ObjectMapper();
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

}
