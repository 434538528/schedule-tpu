package ru.tpu.rasp.data;

import java.io.Serializable;

/**
 * @author andrey.pogrebnoy
 */
public class GroupLesson extends Lesson implements Serializable {

	public final String teacher;
	public final String room;

	public GroupLesson(String startTime, String subject, String type, String teacher, String room) {
		super(startTime, subject, type);
		this.teacher = teacher;
		this.room = room;
	}

	@Override
	public String getFirstSubtitle() {
		return teacher;
	}

	@Override
	public String getSecondSubtitle() {
		return room;
	}
}
