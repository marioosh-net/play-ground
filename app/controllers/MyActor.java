package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import play.Logger;
import akka.actor.UntypedActor;

class MyActor extends UntypedActor {
	@Override
	public void onReceive(Object message) throws Exception {
		Thread.sleep(2000);
		Logger.info(this + ": " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(System.currentTimeMillis())) + ", message: "+ message.toString());
	}
}