package ru.tpu.rasp.data;

import java.util.List;

/**
 * Created by Andrey on 16.03.14.
 */
public class TeacherLesson extends Lesson {
    public final String room;
    public final List<String> groups;

    public TeacherLesson(String startTime, String endTime, String subject, String type, String room, List<String> groups) {
        super(startTime, endTime, subject, type);
        this.room = room;
        this.groups = groups;
    }
}
