package com.example.csaper6.test_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button close;
    private boolean hasCheated;
    public static String EXTRA_CHEATED = "they cheated";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cheat);
        final TextView test = (TextView) findViewById(R.id.test);
        close = (Button) findViewById(R.id.finish);
        Intent i = getIntent();
        final String message = i.getStringExtra(MainActivity.ANSWER);
//        test.setText(message);
        hasCheated= false;
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.setText(message);
               hasCheated = true;


            }
        });





    }

    @Override
    protected void onPause() {
        Intent i = new Intent();
        i.putExtra(EXTRA_CHEATED, hasCheated);
        setResult(RESULT_OK, i);
        super.onPause();
    }
}

