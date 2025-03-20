package com.nxtep.infrastructure.openai;

import com.nxtep.domain.services.ImageGeneratorPort;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class OpenAiImageGeneratorAdapter implements ImageGeneratorPort {
    private final OpenAiImageModel openAiImageModel;
    public OpenAiImageGeneratorAdapter(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }
    @Override
    public File createImageFromAnotherImage(String imageUrl) {
        String promptText = """
            Transform the image at the provided URL into a highly detailed Pixar-style 3D animated character. 
            The illustration must feature only the **single** person from the original image, accurately replicating their clothing, 
            facial expression, and gestures with a smooth, rounded, and expressive look. 
            The character should have exaggerated yet natural facial features, soft lighting, and warm, vibrant colors 
            to match the iconic Pixar aesthetic. 
            Ensure the background and environment include **playful elements such as colorful candies, lollipops, chocolates, 
            and other sweet treats**, arranged in a whimsical and magical way. The atmosphere should feel warm, 
            cheerful, and inviting, like a scene from an animated fantasy world. 
            The person should remain the focal point, with careful attention to detail in **fabric texture, shading, and lighting**, 
            enhancing the charm of a lovable and expressive young child.
            You must use the image allocated at this URL: """ + imageUrl;
        OpenAiImageOptions openAiImageOptions = OpenAiImageOptions.builder()
            .withQuality("standard")
            .withN(1)
            .withHeight(1024)
            .withWidth(1024)
            .build();
        ImagePrompt prompt = new ImagePrompt(promptText, openAiImageOptions);
        ImageResponse imageResponse = this.openAiImageModel.call(prompt);
        String generatedImageUrl = imageResponse.getResult().getOutput().getUrl();
        return downloadImage(generatedImageUrl);
    }
    private File downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            File tempFile = File.createTempFile("generated-image", ".png");
            try (InputStream in = url.openStream();
                 FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Error downloading the image from: " + imageUrl, e);
        }
    }
}
