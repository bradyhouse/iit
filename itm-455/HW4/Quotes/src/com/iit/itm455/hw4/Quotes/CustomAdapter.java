package com.iit.itm455.hw4.Quotes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.iit.itm455.hw4.Quotes.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
	final List<Integer> quoteIds;
	Context context;
	final List<Integer> thumbnailIds;
	private static LayoutInflater inflater = null;

	public CustomAdapter(MainActivity mainActivity, List<Integer> quoteList,
			List<Integer> thumbnailList) {
		this.quoteIds = quoteList;
		this.context = mainActivity;
		this.thumbnailIds = thumbnailList;
		this.setInflater((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	@Override
	public int getCount() {
		return this.quoteIds.size();
	}

	@Override
	public Object getItem(int position) {
		Holder holder = new Holder();
		final View rowView = this.getInflater().inflate(R.layout.list_row, null);
		holder.quote = (TextView) rowView.findViewById(R.id.quote);
		holder.thumbnail = (ImageView) rowView.findViewById(R.id.Thumbnail);
		holder.quote.setText(this.quoteIds.get(position));
		holder.thumbnail.setImageResource(this.thumbnailIds.get(position));
		holder.thumbnail.setTag(this.thumbnailIds.get(position));
		return holder;
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
		final View rowView = this.getInflater().inflate(R.layout.list_row, null);
		holder.quote = (TextView) rowView.findViewById(R.id.quote);
		holder.thumbnail = (ImageView) rowView.findViewById(R.id.Thumbnail);
		holder.quote.setText(this.quoteIds.get(position));
		holder.thumbnail.setImageResource(this.thumbnailIds.get(position));
		holder.thumbnail.setTag(this.thumbnailIds.get(position));
		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				MainActivity activity = (MainActivity) context;
				CustomAdapter adapter = activity.getListAdapter();
				final Integer index = position;
				final String thumbnailId = Integer.toString(adapter.thumbnailIds.get(index));


				TextView quote = (TextView) view.findViewById(R.id.quote);
				ImageView thumbnail = (ImageView) view.findViewById(R.id.Thumbnail);
				Intent intent = new Intent(context, DetailActivity.class);
				intent.putExtra("quote", quote.getText());
				intent.putExtra("thumbnail", thumbnailId);
				context.startActivity(intent);
			}
		});
		rowView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				AlertDialog.Builder adb = new AlertDialog.Builder(
						context);
				adb.setTitle("Delete?");
				adb.setMessage("Are you sure you want to delete " + position);
				final Integer positionToRemove = position;
				adb.setNegativeButton("Cancel", null);
				adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						MainActivity activity = (MainActivity) context;
						CustomAdapter adapter = activity.getListAdapter();
						String currentQuote = activity.getResources().getString(adapter.quoteIds.get(positionToRemove));
						activity.buildList(currentQuote);
					}
				});
				adb.show();
				return false;
			}
		});

		return rowView;
	}


	/**
	 * @return the inflater
	 */
	public LayoutInflater getInflater() {
		return inflater;
	}

	/**
	 * @param inflater the inflater to set
	 */
	public void setInflater(LayoutInflater inflater) {
		CustomAdapter.inflater = inflater;
	}

}
