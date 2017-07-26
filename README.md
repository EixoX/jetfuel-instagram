# jetfuel-instagram
The jetfuel way to instagram usecases.
The general idea of this project is to provide api callable wrapper for instagram api so we can attach to our codebase a general programming model that can be updated if instagram changes. Hopefully but not assertable with minimum code changes. 
Also, we can log and monitor instagram api performance to understand and provision our infrastructure.
The general feeling is:
```java
public abstract class InstagramUsecase<TParams, TResult> extends UsecaseImplementation<TParams, TResult> {

	public static final ObjectMapper MAPPER;

	static {
		MAPPER = new ObjectMapper();
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

}
```

