package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import views.html.auth;

public class Composition extends Controller {

	public static Result index() {
		return ok(auth.render());
	}
	
	@Authenticated
	public static Result secured() {
		return ok("Auth OK");
	}
	
	public static Result login() {
		Context.current().session().put("username", "mario");
		return ok("logged");
	}
	
	public static Result logout() {
		Context.current().session().remove("username");
		return ok("logged out");
	}
}
