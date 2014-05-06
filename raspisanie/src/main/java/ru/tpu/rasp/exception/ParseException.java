package ru.tpu.rasp.exception;

import org.json.JSONException;

/**
 * @author andrey.pogrebnoy
 */
public class ParseException extends Exception {
	public ParseException(JSONException e) {
		super(e);
	}

	public ParseException(String message) {
		super(message);
	}
}
