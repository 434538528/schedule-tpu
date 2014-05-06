package ru.tpu.rasp.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author andrey.pogrebnoy
 */
public class SPConfig implements Config {
	private static final String SP_SCHEDULE_TOKEN = "schedule_token";
	private static final String SP_EVEN = "even";
	private static final String SP_BEFORE_BREAK = "before_break";

	private Context mContext;

	public SPConfig(Context context) {
		mContext = context;
	}

	@Override
	public String getScheduleToken() {
		return getSP().getString(SP_SCHEDULE_TOKEN, null);
	}

	@Override
	public void setScheduleToken(String scheduleToken) {
		getSP().edit().putString(SP_SCHEDULE_TOKEN, scheduleToken).apply();
	}

	@Override
	public boolean isEven() {
		return getSP().getBoolean(SP_EVEN, false);
	}

	@Override
	public boolean isBeforeBreak() {
		return getSP().getBoolean(SP_BEFORE_BREAK, true);
	}

	@Override
	public void setEven(boolean isEven) {
		getSP().edit().putBoolean(SP_EVEN, isEven).apply();
	}

	@Override
	public void setBeforeBreak(boolean isBeforeBreak) {
		getSP().edit().putBoolean(SP_BEFORE_BREAK, isBeforeBreak).apply();
	}

	private SharedPreferences getSP() {
		return PreferenceManager.getDefaultSharedPreferences(mContext);
	}
}
