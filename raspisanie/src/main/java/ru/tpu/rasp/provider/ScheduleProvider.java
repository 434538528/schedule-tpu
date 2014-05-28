package ru.tpu.rasp.provider;

import ru.tpu.rasp.api.TpuClient;
import ru.tpu.rasp.cache.DiskCache;
import ru.tpu.rasp.cache.MemCache;
import ru.tpu.rasp.data.Schedule;

/**
 * @author andrey.pogrebnoy
 */
public class ScheduleProvider {
	private final TpuClient mTpuClient;
	private final DiskCache<Schedule, String> mDiskCache;
	private final MemCache<Schedule, String> mMemCache;

	public ScheduleProvider(TpuClient tpuClient, DiskCache<Schedule, String> diskCache, MemCache<Schedule, String> memCache) {
		this.mTpuClient = tpuClient;
		this.mDiskCache = diskCache;
		this.mMemCache = memCache;
	}

	public Schedule getSchedule(final String token, boolean ignoreCache) throws Exception {
		Schedule schedule;
		if (!ignoreCache) {
			schedule = mMemCache.get(token);
			if (schedule != null) {
				return schedule;
			}
			schedule = mDiskCache.get(token);
			if (schedule != null) {
				mMemCache.put(schedule, token);
				return schedule;
			}
		}
		schedule = mTpuClient.scheduleFor(token);
		if (schedule != null) {
			mDiskCache.put(schedule, token);
			mMemCache.put(schedule, token);
		}
		return schedule;
	}
}
