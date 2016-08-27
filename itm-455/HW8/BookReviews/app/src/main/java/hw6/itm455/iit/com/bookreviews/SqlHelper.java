package hw6.itm455.iit.com.bookreviews;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlHelper extends SQLiteOpenHelper {
    private static final String LOG_PREFIX = "BR-";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookDB";
    private static final String TABLE_BOOKS = "books";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_RATING = "rating";

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "author TEXT, " +
                "rating TEXT)";
        db.execSQL(CREATE_BOOK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        this.onCreate(db);
    }

    public void addBook(Book book) {
        Log.d(this.LOG_PREFIX + "addBook", book.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_RATING, book.getRating());
        db.insert(TABLE_BOOKS,
                null,
                values);
        db.close();
    }

    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();
        String query = "SELECT * FROM " + TABLE_BOOKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setRating(cursor.getString(3));
                books.add(book);
            } while (cursor.moveToNext());
        }
        Log.d(this.LOG_PREFIX + "getAllBooks()", books.toString());
        return books; // return books
    }

    public int updateBook(Book book, String newTitle, String newAuthor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", newTitle); // get title
        values.put("author", newAuthor); // get author
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(4)}); //selection args
        db.close();
        Log.d(this.LOG_PREFIX + "UpdateBook", book.toString());
        return i;
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(book.getId())});
        db.close();
        Log.d(this.LOG_PREFIX + "deleteBook", book.toString());
    }

    public int getIds(Book book) {
        String selectQuery = "SELECT id FROM books";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        Log.d(this.LOG_PREFIX + "getIds", Integer.toString(total));
        return total;
    }

    public Set<String> getTitle() {
        Set<String> set = new HashSet<String>();
        String selectQuery = "select * from " + TABLE_BOOKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                set.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return set;
    }
    public String getAuthor(String title) {
        StringBuilder s = new StringBuilder();
        String selectQuery = "select *   from    " + TABLE_BOOKS + "   where   title=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{title});
        if (cursor.moveToFirst()) {
            do {
                s.append(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s.toString();
    }
    public String getRating(String title) {
        StringBuilder s = new StringBuilder();
        String selectQuery = "select *   from    " + TABLE_BOOKS + "   where   title=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{title});
        if (cursor.moveToFirst()) {
            do {
                s.append(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s.toString();
    }
}
