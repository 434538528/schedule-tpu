package ru.tpu.rasp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ru.tpu.rasp.R;

/**
 * @author andrey.pogrebnoy
 */
public class SearchAdapter extends BaseAdapter {
	private String[] mTokens;
	private LayoutInflater mInflater;

	public SearchAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mTokens == null ? 0 : mTokens.length;
	}

	@Override
	public String getItem(int position) {
		return mTokens[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_search, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tokenText.setText(mTokens[position]);
		return convertView;
	}

	public void setTokens(String[] tokens) {
		mTokens = tokens;
		notifyDataSetChanged();
	}

	private class ViewHolder {
		TextView tokenText;

		ViewHolder(View v) {
			tokenText = (TextView) v.findViewById(R.id.token_text);
		}
	}
}
