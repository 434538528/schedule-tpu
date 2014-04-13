package ru.tpu.rasp.data;

/**
 * Created by Andrey on 16.03.14.
 */
public abstract class Lesson {
	public final String startTime;
	public final String subject;
	public final String type;

	public Lesson(String startTime, String subject, String type) {
		this.startTime = startTime;
		this.subject = subject;
		this.type = type;
	}

	public abstract String getFirstSubtitle();
	public abstract String getSecondSubtitle();
}
