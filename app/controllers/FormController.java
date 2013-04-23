package controllers;

import model.Message;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.form;
import views.html.form2;

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

	public static Result index2() {
		Form<Message> f = Form.form(Message.class);
		return ok(form2.render(f));
    }
	
    public static Result form2post() {
    	Form<Message> f = Form.form(Message.class);
    	f = f.bindFromRequest();
    	if(f.hasErrors()) {
    		return badRequest(f.error("").message());
    	} else {
    		Message message = f.get();
    		flash("message", "message added");
    		return index2();
    	}
    }

}
