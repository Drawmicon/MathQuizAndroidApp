package com.example.psychomath;

import java.util.ArrayList;
import java.util.List;

public class game {

    private List<TheQuestion> quest;
    int correct;
    int incorrect;
    int total;

    int score;
    private TheQuestion currentQuestion;

    public game()
    {
        correct=0;
        incorrect=0;
        total=0;
        score=0;
        currentQuestion=new TheQuestion(10);
        quest = new ArrayList<>();
    }

    public void makeNewQuestion()
    {
        currentQuestion = new TheQuestion(total*2+5);
        total++;
        quest.add(currentQuestion);
    }

    public boolean checkAnswer(int submit)
    {
        boolean isCorrect;
        if(currentQuestion.getAnswer()== submit)
        {
            correct++;
            isCorrect=true;
        }
        else
        {
            incorrect++;
            isCorrect=false;
        }
        score = correct*10-incorrect*30;
        return isCorrect;
    }

    //**********************************************************
    //**********************************************************
    public List<TheQuestion> getQuest() {
        return quest;
    }

    public void setQuest(List<TheQuestion> quest) {
        this.quest = quest;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TheQuestion getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(TheQuestion currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

}
