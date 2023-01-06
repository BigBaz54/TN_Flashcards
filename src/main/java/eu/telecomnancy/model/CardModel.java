package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.CardTag;

public class CardModel extends Observed {
    private String question;
    private String answer;
    private Float probability;
    private StatCard statCard;
    private ArrayList<CardTag> tags;
    private Media media = null;
    private static int id = 0;
    private int idCard;

    public CardModel() {
        this.question = "";
        this.answer = "";
        this.probability = 1F;
        this.statCard = new StatCard();
        this.tags = new ArrayList<>();
        this.idCard = id;
        id++;
    }

    public CardModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.probability = 1F;
        this.statCard = new StatCard();
        this.tags = new ArrayList<>();
        this.idCard = id;
        id++;
    }
    public CardModel(String question, String answer,Media media) {
        this(question,answer);
        this.media=media;
    }
    public CardModel(String question, String answer,Media media,ArrayList<CardTag> tags) {
        this(question,answer);
        this.media=media;
        this.tags=tags;
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

    public ArrayList<CardTag> getTags() {
        return this.tags;
    }

    public void addTag(String tag) {
        this.tags.add(new CardTag(tag));
    }

    public void removeTag(String tag) {
        for (CardTag t : tags) {
            if (t.getName().equals(tag)) {
                tags.remove(t);
                break;
            }
        }
    }

    public StatCard getStatCard() {
        return this.statCard;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setStatCard(StatCard statCard) {
        this.statCard = statCard;
    }

    public int getIdCard() {
        return idCard;
    }
}
