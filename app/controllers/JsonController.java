package controllers;

import org.codehaus.jackson.JsonNode;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class JsonController extends Controller {

	/*
	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello() {
		JsonNode json = request().body().asJson();
		String name = json.findPath("name").getTextValue();
		if (name == null) {
			return badRequest("Missing parameter [name]");
		} else {
			return ok("Hello " + name);
		}
	}
	*/
	
	public static Result sayHello() {
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

}
