package com.eixox.usecases.instagram.user;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.eixox.instagram.models.InstagramResponse;
import com.eixox.instagram.models.User;
import com.eixox.restrictions.Required;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseResultType;
import com.eixox.usecases.instagram.InstagramUsecase;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Get information about a user. The public_content scope is required if the
 * user is not the owner of the access_token.
 * 
 * @author Rodrigo Portela
 *
 */
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
