package ru.tpu.rasp.api;

import android.util.Log;

import java.io.IOException;

import ru.tpu.rasp.app.Utils;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.exception.ParseException;
import ru.tpu.rasp.parser.Parser;
import ru.tpu.rasp.parser.ScheduleParser;
import ru.tpu.rasp.parser.SearchParser;

/**
 * @author andrey.pogrebnoy
 */
public class TpuGrabberClient implements TpuClient {
	private static final String TPU_GRABBER_URL = "http://78.140.13.90:8080/TpuGrabber/";

	@Override
	public String[] search(String part) throws IOException, ParseException {
		String url = new UrlBuilder()
				.setUrl(TPU_GRABBER_URL)
				.setMethod("search")
				.addParam("term", part)
				.build();
		Parser<String[]> parser = new SearchParser();
		return parser.parse(Utils.readStringFromUrl(url));
	}

	@Override
	public Schedule scheduleFor(String token) throws IOException, ParseException {
		String url = new UrlBuilder()
				.setUrl(TPU_GRABBER_URL)
				.setMethod("schedule")
				.addParam("for", token)
				.build();
		Log.d("aaa", url);
		Parser<Schedule> parser = new ScheduleParser();
		return parser.parse(Utils.readStringFromUrl(url));
	}
}
