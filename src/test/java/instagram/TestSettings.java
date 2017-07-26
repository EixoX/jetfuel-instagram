package instagram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class TestSettings {

	public static final String ACCESS_TOKEN = "34524976.c453286.7797d40d98ff46e8956914d3a18bfa09";
	public static final String USER_ID = "34524976";
	public static final String TAG = "glamboxbrasil";

	public static void println(Object obj) {
		if (obj == null)
			System.out.println("null");
		else
			try {
				System.out.println(writer.writeValueAsString(obj));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	}

	private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
}
