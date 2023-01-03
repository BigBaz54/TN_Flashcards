package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.CardTag;

public class CardModel extends Observed {
    private String question;
    private String answer;
    private float probability;
    private ArrayList<CardTag> tags;

    public CardModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.probability = 1;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }
}
