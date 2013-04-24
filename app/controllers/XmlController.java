package controllers;

import org.w3c.dom.Document;
import play.libs.XPath;
import play.mvc.BodyParser.Xml;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.xml;
import play.mvc.BodyParser;

public class XmlController extends Controller {

	public static Result index() {
		return ok(xml.render());
	}

	/**
	 * send xml - get text 1
	 * 
	 * @return
	 */
	public static Result sayHello1() {
		Document dom = request().body().asXml();
		if (dom == null) {
			return badRequest("Expecting Xml data");
		} else {
			String name = XPath.selectText("//name", dom);
			if (name == null) {
				return badRequest("Missing parameter [name]");
			} else {
				return ok("Hello " + name);
			}
		}
	}

	/**
	 * send xml - get text 2
	 * 
	 * @return
	 */
	@BodyParser.Of(Xml.class)
	public static Result sayHello2() {
		Document dom = request().body().asXml();
		if (dom == null) {
			return badRequest("Expecting Xml data");
		} else {
			String name = XPath.selectText("//name", dom);
			if (name == null) {
				return badRequest("Missing parameter [name]");
			} else {
				return ok("Hello " + name);
			}
		}
	}

	/**
	 * send xml - get xml response
	 * 
	 * @return
	 */
	@BodyParser.Of(Xml.class)
	public static Result sayHello3() {
		Document dom = request().body().asXml();
		if (dom == null) {
			return badRequest("Expecting Xml data");
		} else {
			String name = XPath.selectText("//name", dom);
			if (name == null) {
				return badRequest("<message status=\"KO\">Missing parameter [name]</message>");
			} else {
				return ok("<message status=\"OK\">Hello " + name + "</message>");
			}
		}
	}
}
