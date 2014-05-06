package ru.tpu.rasp.api;

import java.io.IOException;

import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.exception.ParseException;

/**
 * @author andrey.pogrebnoy
 */
public interface TpuClient {
	String[] search(String part) throws IOException, ParseException;

	Schedule scheduleFor(String token) throws IOException, ParseException;
}
