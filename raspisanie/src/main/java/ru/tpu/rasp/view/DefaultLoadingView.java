package ru.tpu.rasp.view;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.tpu.rasp.R;

/**
 * View для отображения статуса загрузки данных
 *
 * @author andrey.pogrebnoy
 */
public class DefaultLoadingView extends RelativeLayout implements LoadingView {
	private ProgressBar mProgressBarView;
	private View mErrorView;
	private TextView mErrorText;
	private TextView mRetryButton;

	public DefaultLoadingView(Context context) {
		super(context);
		init();
	}

	public DefaultLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DefaultLoadingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mProgressBarView = new ProgressBar(getContext());
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(CENTER_IN_PARENT, TRUE);
		mProgressBarView.setLayoutParams(layoutParams);
		mProgressBarView.setVisibility(View.INVISIBLE);
		addView(mProgressBarView);

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		mErrorView = inflater.inflate(R.layout.view_loading_error, this, false);
		mRetryButton = (TextView) mErrorView.findViewById(R.id.loading_retry_button);
		mErrorText = (TextView) mErrorView.findViewById(R.id.loading_error_text);
		mErrorView.setVisibility(View.INVISIBLE);
		addView(mErrorView);
		setClickable(true);

		setBackgroundColor(Color.WHITE);
	}

	@Override
	public void showLoading(){
		setVisibility(View.VISIBLE);
		mErrorView.setVisibility(View.INVISIBLE);
		mProgressBarView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showDefaultFail(){
		showFailed(R.string.error_default);
	}

	@Override
	public void showFailed(int messageId){
		setVisibility(View.VISIBLE);
		mErrorText.setText(messageId);
		mErrorView.setVisibility(View.VISIBLE);
		mProgressBarView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void showLoaded(){
		setVisibility(View.GONE);
	}

	@Override
	public void setOnRetryListener(OnClickListener onClickListener) {
		mRetryButton.setOnClickListener(onClickListener);
	}
}
