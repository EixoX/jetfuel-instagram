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
And it goes as:
```java
public class Info extends InstagramUsecase<Info.Parameters, InstagramResponse<User>> {

	/**
	 * The parameters used on exchanging data with instagram;
	 * 
	 * @author Rodrigo Portela
	 *
	 */
	public static class Parameters {

		/**
		 * A valid access token.
		 */
		@Required
		public String access_token;

		/**
		 * A valid existing user id or self is none is supplied;
		 */
		public String id;

	}

	@Override
	protected void mainFlow(UsecaseExecution<Parameters, InstagramResponse<User>> execution) throws Exception {

		String endpoint = String.format(
				"https://api.instagram.com/v1/users/%s/?access_token=%s",
				execution.params.id == null || execution.params.id.isEmpty()
						? "self"
						: execution.params.id,
				execution.params.access_token);

		URL url = new URL(endpoint);
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		try {
			execution.result = MAPPER.readValue(is, new TypeReference<InstagramResponse<User>>() {
			});
		} finally {
			is.close();
		}
		execution.result_type = UsecaseResultType.SUCCESS;
	}
}
```
## Important
Have a look at the jetfuel repository to understand the tech that lies beyond this implementation:
https://github.com/EixoX/jetfuel

