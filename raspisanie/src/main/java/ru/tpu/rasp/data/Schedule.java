package ru.tpu.rasp.data;

import android.util.SparseArray;

import java.io.Serializable;

/**
 * Полное расписание
 */
public class Schedule implements Serializable {

	public static final int EVEN_BEFORE_BREAKING = 0;
	public static final int ODD_BEFORE_BREAKING = 1;
	public static final int EVEN_AFTER_BREAKING = 2;
	public static final int ODD_AFTER_BREAKING = 3;

	private WeekSchedule[] weeks;
	private final int type;

	private Schedule(int type, WeekSchedule[] weeks) {
		this.type = type;
		this.weeks = weeks;
	}

	public int getType() {
		return type;
	}

	public WeekSchedule getWeek(int weekType) {
		return weeks[weekType];
	}

	public static class Builder {
		private final WeekSchedule[] weeks = new WeekSchedule[4];
		private final int type;

		public Builder(int type) {
			this.type = type;
		}

		public Builder setEvenWeekBeforeBreaking(WeekSchedule weekSchedule) {
			weeks[EVEN_BEFORE_BREAKING] = weekSchedule;
			return this;
		}

		public Builder setOddWeekBeforeBreaking(WeekSchedule weekSchedule) {
			weeks[ODD_BEFORE_BREAKING] = weekSchedule;
			return this;
		}

		public Builder setEvenWeekAfterBreaking(WeekSchedule weekSchedule) {
			weeks[EVEN_AFTER_BREAKING] = weekSchedule;
			return this;
		}

		public Builder setOddWeekAfterBreaking(WeekSchedule weekSchedule) {
			weeks[ODD_AFTER_BREAKING] = weekSchedule;
			return this;
		}

		public Schedule build() {
			if (weeks[EVEN_BEFORE_BREAKING] == null){
				weeks[EVEN_BEFORE_BREAKING] = new WeekSchedule.Builder().build();
			}
			if (weeks[ODD_BEFORE_BREAKING] == null){
				weeks[ODD_BEFORE_BREAKING] = new WeekSchedule.Builder().build();
			}
			if (weeks[EVEN_AFTER_BREAKING] == null){
				weeks[EVEN_AFTER_BREAKING] = new WeekSchedule.Builder().build();
			}
			if (weeks[ODD_AFTER_BREAKING] == null){
				weeks[ODD_AFTER_BREAKING] = new WeekSchedule.Builder().build();
			}
			return new Schedule(type, weeks);
		}
	}
}
