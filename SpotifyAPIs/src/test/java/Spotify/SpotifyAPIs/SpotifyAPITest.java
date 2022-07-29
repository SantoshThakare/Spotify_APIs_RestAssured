package Spotify.SpotifyAPIs;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class SpotifyAPITest {

	public String token = "Bearer BQAuuFEtsZdHsP_Z4MvuCvkm2d-A15bHPhcbQeKCflvCcuGV6x2CqrcHS4ddcE-Ib0HkekyoeHf5H8H_HOzBAoi4oCvkfvK3EAzAa9-G9iwRIBLGjerVPrqYYB8s4FpMKYOfwARqhfqf0J-5N0hYBPpvJEH9ZNWpwCpsCO9YwNvumlI23CWTrI9BQtEvSh3lSi9u50yiQ_hQM1u36u2bUxAOmXWk5R7pg5bCzRcYZrK1mK6S";
	public static String UserID = "";

	//Users Profile
	
	@Test(priority = 0)
	public void test_get_current_users_profile() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.accept(ContentType.JSON);
		requestSpecification.contentType("application/json");
		requestSpecification.header("Authorization", token);

		Response response = requestSpecification.request(Method.GET, "https://api.spotify.com/v1/me");

		String title = response.path("title");
		System.out.println("User ID :" + title);

		response.prettyPrint();
		String statusLineString = response.statusLine();
		System.out.println("status Line :" + statusLineString);
		Assert.assertEquals(statusLineString, "HTTP/1.1 200 OK");

//		int statusCode = response.statusCode();
//		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 1)
	public void test_get_user_profile() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.accept(ContentType.JSON);
		requestSpecification.contentType("application/json");
		requestSpecification.header("Authorization", token);

		Response response = requestSpecification.request(Method.GET,
				"https://api.spotify.com/v1/users/31ui7inqbduqtu5soxwxqxxlsjgm");
		response.prettyPrint();
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	// Playlists 

	@Test
	public void test_get_current_user_playlist() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when().get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
	}

	@Test
	public void get_playlist_cover_image() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when()
				.get("https://api.spotify.com/v1/playlists/1x4pEX3db93gUe7zweLr9u/images");
		response.prettyPrint();
	}

	@Test
	public void get_playlist() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when()
				.get("https://api.spotify.com/v1/playlists/1x4pEX3db93gUe7zweLr9u");
		response.prettyPrint();
	}

	@Test
	public void getListOfUsersPlaylist() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).when()
				.get("https://api.spotify.com/v1/users/31ui7inqbduqtu5soxwxqxxlsjgm/playlists");
		response.prettyPrint();
	}

	@Test
	public void test_create_playlist() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\n" + "  \"name\": \"Gym Songs Playlist\",\n"
						+ "  \"description\": \"Special Santosh Favourite description\",\n" + "  \"public\": false\n"
						+ "}")
				.when().post("https://api.spotify.com/v1/users/31ui7inqbduqtu5soxwxqxxlsjgm/playlists");
		response.prettyPrint();
	}

	@Test
	public void test_add_item_to_playlist() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).queryParams("uris", "spotify:track:6cUOiOY5qh2FpIQWIYAd2h").when()
				.post("https://api.spotify.com/v1/playlists/2f2t4JZyCFxSnv5m4UBUSK/tracks");
		response.prettyPrint();
	}

	@Test
	public void test_remove_item_from_playlist() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\n" + "  \"tracks\": [\n" + "    {\n"
						+ "      \"uri\": \"spotify:track:5Or2qbJdOoqn7AblvTQ4YZ\",\n" + "      \"positions\": [\n"
						+ "        0\n" + "      ]\n" + "    }\n" + "  ]\n" + "}")
				.when().delete("https://api.spotify.com/v1/playlists/0BGK51MBiGLQtXauKY2ghD/tracks");
		response.prettyPrint();
	}

	@Test
	public void change_playlist_name() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\n" + "  \"name\": \"Updated Playlist Santosh2\",\n"
						+ "  \"description\": \"Updated playlist description\",\n" + "  \"public\": false\n" + "}")
				.when().put("https://api.spotify.com/v1/playlists/7cq4xbSx99nrG6TRAZKGV7");
		response.prettyPrint();
	}

	
	
}
