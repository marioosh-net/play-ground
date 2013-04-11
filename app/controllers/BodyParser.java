package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

public class BodyParser extends Controller {

	public static Result index() {
		RequestBody body = request().body();
		return ok("Got body: " + body);
	}

	public static Result index2() {
		RequestBody body = request().body();
		if (body != null) {
			Logger.info("text: " + body.asText());
			Logger.info("json: " + body.asJson() + "");
		}
		return ok();
	}
}
