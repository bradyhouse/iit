package com.iit.itm455.hw4.Quotes;


import com.iit.itm455.hw4.Quotes.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {

    ListView list;
    Context context;
    CustomAdapter listAdapter;
    List<Integer> quoteList;
    List<Integer> imageList;
    List<String> skipList;


    public Integer[] thumbImages = {R.drawable.steve_1, R.drawable.steve_2,
            R.drawable.steve_3, R.drawable.steve_4, R.drawable.steve_5,
            R.drawable.steve_6,
            R.drawable.steve_7, R.drawable.steve_8, R.drawable.steve_9,
            R.drawable.steve_10,
    };

    public Integer[] quotes = {R.string.quote_1, R.string.quote_2,
            R.string.quote_3, R.string.quote_4, R.string.quote_5,
            R.string.quote_6, R.string.quote_7, R.string.quote_8,
            R.string.quote_9, R.string.quote_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        this.skipList = new ArrayList<String>();
        this.buildList("empty string");
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

    public void displayDetails(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
    }

    public void buildList(String quote) {

        if (!this.skipList.contains(quote)) {
            this.skipList.add(quote);
        }

        String currentQuote = getResources().getString(R.string.quote_1);

        this.quoteList = new ArrayList<Integer>();
        this.imageList = new ArrayList<Integer>();

        for (int i = 0; i <= quotes.length - 1; i++) {

            currentQuote = getResources().getString(quotes[i]);

            if (!this.skipList.contains(currentQuote)) {
                this.quoteList.add(quotes[i]);
            }
        }

        for (int y = 0; y <= thumbImages.length - 1; y++) {

            currentQuote = getResources().getString(quotes[y]);

            if (!this.skipList.contains(currentQuote)) {
                this.imageList.add(thumbImages[y]);
            }
        }

        this.listAdapter = new CustomAdapter(this, this.quoteList, this.imageList);
        this.list = (ListView) findViewById(R.id.list);
        list.setAdapter(this.listAdapter);
    }


    public CustomAdapter getListAdapter() {
        return this.listAdapter;
    }


}
