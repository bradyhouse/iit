HW5
======

### Introduction

This lab will have you create a quiz via a question bank from the [http://www.papademas.net/sample.txt](http://www.papademas.net/sample.txt). Check it.

### Objective

For this lab you will use file processing techniques to open and read into an arraylist the sample.txt 
file which has questions for the user to answer. This is only a true or false quiz. You will add in 
radio buttons to allow for a user to choose True or False as a response to each question displayed 
via a TextView. An image will be clickable to allow the user to go to the next question. After the user 
goes thru 5 questions you will rate them by showing them how many questions they have correct – which will 
be shown via x number of stars from Androids rating bar! Here is a sample snapshot to get an idea of what you 
will be working with:

![wireframe](images/wireframe.png)


As you will see when you run your app, the user will press the Display Results button to
see if their answer is correct depending if they clicked on the True vs. False button. An
answer of Right! or Wrong! will then appear briefly in a Toast message. The user will then
press the Next image and then the next question will appear at the top in a TextView to try
another question. At the very end a rating bar will appear showing the number of questions
they have correct denoted by Blue Stars.


### STEP 1 Creating a New Android Project

* Create a new project called Quiz.
* Create your first activity and call it Quiz. Use the default layout name. First things first. Make sure your manifest file has this line of code to receive data from the web...


    <uses-permission android:name="android.permission.INTERNET"/>
    
* Also include ONLY the following for your uses-sdk tag...


    <uses-sdk android:minSdkVersion="8"/>

### STEP 2 Obtain an image from the web that will serve as your “clickable” image.

Grab an image icon from the web that says Next or depicts a right arrow or a combination of the two and put it into 
your drawable folder (ex. in your res/drawable-ldpi folder). The image will serve as a guide to assist the user to grab 
another question, as you will see by just clicking on the image itself! *Make sure to name your image next.*

### STEP 3 Open up your activity_login.xml file and include the following code:

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
     android:layout_width="fill_parent"
     android:layout_height="fill_parent"
     android:orientation="vertical" >
     <TextView
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:textSize="16sp"
     android:id="@+id/textView1"/>
    
     <RadioGroup
     android:id="@+id/radioQuestions"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content" >
     <RadioButton
     android:id="@+id/radioTrue"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:text="@string/radio_true"
     android:checked="true" />
     <RadioButton
     android:id="@+id/radioFalse"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:text="@string/radio_false" />
     </RadioGroup>
     <Button
     android:id="@+id/btnDisplay"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:text="@string/btn_display" />
    
     <ImageView
     android:id="@+id/imageView1"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:src="@drawable/next"
     android:clickable="true"/>
    
     <RatingBar
     android:id="@+id/ratingBar"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:numStars="5"
     android:stepSize="1.0"
     android:rating="2.0"
     android:isIndicator="true" />
    </LinearLayout>


Also open up your strings.xml file and add in the following resources:

    <resources>
     <string name="app_name">parser</string>
     <string name="action_settings">Settings</string>
     <string name="radio_true">True</string>
     <string name="radio_false">False</string>
     <string name="btn_display">Display Results</string>
    </resources>
   
### STEP 4 Open up your MainActivity.java file and add in the following code and import statements:

First add the following imports:

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.net.URL;
    import java.util.ArrayList;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.TextView;
    import android.widget.Toast;
    
Next add the following code into your Activity:

    public class MainActivity extends Activity {
    TextView textMsg, textPrompt;
     final String textSource = "http://www.papademas.net/sample.txt";
    
     ArrayList<String> stringList = new ArrayList<String>();
    
     static int questionNum=0;
    
     private RadioGroup radioQuestions;
     private RadioButton radioButton;
     private Button btnDisplay;
     ImageView image;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    URL textUrl;
     try {
     textUrl = new URL(textSource);
     BufferedReader bufferReader = new BufferedReader(
     new InputStreamReader(textUrl.openStream()));
     String StringBuffer;
     while ((StringBuffer = bufferReader.readLine()) != null) {
    
     stringList.add(StringBuffer);
     }
     bufferReader.close();
    
     TextView textView = (TextView) findViewById(R.id.textView1);
     //set initial question
     textView.setText(stringList.get(0));
    
     }catch(Exception e) {//Log.i("MY ERR",e.getMessage());
     }
    
     startQuiz();
    
    }//end onCreate
    
    public void startQuiz() {
    buttonListener();
    }
     public void buttonListener() {
    
    radioQuestions = (RadioGroup) findViewById(R.id.radioQuestions);
    btnDisplay = (Button) findViewById(R.id.btnDisplay);
    btnDisplay.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
    // get selected radio button from radioGroup
    int selectedId = radioQuestions.getCheckedRadioButtonId();
    // find the radiobutton by returned id
    radioButton = (RadioButton) findViewById(selectedId);
    
    switch(questionNum)
    {
     case 0:
    //verify if result matches the right button selection (True or
     //false!)
    if (radioButton.getText().equals("True"))
     Toast.makeText(MainActivity.this,
     " Right!", Toast.LENGTH_SHORT).show();
    else
     Toast.makeText(MainActivity.this,
     " Wrong!", Toast.LENGTH_SHORT).show();
    buttonListener();
    break;
    case 1:
    //verify if result matches the right button selection (True or
     //false!)
    if (radioButton.getText().equals("False"))
     Toast.makeText(MainActivity.this,
     " Right!", Toast.LENGTH_SHORT).show();
    else
     Toast.makeText(MainActivity.this,
     " Wrong!", Toast.LENGTH_SHORT).show();
    buttonListener();
    break;
    //finish switch cases 2-4
    }//end switch
    }
    });
    imageListener();
    }
     public void imageListener() {
    image = (ImageView) findViewById(R.id.imageView1);
    image.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
     // get new question for viewing
     TextView textView = (TextView) findViewById(R.id.textView1);
     if (questionNum==4)
     //reset count to -1 to start first question again
    questionNum=-1;
    textView.setText(stringList.get(++questionNum));
    //reset radio button (radioTrue) to default
    radioQuestions.check(R.id.radioTrue);
     }
     });
     }
    }//end activity
    
    
You’ll notice from the code that you have listener event handling for both the display results button and the
image you have which is made clickable. Study over the code well as you will tweak some things thru
momentarily.
Run your app and test it! You’ll notice a couple of things off the bat. For one, you have 2 stars defaulting
that are visible from your rating bar. That’s okay, that will be adjusted in a bit. Press your Display Results
button for the first two questions leaving the True button checked. You should see a Toast reply of Right! on
the first round. Hit your Next image and press Display Results again and you will a Toast reply of Wrong!
for the next question. Of course pressing the True button at this point your should see a message of Right!
Meaning the correct radio button has been pressed.

### STEP 5 Adjust your code.

Go back to your switch statement in main and finish up your switch cases for cases 2-4. Study over the quiz questions 
from the file for questions 3-5 and determine what is true and what is false. Code each remaining case to reflect a 
correct Toast response message based on your assumptions of what the correct answers maybe for questions 3-5 when cases 
2-4 are triggered. Run your app once again and test all 5 questions and responses. Once you are satisfied with your 
results make a snapshot of a right answer and a wrong answer for a particular question. You may wish to make your Toast 
message set to LENGTH_LONG at this point to grab the message display in ample time.

### STEP 6 Showing the results of questions that were correct to the user.

You can depict via Blue stars in your Rating Bar how many questions the user had correct. The amount of stars you’ll 
notice add up to 5 to reflect the 5 questions rated right or wrong. Gray or silvery stars reflect an incorrect answer by 
default for this app’s purpose. To show the number of Blue stars which actually will represent correct answers, merely
place the following code in your activity where you deem fit.

    RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
    rb.setRating(3);

where the number 3 would be an arbitrary number example depicting the number of correct responses from the user within 
setRating()’s parameter.  Make sure to include the following import in as well:

    import android.widget.RatingBar;

Add in code to track the number of correct answers to your activity where you deem necessary and then pass the result 
into your setRating() method when the last question’s response is displayed so the stars reflect the proper right vs. 
wrong in color detail. Make sure your rating bar is invisible until the last question is chosen with the Display button
being then pressed.  Snapshot question 3 this time with a result of right wrong as a displayed message.  Snapshot also 
the rating bar at the end showing one set of Blue stars and another snapshot showing a differing Blue star set of 
correct responses (hope someone gets them all correct).  Include also your MainActivity.java code as well for credit

### Completed Assignment

[readme.pdf](readme.pdf)
