package ru.tpu.rasp.data;

import android.util.SparseArray;

/**
 * Полное расписание
 */
public class Schedule {

	public static final int EVEN_BEFORE_BREAKING = 0;
	public static final int ODD_BEFORE_BREAKING = 1;
	public static final int EVEN_AFTER_BREAKING = 2;
	public static final int ODD_AFTER_BREAKING = 3;

	private SparseArray<WeekSchedule> weeks;
	private final int type;

	private Schedule(int type, SparseArray<WeekSchedule> weeks) {
		this.type = type;
		this.weeks = weeks;
	}

	public int getType() {
		return type;
	}

	public WeekSchedule getWeek(int weekType) {
		return weeks.get(weekType);
	}

	public static class Builder {
		private final SparseArray<WeekSchedule> weeks = new SparseArray<WeekSchedule>(4);
		private final int type;

		public Builder(int type) {
			this.type = type;
		}

		public Builder setEvenWeekBeforeBreaking(WeekSchedule weekSchedule) {
			weeks.put(EVEN_BEFORE_BREAKING, weekSchedule);
			return this;
		}

		public Builder setOddWeekBeforeBreaking(WeekSchedule weekSchedule) {
			weeks.put(ODD_BEFORE_BREAKING, weekSchedule);
			return this;
		}

		public Builder setEvenWeekAfterBreaking(WeekSchedule weekSchedule) {
			weeks.put(EVEN_AFTER_BREAKING, weekSchedule);
			return this;
		}

		public Builder setOddWeekAfterBreaking(WeekSchedule weekSchedule) {
			weeks.put(ODD_AFTER_BREAKING, weekSchedule);
			return this;
		}

		public Schedule build() {
			if (weeks.indexOfKey(EVEN_BEFORE_BREAKING) < 0) {
				weeks.put(EVEN_BEFORE_BREAKING, new WeekSchedule.Builder().build());
			}
			if (weeks.indexOfKey(ODD_BEFORE_BREAKING) < 0) {
				weeks.put(ODD_BEFORE_BREAKING, new WeekSchedule.Builder().build());
			}
			if (weeks.indexOfKey(EVEN_AFTER_BREAKING) < 0) {
				weeks.put(EVEN_AFTER_BREAKING, new WeekSchedule.Builder().build());
			}
			if (weeks.indexOfKey(ODD_AFTER_BREAKING) < 0) {
				weeks.put(ODD_AFTER_BREAKING, new WeekSchedule.Builder().build());
			}
			return new Schedule(type, weeks);
		}
	}
}
