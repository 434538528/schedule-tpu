package ru.tpu.rasp.parsers;

import ru.tpu.rasp.exceptions.ParseException;

public interface Parser<T> {
    public T parse(String data) throws ParseException;
}
