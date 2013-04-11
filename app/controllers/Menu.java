package controllers;

import model.Message;
import model.Tag;
import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.menu;

public class Menu extends Controller {

	public static Html menuHtml() {
		return menu.render();
	}
	
	public static Result getMenu() {
		return ok(menuHtml());
	}
}
