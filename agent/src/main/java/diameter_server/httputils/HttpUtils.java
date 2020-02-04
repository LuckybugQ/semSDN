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
	 * ͨ������ĳһURL�����Դ�Ļ�ȡ
	 * @param urlString  ĳһ��ַ��URL
	 * @return
	 */
	public static String getURLContent(String urlString) {
		// �����url
		URL url = null;
		// ������http����
		HttpURLConnection httpConn = null;
		// �����������
		BufferedReader in = null;
		// �������Ļ���
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(urlString);
			in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String str = null;
			// һ��һ�еĶ���
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
