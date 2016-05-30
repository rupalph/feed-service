
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;

public class FeedWebServiceTest {
	@Test
	public void testFeedWebService() {

		given().
		contentType("application/json").
		body("{ \"userid\":\"2005\", \"message\":\"test\" }").
		when().
		post("/FeedService/api/feed/post").
		then().
		body(containsString("userid")).
		body(containsString("message")).
		body(containsString("time")).
		statusCode(201);

		//TweetRepository.save(tweet);
		String token = get("/FeedService/api/login/2005").asString();
		System.out.println(token);
		String resp = get("/FeedService/api/feed/2005?authToken="+token).asString();
		System.out.println(resp);

		given().
		contentType("application/json").
		when().
		get("/FeedService/api/feed/2005?authToken="+token).
		then().
		body(containsString("\"message\":\"test\",\"userid\":\"2005\"")).
		statusCode(200);
	}

	
}