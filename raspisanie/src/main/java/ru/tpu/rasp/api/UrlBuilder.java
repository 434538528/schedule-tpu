package ru.tpu.rasp.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Билдер URL строки для запросов с параметрами
 *
 * @author andrey.pogrebnoy
 */
public class UrlBuilder {
	private String mUrl;
	private String mMethod;
	private final Map<String, String> mParams = new HashMap<String, String>();

	public UrlBuilder setUrl(String url) {
		if (!url.endsWith("/")) {
			this.mUrl = url + "/";
		} else {
			this.mUrl = url;
		}
		return this;
	}

	public UrlBuilder setMethod(String method) {
		this.mMethod = method;
		return this;
	}

	public UrlBuilder addParam(String key, String value) {
		try {
			mParams.put(key, URLEncoder.encode(value, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			mParams.put(key, value);
		}
		return this;
	}

	public UrlBuilder addParam(String key, int value) {
		return addParam(key, String.valueOf(value));
	}

	public String build() {
		String result = mUrl + mMethod + "?";
		for (Map.Entry<String, String> entry : mParams.entrySet()) {
			result += entry.getKey() + "=" + entry.getValue() + "&";
		}
		return result;
	}
}
