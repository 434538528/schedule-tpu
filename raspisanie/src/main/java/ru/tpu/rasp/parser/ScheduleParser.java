package ru.tpu.rasp.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ru.tpu.rasp.data.GroupLesson;
import ru.tpu.rasp.data.Lesson;
import ru.tpu.rasp.data.RoomLesson;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.data.TeacherLesson;
import ru.tpu.rasp.data.WeekSchedule;

/**
 * Парсинг расписания
 */
public class ScheduleParser extends TpuGrabberParser<Schedule> {
	@Override
	Schedule parseResult(JSONObject resultJSON) throws JSONException {
		resultJSON = resultJSON.getJSONObject("schedule");
		int type = resultJSON.getInt("type");

		WeekSchedule oddWeekBeforeBreaking = parseWeekSchedule(resultJSON.getJSONObject("before").getJSONArray("uneven"), type);
		WeekSchedule evenWeekBeforeBreaking = parseWeekSchedule(resultJSON.getJSONObject("before").getJSONArray("even"), type);
		WeekSchedule oddWeekAfterBreaking = parseWeekSchedule(resultJSON.getJSONObject("after").getJSONArray("uneven"), type);
		WeekSchedule evenWeekAfterBreaking = parseWeekSchedule(resultJSON.getJSONObject("after").getJSONArray("even"), type);
		return new Schedule.Builder(type)
				.setOddWeekBeforeBreaking(oddWeekBeforeBreaking)
				.setEvenWeekBeforeBreaking(evenWeekBeforeBreaking)
				.setOddWeekAfterBreaking(oddWeekAfterBreaking)
				.setEvenWeekAfterBreaking(evenWeekAfterBreaking)
				.build();
	}

	private WeekSchedule parseWeekSchedule(JSONArray weekArray, int type) throws JSONException {
		return new WeekSchedule.Builder()
				.setMondayLessons(parseLessons(weekArray.getJSONArray(0), type))
				.setTuesdayLessons(parseLessons(weekArray.getJSONArray(1), type))
				.setWednesdayLessons(parseLessons(weekArray.getJSONArray(2), type))
				.setThursdayLessons(parseLessons(weekArray.getJSONArray(3), type))
				.setFridayLessons(parseLessons(weekArray.getJSONArray(4), type))
				.setSaturdayLessons(parseLessons(weekArray.getJSONArray(5), type))
				.build();
	}

	private Lesson[] parseLessons(JSONArray dayArray, int type) throws JSONException {
		Lesson[] lessons = new Lesson[dayArray.length()];
		switch (type) {
			case 0:
				for (int i = 0; i < dayArray.length(); i++) {
					lessons[i] = parseGroupLesson(dayArray.getJSONObject(i));
				}
				break;
			case 1:
				for (int i = 0; i < dayArray.length(); i++) {
					lessons[i] = parseTeacherLesson(dayArray.getJSONObject(i));
				}
				break;
			case 2:
				for (int i = 0; i < dayArray.length(); i++) {
					lessons[i] = parseRoomLesson(dayArray.getJSONObject(i));
				}
				break;
		}
		return lessons;
	}

	private GroupLesson parseGroupLesson(JSONObject lessonJSON) throws JSONException {
		return new GroupLesson(
				lessonJSON.getString("time"),
				lessonJSON.getString("title"),
				lessonJSON.getString("type"),
				lessonJSON.getString("teacher"),
				lessonJSON.getString("room"));
	}

	private TeacherLesson parseTeacherLesson(JSONObject lessonJSON) throws JSONException {
		return new TeacherLesson(
				lessonJSON.getString("time"),
				lessonJSON.getString("title"),
				lessonJSON.getString("type"),
				lessonJSON.getString("room"),
				lessonJSON.getString("groups"));
	}

	private RoomLesson parseRoomLesson(JSONObject lessonJSON) throws JSONException {
		return new RoomLesson(
				lessonJSON.getString("time"),
				lessonJSON.getString("title"),
				lessonJSON.getString("type"),
				lessonJSON.getString("teacher"),
				lessonJSON.getString("groups"));
	}
}
