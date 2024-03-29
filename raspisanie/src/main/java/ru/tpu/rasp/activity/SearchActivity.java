package ru.tpu.rasp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import ru.tpu.rasp.R;
import ru.tpu.rasp.adapter.SearchAdapter;
import ru.tpu.rasp.app.App;
import ru.tpu.rasp.loader.SearchLoader;
import ru.tpu.rasp.provider.OkResult;
import ru.tpu.rasp.provider.Result;

/**
 * Активити для поиска и выбора расписания
 *
 * @author andrey.pogrebnoy
 */
public class SearchActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Result<String[]>> {
	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, SearchActivity.class);
		return intent;
	}

	private EditText mToken;
	private SearchAdapter mAdapter;
	private App mApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		mToken = (EditText) findViewById(R.id.token_edit);
		ListView listView = (ListView) findViewById(android.R.id.list);
		mApp = (App) getApplication();

		mAdapter = new SearchAdapter(getBaseContext());
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mApp.getConfig().setScheduleToken(mAdapter.getItem(position));
				Intent intent = MainActivity.newIntent(SearchActivity.this, true);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		mToken.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				getSupportLoaderManager().restartLoader(0, null, SearchActivity.this).forceLoad();
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	public Loader<Result<String[]>> onCreateLoader(int id, Bundle args) {
		return new SearchLoader(this, mToken.getText().toString());
	}

	@Override
	public void onLoadFinished(Loader<Result<String[]>> loader, Result<String[]> data) {
		try {
			mAdapter.setTokens(data.get());
		} catch (Exception ignored) {
		}
	}

	@Override
	public void onLoaderReset(Loader<Result<String[]>> loader) {

	}
}
