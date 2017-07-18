package com.eixox.usecases.instagram.tags;

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

public class RecentMedia extends InstagramUsecase<RecentMedia.Parameters, InstagramResponse<List<Media>>> {

	public static class Parameters {

		/**
		 * A valid access token.
		 */
		@Required
		public String access_token;

		/**
		 * {tag-name}
		 */
		@Required
		public String tag;

		/**
		 * Return media after this max_tag_id.
		 */
		public String max_tag_id;

		/**
		 * Return media before this min_tag_id.
		 */
		public String min_tag_id;

		/**
		 * Count of tagged media to return.
		 */
		public int count;
	}

	@Override
	protected void mainFlow(UsecaseExecution<RecentMedia.Parameters, InstagramResponse<List<Media>>> execution) throws Exception {

		String endpoint = String.format(
				"https://api.instagram.com/v1/tags/%s/media/recent?access_token=%s",
				execution.params.tag,
				execution.params.access_token);

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
