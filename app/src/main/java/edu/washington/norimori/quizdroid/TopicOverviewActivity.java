package edu.washington.norimori.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TopicOverviewActivity extends ActionBarActivity {

    private String chosenTopicName; //Name of chosen topic from list
    private Topic[] AllTopics; //Collection of all available Topic objects
    private int indexOfCurrQ = 0; //Keep track of current Question
    private int totalCorrQ = 0; //Keep track of number of correctly answered questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);

        Intent launchedMe = getIntent();

        chosenTopicName = launchedMe.getStringExtra("chosenTopic");
        TextView topic = (TextView) findViewById(R.id.topic);
        topic.setText(chosenTopicName);

        //Create all Topics and Question objects for each.
        String[] PokemonQ1Choices = {"Electric", "Psychic", "Water", "Fire"};
        Question PokemonQ1 = new Question("What type is Pikachu?", PokemonQ1Choices, 0);
        String[] PokemonQ2Choices = {"Steel", "Normal", "Water", "Bug"};
        Question PokemonQ2 = new Question("What type is Jigglypuff?", PokemonQ2Choices, 1);
        String[] PokemonQ3Choices = {"Leaf", "Dark", "Ghost", "Ice"};
        Question PokemonQ3 = new Question("What type is Glaceon?", PokemonQ3Choices, 3);
        Question[] PokemonQuestions = {PokemonQ1, PokemonQ2, PokemonQ3};
        Topic Pokemon = new Topic("Pokemon", "Gotta catch'em all!", PokemonQuestions, 3);

        String[] MathQ1Choices = {"5", "1", "3", "2"};
        Question MathQ1 = new Question("1 + 1 = ?", MathQ1Choices, 3);
        String[] MathQ2Choices = {"0", "1", "9000", "90000"};
        Question MathQ2 = new Question("9000 x 0 = ?", MathQ2Choices, 0);
        String[] MathQ3Choices = {"0", "55", "10", "-10"};
        Question MathQ3 = new Question("5 + 5 = ?", MathQ3Choices, 2);
        String[] MathQ4Choices = {"-3", "107", "3", "17"};
        Question MathQ4 = new Question("10 - 7 = ?", MathQ4Choices, 2);
        String[] MathQ5Choices = {"21", "12", "75", "-57"};
        Question MathQ5 = new Question("7 + 5", MathQ5Choices, 1);
        Question[] MathQuestions = {MathQ1, MathQ2, MathQ3, MathQ4, MathQ5};
        Topic Math = new Topic("Math", "MAAAAAAAATH", MathQuestions, 5);

        String[] PhysicsQ1Choices = {"Charmander", "Mikasa", "Jeffery", "Isaac Newton"};
        Question PhysicsQ1 = new Question("What famous dude had an apple fall on his head?", PhysicsQ1Choices, 3);
        String[] PhysicsQ2Choices = {"March 14th, 1879", "Today", "Christmas", "Next year"};
        Question PhysicsQ2 = new Question("When was Albert Einsten Born?", PhysicsQ2Choices, 0);
        String[] PhysicsQ3Choices = {"FALSE", "NO", "9.807m/s^2", "yes"};
        Question PhysicsQ3 = new Question("How much is gravity on Earth?", PhysicsQ3Choices, 2);
        String[] PhysicsQ4Choices = {"atmosphereic pressure/weight", "density/volume", "distance/time", "your enthusiasm!"};
        Question PhysicsQ4 = new Question("How do you calculate speed?", PhysicsQ4Choices, 2);
        String[] PhysicsQ5Choices = {"PIE", "999.97 kg/m^3", "-5", "0"};
        Question PhysicsQ5 = new Question("What is the density of water?", PhysicsQ5Choices, 1);
        String[] PhysicsQ6Choices = {"-50F", "100C", "infinty", "never"};
        Question PhysicsQ6 = new Question("What is the boiling point of water?", PhysicsQ6Choices, 1);
        Question[] PhysicsQuestions = {PhysicsQ1, PhysicsQ2, PhysicsQ3, PhysicsQ4, PhysicsQ5, PhysicsQ6};
        Topic Physics = new Topic("Physics", "IT'S EVERYWHERE ∩(ﾟ∀ﾟ∩)", PhysicsQuestions, 6);

        String[] MarvelSuperHeroesQ1Choices = {"Arhcer", "Ironman", "Hulk", "Spiderman"};
        Question MarvelSuperHeroesQ1 = new Question("Who is the strongest Marvel super hero", MarvelSuperHeroesQ1Choices, 2);
        String[] MarvelSuperHeroesQ2Choices = {"Wolverine", "Professor X", "Iceman", "Beast"};
        Question MarvelSuperHeroesQ2 = new Question("Who was not in the X-Men originally?", MarvelSuperHeroesQ2Choices, 0);
        String[] MarvelSuperHeroesQ3Choices = {"Pikachu", "The Prince", "Charles Francis Xavier", "Billy Bob Joe"};
        Question MarvelSuperHeroesQ3 = new Question("What is Professor X's full name?", MarvelSuperHeroesQ3Choices, 2);
        String[] MarvelSuperHeroesQ4Choices = {"Spongebob", "Patrick", "Robert Louis Drake", "Jim"};
        Question MarvelSuperHeroesQ4 = new Question("What is Iceman's real name?", MarvelSuperHeroesQ4Choices, 2);
        String[] MarvelSuperHeroesQ5Choices = {"rainbow", "blue", "orange", "pink"};
        Question MarvelSuperHeroesQ5 = new Question("What is the color of Beast's fur?", MarvelSuperHeroesQ5Choices, 1);
        Question[] MarvelSuperHeroesQuestions = {MarvelSuperHeroesQ1, MarvelSuperHeroesQ2, MarvelSuperHeroesQ3, MarvelSuperHeroesQ4, MarvelSuperHeroesQ5};
        Topic MarvelSuperHeroes = new Topic("MarvelSuperHeroes", "POW POOOOOWWWWWWW", MarvelSuperHeroesQuestions, 5);

        AllTopics = new Topic[]{Pokemon, Math, Physics, MarvelSuperHeroes};

        //Display chosen topic overview
        TextView desc = (TextView) findViewById(R.id.desc);
        TextView totalQ = (TextView) findViewById(R.id.totalQ);
        for (int i = 0; i < AllTopics.length; i++) {
            if (AllTopics[i].getName().equals(chosenTopicName)) {
                desc.setText("Topic Description: " + AllTopics[i].getDescription());
                totalQ.setText("Total Number of Questions: " + AllTopics[i].getTotalQ());
            }
        }

        //Begin button sends selected Topic object
        Button btnBegin = (Button) findViewById(R.id.Begin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(TopicOverviewActivity.this, QuestionActivity.class);
                for (int i = 0; i < AllTopics.length; i++) {
                    if (AllTopics[i].getName().equals(chosenTopicName)) {
                        nextActivity.putExtra("topic", AllTopics[i]);
                        nextActivity.putExtra("indexOfCurrQ", indexOfCurrQ);
                        nextActivity.putExtra("totalCorrQ", totalCorrQ);
                    }
                }
                startActivity(nextActivity);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
