package controllers;

import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import play.Logger;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.ws;

/**
 * calling web-services
 * @author marioosh
 *
 */
public class WSController extends Controller {

	public static Result index() {
		return ok(ws.render());
	}

	public static Result ws1() {
		final Promise<WS.Response> usaWeather = WS.url("http://wsf.cdyne.com/WeatherWS/Weather.asmx/GetWeatherInformation").get();

		return async(usaWeather.map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(Response a) throws Throwable {
				Document doc = a.asXml();
				DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
			    LSSerializer lsSerializer = domImplementation.createLSSerializer();
				return ok(lsSerializer.writeToString(doc));
			}
		}));
		
		/**
		 * multiple calls in sequence (....flatMap(...))
		 *
		return async(usaWeather.flatMap(new Function<WS.Response, Promise<Result>>() {

			@Override
			public Promise<Result> apply(Response a) throws Throwable {
				return usaWeather.map(new Function<WS.Response, Result>() {

					@Override
					public Result apply(Response a) throws Throwable {
						Document doc = a.asXml();
						DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
						LSSerializer lsSerializer = domImplementation.createLSSerializer();
						return ok(lsSerializer.writeToString(doc));
					}
				});
			}
		}));
		*/
	}

}
