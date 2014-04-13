package ru.tpu.rasp.data;

/**
 * Created by Andrey on 16.03.14.
 */
public class RoomLesson extends Lesson {

	public final String teacher;
	public final String groups;

	public RoomLesson(String startTime, String subject, String type, String teacher, String groups) {
		super(startTime, subject, type);
		this.teacher = teacher;
		this.groups = groups;
	}

	@Override
	public String getFirstSubtitle() {
		return teacher;
	}

	@Override
	public String getSecondSubtitle() {
		return groups;
	}
}
