package ru.tpu.rasp.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import ru.tpu.rasp.R;
import ru.tpu.rasp.app.App;
import ru.tpu.rasp.app.Config;

/**
 * Активити с slide-menu. При добавлении основного лэйаута нужно использовать setDrawerView вместо setContentView
 *
 * @author andrey.pogrebnoy
 */
public abstract class BaseNavigationActivity extends ActionBarActivity {

	public static final int EVEN = 0;
	public static final int ODD = 1;
	public static final int BEFORE_BREAK = 2;
	public static final int AFTER_BREAK = 3;

	private App mApp;
	private Config mConfig;

	private CharSequence mTitle;
	private String mAppTitle;

	private DrawerLayout mDrawerLayout;
	private FrameLayout mDrawerContent;
	private View mNavigationView;
	private TextView mNavigateToEven;
	private TextView mNavigateToOdd;
	private TextView mNavigateToBeforeBreak;
	private TextView mNavigateToAfterBreak;

	private boolean mIsEven;
	private boolean mIsBeforeBreak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApp = ((App) getApplication());
		mConfig = mApp.getConfig();

		mTitle = mConfig.getScheduleToken();
		if (mTitle == null) {
			startActivity(SearchActivity.newIntent(this));
			finish();
		}

		setContentView(R.layout.activity_navigation_base);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mNavigationView = findViewById(R.id.left_drawer);
		mDrawerContent = (FrameLayout) findViewById(R.id.container);
		mNavigateToEven = (TextView) findViewById(R.id.navigate_to_even);
		mNavigateToOdd = (TextView) findViewById(R.id.navigate_to_odd);
		mNavigateToBeforeBreak = (TextView) findViewById(R.id.navigate_to_before_break);
		mNavigateToAfterBreak = (TextView) findViewById(R.id.navigate_to_after_break);

		mTitle = getTitle();
		mAppTitle = getResources().getString(R.string.app_name);

		mNavigateToEven.setOnClickListener(new OnNavItemClickListener());
		mNavigateToOdd.setOnClickListener(new OnNavItemClickListener());
		mNavigateToBeforeBreak.setOnClickListener(new OnNavItemClickListener());
		mNavigateToAfterBreak.setOnClickListener(new OnNavItemClickListener());

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
			@Override
			public void onDrawerSlide(View view, float v) {
			}

			@Override
			public void onDrawerOpened(View view) {
				getSupportActionBar().setTitle(mAppTitle);
				supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerStateChanged(int newState) {

			}
		});


	}

	private class OnNavItemClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.navigate_to_even: {
					navigateTo(EVEN);
					closeDrawer();
					break;
				}
				case R.id.navigate_to_odd: {
					navigateTo(ODD);
					closeDrawer();
					break;
				}
				case R.id.navigate_to_after_break: {
					navigateTo(AFTER_BREAK);
					closeDrawer();
					break;
				}
				case R.id.navigate_to_before_break:
					navigateTo(BEFORE_BREAK);
					closeDrawer();
					break;
			}
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		this.mTitle = title;
		getSupportActionBar().setTitle(title);
	}

	public abstract void navigateTo(int index);

	public void selectItem(int index) {
		if (index == EVEN)
			mIsEven = true;
		else if (index==ODD)
			mIsEven = false;
		if (index == BEFORE_BREAK)
			mIsBeforeBreak = true;
		else if (index == AFTER_BREAK)
			mIsBeforeBreak = false;
		mNavigateToEven.setSelected(mIsEven);
		mNavigateToOdd.setSelected(!mIsEven);
		mNavigateToBeforeBreak.setSelected(mIsBeforeBreak);
		mNavigateToAfterBreak.setSelected(!mIsBeforeBreak);
	}

	public void setDrawerView(int layoutId) {
		mDrawerContent.addView(getLayoutInflater().inflate(layoutId, null));
	}

	public FrameLayout getDrawerContent() {
		return mDrawerContent;
	}

	public DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	public String getAppTitle() {
		return mAppTitle;
	}

	public Config getConfig(){
		return mConfig;
	}

	public void openDrawer() {
		mDrawerLayout.openDrawer(mNavigationView);
	}

	public void closeDrawer() {
		mDrawerLayout.closeDrawer(mNavigationView);
	}

	public boolean isDrawerOpen(){
		return mDrawerLayout.isDrawerOpen(mNavigationView);
	}

	public boolean isEven(){
		return mIsEven;
	}

	public boolean isBeforeBreak(){
		return mIsBeforeBreak;
	}
}
