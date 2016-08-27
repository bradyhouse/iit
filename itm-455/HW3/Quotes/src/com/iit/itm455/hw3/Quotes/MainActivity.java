package com.iit.itm455.hw3.Quotes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	ListView list;
	Context context;

	public static int[] thumbImages = { R.drawable.steve_1, R.drawable.steve_2,
			R.drawable.steve_3, R.drawable.steve_4, R.drawable.steve_5,
			R.drawable.steve_7, R.drawable.steve_8, R.drawable.steve_9,
			R.drawable.steve_10,
	};

	public static int[] quotes = { R.string.quote_1, R.string.quote_2,
			R.string.quote_3, R.string.quote_4, R.string.quote_5,
			R.string.quote_6, R.string.quote_7, R.string.quote_8,
			R.string.quote_9, R.string.quote_10 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		this.list = (ListView) findViewById(R.id.list);
		list.setAdapter(new CustomAdapter(this, this.quotes, this.thumbImages));
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
}
