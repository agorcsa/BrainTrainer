package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.braintrainer.databinding.ActivitySecondBinding;
import java.util.Random;


public class SecondActivity extends AppCompatActivity {

    // 1 second
    private static final int ONE_SECOND = 1000;
    // 30 seconds
    private static final int MAX = 30000;

    private ActivitySecondBinding binding;
    private BrainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);

        viewModel = ViewModelProviders.of(this).get(BrainViewModel.class);

        binding.scoreTextView.setText(viewModel.getScore() + "/" + viewModel.getQuestionNr());

        buttonInvisible();
        setupTimer();
        showRandomNumbers();
    }

    public void setupTimer() {

        viewModel.setTimer(new CountDownTimer(MAX, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {

                buttonsClickableTrue();

                int min = 0;
                int sec = (int) (millisUntilFinished / ONE_SECOND);

                String formattedTime = String.format("%02d:%02d", min, sec);

                binding.counterTextView.setText(formattedTime);
            }

            @Override
            public void onFinish() {
                buttonVisible();
                buttonsClickableFalse();

                binding.answerTypeTextView.setText("You have answered " + viewModel.getScore() + " from " + viewModel.getQuestionNr() + " correctly!");
                binding.answerTypeTextView.setTextSize(20f);
            }
        }.start());
    }

    public void showRandomNumbers() {

        // creates random numbers for the sum  (a + b)
        Random randomNumber = new Random();

        viewModel.setA(randomNumber.nextInt(21));
        viewModel.setB(randomNumber.nextInt(21));

        binding.sumTextView.setText(viewModel.getA() + " + " + viewModel.getB());


        //
        viewModel.setCorrectAnswerPos(randomNumber.nextInt(4));

        for (int i = 0; i < 4; i++) {

            if (i == viewModel.getCorrectAnswerPos()) {

                viewModel.setAnswers(viewModel.getA() + viewModel.getB());

            } else {

                viewModel.setIncorrectAnswer(randomNumber.nextInt(41));

                while (viewModel.getIncorrectAnswer() == viewModel.getA() + viewModel.getB()) {

                    viewModel.setIncorrectAnswer(randomNumber.nextInt(41));
                }

                viewModel.setAnswers(viewModel.getIncorrectAnswer());
            }
        }

        binding.button0.setText(Integer.toString(viewModel.getAnswers().get(0)));
        binding.button1.setText(Integer.toString(viewModel.getAnswers().get(1)));
        binding.button2.setText(Integer.toString(viewModel.getAnswers().get(2)));
        binding.button3.setText(Integer.toString(viewModel.getAnswers().get(3)));
    }
    public void onButtonClick(View view) {

        String tag = view.getTag().toString();

        if (tag.equals(String.valueOf(viewModel.getCorrectAnswerPos()))) {

            binding.answerTypeTextView.setText("Correct!");

            // increments the score
            viewModel.setScore(viewModel.getScore() + 1);

            updateScore();

        } else {

            binding.answerTypeTextView.setText("Wrong!");

            updateScore();
        }
    }

    public void updateScore() {
        // increments the number od questions
        viewModel.setQuestionNr(viewModel.getQuestionNr() + 1);
        binding.scoreTextView.setText(viewModel.getScore() + "/" + viewModel.getQuestionNr());
        showRandomNumbers();
    }

    public void playAgain(View view) {
        buttonInvisible();
        setupTimer();
        binding.answerTypeTextView.setText("");
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

