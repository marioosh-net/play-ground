package controllers;

import java.util.List;
import java.util.concurrent.Callable;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxRunnable;
import model.Message;
import play.data.Form;
import play.db.ebean.Transactional;
import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.sql;

public class SqlController extends Controller {

	public static Result index() {
		Promise<List<Message>> p = Akka.future(new Callable<List<Message>>() {

			@Override
			public List<Message> call() throws Exception {
				return Message.find.all();
			}
		});
		
		return async(p.map(new Function<List<Message>, Result>() {
			@Override
			public Result apply(List<Message> a) throws Throwable {
				return ok(sql.render(a));
			}
		}));
	}
	
	@Transactional
	public static Result add() {
    	Form<Message> f = Form.form(Message.class);
    	f = f.bindFromRequest();
    	if(f.hasErrors()) {
    		return badRequest(f.error("").message());
    	} else {
    		Message message = f.get();
    		message.save();
    		flash("message", "message added");
    		return index();
    	}
	}
	
	public static Result delete(final Long id) {
		
		/**
		 * rollback on any exception
		 */
		Ebean.execute(new TxRunnable() {
			@Override
			public void run() {
				Message.find.byId(id).delete();
			}
		});

		flash("message", "message deleted");
		return index();
	}

}
