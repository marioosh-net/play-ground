package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.comet;

public class Comet extends Controller {

	public static Result index() {
		return ok(comet.render());
	}

	public static Result comet() {
		play.libs.Comet comet = new play.libs.Comet("parent.sendMessage") {
			public void onConnected() {
				sendMessage("comet message 1");
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				sendMessage("comet message 2");
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				sendMessage("comet message 3");
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				sendMessage("DONE");
				close();
			}
		};
		return ok(comet);
	}

}
