package ru.tpu.rasp.loader;

import android.content.Context;

import ru.tpu.rasp.app.App;
import ru.tpu.rasp.data.Schedule;

/**
 * Created by Andrey on 13.04.2014.
 */
public class ScheduleLoader extends ResultLoader<Schedule> {
	private String mToken;

	public ScheduleLoader(Context context, String token) {
		super(context);
		mToken = token;
	}

	@Override
	Schedule loadSafe() throws Exception {
		return ((App) getContext().getApplicationContext()).getScheduleProvider().getSchedule(mToken);
	}
}
