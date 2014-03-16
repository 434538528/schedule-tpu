package ru.tpu.rasp.app;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    public static HttpParams httpParameters;

    static {
        httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
        HttpConnectionParams.setSoTimeout(httpParameters, 5000);
    }

    public static String readStringFromUrl(String url) throws IOException {
        HttpClient client = new DefaultHttpClient(httpParameters);
        HttpGet request = new HttpGet(url);
        InputStream is = null;

        HttpResponse apiResponse = client.execute(request);

        HttpEntity entity = apiResponse.getEntity();
        StringBuilder sb = new StringBuilder();
        if (entity != null) {
            try {
                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
            } catch (IOException e) {
                is.close();
                throw e;
            } finally {
                is.close();
            }
        }

        return sb.toString();
    }
}
