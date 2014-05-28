package ru.tpu.rasp.loader;

import android.content.Context;

import ru.tpu.rasp.app.App;
import ru.tpu.rasp.data.Schedule;

/**
 * @author andrey.pogrebnoy
 */
public class ScheduleLoader extends ResultLoader<Schedule> {
	private String mToken;
	private boolean mIgnoreCache;

	public ScheduleLoader(Context context, String token, boolean ignoreCache) {
		super(context);
		mToken = token;
	}

	@Override
	Schedule loadSafe() throws Exception {
		return ((App) getContext().getApplicationContext()).getScheduleProvider().getSchedule(mToken, mIgnoreCache);
	}
}
