package controllers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.json;

public class JsonController extends Controller {

	public static Result index() {
		return ok(json.render());
	}
	
	public static Result sayHello1() {
		System.out.println(request().body());
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("Expecting Json data");
		} else {
			String name = json.findPath("name").getTextValue();
			if (name == null) {
				return badRequest("Missing parameter [name]");
			} else {
				return ok("Hello " + name);
			}
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello2() {
		JsonNode json = request().body().asJson();
		String name = json.findPath("name").getTextValue();
		if (name == null) {
			return badRequest("Missing parameter [name]");
		} else {
			return ok("Hello " + name);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello3() {
		JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject();
		String name = json.findPath("name").getTextValue();
		if (name == null) {
			result.put("status", "KO");
			result.put("message", "Missing parameter [name]");
			return badRequest(result);
		} else {
			result.put("status", "OK");
			result.put("message", "Hello " + name);
			return ok(result);
		}
	}

}
