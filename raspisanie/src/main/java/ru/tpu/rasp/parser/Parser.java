package ru.tpu.rasp.parser;

import ru.tpu.rasp.exception.ParseException;

public interface Parser<T> {
	public T parse(String data) throws ParseException;
}
