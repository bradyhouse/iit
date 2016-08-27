package hw5.itm455.iit.com.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar;

import java.util.ArrayList;

import hw5.itm455.iit.com.quiz.model.Question;
import hw5.itm455.iit.com.quiz.store.Questions;
import hw5.itm455.iit.com.quiz.util.DownloadTask;
import hw5.itm455.iit.com.quiz.util.IDownloadTaskComplete;

public class MainActivity extends Activity {

    final String textSource = "http://www.papademas.net/sample.txt";
    static int questionNum = -1;


    private Questions questions;
    private Boolean actual;
    private TextView textView;
    private RadioGroup radioQuestions;
    private RadioButton radioButton;
    private Button btnDisplay;
    private Button btnRetry;
    private Button btnExit;
    private ImageView image;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        downloadListener();
    }

    public void downloadListener() {
        textView = (TextView) findViewById(R.id.textMessage);
        btnRetry = (Button) findViewById(R.id.btnRetry);
        textView.setText(R.string.text_msg_downloading);
        btnRetry.setVisibility(View.INVISIBLE);
        DownloadTask downloadQuestions = new DownloadTask(new IDownloadTaskComplete() {
            @Override
            public void onDownloadComplete(ArrayList<Question> list) {
                questions = new Questions(list);
                startQuiz();
            }
        }, textSource);
        downloadQuestions.execute();
    }

    public void startQuiz() {
        stateListener();
        buttonListener();
        radioListener();
        imageListener();
    }

    public void stateListener() {
        textView = (TextView) findViewById(R.id.textMessage);
        radioQuestions = (RadioGroup) findViewById(R.id.radioQuestions);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        image = (ImageView) findViewById(R.id.imageNext);
        btnRetry = (Button) findViewById(R.id.btnRetry);
        btnExit = (Button) findViewById(R.id.btnExit);
        Question question;
        if (!this.questions.isEmpty()) {
            question = questions.getList().get(++questionNum);
            textView.setText(Integer.toString(question.getId()) + ". " + question.getQuestion());
            btnRetry.setVisibility(View.INVISIBLE);
            btnExit.setVisibility(View.INVISIBLE);
            radioQuestions.setVisibility(View.VISIBLE);
            btnDisplay.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
        } else {
            textView.setText(R.string.text_msg_download_err);
            btnRetry.setVisibility(View.VISIBLE);
            btnExit.setVisibility(View.VISIBLE);
        }
    }

    public void buttonListener() {
        if (!this.questions.isEmpty()) {
            radioQuestions = (RadioGroup) findViewById(R.id.radioQuestions);
            btnDisplay = (Button) findViewById(R.id.btnDisplay);
            btnDisplay.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedId = radioQuestions.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    Question question = questions.getList().get(questionNum);
                    if (question.getId() == 5) {
                        displayRating();
                    } else {
                        displayResult(questions.getList().get(questionNum).isCorrect());
                    }
                }
            });
        } else {
            btnRetry = (Button) findViewById(R.id.btnRetry);
            btnExit = (Button) findViewById(R.id.btnExit);
            btnRetry.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnRetry.setVisibility(View.INVISIBLE);
                    btnExit.setVisibility(View.INVISIBLE);
                    downloadListener();
                }
            });
            btnExit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }
    }

    public void radioListener() {
        if (!questions.isEmpty()) {
            radioQuestions = (RadioGroup) findViewById(R.id.radioQuestions);
            radioQuestions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int selectedId = radioQuestions.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);

                    if (radioButton.getText().equals("True")) {
                        actual = true;
                    } else {
                        actual = false;
                    }
                    questions.getList().get(questionNum).setActual(actual);
                }
            });
        }
    }

    public void imageListener() {
        if (!questions.isEmpty()) {
            textView = (TextView) findViewById(R.id.textMessage);
            image = (ImageView) findViewById(R.id.imageNext);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Question question = questions.getList().get(questionNum);
                    hideRating();
                    if (question.getId() == 5) {
                        questionNum = -1;
                    }
                    question = questions.getList().get(++questionNum);
                    textView.setText(Integer.toString(question.getId()) + ". " + question.getQuestion());
                    if (question.getActual()) {
                        radioQuestions.check(R.id.radioTrue);
                    } else {
                        radioQuestions.check(R.id.radioFalse);
                    }
                }
            });
        }
    }

    public void displayResult(boolean correct) {
        if (correct) {
            Toast.makeText(MainActivity.this,
                    " Right!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,
                    " Wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayRating() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating((float) questions.getTotalCorrect());
        ratingBar.setVisibility(RatingBar.VISIBLE);

    }

    public void hideRating() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setVisibility(RatingBar.INVISIBLE);
    }

}
