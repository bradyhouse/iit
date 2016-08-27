package hw6.itm455.iit.com.bookreviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Set<String> _set;
    private SqlHelper _db;
    private boolean _blnFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        this._db = new SqlHelper(this);
        if (this._db.getAllBooks().size() == 0) {
            this._db.addBook(new Book("Hello, Android", "Ed Burnette", "1"));
            this._db.addBook(new Book("Professional Android 4 Application Development", "Reto Meier", "3"));
            this._db.addBook(new Book("Beginning Android 4 Application Development", "WeiMeng Lee", "4"));
            this._db.addBook(new Book("Programming Android", "Zigurd Mednieks", "1"));
        }
        List<Book> list = this._db.getAllBooks();
        ListView listContent = (ListView) findViewById(R.id.list);
        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner1);
        this._set = this._db.getTitle();
        List<String> blist = new ArrayList<String>(this._set);
        Collections.sort(blist, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
        blist.add(0, "Select title...");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, blist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setWillNotDraw(false);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {
        //  Auto-generated method stub
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (this._blnFlag) {
            if (position > 0) {
                String title = parent.getItemAtPosition(position).toString();
                Toast.makeText(this, "Author's Name :: " + this._db.getAuthor(title) + "\n" +
                                "Ratings :: " + this._db.getRating(title),
                        Toast.LENGTH_LONG).show();
            }
        }
        this._blnFlag = true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //  Auto-generated method stub
    }
}
