package ru.tpu.rasp.provider;

import ru.tpu.rasp.data.Schedule;

/**
 * @author andrey.pogrebnoy
 */
class BindedSchedule {
	final String token;
	final Schedule schedule;

	BindedSchedule(String token, Schedule schedule) {
		this.token = token;
		this.schedule = schedule;
	}
}
