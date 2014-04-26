package ru.tpu.rasp.app;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utils {

	public static HttpParams httpParameters;
	private static final String TAG = Utils.class.getSimpleName();

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

	public static boolean saveObjectToFile(Object object, File file) {
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(object);
			return true;
		} catch (IOException e) {
			Log.e(TAG, "Unable to save object:", e);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					Log.wtf(TAG, "Unable to close stream:", e);
				}
			}
		}
		return false;
	}

	public static Object readObjectFromFile(File file) {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream(file));
			return input.readObject();
		} catch (Exception e) {
			Log.e(TAG, "Unable to read object:", e);
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				Log.wtf(TAG, "Unable to close stream:", e);
			}
		}
		return null;
	}
}
