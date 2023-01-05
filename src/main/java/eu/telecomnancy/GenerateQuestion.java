package eu.telecomnancy;

import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GenerateQuestion {
    public ArrayList<CardModel> generateQuestion(DeckModel deck, int nbQuestion) throws IOException {
        //extrat all the question from the deck
        ArrayList<CardModel> cards = deck.getCards();
        ArrayList<String> questions = new ArrayList<>();
        for (CardModel card : cards) {
            if (card.getQuestion() != null) {
                questions.add(card.getQuestion());
            }
        }
        //generate the prompt
        StringBuilder prompt = new StringBuilder("Generate" + nbQuestion + "new question (separing each by //) from this already existing list: ");
        for (String question : questions) {
            prompt.append(question).append("? ");
        }
        //generate the answer
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        String endpoint = "https://api.openai.com/v1/completions";
        String apiKey = "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";
        HttpPost request = new HttpPost(endpoint);
        request.addHeader("Authorization", "Bearer " + apiKey);
        request.addHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity("{\"model\": \"text-davinci-003\",\"prompt\": \"" + prompt + "\", \"max_tokens\":1024, \"temperature\": 0.5}"));
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity);
        //parse the answer
        String answer = responseString.substring(responseString.indexOf("text") + 7, responseString.indexOf("finish_reason") - 3);
        String[] answers = answer.split("//");
        ArrayList<CardModel> newCards = new ArrayList<>();
        for (String newAnswer : answers) {
            newCards.add(new CardModel(newAnswer, null));
        }
        return newCards;
    }
}
