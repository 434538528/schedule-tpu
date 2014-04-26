package ru.tpu.rasp.app;

import android.app.Application;

import java.io.File;

import ru.tpu.rasp.api.TpuClient;
import ru.tpu.rasp.api.TpuGrabberClient;
import ru.tpu.rasp.cache.DiskCache;
import ru.tpu.rasp.cache.MemCache;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.provider.ScheduleProvider;
import ru.tpu.rasp.provider.SearchProvider;

public class App extends Application {

	private static final String SCHEDULE_CACHE_FILE = "schedule.cache";
	private static final boolean SCHEDULE_REWRITE = true;

	private SearchProvider searchProvider;
	private ScheduleProvider scheduleProvider;
	private Config config;

	@Override
	public void onCreate() {
		super.onCreate();
		TpuClient tpuClient = new TpuGrabberClient();
		searchProvider = new SearchProvider(tpuClient);
		scheduleProvider = new ScheduleProvider(
				tpuClient,
				new DiskCache<Schedule, String>(new File(getCacheDir(), SCHEDULE_CACHE_FILE), SCHEDULE_REWRITE),
				new MemCache<Schedule, String>(SCHEDULE_REWRITE));
		config = new SPConfig(this);
	}

	public SearchProvider getSearchProvider() {
		return searchProvider;
	}

	public ScheduleProvider getScheduleProvider() {
		return scheduleProvider;
	}

	public Config getConfig() {
		return config;
	}
}
