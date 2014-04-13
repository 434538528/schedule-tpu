package ru.tpu.rasp.fragment;

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
import ru.tpu.rasp.adapter.LessonsPagerAdapter;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.loader.ScheduleLoader;
import ru.tpu.rasp.provider.Result;
import ru.tpu.rasp.view.LoadingView;

/**
 * Фрагмент для отображения расписания на неделю
 */
public class WeekScheduleFragment extends Fragment implements LoaderManager.LoaderCallbacks<Result<Schedule>> {
	private static final String TAG = WeekScheduleFragment.class.getSimpleName();

	public static WeekScheduleFragment newInstance(String token, boolean isBroken, boolean isEven, int dayOfWeek) {
		WeekScheduleFragment weekScheduleFragment = new WeekScheduleFragment();
		Bundle bundle = new Bundle();
		bundle.putString("token", token);
		bundle.putBoolean("isBroken", isBroken);
		bundle.putBoolean("isEven", isEven);
		bundle.putInt("dayOfWeek", dayOfWeek);
		weekScheduleFragment.setArguments(bundle);
		return weekScheduleFragment;
	}

	private Context mContext;
	private LoadingView mLoadingView;
	private String mToken;
	private LessonsPagerAdapter mLessonsPagerAdapter;

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
		mLoadingView = (LoadingView) v.findViewById(R.id.loading_view);

		mLessonsPagerAdapter = new LessonsPagerAdapter(mContext);
		viewPager.setAdapter(mLessonsPagerAdapter);
		weeksTabs.setViewPager(viewPager);

		getLoaderManager().initLoader(0, null, this);
		return v;
	}

	@Override
	public Loader<Result<Schedule>> onCreateLoader(int id, Bundle args) {
		mLoadingView.showLoading();
		return new ScheduleLoader(mContext, mToken);
	}

	@Override
	public void onLoadFinished(Loader<Result<Schedule>> loader, Result<Schedule> data) {
		try {
			mLoadingView.showLoaded();
			mLessonsPagerAdapter.setSchedule(data.get().getWeek(Schedule.EVEN_AFTER_BREAKING));
		} catch (Exception e) {
			Log.e(TAG, "не удалось загрузить расписание:", e);
			mLoadingView.showDefaultFail();
		}
	}

	@Override
	public void onLoaderReset(Loader<Result<Schedule>> loader) {

	}
}
