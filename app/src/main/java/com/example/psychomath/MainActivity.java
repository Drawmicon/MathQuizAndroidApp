package com.example.psychomath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    Button start, topR, topL, botR, botL;
    TextView textR, textL, textM, textViewBottom;
    //timer bar
    ProgressBar pbj;
    game g = new game();
    CountDownTimer timer;
    private static int defaultTime = 10;

    int score = 0, seconds = defaultTime;

    boolean timerOn = false;

    long timerTimeLeft = 5000;

    //https://www.youtube.com/watch?v=LMYQS1dqfo8
    void startTimer() {
        int x = seconds*1000;
        timer = new CountDownTimer(x, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seconds--;
                textL.setText(seconds + " Seconds");
                pbj.setProgress((defaultTime - seconds) * (100/defaultTime));
            }

            @Override
            public void onFinish() {
                topL.setEnabled(false);
                topR.setEnabled(false);
                botL.setEnabled(false);
                botR.setEnabled(false);
                start.setVisibility(View.VISIBLE);
                start.setEnabled(true);
                textViewBottom.setText("Time Up");
                textL.setText(seconds + " Seconds");
                score = 0;
                //seconds = 0;
                //pbjTime=0;
                g = new game();
            }
        }.start();//starts timer as soon as it's created
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topR = findViewById(R.id.topRight);
        topL = findViewById(R.id.topLeft);
        botR = findViewById(R.id.bottomRight);
        botL = findViewById(R.id.bottomLeft);

        start = findViewById(R.id.startButton);
        textR = findViewById(R.id.textViewRight);
        textM = findViewById(R.id.textViewMid);
        textL = findViewById(R.id.textViewLeft);
        textViewBottom = findViewById(R.id.textViewBottom);

        pbj = findViewById(R.id.progressBar);

        topL.setEnabled(false);
        topR.setEnabled(false);
        botL.setEnabled(false);
        botR.setEnabled(false);

        textViewBottom.setText("Press Go");
        textR.setText("Score: "+score);
        textL.setText(seconds+" Seconds");
        textM.setText("");

        seconds = defaultTime;
        //idea, answers move around
        //correct answers add to timer
        //change color when right/wrong

        View.OnClickListener startButtonCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //***********************************
                textR.setText("Press Answer");
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                //https://www.youtube.com/watch?v=PJ5XW-dLblw&list=PLhPyEFL5u-i1j8UdN4i5awkrhfRCT_pnl&index=25
                //timer.cancel();
                //timer.start();
                startTimer();
                nextGame();
                seconds=defaultTime;
                textR.setText("Score: " +score);
            }
        };

        start.setOnClickListener(startButtonCLickListener);

        /*
        topL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });
        topR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });
        botL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });
        botR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

            }
        });
         */

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //***********************************
                Button buttonClicked = (Button) v;

                int answerSelected = parseInt(buttonClicked.getText().toString());
                boolean julian = g.checkAnswer(answerSelected);
                if(julian)
                {
                    seconds=defaultTime;
                    timer.cancel();
                    //timer.start();
                    startTimer();
                }

                textR.setText(Integer.toString(g.getScore()));
                nextGame();
            }
        };

        topL.setOnClickListener(answerButtonClickListener);
        topR.setOnClickListener(answerButtonClickListener);
        botL.setOnClickListener(answerButtonClickListener);
        botR.setOnClickListener(answerButtonClickListener);


    }
    private void nextGame()
    {
        g.makeNewQuestion();
        textM.setText(g.getCurrentQuestion().getQuestionPhrase());
        int [] answers = g.getCurrentQuestion().getAnswerOptions();
        topL.setText(Integer.toString(answers[0]));
        topR.setText(Integer.toString(answers[1]));
        botL.setText(Integer.toString(answers[2]));
        botR.setText(Integer.toString(answers[3]));
        topL.setEnabled(true);
        topR.setEnabled(true);
        botL.setEnabled(true);
        botR.setEnabled(true);
        textViewBottom.setText(g.getCorrect()+"/"+(g.getTotal()-1));
        score = 0;
        //seconds = 5;
    }

    private void continueGame()
    {
        textM.setText(g.getCurrentQuestion().getQuestionPhrase());
        int [] answers = g.getCurrentQuestion().getAnswerOptions();
        topL.setText(Integer.toString(answers[0]));
        topR.setText(Integer.toString(answers[1]));
        botL.setText(Integer.toString(answers[2]));
        botR.setText(Integer.toString(answers[3]));
        if(seconds > 0) {
            start.setVisibility(View.INVISIBLE);
            start.setEnabled(false);
            topL.setEnabled(true);
            topR.setEnabled(true);
            botL.setEnabled(true);
            botR.setEnabled(true);
        }
        textViewBottom.setText(g.getCorrect()+"/"+(g.getTotal()-1));
        score = g.getScore();
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("timer", seconds*1000);
        outState.putInt("score", g.getScore());
        outState.putInt("incorrect", g.getIncorrect());
        outState.putInt("correct", g.getCorrect());
        outState.putString("question", g.getCurrentQuestion().getQuestionPhrase());
        outState.putInt("firstNumber", g.getCurrentQuestion().getFirstNumber());
        outState.putInt("secondNumber", g.getCurrentQuestion().getSecondNumber());
        outState.putInt("answer", g.getCurrentQuestion().getAnswer());
        outState.putInt("answerPosition", g.getCurrentQuestion().getAnswerPosition());
        outState.putInt("getUpperLimit", g.getCurrentQuestion().getUpperLimit());
        outState.putIntArray("answerOptions", g.getCurrentQuestion().getAnswerOptions());
        outState.putString("questionPhrase", g.getCurrentQuestion().getQuestionPhrase());

        /*
        private int firstNumber, secondNumber, answer, answerPosition, upperLimit;
        private int[] answerOptions;//position in grid of buttons
        private String questionPhrase;
         */

        /*
        private List<TheQuestion> quest;
        int correct;
        int incorrect;
        int total;
        int score;
        private TheQuestion currentQuestion;
         */

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        g.makeNewQuestion();

        seconds=(int)(savedInstanceState.getLong("timer")/1000);
        timerTimeLeft = savedInstanceState.getLong("timer");
        g.setScore(savedInstanceState.getInt("score"));

        g.setIncorrect(savedInstanceState.getInt("incorrect"));
        g.setCorrect(savedInstanceState.getInt("correct"));
        TheQuestion temp = new TheQuestion(savedInstanceState.getInt("getUpperLimit"));
        temp.setFirstNumber(savedInstanceState.getInt("firstNumber"));
        temp.setSecondNumber(savedInstanceState.getInt("secondNumber"));
        temp.setAnswer(savedInstanceState.getInt("answer"));
        temp.setAnswerPosition(savedInstanceState.getInt("answerPosition"));
        temp.setQuestionPhrase(savedInstanceState.getString("question"));
        temp.setAnswerOptions(savedInstanceState.getIntArray("answerOptions"));
        temp.setQuestionPhrase(savedInstanceState.getString("questionPhrase"));
        g.setCurrentQuestion(temp);
        //onStart();
        startTimer();
        continueGame();

    }

}


