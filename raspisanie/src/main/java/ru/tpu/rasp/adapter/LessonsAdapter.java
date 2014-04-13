package ru.tpu.rasp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.tpu.rasp.R;
import ru.tpu.rasp.data.Lesson;

/**
 * Адаптер для списка занятий
 */
public class LessonsAdapter extends BaseAdapter {

	private List<Lesson> mLessons;
	private LayoutInflater mInflater;

	public LessonsAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mLessons == null ? 0 : mLessons.size();
	}

	@Override
	public Lesson getItem(int position) {
		return mLessons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_lesson, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Lesson lesson = mLessons.get(position);
		holder.startTime.setText(lesson.startTime);
		holder.title.setText(lesson.subject);
		holder.type.setText(lesson.type);
		holder.subtitle1.setText(lesson.getFirstSubtitle());
		holder.subtitle2.setText(lesson.getSecondSubtitle());
		return convertView;
	}

	public void setLessons(List<Lesson> lessons){
		mLessons = lessons;
		notifyDataSetChanged();
	}

	private class ViewHolder {
		TextView startTime;
		TextView title;
		TextView type;
		TextView subtitle1;
		TextView subtitle2;

		public ViewHolder(View v) {
			startTime = (TextView) v.findViewById(R.id.lesson_start_time);
			title = (TextView) v.findViewById(R.id.lesson_title);
			type = (TextView) v.findViewById(R.id.lesson_type);
			subtitle1 = (TextView) v.findViewById(R.id.lesson_subtitle_1);
			subtitle2 = (TextView) v.findViewById(R.id.lesson_subtitle_2);
		}
	}
}
