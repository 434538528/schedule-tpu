package ru.tpu.rasp.provider;

import ru.tpu.rasp.api.TpuClient;
import ru.tpu.rasp.cache.DiskCache;
import ru.tpu.rasp.cache.MemCache;
import ru.tpu.rasp.data.Schedule;

/**
 * Created by Andrey on 06.04.2014.
 */
public class ScheduleProvider {
	private final TpuClient tpuClient;
	private final DiskCache<Schedule, String> diskCache;
	private final MemCache<Schedule, String> memCache;

	public ScheduleProvider(TpuClient tpuClient, DiskCache<Schedule, String> diskCache, MemCache<Schedule, String> memCache) {
		this.tpuClient = tpuClient;
		this.diskCache = diskCache;
		this.memCache = memCache;
	}

	public Schedule getSchedule(final String token) throws Exception {
		Schedule schedule = memCache.get(token);
		if (schedule != null) {
			return schedule;
		}
		schedule = diskCache.get(token);
		if (schedule != null) {
			memCache.put(schedule, token);
			return schedule;
		}
		schedule = tpuClient.scheduleFor(token);
		if (schedule != null) {
			diskCache.put(schedule, token);
			memCache.put(schedule, token);
		}
		return schedule;
	}
}
