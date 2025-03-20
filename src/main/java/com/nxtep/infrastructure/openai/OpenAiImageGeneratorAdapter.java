package com.nxtep.infrastructure.openai;

import com.nxtep.domain.services.ImageGeneratorPort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class OpenAiImageGeneratorAdapter implements ImageGeneratorPort {
    @Value("${openai.url}")
    private String openAiUrl;
    @Value("${openai.key}")
    private String openAiApiKey;
    private final RestTemplate restTemplate;
    public OpenAiImageGeneratorAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public File createImageFromAnotherImage(String imageUrl) {
        String prompt = imageUrl + ", Convert the previous image shared via URL into a Pixar-style illustration, maintaining their exact clothing and replicating their facial expression and gestures with a smooth, rounded, and expressive 3D animated look. The transformation should emphasize exaggerated yet natural facial features, soft lighting, and warm, vibrant colors to achieve the signature Pixar aesthetic. Every detail, from fabric texture to shading, should match the original photo while enhancing the character's charm and adorable young boy, incorporating a beloved children's toy into the scene.";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);
        Map<String, Object> requestBody = Map.of(
            "model", "dall-e-2",
            "prompt", prompt,
            "size", "1024x1024",
            "response_format", "url",
            "n", 1
        );
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            openAiUrl, HttpMethod.POST, requestEntity, Map.class
        );
        Map<?, ?> responseBody = responseEntity.getBody();
        if (responseBody != null && responseBody.containsKey("data")) {
            try {
                String imageUrlResponse = ((Map<?, ?>) ((List<?>) responseBody.get("data")).get(0)).get("url").toString();
                return downloadImage(imageUrlResponse);
            } catch (Exception e) {
                throw new RuntimeException("Error parsing OpenAI response: " + responseBody, e);
            }
        } else {
            throw new RuntimeException("Invalid response from OpenAI: " + responseBody);
        }
    }
    protected File downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        File tempFile = File.createTempFile("generated-avatar", ".png");
        try (InputStream in = url.openStream(); FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }
}
