package ru.tpu.rasp.view;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.tpu.rasp.R;

/**
 * View для отображения статуса загрузки данных
 */
public class DefaultLoadingView extends RelativeLayout implements LoadingView {
	private ProgressBar loadingView;
	private View errorView;
	private TextView errorText;
	private TextView retryButton;

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
		loadingView = new ProgressBar(getContext());
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(CENTER_IN_PARENT, TRUE);
		loadingView.setLayoutParams(layoutParams);
		loadingView.setVisibility(View.INVISIBLE);
		addView(loadingView);

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		errorView = inflater.inflate(R.layout.view_loading_error, this, false);
		retryButton = (TextView) errorView.findViewById(R.id.loading_retry_button);
		errorText = (TextView) errorView.findViewById(R.id.loading_error_text);
		errorView.setVisibility(View.INVISIBLE);
		addView(errorView);
		setClickable(true);

		setBackgroundColor(Color.WHITE);
	}

	@Override
	public void showLoading(){
		setVisibility(View.VISIBLE);
		errorView.setVisibility(View.INVISIBLE);
		loadingView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showDefaultFail(){
		showFailed(R.string.error_default);
	}

	@Override
	public void showFailed(int messageId){
		setVisibility(View.VISIBLE);
		errorText.setText(messageId);
		errorView.setVisibility(View.VISIBLE);
		loadingView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void showLoaded(){
		setVisibility(View.GONE);
	}

	@Override
	public void setOnRetryListener(OnClickListener onClickListener) {
		retryButton.setOnClickListener(onClickListener);
	}
}
