package controllers;

import model.Message;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.form;

public class FormController extends Controller {

	public static Result index() {
		return ok(form.render());
    }
	
    public static Result post() {
    	Form<Message> f = Form.form(Message.class);
    	f = f.bindFromRequest();
    	if(f.hasErrors()) {
    		return badRequest(f.error("").message());
    	} else {
    		Message message = f.get();
    		flash("message", "message added");
    		return index();
    	}
    }

}
