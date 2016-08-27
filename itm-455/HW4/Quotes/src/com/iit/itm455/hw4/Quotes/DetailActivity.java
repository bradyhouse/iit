package com.iit.itm455.hw4.Quotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Intent intent = getIntent();
		String quote = intent.getStringExtra("quote");
		Integer thumbnailId = Integer.parseInt(intent.getStringExtra("thumbnail"));
		this.setQuote(quote);
		this.setImage(thumbnailId);
		setResult(Activity.RESULT_OK);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void setQuote(String quote) {
		TextView labelQuote = (TextView) findViewById(R.id.labelQuote);
		labelQuote.setText(quote);
	}

	public void setImage(Integer id) {
		ImageView imageView = (ImageView) findViewById(R.id.Thumbnail);
		imageView.setImageResource(id);
	}

	
	public void onBackClick(View view) {
		this.finish();
	}
	
}
