package com.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource; 

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        ClassPathResource serviceAccountResource = new ClassPathResource("firebase-service-account.json");

        InputStream serviceAccount = serviceAccountResource.getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }
}