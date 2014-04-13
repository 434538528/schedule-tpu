package ru.tpu.rasp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ru.tpu.rasp.R;
import ru.tpu.rasp.data.WeekSchedule;

/**
 * Created by Andrey on 16.03.14.
 */
public class LessonsPagerAdapter extends PagerAdapter {

	private Context mContext;
	private String[] mDaysOfWeek;
	private WeekSchedule mWeekSchedule;
	private SparseArray<ListView> mLists = new SparseArray<ListView>();
	private SparseArray<LessonsAdapter> mAdapters = new SparseArray<LessonsAdapter>();

	public LessonsPagerAdapter(Context context) {
		mContext = context;
		mDaysOfWeek = context.getResources().getStringArray(R.array.daysOfWeek);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ListView view = new ListView(mContext);
		LessonsAdapter lessonsAdapter = new LessonsAdapter(mContext);
		if (mWeekSchedule != null){
			lessonsAdapter.setLessons(mWeekSchedule.getDayLessons(position));
		}
		view.setAdapter(lessonsAdapter);
		container.addView(view);
		mLists.put(position, view);
		mAdapters.put(position, lessonsAdapter);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		mLists.remove(position);
		mAdapters.remove(position);
	}

	@Override
	public int getCount() {
		return mDaysOfWeek.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mDaysOfWeek[position];
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void notifyDataSetChanged() {
		for (int i = 0; i < mAdapters.size(); i++) {
			int key = mAdapters.keyAt(i);
			mAdapters.get(key).setLessons(mWeekSchedule.getDayLessons(key));
		}
		super.notifyDataSetChanged();
	}

	public void setSchedule(WeekSchedule weekSchedule){
		mWeekSchedule = weekSchedule;
		notifyDataSetChanged();
	}
}
