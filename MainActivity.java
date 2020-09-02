package com.example.brainexerciseapp;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score =0;
    TextView resultTextView;
    int numberOfQuestions = 0;
    TextView pointTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    Button retryButton;



    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;

        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        generateQuestion();


        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");


            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score:"+ Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
                resultTextView.setTextColor(0xFF535152);
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.pop);
                mplayer.start();


            }
        }.start();

          /* String text = textViewTimer.getText().toString();
        int seconds = Integer.valueOf(text);

           //get how many repeat
        String repeatText = textViewTimer.getText().toString();
        final int repeat = Integer.valueOf(repeatText);

        final int[] count = {0};
        final CountDownTimer countDownTimer = new countDowntimer;

        new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millis) {
                textViewTimer.setText("time:" + (int)(millis / 1000));
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("Finished!");
                count[0]++;
                //check if count still less than repeat number
                if(count[0] < repeat){
                    Runnable r = new Runnable() {
                        public void run() {
                            countDownTimer.start();
                        }};

                    new Handler().postDelayed(r,2000); //delay repeat timer 2 seconds

                }
                else{
                    countDownTimer.cancel();
                    count[0] = 0;
                }
            }
        }.start();*/


    }




    public void generateQuestion(){
        Random rand = new Random();



        int a = rand.nextInt(21);
        int b=  rand.nextInt(21);
        int c= a+b;

        String[] operator = {"+", "-", "/", "*", "%"};

        Random ran = new Random();
        String operator_ran = operator[ran.nextInt(operator.length)];










        sumTextView.setText(Integer.toString(a)+ "+" + Integer.toString(b));




        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for (int i=0;i<4;i++){
            if(i== locationOfCorrectAnswer){
                answers.add(a+b);

            } else{
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer== a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
            resultTextView.setTextColor(0xFF0AE113);
            MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.correct);
            mplayer.start();


        }else{
            resultTextView.setText("Wrong!");
            resultTextView.setTextColor(0xFFE91E63);
            MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
            mplayer.start();
        }
        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestion();

    }
    public void retryGame(View view){

        playAgain(findViewById(R.id.playAgainButton));




    }

    public void start (View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
        MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.go);
        mplayer.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView= (TextView) findViewById(R.id.resultTextView);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton= (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout= (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        retryButton = (Button) findViewById(R.id.retryButton);

        /*android:id="@+id/playAgainButton"
        android:layout_width="300dp"
        android:layout_height="150dp"
        android:layout_marginStart="50dp"
        android:layout_marginTop="200dp"
        android:layout_marginBottom="100dp"
        android:background="#0FF1CF"
        android:fontFamily="cursive"
        android:text="Play Again"
        android:textColor="#ED0C0C0C"
        android:textSize="50sp" />*/





    }
}
