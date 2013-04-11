package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	public static Result index() {
		Http.Context.current().args.put("pages", new Integer[5]);
        return ok(index.render());
    }
	
    public static Result search(String search) {
    	return ok(search);
    }

}
