package hw6.itm455.iit.com.bookreviews;

import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlHelper db = new SqlHelper(this);
        /** CRUD Operations **/
        // add Books
        db.addBook(new Book("Professional Android 4 Application Development",
                "Reto Meier"));
        db.addBook(new Book("Beginning Android 4 Application Development", "WeiMeng Lee"));
                db.addBook(new Book("Programming Android", "Wallace Jackson"));
        db.addBook(new Book("Hello, Android", "Wallace Jackson"));

        // get all books
        List<Book> list = db.getAllBooks();

        // update one book
        int j = db.updateBook(list.get(0), "Hello, Android", "Ben Wallace");
        // delete one book
        db.deleteBook(list.get(0));
        // get all books
        db.getAllBooks();
        // get the book count
        db.getIds(list.get(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
