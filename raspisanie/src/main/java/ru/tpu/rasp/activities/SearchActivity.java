package ru.tpu.rasp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import ru.tpu.rasp.R;
import ru.tpu.rasp.adapters.SearchAdapter;
import ru.tpu.rasp.app.App;
import ru.tpu.rasp.loaders.SearchLoader;
import ru.tpu.rasp.providers.Result;

/**
 * Активити для поиска и выбора расписания
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
				startActivity(MainActivity.newIntent(SearchActivity.this));
			}
		});
		mToken.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				getSupportLoaderManager().restartLoader(0, null, SearchActivity.this);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLoaderReset(Loader<Result<String[]>> loader) {

	}
}
