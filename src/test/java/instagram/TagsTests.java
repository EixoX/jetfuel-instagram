package instagram;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.eixox.instagram.models.InstagramResponse;
import com.eixox.instagram.models.Media;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.instagram.tags.RecentMedia;
import com.eixox.usecases.instagram.tags.RecentMedia.Parameters;

public class TagsTests {

	@Test
	public void readRecentMedia() {

		UsecaseExecution<Parameters, InstagramResponse<List<Media>>> execution = UsecaseExecution.create(RecentMedia.class);
		execution.params = new Parameters();
		execution.params.tag = TestSettings.TAG;
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.run();
		TestSettings.println(execution.result);
		Assert.assertTrue(execution.result != null);
		Assert.assertTrue(execution.result.data.get(0).images != null);
	}
}
