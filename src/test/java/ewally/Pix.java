package ewally;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Pix {

	String baseUrl = "https://apidev.ewally.com.br";
	String path = "/user/login";

	RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
	JSONObject json = new JSONObject();
	Response response;

	public String login(String username, String senha) {
		json.put("username", username);
		json.put("password", senha);

		request.body(json.toJSONString());

		response = request.post(baseUrl + path);
		String token = response.jsonPath().get("token");
		return token;

	}

	@Test

	public void testLoginValidoCnpj() {
		System.out.println("Token Cnpj" + login("64440513000104", "1234"));
		assertEquals(200, response.getStatusCode());
	}

	@Test

	public void testLoginValidoCpf() {
		System.out.println("Token Cpf" + login("97344890038", "1234"));
		assertEquals(200, response.getStatusCode());

	}
}