package ru.tpu.rasp.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Парсер для метода search
 */
public class SearchParser extends TpuGrabberParser<String[]> {
	@Override
	public String[] parseResult(JSONObject resultJSON) throws JSONException {
		JSONArray keysJSON = resultJSON.getJSONArray("keys");
		String[] keys = new String[keysJSON.length()];
		for (int i = 0; i < keysJSON.length(); i++) {
			keys[i] = keysJSON.getString(i);
		}
		return keys;
	}
}
