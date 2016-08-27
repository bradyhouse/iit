package com.iit.itm455.hw3.Quotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
	int[] quoteIds;
	Context context;
	int[] thumbnailIds;
	private static LayoutInflater inflater = null;

	public CustomAdapter(MainActivity mainActivity, int[] quoteList,
			int[] thumbnailList) {
		this.quoteIds = quoteList;
		this.context = mainActivity;
		this.thumbnailIds = thumbnailList;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return this.quoteIds.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class Holder {
		TextView quote;
		ImageView thumbnail;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.list_row, null);
		holder.quote = (TextView) rowView.findViewById(R.id.quote);
		holder.thumbnail = (ImageView) rowView.findViewById(R.id.Thumbnail);
		holder.quote.setText(this.quoteIds[position]);
		holder.thumbnail.setImageResource(this.thumbnailIds[position]);
		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				TextView quote = (TextView) view.findViewById(R.id.quote);
				Toast.makeText(context, quote.getText(),
						Toast.LENGTH_LONG).show();
			}
		});
		return rowView;
	}

}
