package ru.tpu.rasp.data;

import android.util.SparseArray;

/**
 * Created by Andrey on 16.03.14.
 */
public class Schedule<T extends Lesson> {

	private static final int EVEN_BEFORE_BREAKING = 0;
	private static final int ODD_BEFORE_BREAKING = 1;
	private static final int EVEN_AFTER_BREAKING = 2;
	private static final int ODD_AFTER_BREAKING = 3;

	private SparseArray<WeekSchedule<T>> weeks;

	private Schedule(SparseArray<WeekSchedule<T>> weeks) {
		this.weeks = weeks;
	}

	public static class Builder<T extends Lesson> {
		private SparseArray<WeekSchedule<T>> weeks = new SparseArray<WeekSchedule<T>>(4);

		public Builder setEvenWeekBeforeBreaking(WeekSchedule<T> weekSchedule) {
			weeks.put(EVEN_BEFORE_BREAKING, weekSchedule);
			return this;
		}

		public Builder setOddWeekBeforeBreaking(WeekSchedule<T> weekSchedule) {
			weeks.put(ODD_BEFORE_BREAKING, weekSchedule);
			return this;
		}

		public Builder setEvenWeekAfterBreaking(WeekSchedule<T> weekSchedule) {
			weeks.put(EVEN_AFTER_BREAKING, weekSchedule);
			return this;
		}

		public Builder setOddWeekAfterBreaking(WeekSchedule<T> weekSchedule) {
			weeks.put(ODD_AFTER_BREAKING, weekSchedule);
			return this;
		}

		public Schedule<T> build() {
			if (weeks.indexOfKey(EVEN_BEFORE_BREAKING) < 0) {
				weeks.put(EVEN_BEFORE_BREAKING, new WeekSchedule.Builder<T>().build());
			}
			if (weeks.indexOfKey(ODD_BEFORE_BREAKING) < 0) {
				weeks.put(ODD_BEFORE_BREAKING, new WeekSchedule.Builder<T>().build());
			}
			if (weeks.indexOfKey(EVEN_AFTER_BREAKING) < 0) {
				weeks.put(EVEN_AFTER_BREAKING, new WeekSchedule.Builder<T>().build());
			}
			if (weeks.indexOfKey(ODD_AFTER_BREAKING) < 0) {
				weeks.put(ODD_AFTER_BREAKING, new WeekSchedule.Builder<T>().build());
			}
			return new Schedule<T>(weeks);
		}
	}
}
