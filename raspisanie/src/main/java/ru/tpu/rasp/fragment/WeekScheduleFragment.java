package ru.tpu.rasp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import ru.tpu.rasp.R;
import ru.tpu.rasp.adapter.LessonsPagerAdapter;
import ru.tpu.rasp.data.Schedule;
import ru.tpu.rasp.data.WeekSchedule;
import ru.tpu.rasp.loader.ScheduleLoader;
import ru.tpu.rasp.provider.Result;
import ru.tpu.rasp.view.DefaultLoadingView;
import ru.tpu.rasp.view.LoadingView;

/**
 * Фрагмент для отображения расписания на неделю
 *
 * @author andrey.pogrebnoy
 */
public class WeekScheduleFragment extends Fragment implements LoaderManager.LoaderCallbacks<Result<Schedule>> {

	public static WeekScheduleFragment newInstance(String token, boolean isEven, boolean isBeforeBreak, int dayOfWeek) {
		WeekScheduleFragment weekScheduleFragment = new WeekScheduleFragment();
		Bundle bundle = new Bundle();
		bundle.putString("token", token);
		bundle.putBoolean("isEven", isEven);
		bundle.putBoolean("isBeforeBreak", isBeforeBreak);
		bundle.putInt("dayOfWeek", dayOfWeek);
		weekScheduleFragment.setArguments(bundle);
		return weekScheduleFragment;
	}

	private Context mContext;
	private LoadingView mLoadingView;
	private String mToken;
	private LessonsPagerAdapter mLessonsPagerAdapter;
	private boolean mIsBeforeBreak;
	private boolean mIsEven;
	private int mDayOfWeek;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mIsBeforeBreak = getArguments().getBoolean("isBeforeBreak");
		mIsEven = getArguments().getBoolean("isEven");
		mToken = getArguments().getString("token");
		mDayOfWeek = getArguments().getInt("dayOfWeek");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = inflater.getContext();
		View v = inflater.inflate(R.layout.fragment_schedule, container, false);
		assert v != null;

		PagerSlidingTabStrip weeksTabs = (PagerSlidingTabStrip) v.findViewById(R.id.weeks_tabs);
		ViewPager viewPager = (ViewPager) v.findViewById(R.id.lessons_pager);
		mLoadingView = (DefaultLoadingView) v.findViewById(R.id.loading_view);

		mLoadingView.setOnRetryListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				reload(mIsEven, mIsBeforeBreak);
			}
		});

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
			WeekSchedule weekSchedule;
			if (mIsEven){
				if (mIsBeforeBreak)
					weekSchedule = data.get().getWeek(Schedule.EVEN_BEFORE_BREAKING);
				else
					weekSchedule = data.get().getWeek(Schedule.EVEN_AFTER_BREAKING);
			} else {
				if (mIsBeforeBreak)
					weekSchedule = data.get().getWeek(Schedule.ODD_BEFORE_BREAKING);
				else
					weekSchedule = data.get().getWeek(Schedule.ODD_AFTER_BREAKING);
			}
			mLessonsPagerAdapter.setSchedule(weekSchedule);
		} catch (Exception e) {
			mLoadingView.showDefaultFail();
		}
	}

	@Override
	public void onLoaderReset(Loader<Result<Schedule>> loader) {
	}

	public void reload(boolean isEven, boolean isBeforeBreak) {
		mIsEven = isEven;
		mIsBeforeBreak = isBeforeBreak;
		getLoaderManager().restartLoader(0, null, this);
	}
}
