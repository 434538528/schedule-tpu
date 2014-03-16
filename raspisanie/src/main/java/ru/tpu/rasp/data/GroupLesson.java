package ru.tpu.rasp.data;

import java.util.List;

/**
 * Created by Andrey on 16.03.14.
 */
public class GroupLesson extends Lesson {

    public final String teacher;
    public final String room;

    public GroupLesson(String startTime, String endTime, String subject, String type, String teacher, String room) {
        super(startTime, endTime, subject, type);
        this.teacher = teacher;
        this.room = room;
    }
}
