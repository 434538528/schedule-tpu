package ru.tpu.rasp.providers;

import ru.tpu.rasp.api.TpuClient;
import ru.tpu.rasp.data.Schedule;

/**
 * Created by Andrey on 06.04.2014.
 */
public class ScheduleProvider {
	private TpuClient tpuClient;

	public ScheduleProvider(TpuClient tpuClient) {
		this.tpuClient = tpuClient;
	}

	/**
	 * throws IOException, ParseException, TpuGrabberException
	 */
	public Result<Schedule> getSchedule(final String token) {
		return new Result<Schedule>(new Result.Processor<Schedule>() {
			@Override
			public Schedule process() throws Exception {
				return tpuClient.scheduleFor(token);
			}
		});
	}
}
