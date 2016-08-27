package com.iit.itm455.hw1.tipCalc;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private double mealCost;
	private double totalCost;
	private double taxPercentage = 0.0;
	private double tipPercentage = 0.0;
	
	public void calculateTotalCost() {
		if (this.getMealCost() > 0 && this.getTaxPercentage() >= 0
				&& this.getTipPercentage() >= 0) {
			double tax = (this.getTaxPercentage() / 100) * this.getMealCost();
			double tip = (this.getTipPercentage() / 100) * this.getMealCost();
			double totalCost = tax + tip + this.getMealCost();
			EditText editTotalCost = (EditText) findViewById(R.id.editTotalMealCost);
			editTotalCost.setText(String.format("%.2f", totalCost));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonCalc = (Button) findViewById(R.id.buttonCalculate);
		buttonCalc.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getTipPercentage() {
		return tipPercentage;
	}

	public void setTipPercentage(double tipPercentage) {
		this.tipPercentage = tipPercentage;
	}

	public double getMealCost() {
		return mealCost;
	}

	public void setMealCost(double mealCost) {
		this.mealCost = mealCost;
	}

	@Override
	public void onClick(View v) {
		EditText editCost = (EditText) findViewById(R.id.editMealCost);
		EditText editTip = (EditText) findViewById(R.id.editTipPercentage);
		EditText editTax = (EditText) findViewById(R.id.editTaxPercentage);

		double tip = editTip.getText().length() > 0 ? Double
				.parseDouble(editTip.getText().toString()) : 0.0;
		double tax = editTax.getText().length() > 0 ? Double
				.parseDouble(editTax.getText().toString()) : 0.0;

		if (editCost.getText().length() > 0) {
			this.setMealCost(Double.parseDouble(editCost.getText().toString()));
			this.setTipPercentage(tip);
			this.setTaxPercentage(tax);
			this.calculateTotalCost();
		}
	}
}
