package com.javaweb.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.javaweb.service.IImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class FirebaseImageService implements IImageService {

    @Autowired
    Properties properties;
    @EventListener
    public void init(ApplicationReadyEvent event) {
    	
        if (FirebaseApp.getApps().isEmpty()) {
            try {
                ClassPathResource serviceAccount = new ClassPathResource("/firebase.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                        .setStorageBucket("image-highland-6ae35.appspot.com")
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("FirebaseApp initialized successfully.");
            } catch (IOException e) {
                System.out.println("Error initializing FirebaseApp: " + e.getMessage());
            }
        } else {
            System.out.println("FirebaseApp already initialized.");
        }
    }


    @Override
    public String save(MultipartFile file) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = generateFileName(file.getOriginalFilename());

        bucket.create(name, file.getBytes(), file.getContentType());
        String linkString = getDownloadUrl(name);
        return linkString;
    }

    @Override
    public String save(BufferedImage bufferedImage, String originalFileName) throws IOException {

        byte[] bytes = getByteArrays(bufferedImage, getExtension(originalFileName));

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = generateFileName(originalFileName);

        bucket.create(name, bytes);

        return name;
    
    }
    @Override
    public String getImageUrl(String name) {
    	return String.format(properties.imageUrl, name);
    }
    private String getDownloadUrl(String fileName) {
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", properties.getBucketName(), fileName);
    }
    @Override
    public void delete(String name) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        if (StringUtils.isEmpty(name)) {
            throw new IOException("invalid file name");
        }

        Blob blob = bucket.get(name);

        if (blob == null) {
            throw new IOException("file not found");
        }

        blob.delete();
    }
 
    @Configuration
    @ConfigurationProperties(prefix = "firebase")
    public class Properties {

        private String bucketName;

        private String imageUrl;

		public String getBucketName() {
			return bucketName;
		}

		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
        
        
    }
    
}
