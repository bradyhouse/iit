package fp.itm455.iit.com.puzzler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

public class MainActivity extends Activity {

    private Button button3x3;
    private Button button4x4;
    private Button button5x5;
    private Button buttonInstructions;
    private int screenWidth;
    private int screenHeight;
    private int buttonWidth;
    private int margin;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        Resources r = getResources();
        this.margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, r.getDisplayMetrics());
        buttonWidth = this.screenWidth/2;
        buttonInstructions = (Button)findViewById(R.id.buttonInstructions);
        button3x3 = (Button)findViewById(R.id.button3x3);
        button4x4 = (Button)findViewById(R.id.button4x4);
        button5x5 = (Button)findViewById(R.id.button5x5);
        configureButtons();
    }
    private void configureButtons() {
        this.stretchButton(this.buttonInstructions);
        this.stretchButton(this.button3x3);
        this.stretchButton(this.button4x4);
        this.stretchButton(this.button5x5);
        addListeners();
    }

    private void stretchButton(Button button) {
        TableRow.LayoutParams params = (TableRow.LayoutParams) button.getLayoutParams();
        params.width = buttonWidth;
        button.setLayoutParams(params);
    }

    private void addListeners() {

        button3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayButtonClick(v, 1);
            }
        });

        button4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayButtonClick(v, 2);
            }
        });

        button5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayButtonClick(v, 3);
            }
        });

        buttonInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonInstructionsClick(v);
            }
        });
    }

    private void onPlayButtonClick(View v, Integer level) {
        Intent intent = new Intent(v.getContext(), PuzzleActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }

    private void onButtonInstructionsClick(View view) {
        startActivity(new Intent(view.getContext(), InstructionsActivity.class));
    }

}
