package ru.tpu.rasp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import ru.tpu.rasp.R;
import ru.tpu.rasp.adapters.LessonsPagerAdapter;

/**
 * Created by Andrey on 16.03.14.
 */
public class ScheduleFragment extends Fragment {
	public static ScheduleFragment newInstance(boolean isBroken, boolean isEven, int dayOfWeek) {
		ScheduleFragment scheduleFragment = new ScheduleFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean("isBroken", isBroken);
		bundle.putBoolean("isEven", isEven);
		bundle.putInt("dayOfWeek", dayOfWeek);
		scheduleFragment.setArguments(bundle);
		return scheduleFragment;
	}

	private Context context;
	private View throbber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = inflater.getContext();
		View v = inflater.inflate(R.layout.fragment_schedule, container, false);
		assert v != null;

		PagerSlidingTabStrip weeksTabs = (PagerSlidingTabStrip) v.findViewById(R.id.weeks_tabs);
		ViewPager viewPager = (ViewPager) v.findViewById(R.id.lessons_pager);
		throbber = v.findViewById(R.id.throbber);

		viewPager.setAdapter(new LessonsPagerAdapter(context));
		weeksTabs.setViewPager(viewPager);

		throbber.setVisibility(View.INVISIBLE);
		return v;
	}
}
