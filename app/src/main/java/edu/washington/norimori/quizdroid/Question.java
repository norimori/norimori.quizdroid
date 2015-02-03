package edu.washington.norimori.quizdroid;

import java.io.Serializable;

/**
 * Created by midori on 2/3/15.
 */
public class Question implements Serializable{

    private String qText;
    private String[] possibleA;
    int indexOfCorrA;

    public Question(String qText, String[] possibleA, int indexOfCorrA) {
        this.qText = qText;
        this.possibleA = possibleA;
        this.indexOfCorrA = indexOfCorrA;
    }

    public void setqText(String value) {
        qText = value;
    }

    public String getqText() {
        return qText;
    }

    public void setPossibleA(String[] value) {
        possibleA = value;
    }

    public String[] getPossibleA() {
        return possibleA;
    }

    public String getChosenA(int value) {
        return possibleA[value];
    }

    public void setIndexOfCorrA(int value) {
       indexOfCorrA = value;
    }

    public int getIndexOfCorrA() {
        return indexOfCorrA;
    }

    public String getCorrAText() {
        return possibleA[indexOfCorrA];
    }

}
