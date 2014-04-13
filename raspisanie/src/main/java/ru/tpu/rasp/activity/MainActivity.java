package ru.tpu.rasp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import ru.tpu.rasp.R;
import ru.tpu.rasp.app.App;
import ru.tpu.rasp.fragment.NavigationDrawerFragment;
import ru.tpu.rasp.fragment.WeekScheduleFragment;


public class MainActivity extends ActionBarActivity
		implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		return intent;
	}

	private App mApp;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private String mScheduleToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApp = ((App) getApplication());

		mScheduleToken = mApp.getConfig().getScheduleToken();
		if (mScheduleToken == null) {
			startActivity(SearchActivity.newIntent(this));
			finish();
		}

		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment)
				getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, WeekScheduleFragment.newInstance(mScheduleToken, false, false, 0))
				.commit();
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mScheduleToken.substring(0, mScheduleToken.indexOf('/')));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_search) {
			startActivity(SearchActivity.newIntent(this));
			return true;
		}
		if (id == R.id.action_refresh) {
			getWeekScheduleFragment().reload();
		}
		return super.onOptionsItemSelected(item);
	}

	public WeekScheduleFragment getWeekScheduleFragment() {
		return (WeekScheduleFragment) getSupportFragmentManager().findFragmentById(R.id.container);
	}

}
