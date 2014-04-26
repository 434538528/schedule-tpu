package ru.tpu.rasp.data;

import java.io.Serializable;

/**
 * Created by Andrey on 16.03.14.
 */
public abstract class Lesson implements Serializable {
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
