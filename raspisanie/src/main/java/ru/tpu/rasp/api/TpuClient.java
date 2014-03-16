package ru.tpu.rasp.api;

import java.io.IOException;

import ru.tpu.rasp.exceptions.ParseException;

/**
 * Created by Andrey on 16.03.14.
 */
public interface TpuClient {
    String[] search(String part) throws IOException, ParseException;
}
