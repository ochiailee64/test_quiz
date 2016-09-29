package com.example.csaper6.test_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button trueButton, falseButton, next, back, cheat;
    private TextView questiontextview, scoretracker;
    private int current;
    private ImageButton image;
    private int score;


    public static final String EXTRA_MESSAGE = "message";
    public static final String ANSWER = "answer";

    public static final int REQUEST_CHEATED = 0;
//for the activity result, to see if one cheated.... Y WOULD THEY CHEAT>>>>>>>>

    private boolean cheating;


    private Question[] questionBank = {new Question(R.string.question_text, true),
            new Question(R.string.question_text1, false),
            new Question(R.string.question_text2, true),
            new Question(R.string.question_text3, false),
            new Question(R.string.question_text4, true)

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_cheat:
                toggleCheating();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleCheating() {
    if(cheating) {
        cheating = false;
        cheat.setVisibility(View.GONE);

    }
    else {
       cheating = true;
        cheat.setVisibility(View.VISIBLE);
    }
    }


    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //wire buttons and text view
        //create new question frm object from the string resources
        //set the textView text to the Questions text
        // make a question object and pass it in the constructor
        //make view.onclick listener using the anonymous inter class
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        next = (Button) findViewById(R.id.next);
        back = (Button) findViewById(R.id.back);
        cheat = (Button) findViewById(R.id.cheat);
        current =0;
        image = (ImageButton) findViewById(R.id.imageButton);
        cheating = false;
        cheat.setVisibility(View.GONE);
        score = 0;
        scoretracker = (TextView) findViewById(R.id.scoring);






        questiontextview = (TextView) findViewById(R.id.textview_question);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int n = rand.nextInt(questionBank.length);
                checkanswer(questionBank[n]);
                questiontextview.setText(questionBank[n].getQuestionId());

            }
        });


        checkanswer(questionBank[current]);
        next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current++;
                    if(current == questionBank.length)
                        current =0;

                    checkanswer(questionBank[current]);
                    questiontextview.setText(questionBank[current].getQuestionId());


                }
            });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current--;
                if(current == -1)
                    current =0;
                checkanswer(questionBank[current]);
                questiontextview.setText(questionBank[current].getQuestionId());


            }
        });





        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //use intents to get from one activity to another
                Intent openCheatActivity = new Intent(MainActivity.this,CheatActivity.class);
                //load up intent with extra information to take to the new activity
                //you have a key to identify the extra & a value that is stored with it
                openCheatActivity.putExtra(EXTRA_MESSAGE,"hi");
                openCheatActivity.putExtra(ANSWER, questionBank[current].checkAnswer(true)+"");


                updateScoreNegative();
                updateScoreNegative();
                updateScoreNegative();
                updateScoreNegative();

                //startActivity(openCheatActivity);
                // make a constant for the request code
                //identifies what result we are trying to get
                startActivityForResult(openCheatActivity, REQUEST_CHEATED);

                disable();






            }
        });


    }




    public void checkanswer(Question question) {
        enable();
        if (question.checkAnswer(true)) {
            trueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(MainActivity.this, R.string.toastcorrect, Toast.LENGTH_SHORT).show();
                    updateScorePositive();
                    disable();

                }
            });
            falseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, R.string.toastincorrect, Toast.LENGTH_SHORT).show();
                    updateScoreNegative();
                    disable();
                }
            });




        }
        else
        {
            trueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, R.string.toastincorrect, Toast.LENGTH_SHORT).show();
                    updateScoreNegative();
                    disable();
                }
            });
            falseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, R.string.toastcorrect, Toast.LENGTH_SHORT).show();
                    updateScorePositive();
                    disable();

                }
            });









        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_OK && requestCode == REQUEST_CHEATED)
        {
            scoretracker.setText("STOP CHEATING");
        }
    }

    public void updateScorePositive()
    {
        score++;
        scoretracker.setText(score+"");


    }
    public void updateScoreNegative()
    {
        score--;
        scoretracker.setText(score+"");

    }
    private void disable()
    {
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);


    }
    private void enable()
    {
        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
    }


}


























