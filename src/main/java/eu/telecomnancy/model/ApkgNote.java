package eu.telecomnancy.model;

public class ApkgNote {
    private String question;
    private String answer;
    private Media media;

    public ApkgNote(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.media = null;
    }

    public ApkgNote(String question, String answer, Media media) {
        this.question = question;
        this.answer = answer;
        this.media = media;
        System.out.println("answer: " + answer);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Media getMedia() {
        return media;
    }
}
