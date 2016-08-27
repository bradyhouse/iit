package fp.itm455.iit.com.puzzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import fp.itm455.iit.com.puzzler.model.Square;
import fp.itm455.iit.com.puzzler.store.Squares;
import fp.itm455.iit.com.puzzler.util.Util;

import java.util.Iterator;


public class PuzzleActivity extends Activity {

    Vibrator vibrator;
    Squares squares;
    Context context;
    TableLayout table;
    Integer cols = 3;
    Integer rows = 3;
    Integer screenWidth;
    Integer screenHeight;
    Integer buttonWidth;
    Integer buttonHeight;
    Integer margin;
    AlertDialog.Builder builder;
    Integer level;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        Intent intent = getIntent();
        this.context = this.getApplicationContext();
        this.table = (TableLayout) findViewById(R.id.puzzleTable);
        this.vibrator = (Vibrator) PuzzleActivity.this.getSystemService(context.VIBRATOR_SERVICE);
        this.level = intent.getIntExtra("level", 1);
        Integer[] dimensions = Util.mapGameDimensions(this.level);
        if (dimensions.length > 0) {
            this.cols = dimensions[1];
            this.rows = dimensions[0];
        }
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.screenWidth = size.x;
        this.screenHeight = size.y;
        Resources r = getResources();
        this.margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, r.getDisplayMetrics());
        this.buttonWidth = (this.screenWidth - this.margin) / this.cols;
        this.buttonHeight = (this.screenHeight - this.margin) / (this.rows + 1);
        this.squares = new Squares(this.rows, this.cols, this.context, this.table);
        configureSquares();
        configureAlertBuilder();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, R.id.puzzle_settings_reset, Menu.NONE, R.string.puzzle_settings_reset);
        menu.add(Menu.NONE, R.id.puzzle_settings_level_1, Menu.NONE, R.string.puzzle_settings_level_1);
        menu.add(Menu.NONE, R.id.puzzle_settings_level_2, Menu.NONE, R.string.puzzle_settings_level_2);
        menu.add(Menu.NONE, R.id.puzzle_settings_level_3, Menu.NONE, R.string.puzzle_settings_level_3);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.puzzle_settings_reset:
                squares.reset();
                return true;
            case R.id.puzzle_settings_level_1:
                reconfigurePuzzle(1);
                return true;
            case R.id.puzzle_settings_level_2:
                reconfigurePuzzle(2);
                return true;
            case R.id.puzzle_settings_level_3:
                reconfigurePuzzle(3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void reconfigurePuzzle(Integer level) {
        Intent intent = getIntent();
        intent.putExtra("level", level);
        recreate();
    }

    private void onSquareClick(View v) {
        vibrator.vibrate(1000);
        Square squareA = mapSquare((Button) findViewById(v.getId()));
        Square squareB = mapEmptySquare();
        if (!squareA.isEmpty() && isValidMove(squareA, squareB)) {
            squareB.setValue(squareA.getValue());
            squareA.setValue(getString(R.string.square_empty_text));
        }
        if (squares.isSolved()) {
            if (this.level < 3) {
                AlertDialog alert = this.builder.create();
                alert.show();
            } else {
                Toast.makeText(context, "Genius!", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void configureAlertBuilder() {
        this.builder = new AlertDialog.Builder(this);

        builder.setTitle("Genius!");
        builder.setMessage("Do you want to play again or continue to the next level?");

        builder.setPositiveButton("Play again", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                squares.reset();
                dialog.dismiss();
            }

        });

        builder.setNegativeButton("Next level", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                reconfigurePuzzle(level+1);
                dialog.dismiss();
            }
        });

    }

    private Boolean isValidMove(Square a, Square b) {
        int rowDelta = Math.abs(a.getRow() - b.getRow()),
                colDelta = Math.abs(a.getCol() - b.getCol());
        if (a.getRow() == b.getRow() || a.getCol() == b.getCol()) {
            return (rowDelta == 0 || rowDelta == 1) && (colDelta == 0 || colDelta == 1);
        }
        return false;
    }


    private Square mapEmptySquare() {
        Iterator squareIterator = squares.getData().iterator();
        Square square = squares.getData().get(squares.getData().size() - 1);
        if (!square.isEmpty()) {
            while (squareIterator.hasNext()) {
                square = (Square) squareIterator.next();
                if (square.isEmpty()) {
                    return square;
                }
            }
        }
        return square;
    }

    private Square mapSquare(Button button) {
        Iterator squareIterator = squares.getData().iterator();
        Square square = squares.getData().get(0);

        if (square.getButton().getId() != button.getId()) {
            while (squareIterator.hasNext()) {
                square = (Square) squareIterator.next();
                if (square.getButton().getId() == button.getId()) {
                    return square;
                }
            }
        }
        return square;
    }

    private void configureSquares() {
        Iterator squareIterator = squares.getData().iterator();
        Square square;
        while (squareIterator.hasNext()) {
            square = (Square) squareIterator.next();

            TableRow.LayoutParams params = (TableRow.LayoutParams) square.getButton().getLayoutParams();
            params.width = this.buttonWidth;
            params.height = this.buttonHeight;
            square.getButton().setLayoutParams(params);
            square.getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSquareClick(v);
                }
            });
        }
    }

}
