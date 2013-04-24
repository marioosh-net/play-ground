package controllers;

import java.io.File;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.upload;

public class UploadController extends Controller {

	public static Result index() {
		return ok(upload.render());
	}

	public static Result upload() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart filePart = body.getFile("file");
		
		//if (filePart != null) {
		if(!filePart.getFilename().equals("")) {
			String fileName = filePart.getFilename();
			String contentType = filePart.getContentType();
			File f = filePart.getFile();
			flash("message", "File uploaded: "+fileName + " ("+contentType+")");
			return ok(upload.render());
		} else {
			flash("message", "Missing file");
			return ok(upload.render());
		}
	}

}
