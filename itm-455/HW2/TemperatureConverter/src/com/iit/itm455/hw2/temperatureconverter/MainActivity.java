package com.iit.itm455.hw2.temperatureconverter;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	public SeekBar seekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBar = (SeekBar) findViewById(R.id.seekBarTemperatureRange);
		TextView statusMessage = (TextView) findViewById(R.id.labelStatusMessage);
		statusMessage.setBackgroundResource(R.color.blue);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int factor = 0;
			float fahrenheit = 0.0f;
			float celsius = 0.0f;
			String status = "Too cold!";
			int statusDrawableId = 0;
			int statusColorId = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				this.factor = progress;
				calculateFahrenheit();
				calculateCelsius();
				calculateStatus();
				bindView();
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}
			private void calculateFahrenheit() {
				float multiplier = 2f;
				this.fahrenheit = factor * multiplier;
			}
			private void calculateCelsius() {
				this.celsius = (5f / 9f) * (this.fahrenheit - 32f);
			}
			private void calculateStatus() {
				if (fahrenheit <= 40.0f) {
					this.status = "Too cold!";
					this.statusColorId = R.color.blue;
					this.statusDrawableId = R.drawable.too_cold;
				} else if (this.fahrenheit > 40.0f && this.fahrenheit < 90.0f) {
					this.status = "Just Right!";
					this.statusColorId = R.color.green;
					this.statusDrawableId = R.drawable.just_right;
				} else if (this.fahrenheit >= 90.0f) {
					this.status = "Too Hot!";
					this.statusColorId = R.color.red;
					this.statusDrawableId = R.drawable.too_hot;
				}
			}
			private void bindView() {
				//super.editCelsius.setText(String.format("%.2f", this.celsius));
				TextView editCelsius = (TextView) findViewById(R.id.editTemperatureCelsius);
				TextView editFahrenheit = (TextView) findViewById(R.id.editTemperatureFahrenheit);
				ImageView imageViewStatus = (ImageView) findViewById(R.id.imageViewStatus);
				TextView statusMessage = (TextView) findViewById(R.id.labelStatusMessage);
				editCelsius.setText(String.format("%.2f", this.celsius));
				editFahrenheit.setText(String.format("%.2f", this.fahrenheit));
				statusMessage.setText(this.status);
				statusMessage.setBackgroundResource(this.statusColorId);
				imageViewStatus.setImageResource(this.statusDrawableId);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
