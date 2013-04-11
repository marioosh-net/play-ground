package controllers;

import model.Message;
import model.Tag;
import play.Logger;
import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

public class BodyParser extends Controller {

	public static Result test() {
		RequestBody body = request().body();
		if(body != null) {
			Logger.info("text: "+body.asText());
			Logger.info("json: "+body.asJson()+"");
		}
		return ok();
	}
}
