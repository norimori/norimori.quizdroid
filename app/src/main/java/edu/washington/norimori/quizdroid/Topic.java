package edu.washington.norimori.quizdroid;

import java.io.Serializable;

/**
 * Created by midori on 2/3/15.
 */
public class Topic implements Serializable {

    private String name;
    private String description;
    private Question[] questions; //Collection of all questions for topic
    private int totalQ;

    public Topic(String name, String description, Question[] questions, int totalQ) {
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.totalQ = totalQ;
    }

    public void setName(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String value) {
        description = value;
    }

    public String getDescription() {
        return description;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] value) {
        questions = value;
    }

    public void setTotalQ(int value) {
        totalQ = value;
    }

    public int getTotalQ() {
        return totalQ;
    }

}
