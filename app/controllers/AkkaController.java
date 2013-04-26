package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.UntypedActor;
import play.Logger;
import play.libs.Akka;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.duration.Duration;
import views.html.akka;

public class AkkaController extends Controller {

	public static Result index() {

		ActorRef myActor = Akka.system().actorOf(new Props(MyActor.class));

		/**
		 * scheduler 1
		 */
		Cancellable c1 = Akka.system().scheduler().schedule(
				Duration.create(0, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
				Duration.create(1, TimeUnit.SECONDS), //Frequency 1s
				myActor, "tick", Akka.system().dispatcher()
		);

		/**
		 * scheduler 2
		 */
		Cancellable c2 = Akka.system().scheduler().schedule(
				Duration.create(0, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
				Duration.create(1, TimeUnit.SECONDS), //Frequency 1s
				new Runnable() {
					@Override
					public void run() {
						Logger.info(this + ": " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(System.currentTimeMillis())));
					}
				},
				Akka.system().dispatcher()
		);		
		return ok(akka.render());
	}

}
