package com.example.psychomath;

import java.util.Random;

public class TheQuestion {
    private int firstNumber, secondNumber, answer, answerPosition, upperLimit;
    private int[] answerOptions;//position in grid of buttons
    private String questionPhrase;

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public int[] getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(int[] answerOptions) {
        this.answerOptions = answerOptions;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }

    public TheQuestion(int upperLimit)
    {
        this.upperLimit = upperLimit;
        Random rando= new Random();
        //generate two random numbers for the question
        this.firstNumber= rando.nextInt(upperLimit);
        this.secondNumber=rando.nextInt(upperLimit);
        //get addition answer
        this.answer=this.firstNumber+this.secondNumber;
        //
        this.answerPosition=rando.nextInt(4);
        this.answerOptions = new int []{0,1,2,3};
        this.answerOptions[0]=answer+1;
        this.answerOptions[0]=answer+10;
        this.answerOptions[0]=answer+-5;
        this.answerOptions[0]=answer+2;
        this.answerOptions = shuffleArray(this.answerOptions);
        answerOptions[answerPosition] = answer;

        this.questionPhrase = firstNumber+" + "+secondNumber +" = ?";
    }

    private int [] shuffleArray(int[] arr)
    {
        int index, temp;
        //int ballchain[] = new int [arr.length];

        Random shuffle = new Random();
        for(int i = arr.length-1; i>0; i--)
        {
           index = shuffle.nextInt(i+1);
           temp = arr[index];
           arr[index]=arr[i];
           arr[i]=temp;
        }

        return arr;
    }


}
