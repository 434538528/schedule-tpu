package ru.tpu.rasp.loader;

import android.content.Context;

import ru.tpu.rasp.app.App;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.provider.Result;

/**
 * Created by Andrey on 13.04.2014.
 */
public class ScheduleLoader extends BackgroundLoader<Schedule> {
	private String mToken;

	public ScheduleLoader(Context context, String token) {
		super(context);
		mToken = token;
	}

	@Override
	public Result<Schedule> loadInBackground() {
		return ((App) getContext().getApplicationContext()).getScheduleProvider().getSchedule(mToken);
	}
}
