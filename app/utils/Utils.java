package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import model.Message;
import org.apache.commons.io.IOUtils;
import play.Logger;

public class Utils {
    
	public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
    
	/**
     * url to base64
     * @param url
     * @return
     * @throws IOException
     */
    public static String toBase64(URL url) throws IOException {
		URLConnection c = url.openConnection();
		InputStream in = c.getInputStream();
		// return "data:"+c.getContentType()+";base64,"+org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(IOUtils.toByteArray(in));
		return "data:"+c.getContentType()+";base64,"+org.apache.commons.codec.binary.Base64.encodeBase64String(IOUtils.toByteArray(in));
		/*
		File f = File.createTempFile(UUID.randomUUID().toString(), "");
		FileOutputStream o = new FileOutputStream(f);
		IOUtils.copy(in, o);
		o.close();	
		return f;
		*/
    }
    
    /**
     * URL -> Message 
     * @param url1
     * @return
     * @throws IOException
     */
    public static Message downloadFile(String url1) throws IOException {
    	Message l = new Message();
    	URL url = new URL(url1);
		URLConnection c = url.openConnection();
		c.addRequestProperty("User-Agent", DEFAULT_USER_AGENT);
		InputStream in = c.getInputStream();

    	/**
    	 * wada:
    	 * jak jest przekierowanie Status 302, 
    	 * to np. zamiast zwrocic png, zwroci stronke html
    	 * 
    	 * obsluga 302
    	 */
		Integer code = null;
		String location302 = null;
		if(c instanceof HttpURLConnection) {
			HttpURLConnection hc = (HttpURLConnection) c;
			code = hc.getResponseCode();
			if(code != null && code == 302) {
				location302 = hc.getHeaderField("Location");
				url1 = location302;
				in.close();
				url = new URL(location302);
				c = url.openConnection();
				c.addRequestProperty("User-Agent", DEFAULT_USER_AGENT);
				in = c.getInputStream();
			}
			Logger.info(code + " " + url1);
		}
		
		l.setUrl(url1);
		l.setContentType(c.getContentType());
		l.setContentEncoding(c.getContentEncoding());
		l.setHeaderFields(c.getHeaderFields());
		File f = File.createTempFile(UUID.randomUUID().toString(), "");		
		f.deleteOnExit();
		FileOutputStream o = new FileOutputStream(f);
		IOUtils.copy(in, o);
		o.close();
		in = new FileInputStream(f);
		l.setData(IOUtils.toByteArray(in));
		in.close();
		return l;
    }    
}
