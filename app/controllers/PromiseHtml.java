package controllers;

import java.util.concurrent.Callable;
import play.api.templates.Html;
import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import scala.collection.mutable.StringBuilder;
import views.html.test;

/**
 * jmeter and many user thread show the difference between old and new way ;) 
 * @author marioosh
 *
 */
public class PromiseHtml extends Controller {

	public static Promise<Html> promiseHtml() {
		return Akka.future(new Callable<Html>() {

			@Override
			public Html call() throws Exception {
				/**
				 * There is no HTTP Context available from here.
				 * session(), flash(), etc doesn't work here
				 */
				// Http.Context.current();
				
				Thread.sleep(2000);
				return test.render();
			}
		});
	}
	
	public static Result html() {

		return async(
			promiseHtml().map(
				/**
				 * callback function
				 */
				new Function<Html, Result>() {
					@Override
					public Result apply(Html html) throws Exception {
						/**
						 * HTTP Context is here
						 */
						Http.Context.current();
						return ok(html);
					}			
				}
			)
		);

	}
}
