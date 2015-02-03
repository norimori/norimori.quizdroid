package edu.washington.norimori.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;


public class QuestionActivity extends ActionBarActivity {

    private RadioGroup radioGroup;
    //private RadioButton radioButton;
    private Button btnSubmit;
    private Question currQuestion;
    private int indexOfCurrQ;
    private int totalCorrQ;
    private Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent launchedMe = getIntent();

        topic = (Topic) launchedMe.getSerializableExtra("topic");
        indexOfCurrQ = (int) launchedMe.getIntExtra("indexOfCurrQ", 0);
        totalCorrQ = (int) launchedMe.getIntExtra("totalCorrQ", 0);


        //Make "Submit" button invisible when no radio buttons are selected.
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setVisibility(btnSubmit.INVISIBLE);

        TextView qText = (TextView) findViewById(R.id.qText);
        Question[] questions = topic.getQuestions();
        currQuestion = questions[indexOfCurrQ]; //Get current question player is on
        qText.setText(currQuestion.getqText());

        String[] possibleA = currQuestion.getPossibleA();
        RadioButton option1 = (RadioButton) findViewById(R.id.option1);
        option1.setText(possibleA[0]);
        RadioButton option2 = (RadioButton) findViewById(R.id.option2);
        option2.setText(possibleA[1]);
        RadioButton option3 = (RadioButton) findViewById(R.id.option3);
        option3.setText(possibleA[2]);
        RadioButton option4 = (RadioButton) findViewById(R.id.option4);
        option4.setText(possibleA[3]);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(QuestionActivity.this, AnswerSummaryActivity.class);
                nextActivity.putExtra("question", currQuestion);
                if(radioGroup.getCheckedRadioButtonId() != -1) { //if a radio button is checked
                    int id= radioGroup.getCheckedRadioButtonId();
                    View radioButton = radioGroup.findViewById(id);
                    int radioId = radioGroup.indexOfChild(radioButton);
                    nextActivity.putExtra("topic", topic);
                    nextActivity.putExtra("chosen", radioId);
                    nextActivity.putExtra("indexOfCurrQ", indexOfCurrQ);
                    nextActivity.putExtra("totalCorrQ", totalCorrQ);
                }
                startActivity(nextActivity);
            }
        });
    }

    //Adjust visiblity of "Submit" button when radio button is selected
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            btnSubmit.setVisibility(btnSubmit.VISIBLE);
        } else {
            btnSubmit.setVisibility(btnSubmit.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
