package ru.tpu.rasp.exception;

import org.json.JSONException;

public class ParseException extends Exception {
	public ParseException(JSONException e) {
		super(e);
	}

	public ParseException(String message) {
		super(message);
	}
}
