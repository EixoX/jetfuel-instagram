package com.eixox.usecases.instagram.user;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.eixox.instagram.models.InstagramResponse;
import com.eixox.instagram.models.Media;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseResultType;
import com.eixox.usecases.instagram.InstagramUsecase;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Get the list of recent media liked by the owner of the access_token.
 * 
 * @author Rodrigo Portela
 *
 */
public class LikedMedia extends InstagramUsecase<LikedMedia.Parameters, InstagramResponse<List<Media>>> {

	public static class Parameters {

		/**
		 * A valid access token.
		 */
		public String access_token;

		/**
		 * Return media liked before this id.
		 */
		public String max_like_id;

		/**
		 * Count of media to return.
		 */
		public int count;
	}

	@Override
	protected void mainFlow(UsecaseExecution<Parameters, InstagramResponse<List<Media>>> execution) throws Exception {

		String endpoint = String.format(
				"https://api.instagram.com/v1/users/self/media/liked?access_token=%s%s%s",
				execution.params.access_token,
				execution.params.max_like_id != null && !execution.params.max_like_id.isEmpty()
						? "&max_like_id=" + execution.params.max_like_id
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
