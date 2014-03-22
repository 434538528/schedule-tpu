package ru.tpu.rasp.data;

import android.util.SparseArray;

import java.util.Collections;
import java.util.List;

/**
 * Created by Andrey on 16.03.14.
 */
public class WeekSchedule<T extends Lesson> {

	private static final int MONDAY = 0;
	private static final int TUESDAY = 1;
	private static final int WEDNESDAY = 2;
	private static final int THURSDAY = 3;
	private static final int FRIDAY = 4;
	private static final int SATURDAY = 5;

	private final SparseArray<List<T>> lessons;

	public List<T> getMondayLessons() {
		return lessons.get(MONDAY);
	}

	public List<T> getTuesdayLessons() {
		return lessons.get(TUESDAY);
	}

	public List<T> getWednesdayLessons() {
		return lessons.get(WEDNESDAY);
	}

	public List<T> getThursdayLessons() {
		return lessons.get(THURSDAY);
	}

	public List<T> getFridayLessons() {
		return lessons.get(FRIDAY);
	}

	public List<T> getSaturdayLessons() {
		return lessons.get(SATURDAY);
	}

	private WeekSchedule(SparseArray<List<T>> lessons) {
		this.lessons = lessons;
	}

	public static class Builder<T extends Lesson> {
		private SparseArray<List<T>> lessons = new SparseArray<List<T>>(6);

		public Builder setMondayLessons(List<T> lessons) {
			this.lessons.put(MONDAY, lessons);
			return this;
		}

		public Builder setThuesdayLessons(List<T> lessons) {
			this.lessons.put(TUESDAY, lessons);
			return this;
		}

		public Builder setWednesdayLessons(List<T> lessons) {
			this.lessons.put(WEDNESDAY, lessons);
			return this;
		}

		public Builder setThursdayLessons(List<T> lessons) {
			this.lessons.put(THURSDAY, lessons);
			return this;
		}

		public Builder setFridayLessons(List<T> lessons) {
			this.lessons.put(FRIDAY, lessons);
			return this;
		}

		public Builder setSaturdayLessons(List<T> lessons) {
			this.lessons.put(SATURDAY, lessons);
			return this;
		}

		public WeekSchedule<T> build() {
			if (lessons.indexOfKey(MONDAY) < 0) {
				lessons.put(MONDAY, Collections.<T>emptyList());
			}
			if (lessons.indexOfKey(TUESDAY) < 0) {
				lessons.put(MONDAY, Collections.<T>emptyList());
			}
			if (lessons.indexOfKey(WEDNESDAY) < 0) {
				lessons.put(MONDAY, Collections.<T>emptyList());
			}
			if (lessons.indexOfKey(THURSDAY) < 0) {
				lessons.put(MONDAY, Collections.<T>emptyList());
			}
			if (lessons.indexOfKey(FRIDAY) < 0) {
				lessons.put(MONDAY, Collections.<T>emptyList());
			}
			if (lessons.indexOfKey(SATURDAY) < 0) {
				lessons.put(MONDAY, Collections.<T>emptyList());
			}
			return new WeekSchedule<T>(lessons);
		}
	}
}
