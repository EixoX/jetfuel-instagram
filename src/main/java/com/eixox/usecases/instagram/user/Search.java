package com.eixox.usecases.instagram.user;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.eixox.instagram.models.InstagramResponse;
import com.eixox.instagram.models.User;
import com.eixox.restrictions.Required;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseResultType;
import com.eixox.usecases.instagram.InstagramUsecase;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Get a list of users matching the query.
 * 
 * @author Rodrigo Portela
 *
 */
public class Search extends InstagramUsecase<Search.Parameters, InstagramResponse<List<User>>> {

	public static class Parameters {

		@Required
		public String access_token;

		public String query;

		public int count;
	}

	@Override
	protected void mainFlow(UsecaseExecution<Parameters, InstagramResponse<List<User>>> execution) throws Exception {
		String endpoint = String.format(
				"https://api.instagram.com/v1/users/search?q=%s&access_token=%s%s",
				execution.params.query,
				execution.params.access_token,
				execution.params.count > 0
						? "&count=" + execution.params.count
						: "");

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
