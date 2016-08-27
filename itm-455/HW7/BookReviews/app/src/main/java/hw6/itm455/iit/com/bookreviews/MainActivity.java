package hw6.itm455.iit.com.bookreviews;

import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlHelper db = new SqlHelper(this);
        if (db.getAllBooks().size() == 0) {
            db.addBook(new Book("Hello, Android", "Ed Burnette", "1"));
            db.addBook(new Book("Professional Android 4 Application Development", "Reto Meier", "3"));
            db.addBook(new Book("Beginning Android 4 Application Development", "WeiMeng Lee", "4"));
            db.addBook(new Book("Programming Android", "Zigurd Mednieks", "1"));
        }
        List<Book> list = db.getAllBooks();
        ListView listContent = (ListView) findViewById(R.id.list);
        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlistrow, list );
        listContent.setAdapter(customAdapter);
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
}
