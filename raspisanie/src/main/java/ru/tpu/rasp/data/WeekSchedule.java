package ru.tpu.rasp.data;

import android.util.SparseArray;

import java.util.Collections;
import java.util.List;

/**
 * Расписание на неделю
 */
public class WeekSchedule {

	public static final int MONDAY = 0;
	public static final int TUESDAY = 1;
	public static final int WEDNESDAY = 2;
	public static final int THURSDAY = 3;
	public static final int FRIDAY = 4;
	public static final int SATURDAY = 5;

	private final SparseArray<List<Lesson>> lessons;

	public List<Lesson> getDayLessons(int day) {
		return lessons.get(day);
	}

	private WeekSchedule(SparseArray<List<Lesson>> lessons) {
		this.lessons = lessons;
	}

	public static class Builder {
		private SparseArray<List<Lesson>> lessons = new SparseArray<List<Lesson>>(6);

		public Builder setMondayLessons(List<Lesson> lessons) {
			this.lessons.put(MONDAY, lessons);
			return this;
		}

		public Builder setTuesdayLessons(List<Lesson> lessons) {
			this.lessons.put(TUESDAY, lessons);
			return this;
		}

		public Builder setWednesdayLessons(List<Lesson> lessons) {
			this.lessons.put(WEDNESDAY, lessons);
			return this;
		}

		public Builder setThursdayLessons(List<Lesson> lessons) {
			this.lessons.put(THURSDAY, lessons);
			return this;
		}

		public Builder setFridayLessons(List<Lesson> lessons) {
			this.lessons.put(FRIDAY, lessons);
			return this;
		}

		public Builder setSaturdayLessons(List<Lesson> lessons) {
			this.lessons.put(SATURDAY, lessons);
			return this;
		}

		public WeekSchedule build() {
			if (lessons.indexOfKey(MONDAY) < 0) {
				lessons.put(MONDAY, Collections.<Lesson>emptyList());
			}
			if (lessons.indexOfKey(TUESDAY) < 0) {
				lessons.put(MONDAY, Collections.<Lesson>emptyList());
			}
			if (lessons.indexOfKey(WEDNESDAY) < 0) {
				lessons.put(MONDAY, Collections.<Lesson>emptyList());
			}
			if (lessons.indexOfKey(THURSDAY) < 0) {
				lessons.put(MONDAY, Collections.<Lesson>emptyList());
			}
			if (lessons.indexOfKey(FRIDAY) < 0) {
				lessons.put(MONDAY, Collections.<Lesson>emptyList());
			}
			if (lessons.indexOfKey(SATURDAY) < 0) {
				lessons.put(MONDAY, Collections.<Lesson>emptyList());
			}
			return new WeekSchedule(lessons);
		}
	}
}
