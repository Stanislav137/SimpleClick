package app.simpleclick.stas.simpleclick;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import anim.ExplosionField;

public class GameActivity extends AppCompatActivity {

    private ExplosionField mExplosionField;
    private int difficulty;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private TextView textScore;
    private TextView textLives;
    private int score = 0;
    private int lives = 3;
    private boolean pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        textScore = (TextView) findViewById(R.id.textScore);
        textLives = (TextView) findViewById(R.id.textLives);
        mExplosionField = ExplosionField.attach2Window(this);
    }
    @Override
    protected void onStart()
    {
        getDifficulty();
        makeButtonsInvisible();
        makeButtonsToBlink(getRandomButton());
        super.onStart();
    }
    private void getDifficulty(){
        Bundle bundle = getIntent().getExtras();
        difficulty = bundle.getInt("difficulty");
    }
    private void makeButtonsInvisible(){
        ArrayList<View> allButtons;
        allButtons = ((GridLayout) findViewById(R.id.buttonsGrid)).getTouchables();
        for (View button : allButtons){
            button.setVisibility(View.INVISIBLE);
        }
    }
    private void makeButtonsNotclickable(){
        ArrayList<View> allButtons;
        allButtons = ((GridLayout) findViewById(R.id.buttonsGrid)).getTouchables();
        for (View button : allButtons){
            button.setClickable(false);
        }
    }
    private void changeColorToPressed(){
        ArrayList<View> allButtons;
        allButtons = ((GridLayout) findViewById(R.id.buttonsGrid)).getTouchables();
        for (View button : allButtons){
            button.setBackgroundColor(Color.GREEN);
            //mExplosionField.explode(button);
        }
    }
    private void checkLives(){
        if(lives < 1){

        }
    }

    public void afterAppeared(Button button){

        textLives.setText("Lives: " + lives);
        button.setVisibility(View.VISIBLE);
        button.setBackgroundColor(Color.RED);
        button.setClickable(true);
        pressed = false;
    }

    public void whenBlinked(Button button){

        textLives.setText("Lives: " + lives);
        if (!pressed){
            lives--;
            checkLives();
        }
        button.setVisibility(View.INVISIBLE);
        makeButtonsToBlink(getRandomButton());
    }

    public void makeButtonsToBlink(final Button button){

        afterAppeared(button);

        new CountDownTimer(difficulty, 1000) { //https://developer.android.com/reference/android/os/CountDownTimer.html

            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                whenBlinked(button);
            }
        }.start();
    }
    public Button getRandomButton(){

        int randomButton = 1 + (int)(Math.random() * ((9 - 1) + 1));

        switch (randomButton){
            case 1 : return btn1;
            case 2 : return btn2;
            case 3 : return btn3;
            case 4 : return btn4;
            case 5 : return btn5;
            case 6 : return btn6;
            case 7 : return btn7;
            case 8 : return btn8;
            case 9 : return btn9;
        }
        return null;
    }

    public void whenPressed(View view) {

        changeColorToPressed();
        makeButtonsNotclickable();
        pressed = true;
        score++;
        textScore.setText("Score: " + score);

        if (score % 5 == 0){
            lives++;
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Five in a row, you got additional life", Toast.LENGTH_SHORT);
            toast.show();
            difficulty-=100;
        }

        textLives.setText("Lives: " + lives);
    }
}
