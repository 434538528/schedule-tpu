package ru.tpu.rasp.api;

import java.io.IOException;

import ru.tpu.rasp.app.Utils;
import ru.tpu.rasp.exceptions.ParseException;
import ru.tpu.rasp.parsers.Parser;
import ru.tpu.rasp.parsers.SearchParser;

/**
 * Created by Andrey on 16.03.14.
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
}
