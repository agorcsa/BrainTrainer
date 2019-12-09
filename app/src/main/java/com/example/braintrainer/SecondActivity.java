package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.braintrainer.databinding.ActivitySecondBinding;

import java.util.ArrayList;
import java.util.Random;


public class SecondActivity extends AppCompatActivity {

    // 1 second
    private static final int ONE_SECOND = 1000;
    // 30 seconds
    private static final int MAX = 30000;

    private CountDownTimer timer;

    private int a;
    private int b;
    private int sum;
    private int score = 0;
    private int numberOfQuestions = 0;

    private ArrayList<Integer> answers;
    private int correctAnswerLocation;
    private int incorrectAnswer;

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);

        binding.scoreTextView.setText(score + "/" + numberOfQuestions);
        buttonInvisible();
        setupTimer();
        showRandomNumbers();
    }

    public void setupTimer() {
        timer = new CountDownTimer(MAX, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {

                buttonsClickableTrue();

                int min = 0;
                int sec = (int) (millisUntilFinished / ONE_SECOND);

                String formattedTime = String.format("%02d:%02d", min, sec);

                binding.counterTextView.setText(formattedTime);

                //Log.i("Counter", formattedTime);
            }

            @Override
            public void onFinish() {
                buttonVisible();
                buttonsClickableFalse();

                binding.answerTypeTextView.setText("You have answered " + score + " from " + numberOfQuestions + " correctly!");
                binding.answerTypeTextView.setTextSize(20f);
            }
        }.start();
    }

    public void showRandomNumbers() {

        answers = new ArrayList<>();

        Random randomNumber = new Random();
        a = randomNumber.nextInt(21);
        b = randomNumber.nextInt(21);

        binding.sumTextView.setText(a + " + " + b);

        correctAnswerLocation = randomNumber.nextInt(4);

        for (int i = 0; i < 4; i++) {

            if (i == correctAnswerLocation) {

                answers.add(a + b);

            } else {

                incorrectAnswer = randomNumber.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = randomNumber.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        binding.button0.setText(Integer.toString(answers.get(0)));
        binding.button1.setText(Integer.toString(answers.get(1)));
        binding.button2.setText(Integer.toString(answers.get(2)));
        binding.button3.setText(Integer.toString(answers.get(3)));
    }

    public void onButtonClick(View view) {

        String tag = view.getTag().toString();

        //Log.i("Button tag is ", tag);

        if (tag.equals(String.valueOf(correctAnswerLocation))) {

            Log.i("Answer type", "correct");

            binding.answerTypeTextView.setText("Correct!");

            score++;

            updateScore();

        } else {

            Log.i("Answer type", "wrong");

            binding.answerTypeTextView.setText("Wrong!");

            updateScore();
        }
    }

    public void updateScore() {
        numberOfQuestions++;
        binding.scoreTextView.setText(score + "/" + numberOfQuestions);
        showRandomNumbers();
    }

    public void playAgain(View view) {
        setupTimer();
        binding.answerTypeTextView.setText("");
        buttonInvisible();
        showRandomNumbers();
    }

    public void buttonVisible() {
        binding.buttonPlayAgain.setVisibility(View.VISIBLE);
    }

    public void buttonInvisible() {
        binding.buttonPlayAgain.setVisibility(View.INVISIBLE);
    }

    private void buttonsClickableTrue() {
        binding.button0.setClickable(true);
        binding.button1.setClickable(true);
        binding.button2.setClickable(true);
        binding.button3.setClickable(true);
    }

    private void buttonsClickableFalse() {
        binding.button0.setClickable(false);
        binding.button1.setClickable(false);
        binding.button2.setClickable(false);
        binding.button3.setClickable(false);
    }
}

