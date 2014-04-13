package ru.tpu.rasp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import ru.tpu.rasp.R;
import ru.tpu.rasp.adapters.LessonsPagerAdapter;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.loaders.ScheduleLoader;
import ru.tpu.rasp.providers.Result;

/**
 * Created by Andrey on 16.03.14.
 */
public class ScheduleFragment extends Fragment implements LoaderManager.LoaderCallbacks<Result<Schedule>> {
	public static ScheduleFragment newInstance(String token, boolean isBroken, boolean isEven, int dayOfWeek) {
		ScheduleFragment scheduleFragment = new ScheduleFragment();
		Bundle bundle = new Bundle();
		bundle.putString("token", token);
		bundle.putBoolean("isBroken", isBroken);
		bundle.putBoolean("isEven", isEven);
		bundle.putInt("dayOfWeek", dayOfWeek);
		scheduleFragment.setArguments(bundle);
		return scheduleFragment;
	}

	private Context mContext;
	private View mThrobber;
	private String mToken;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = inflater.getContext();
		mToken = getArguments().getString("token");
		View v = inflater.inflate(R.layout.fragment_schedule, container, false);
		assert v != null;

		PagerSlidingTabStrip weeksTabs = (PagerSlidingTabStrip) v.findViewById(R.id.weeks_tabs);
		ViewPager viewPager = (ViewPager) v.findViewById(R.id.lessons_pager);
		mThrobber = v.findViewById(R.id.throbber);

		viewPager.setAdapter(new LessonsPagerAdapter(mContext));
		weeksTabs.setViewPager(viewPager);

		mThrobber.setVisibility(View.INVISIBLE);
		getLoaderManager().initLoader(0, null, this);
		return v;
	}

	@Override
	public Loader<Result<Schedule>> onCreateLoader(int id, Bundle args) {
		return new ScheduleLoader(mContext, mToken);
	}

	@Override
	public void onLoadFinished(Loader<Result<Schedule>> loader, Result<Schedule> data) {
		try {
			Log.d("aaa", data.get().getType() + "");
		} catch (Exception e) {
			Log.e("aaa", "beda", e);
		}
	}

	@Override
	public void onLoaderReset(Loader<Result<Schedule>> loader) {

	}
}
