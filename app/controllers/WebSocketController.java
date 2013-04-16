package controllers;

import play.Logger;
import play.mvc.WebSocket;
import play.libs.F.Callback;
import play.libs.F.Callback0;

/**
 * go to http://www.websocket.org/echo.html
 * set Location: ws://localhost:9000/websocket
 * 
 * @author marioosh
 *
 */
public class WebSocketController {

	public static WebSocket<String> index() {
		return new WebSocket<String>() {

			// Called when the Websocket Handshake is done.
			public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {

				// For each event received on the socket,
				in.onMessage(new Callback<String>() {
					public void invoke(String event) {
						// Log events to the console
						Logger.info(event+"");
					}
				});

				// When the socket is closed.
				in.onClose(new Callback0() {
					public void invoke() {
						Logger.info("Disconnected");
					}
				});

				// Send a single 'Hello!' message
				out.write("Hello!");

			}

		};
	}
}
