package edu.washington.norimori.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AnswerSummaryActivity extends ActionBarActivity {

    private int indexOfCurrQ;
    private int totalCorrQ;
    private Topic topic;
    private Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_summary2);

        Intent launchedMe = getIntent();
        topic = (Topic) launchedMe.getSerializableExtra("topic");
        indexOfCurrQ = (int) launchedMe.getIntExtra("indexOfCurrQ", 0);
        totalCorrQ = (int) launchedMe.getIntExtra("totalCorrQ", 0);
        Question question = (Question) launchedMe.getSerializableExtra("question");
        int chosen = launchedMe.getIntExtra("chosen", 0); //Index of chosen radio button

        //Display user's chosen answer
        TextView chosenA = (TextView) findViewById(R.id.chosenA);
        chosenA.setText("You chose: " + question.getChosenA(chosen));

        //Display correct answer and corresponding message
        TextView correctA = (TextView) findViewById(R.id.correctA);
        if(question.indexOfCorrA == chosen) { //If the chosen answer is correct
            correctA.setText("You're right! The correct answer is: " + question.getCorrAText());
            totalCorrQ++;
        } else {
            correctA.setText("Nope. The correct answer is: " + question.getCorrAText());
        }
        indexOfCurrQ++;
        Log.d("yay", "" + indexOfCurrQ);

        TextView score = (TextView) findViewById(R.id.score);
        score.setText("You have answered " + totalCorrQ + " out of " + topic.getTotalQ() + " correct.");

        //Next button to go to the next question (or Finish button to end quiz)
        btnAction = (Button) findViewById(R.id.action);
        if(finished()) {
            btnAction.setText("Finish!");
        } else {
            btnAction.setText("Next");
        }

        //Execute action based on quiz progress = Go to next question or finish quiz.
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finished()) {
                    Intent nextActivity = new Intent(AnswerSummaryActivity.this, MainActivity.class);
                    startActivity(nextActivity);
                } else {
                    Intent nextActivity = new Intent(AnswerSummaryActivity.this, QuestionActivity.class);
                    nextActivity.putExtra("topic", topic);
                    nextActivity.putExtra("indexOfCurrQ", indexOfCurrQ);
                    nextActivity.putExtra("totalCorrQ", totalCorrQ);
                    startActivity(nextActivity);
                }
            }
        });
    }

    //Returns true if all questions have been answered.
    public boolean finished() {
       return indexOfCurrQ == (topic.getTotalQ());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
