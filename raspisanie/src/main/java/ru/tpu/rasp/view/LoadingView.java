package ru.tpu.rasp.view;

import android.view.View;

import ru.tpu.rasp.R;

/**
 * @author andrey.pogrebnoy
 */
public interface LoadingView {
	void showLoading();

	public void showDefaultFail();

	public void showFailed(int messageId);

	public void showLoaded();

	public void setOnRetryListener(View.OnClickListener onClickListener);
}
