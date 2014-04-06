package ru.tpu.rasp.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPConfig implements Config {
	private static final String SP_IS_SCHEDULE_SET = "is_schedule_set";
	private static final String SP_SCHEDULE_TOKEN = "schedule_token";

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

	private SharedPreferences getSP() {
		return PreferenceManager.getDefaultSharedPreferences(mContext);
	}
}
