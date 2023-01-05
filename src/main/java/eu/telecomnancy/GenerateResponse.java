package eu.telecomnancy;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
public class GenerateResponse {
    public String getResponse(String question) throws IOException {
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        String endpoint = "https://api.openai.com/v1/completions";
        String apiKey = "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";
        String prompt2 = "Generate response to this question : " + question;
        HttpPost request = new HttpPost(endpoint);
        request.addHeader("Authorization", "Bearer " + apiKey);
        request.addHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity("{\"model\": \"text-davinci-003\",\"prompt\": \"" + prompt2 + "\", \"max_tokens\":2048, \"temperature\": 0.5}"));
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseString, JsonObject.class);
        return jsonObject.get("choices").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
    }
}
