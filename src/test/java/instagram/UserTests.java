package instagram;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.eixox.instagram.models.InstagramResponse;
import com.eixox.instagram.models.Media;
import com.eixox.instagram.models.User;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.instagram.user.Follows;
import com.eixox.usecases.instagram.user.Info;
import com.eixox.usecases.instagram.user.Info.Parameters;
import com.eixox.usecases.instagram.user.RecentMedia;
import com.eixox.usecases.instagram.user.Search;

public class UserTests {

	@Test
	public void readSelf() {

		UsecaseExecution<Parameters, InstagramResponse<User>> execution = UsecaseExecution.create(Info.class);
		execution.params = new Parameters();
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.run();
		TestSettings.println(execution.result);
		Assert.assertTrue(execution.result.data != null);
		Assert.assertTrue(execution.result.data.id != null);
	}

	@Test
	public void readInfo() {

		UsecaseExecution<Parameters, InstagramResponse<User>> execution = UsecaseExecution.create(Info.class);
		execution.params = new Parameters();
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.params.id = TestSettings.USER_ID;
		execution.run();
		TestSettings.println(execution.result);
		Assert.assertTrue(execution.result.data != null);
		Assert.assertTrue(execution.result.data.id != null);

	}

	@Test
	public void readSelfRecentMedia() {

		UsecaseExecution<RecentMedia.Parameters, InstagramResponse<List<Media>>> execution = UsecaseExecution
				.create(RecentMedia.class);
		execution.params = new RecentMedia.Parameters();
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.params.user_id = "self";
		execution.run();
		TestSettings.println(execution.result);
		Assert.assertTrue(execution.result.data != null);
		Assert.assertTrue(execution.result.data.size() > 0);

	}

	@Test
	public void readUserRecentMedia() {

		UsecaseExecution<RecentMedia.Parameters, InstagramResponse<List<Media>>> execution = UsecaseExecution
				.create(RecentMedia.class);
		execution.params = new RecentMedia.Parameters();
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.params.user_id = TestSettings.USER_ID;
		execution.run();
		TestSettings.println(execution.result);
		Assert.assertTrue(execution.result.data != null);
		Assert.assertTrue(execution.result.data.size() > 0);

	}

	@Test
	public void readSelfLikedMedia() {

		UsecaseExecution<RecentMedia.Parameters, InstagramResponse<List<Media>>> execution = UsecaseExecution
				.create(RecentMedia.class);
		execution.params = new RecentMedia.Parameters();
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.params.user_id = "self";
		execution.run();
		TestSettings.println(execution.result);
		Assert.assertTrue(execution.result.data != null);
		Assert.assertTrue(execution.result.data.size() > 0);

	}

	@Test
	public void search() {

		UsecaseExecution<Search.Parameters, InstagramResponse<List<User>>> execution = UsecaseExecution
				.create(Search.class);

		execution.params = new Search.Parameters();
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.params.query = "portela";
		execution.run();
		TestSettings.println(execution.result);
		Assert.assertTrue(execution.result.data != null);
		Assert.assertTrue(execution.result.data.size() > 0);
	}

	@Test
	public void follows() {

		UsecaseExecution<Follows.Parameters, InstagramResponse<List<User>>> execution = UsecaseExecution
				.create(Follows.class);

		execution.params = new Follows.Parameters();
		execution.params.access_token = TestSettings.ACCESS_TOKEN;
		execution.run();

		Assert.assertTrue(execution.result.data != null);
		Assert.assertTrue(execution.result.data.size() > 0);
	}


}
