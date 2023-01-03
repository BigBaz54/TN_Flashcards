package eu.telecomnancy.model;

import java.util.ArrayList;

public class Card {
    private String question;
    private String answer;
    private float probability;
    private ArrayList<Tag> tags;
    private int idNumber;
    private static int id;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.idNumber = id ;
        id ++;
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
}
