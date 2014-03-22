package ru.tpu.rasp.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Билдер URL строки для запросов с параметрами
 */
public class UrlBuilder {
	private String url;
	private String method;
	private final Map<String, String> params = new HashMap<String, String>();

	public UrlBuilder setUrl(String url) {
		if (!url.endsWith("/")) {
			this.url = url + "/";
		} else {
			this.url = url;
		}
		return this;
	}

	public UrlBuilder setMethod(String method) {
		this.method = method;
		return this;
	}

	public UrlBuilder addParam(String key, String value) {
		params.put(key, value);
		return this;
	}

	public UrlBuilder addParam(String key, int value) {
		params.put(key, String.valueOf(value));
		return this;
	}

	public String build() {
		String result = url + method + "?";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			result += entry.getKey() + "=" + entry.getValue() + "&";
		}
		return result;
	}
}
