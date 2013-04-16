package controllers;

import java.util.concurrent.Callable;
import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

public class PromiseController extends Controller {

	public static Result async1() {

		return async(
				Akka.future(new Callable<Integer>() {
					/**
					 * long running job
					 */
					public Integer call() {
						try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
						return 24;
					}
				}).map(
						/**
						 * callback function
						 */
						new Function<Integer, Result>() {
							@Override
							public Result apply(Integer arg0) throws Exception {
								return ok(arg0 + "");
							}			
						}
					)
		);

	}
}
