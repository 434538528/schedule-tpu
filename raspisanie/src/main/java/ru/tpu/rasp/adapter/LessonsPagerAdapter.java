package ru.tpu.rasp.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ru.tpu.rasp.R;
import ru.tpu.rasp.data.WeekSchedule;

/**
 * @author andrey.pogrebnoy
 */
public class LessonsPagerAdapter extends PagerAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private String[] mDaysOfWeek;
	private WeekSchedule mWeekSchedule;
	private SparseArray<ListView> mLists = new SparseArray<ListView>();
	private SparseArray<LessonsAdapter> mAdapters = new SparseArray<LessonsAdapter>();

	public LessonsPagerAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDaysOfWeek = context.getResources().getStringArray(R.array.days_of_week);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = mInflater.inflate(R.layout.item_lessons_list, container, false);
		ListView listView = (ListView) v.findViewById(android.R.id.list);
		View emptyView = v.findViewById(android.R.id.empty);
		LessonsAdapter lessonsAdapter = new LessonsAdapter(mContext);

		listView.setEmptyView(emptyView);
		listView.setAdapter(lessonsAdapter);
		if (mWeekSchedule != null){
			lessonsAdapter.setLessons(mWeekSchedule.getDayLessons(position));
		}
		container.addView(v);
		mLists.put(position, listView);
		mAdapters.put(position, lessonsAdapter);
		return v;
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
