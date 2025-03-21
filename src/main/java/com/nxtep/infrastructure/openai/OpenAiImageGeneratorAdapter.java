package com.nxtep.infrastructure.openai;

import com.nxtep.domain.exceptions.ImageProcessingException;
import com.nxtep.domain.services.ImageGeneratorPort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

@Slf4j
@Component
public class OpenAiImageGeneratorAdapter implements ImageGeneratorPort {
    private final OpenAiChatModel openAiChatModel;
    private final OpenAiImageModel openAiImageModel;
    public OpenAiImageGeneratorAdapter(OpenAiChatModel openAiChatModel, OpenAiImageModel openAiImageModel) {
        this.openAiChatModel = openAiChatModel;
        this.openAiImageModel = openAiImageModel;
    }
    @Override
    public File createImageFromAnotherImage(String username, String imageUrl) throws ImageProcessingException {
        try {
            URI imageUri = new URI(imageUrl);
            URL imageUrlObj = imageUri.toURL();
            UserMessage userMessage = new UserMessage(
                "Analyze the image and provide a detailed description of the person. Focus primarily on determining their gender with high accuracy. Also, describe their hairstyle, clothing, and any visible accessories.",
                new Media(MimeTypeUtils.IMAGE_JPEG, imageUrlObj)
            );
            ChatResponse chatResponse = this.openAiChatModel.call(new Prompt(userMessage));
            String description = chatResponse.getResult().getOutput().getText();
            log.info("Description ({}): {}", username, description);
            String promptText = """
            Transform the provided person description into a high-quality Pixar-style 3D animated character, depicting a **younger (infant or baby) version** of the person. Follow these guidelines:
            1. **Person's Characteristics**:
               - Use the following description as reference: %s
               - Maintain recognizable features but adapt them to a childlike, baby version.
               - Ensure a smooth, rounded, and expressive appearance, with exaggerated but natural proportions.
            2. **Clothing and Expression**:
               - Preserve the **clothing style, facial expression, and gestures** as closely as possible.
               - Adjust the outfit’s fit and proportions to match the character's younger appearance.
               - Retain accessories where appropriate, adapting them for a baby-like version.
            3. **Pixar Aesthetic**:
               - Apply **soft lighting and warm, vibrant colors** for a lively and appealing look.
               - Use detailed **fabric textures, shading, and lighting** to enhance realism.
            4. **Magical Candy-Themed Background**:
               - Create a whimsical world filled with **colorful candies, lollipops, chocolates, and other sweet treats**.
               - The scene should feel cheerful, inviting, and reminiscent of Pixar’s fantasy settings.
            5. **Final Adjustments**:
               - Ensure the person remains the **central focus** with a **lovable and expressive childlike charm**.
               - Pay close attention to details that make the baby version instantly recognizable.
        """.formatted(description);
            OpenAiImageOptions openAiImageOptions = OpenAiImageOptions.builder()
                .withQuality("standard")
                .withN(1)
                .withHeight(1024)
                .withWidth(1024)
                .build();
            ImagePrompt imagePrompt = new ImagePrompt(promptText, openAiImageOptions);
            ImageResponse imageResponse = this.openAiImageModel.call(imagePrompt);
            String generatedImageUrl = imageResponse.getResult().getOutput().getUrl();
            return downloadImage(generatedImageUrl);
        } catch (Exception exception) {
            throw new ImageProcessingException("Error al generar avatar utilizando los servicios de OpenAI");
        }
    }
    private File downloadImage(String imageUrl) throws ImageProcessingException {
        try {
            URI uri = URI.create(imageUrl);
            URL url = uri.toURL();
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
            throw new ImageProcessingException("Error al intentar descargar la imagen generada por OpenAI");
        }
    }
}
