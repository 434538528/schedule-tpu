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

/**
 * @author andrey.pogrebnoy
 */
public class App extends Application {

	private static final String SCHEDULE_CACHE_FILE = "schedule.cache";
	private static final boolean SCHEDULE_REWRITE = true;

	private SearchProvider mSearchProvider;
	private ScheduleProvider mScheduleProvider;
	private Config mConfig;

	@Override
	public void onCreate() {
		super.onCreate();
		TpuClient tpuClient = new TpuGrabberClient();
		mSearchProvider = new SearchProvider(tpuClient);
		mScheduleProvider = new ScheduleProvider(
				tpuClient,
				new DiskCache<Schedule, String>(new File(getCacheDir(), SCHEDULE_CACHE_FILE), SCHEDULE_REWRITE),
				new MemCache<Schedule, String>(SCHEDULE_REWRITE));
		mConfig = new SPConfig(this);
	}

	public SearchProvider getSearchProvider() {
		return mSearchProvider;
	}

	public ScheduleProvider getScheduleProvider() {
		return mScheduleProvider;
	}

	public Config getConfig() {
		return mConfig;
	}
}
