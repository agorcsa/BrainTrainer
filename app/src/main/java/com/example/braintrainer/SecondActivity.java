package com.example.braintrainer;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class SecondActivity extends AppCompatActivity {

    // 1 second
    private static final int ONE_SECOND = 1000;
    // 30 seconds
    private static final int MAX = 30000;
    private CountDownTimer timer;
    private TextView counterTextView;
    private TextView sumTextView;
    private TextView answerTypeTextView;
    private TextView scoreTextView;

    private int firstNumber;
    private int secondNumber;
    private int sum;
    private int score = 0;
    private int numberOfQuestions = 0;

    private Button playAgainButton;

    private ArrayList<Integer> answers;
    private int correctAnswerLocation;
    private int incorrectAnswer;

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        counterTextView = findViewById(R.id.counter_textView);
        playAgainButton = findViewById(R.id.button_play_again);
        sumTextView = findViewById(R.id.sum_textView);
        answerTypeTextView = findViewById(R.id.answer_type_textView);
        scoreTextView = findViewById(R.id.score_textView);

        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);

        scoreTextView.setText(score + "/" + numberOfQuestions);

        buttonInvisible();

        setupTimer();
        showRandomNumbers();

        calculateSum();
    }

    public void setupTimer() {
        timer = new CountDownTimer(MAX, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {

                buttonsClickableTrue();

                int min = 0;
                int sec = (int) (millisUntilFinished / ONE_SECOND);

                String formattedTime = String.format("%02d:%02d", min, sec);

                counterTextView.setText(formattedTime);

                //Log.i("Counter", formattedTime);
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                buttonsClickableFalse();

                answerTypeTextView.setText("You have answered " + score + " from " + numberOfQuestions + " correctly!");
                answerTypeTextView.setTextSize( 20f);
            }
        }.start();
    }

    public void showRandomNumbers() {

        answers = new ArrayList<>();

        Random randomNumber = new Random();
        firstNumber = randomNumber.nextInt(21);
        secondNumber = randomNumber.nextInt(21);

        sumTextView.setText(firstNumber + " + " + secondNumber);

        correctAnswerLocation = randomNumber.nextInt(4);

        for (int i = 0; i < 4; i++) {

            if (i == correctAnswerLocation) {

                answers.add(firstNumber + secondNumber);

            } else {

                incorrectAnswer = randomNumber.nextInt(41);

                while (incorrectAnswer == firstNumber + secondNumber) {

                    incorrectAnswer = randomNumber.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void buttonInvisible() {
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    public void playAgain(View view) {
        setupTimer();
        buttonInvisible();
        answerTypeTextView.setText("");

        showRandomNumbers();
    }

    public int calculateSum() {

        sum = firstNumber + secondNumber;
        return sum;
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;

        String tag = button.getTag().toString();

        //Log.i("Button tag is ", tag);

        if (tag.equals(String.valueOf(correctAnswerLocation))) {

            Log.i("Answer type", "correct");

            answerTypeTextView.setText("Correct!");

            score++;

            numberOfQuestions++;

            scoreTextView.setText(score + "/" + numberOfQuestions);

            showRandomNumbers();

        } else {

            Log.i("Answer type", "wrong");

            answerTypeTextView.setText("Wrong!");

            numberOfQuestions++;

            scoreTextView.setText(score + "/" + numberOfQuestions);

            showRandomNumbers();
        }
    }

    private void buttonsClickableTrue() {
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
    }

    private void buttonsClickableFalse() {
        button0.setClickable(false);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
    }
}

