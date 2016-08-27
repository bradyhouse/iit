package fp.itm455.iit.com.puzzler.model;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TableRow;

import fp.itm455.iit.com.puzzler.R;
import fp.itm455.iit.com.puzzler.util.Util;

public class Square {
    private Integer id;
    private Integer row;
    private Integer col;
    private String value;
    private String expectedValue;
    private TableRow tableRow;
    private Context context;
    private Button button;

    public Square(Integer id, Integer row, Integer col, String value, String expectedValue, TableRow tableRow, Context context) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.value = value;
        this.expectedValue = expectedValue;
        this.tableRow = tableRow;
        this.context = context;
        this.init();
    }

    public TableRow getTableRow() {
        return tableRow;
    }

    public void setTableRow(TableRow tableRow) {
        this.tableRow = tableRow;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.button.setText(value);
        if (this.isEmpty()) {
            this.button.setBackgroundColor(Color.TRANSPARENT);
        } else {
            if (Util.isEven(Integer.parseInt(this.value))) {
                this.button.setBackgroundResource(R.drawable.even_square);
            } else {
                this.button.setBackgroundResource(R.drawable.odd_square);
            }
        }
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public boolean isEmpty() {
        return this.value == context.getString(R.string.square_empty_text);
    }

    public boolean isCorrect() {
        return this.value == this.expectedValue;
    }

    private void init() {
        this.button = new Button(context);
        this.button.setTextAppearance(context, R.style.ButtonText);
        this.button.setId(id);
        this.button.setText(this.value);
        if (this.isEmpty()) {
            this.button.setBackgroundColor(Color.TRANSPARENT);
        } else {
            if (Util.isEven(Integer.parseInt(this.value))) {
                this.button.setBackgroundResource(R.drawable.even_square);
            } else {
                this.button.setBackgroundResource(R.drawable.odd_square);
            }
        }
        this.tableRow.addView(this.button);
    }
}
