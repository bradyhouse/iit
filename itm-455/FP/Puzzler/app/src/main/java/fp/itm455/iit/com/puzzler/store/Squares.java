package fp.itm455.iit.com.puzzler.store;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Iterator;

import fp.itm455.iit.com.puzzler.R;
import fp.itm455.iit.com.puzzler.model.Square;
import fp.itm455.iit.com.puzzler.util.Util;


public class Squares {

    private Integer cols;
    private Integer rows;
    private Context context;
    private TableLayout tableLayout;
    private ArrayList<Square> data;
    private ArrayList<Integer> sequence;


    public Squares(Integer cols, Integer rows, Context context, TableLayout tableLayout) {
        this.cols = cols;
        this.rows = rows;
        this.context = context;
        this.tableLayout = tableLayout;
        this.init();
    }

    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public void setSequence(ArrayList<Integer> sequence) {
        this.sequence = sequence;
    }

    public TableLayout getTableLayout() {
        return tableLayout;
    }

    public void setTableLayout(TableLayout tableLayout) {
        this.tableLayout = tableLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public ArrayList<Square> getData() {
        return data;
    }

    public void setData(ArrayList<Square> data) {
        this.data = data;
    }

    public Integer range() {
        return cols * rows;
    }

    public void load() {
        Integer squareCount = range() - 1,
                col = 1,
                row = 1,
                i = 0;
        if (data.size() == 0) {
            this.sequence = Util.generateGameSequence(1, squareCount, squareCount);
            for (; row <= rows; row++) {
                TableRow tableRow = new TableRow(context);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);
                tableRow.setId(Util.rowIds[row]);
                for (; col <= cols; col++) {
                    String value = i < sequence.size() ? sequence.get(i).toString() : context.getString(R.string.square_empty_text);
                    String expectedValue = i < sequence.size() ? Integer.toString(i + 1) : context.getString(R.string.square_empty_text);
                    Square square = new Square(Util.squareIds[i], row, col, value, expectedValue, tableRow, context);
                    data.add(square);
                    i++;
                }
                col = 1;
                this.tableLayout.addView(tableRow, (row - 1));
            }
        }
    }

    public void unload() {
        this.tableLayout.removeAllViews();
        this.data = new ArrayList<>();
        this.load();
    }

    private ArrayList<Square> getCorrectSquares() {
        Iterator iterator = this.data.iterator();
        ArrayList<Square> correctSquares = new ArrayList<>();
        while(iterator.hasNext()) {
            Square square = (Square)iterator.next();
            if(square.isCorrect()){
                correctSquares.add(square);
            }
        }
        return correctSquares;
    }

    public boolean isSolved() {
        ArrayList<Square> moves = this.getCorrectSquares();
        return moves.size() == this.range() ? true : false;
    }

    public void reset() {
        Integer squareCount = this.range() - 1;
        Integer i = 0;
        Iterator iterator = this.data.iterator();
        this.sequence = Util.generateGameSequence(1, squareCount, squareCount);
        while(iterator.hasNext()) {
            Square square = (Square)iterator.next();
            if (i < sequence.size()) {
                square.setValue(sequence.get(i).toString());
            } else {
                square.setValue(context.getString(R.string.square_empty_text));
            }
            i++;
        }
    }

    public void init() {
        this.data = new ArrayList<>();
        this.load();
    }

}
