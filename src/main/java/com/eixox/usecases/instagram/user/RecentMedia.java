package com.eixox.usecases.instagram.user;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.eixox.instagram.models.InstagramResponse;
import com.eixox.instagram.models.Media;
import com.eixox.restrictions.Required;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseResultType;
import com.eixox.usecases.instagram.InstagramUsecase;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Get the most recent media published by a user. The public_content scope is
 * required if the user is not the owner of the access_token.
 * 
 * @author Rodrigo Portela
 *
 */
public class RecentMedia extends InstagramUsecase<RecentMedia.Parameters, InstagramResponse<List<Media>>> {

	/**
	 * The parameters sent to instagram;
	 * 
	 * @author Rodrigo Portela
	 *
	 */
	public static class Parameters {

		/**
		 * ACCESS_TOKEN A valid access token.
		 */
		@Required
		public String access_token;

		/**
		 * The user id to retrieve data from;
		 */
		public String user_id;

		/**
		 * MAX_ID Return media earlier than this.
		 */
		public String max_id;

		/**
		 * MAX_ID Return media earlier than this
		 */
		public String min_id;

		/**
		 * COUNT Count of media to return.
		 */
		public int count;
	}

	@Override
	protected void mainFlow(UsecaseExecution<RecentMedia.Parameters, InstagramResponse<List<Media>>> execution) throws Exception {
		String endpoint = String.format(
				"https://api.instagram.com/v1/users/%s/media/recent/?access_token=%s%s%s%s",
				execution.params.user_id == null || execution.params.user_id.isEmpty()
						? "self"
						: execution.params.user_id,
				execution.params.access_token,
				execution.params.max_id != null && !execution.params.max_id.isEmpty()
						? "&max_id=" + execution.params.max_id
						: "",
				execution.params.min_id != null && !execution.params.min_id.isEmpty()
						? "&min_id=" + execution.params.min_id
						: "",
				execution.params.count > 0
						? "&count=" + execution.params.count
						: "");

		URL url = new URL(endpoint);
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		try {
			execution.result = MAPPER.readValue(is, new TypeReference<InstagramResponse<List<Media>>>() {
			});
		} finally {
			is.close();
		}
		execution.result_type = UsecaseResultType.SUCCESS;

	}
}
