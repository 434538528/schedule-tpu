package ru.tpu.rasp.data;

import java.io.Serializable;

/**
 * Расписание на неделю
 *
 * @author andrey.pogrebnoy
 */
public class WeekSchedule implements Serializable {

	public static final int MONDAY = 0;
	public static final int TUESDAY = 1;
	public static final int WEDNESDAY = 2;
	public static final int THURSDAY = 3;
	public static final int FRIDAY = 4;
	public static final int SATURDAY = 5;

	private final Lesson[][] mLessons;

	public Lesson[] getDayLessons(int day) {
		return mLessons[day];
	}

	private WeekSchedule(Lesson[][] lessons) {
		mLessons = lessons;
	}

	public static class Builder {
		private Lesson[][] lessons = new Lesson[6][];

		public Builder setMondayLessons(Lesson[] lessons) {
			this.lessons[MONDAY] = lessons;
			return this;
		}

		public Builder setTuesdayLessons(Lesson[] lessons) {
			this.lessons[TUESDAY] = lessons;
			return this;
		}

		public Builder setWednesdayLessons(Lesson[] lessons) {
			this.lessons[WEDNESDAY] = lessons;
			return this;
		}

		public Builder setThursdayLessons(Lesson[] lessons) {
			this.lessons[THURSDAY] = lessons;
			return this;
		}

		public Builder setFridayLessons(Lesson[] lessons) {
			this.lessons[FRIDAY] = lessons;
			return this;
		}

		public Builder setSaturdayLessons(Lesson[] lessons) {
			this.lessons[SATURDAY] = lessons;
			return this;
		}

		public WeekSchedule build() {
			if (lessons[MONDAY] == null) {
				lessons[MONDAY] = new Lesson[0];
			}
			if (lessons[TUESDAY] == null) {
				lessons[TUESDAY] = new Lesson[0];
			}
			if (lessons[WEDNESDAY] == null) {
				lessons[WEDNESDAY] = new Lesson[0];
			}
			if (lessons[THURSDAY] == null) {
				lessons[THURSDAY] = new Lesson[0];
			}
			if (lessons[FRIDAY] == null) {
				lessons[FRIDAY] = new Lesson[0];
			}
			if (lessons[SATURDAY] == null) {
				lessons[SATURDAY] = new Lesson[0];
			}
			return new WeekSchedule(lessons);
		}
	}
}
