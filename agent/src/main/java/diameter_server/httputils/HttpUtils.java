package diameter_server.httputils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpUtils {


	/**
	 * 通过传入某一URL完成资源的获取
	 * @param urlString  某一地址的URL
	 * @return
	 */
	public static String getURLContent(String urlString) {
		// 请求的url
		URL url = null;
		// 建立的http链接
		HttpURLConnection httpConn = null;
		// 请求的输入流
		BufferedReader in = null;
		// 输入流的缓冲
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(urlString);
			in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String str = null;
			// 一行一行的读入
			while((str = in.readLine()) != null) {
				sb.append( str );
			}   
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

}
