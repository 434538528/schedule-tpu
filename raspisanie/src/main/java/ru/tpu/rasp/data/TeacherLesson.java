package ru.tpu.rasp.data;

/**
 * Created by Andrey on 16.03.14.
 */
public class TeacherLesson extends Lesson {
	public final String room;
	public final String groups;

	public TeacherLesson(String startTime, String subject, String type, String room, String groups) {
		super(startTime, subject, type);
		this.room = room;
		this.groups = groups;
	}

	@Override
	public String getFirstSubtitle() {
		return room;
	}

	@Override
	public String getSecondSubtitle() {
		return groups;
	}
}
