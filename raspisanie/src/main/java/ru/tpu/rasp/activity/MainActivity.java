package ru.tpu.rasp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.tpu.rasp.R;
import ru.tpu.rasp.fragment.WeekScheduleFragment;


public class MainActivity extends BaseNavigationActivity {

	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		return intent;
	}

	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this,
				getDrawerLayout(),
				R.drawable.ic_drawer,
				R.string.navigation_drawer_open,
				R.string.navigation_drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(getConfig().getScheduleToken());
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(getAppTitle());
				supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				super.onDrawerStateChanged(newState);
			}
		};
		getDrawerLayout().setDrawerListener(mDrawerToggle);
		selectItem(getConfig().isEven() ? EVEN : ODD);
		selectItem(getConfig().isBeforeBreak() ? BEFORE_BREAK : AFTER_BREAK);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, WeekScheduleFragment.newInstance(getConfig().getScheduleToken(), isEven(), isBeforeBreak(), 0))
				.commit();
	}

	@Override
	public void navigateTo(int index) {
		selectItem(index);
		getWeekScheduleFragment().reload(isEven(), isBeforeBreak());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		int id = item.getItemId();
		if (id == R.id.action_search) {
			startActivity(SearchActivity.newIntent(this));
			return true;
		}
		if (id == R.id.action_refresh) {
			getWeekScheduleFragment().reload(isEven(), isBeforeBreak());
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public WeekScheduleFragment getWeekScheduleFragment() {
		return (WeekScheduleFragment) getSupportFragmentManager().findFragmentById(R.id.container);
	}

}
