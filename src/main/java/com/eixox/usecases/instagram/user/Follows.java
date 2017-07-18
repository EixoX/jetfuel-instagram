package com.eixox.usecases.instagram.user;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.eixox.instagram.models.InstagramResponse;
import com.eixox.instagram.models.User;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseResultType;
import com.eixox.usecases.instagram.InstagramUsecase;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Get the list of users this user follows. REQUIREMENTS Scope: follower_list
 * 
 * @author Rodrigo Portela
 *
 */
public class Follows extends InstagramUsecase<Follows.Parameters, InstagramResponse<List<User>>> {

	public static class Parameters {
		/**
		 * A valid access token;
		 */
		public String access_token;

		/**
		 * A user id;
		 */
		public String user_id = "self";

	}

	@Override
	protected void mainFlow(UsecaseExecution<Parameters, InstagramResponse<List<User>>> execution) throws Exception {

		String endpoint = String.format(
				"https://api.instagram.com/v1/users/%s/follows?access_token=%s",
				execution.params.user_id,
				execution.params.access_token);

		URL url = new URL(endpoint);
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		try {
			execution.result = MAPPER.readValue(is, new TypeReference<InstagramResponse<List<User>>>() {
			});
		} finally {
			is.close();
		}
		execution.result_type = UsecaseResultType.SUCCESS;
	}
}
