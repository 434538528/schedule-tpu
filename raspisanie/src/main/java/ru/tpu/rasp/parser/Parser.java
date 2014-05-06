package ru.tpu.rasp.parser;

import ru.tpu.rasp.exception.ParseException;

/**
 * @author andrey.pogrebnoy
 */
public interface Parser<T> {
	public T parse(String data) throws ParseException;
}
