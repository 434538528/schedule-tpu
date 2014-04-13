package ru.tpu.rasp.parser;

import org.json.JSONException;
import org.json.JSONObject;

import ru.tpu.rasp.exception.ParseException;
import ru.tpu.rasp.exception.TpuGrabberException;

/**
 * Парсер ответов от граббера данных с ТПУ. Проверяет, успешно ли сервер обработал данные
 */
public abstract class TpuGrabberParser<T> implements Parser<T> {
	@Override
	public T parse(String data) throws ParseException {
		try {
			JSONObject responseJSON = new JSONObject(data);
			if (responseJSON.getBoolean("success")) {
				return parseResult(responseJSON.getJSONObject("result"));
			} else {
				throw new TpuGrabberException(responseJSON.getString("message"));
			}
		} catch (JSONException e) {
			throw new ParseException(e);
		}
	}

	public abstract T parseResult(JSONObject resultJSON) throws JSONException;
}
