package ru.tpu.rasp.data;

/**
 * Created by Andrey on 16.03.14.
 */
public class Lesson {
	public final String startTime;
	public final String endTime;
	public final String subject;
	public final String type;

	public Lesson(String startTime, String endTime, String subject, String type) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.subject = subject;
		this.type = type;
	}
}
