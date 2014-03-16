package ru.tpu.rasp.data;

import java.util.List;

/**
 * Created by Andrey on 16.03.14.
 */
public class RoomLesson extends Lesson {

    public final String teacher;
    public final List<String> groups;

    public RoomLesson(String startTime, String endTime, String subject, String type, String teacher, List<String> groups) {
        super(startTime, endTime, subject, type);
        this.teacher = teacher;
        this.groups = groups;
    }
}
