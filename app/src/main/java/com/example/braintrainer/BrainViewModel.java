package com.example.braintrainer;

import android.os.CountDownTimer;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;


public class BrainViewModel extends ViewModel {

    private int a;
    private int b;

    private int correctAnswerPos;
    private int incorrectAnswer;

    private ArrayList<Integer> answers = new ArrayList<>();

    private int score = 0;
    private int questionNr = 0;

    private CountDownTimer timer;


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getIncorrectAnswer() {
        return incorrectAnswer;
    }

    public void setIncorrectAnswer(int incorrectAnswer) {
        this.incorrectAnswer = incorrectAnswer;
    }

    public int getCorrectAnswerPos() {
        return correctAnswerPos;
    }

    public void setCorrectAnswerPos(int correctAnswerPos) {
        this.correctAnswerPos = correctAnswerPos;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestionNr() {
        return questionNr;
    }

    public void setQuestionNr(int questionNr) {
        this.questionNr = questionNr;
    }

    public CountDownTimer getTimer() {
        return timer;
    }

    public void setTimer(CountDownTimer timer) {
        this.timer = timer;
    }
}
