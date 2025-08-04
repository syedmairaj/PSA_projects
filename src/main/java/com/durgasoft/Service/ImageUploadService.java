package com.durgasoft.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.PropertyImages;
import com.durgasoft.Repository.PropertyImagesRepository;
import com.durgasoft.Repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageUploadService {


    private AmazonS3 amazonS3;
    //write the code to upload file in s3
    private Property property;
    private PropertyImagesRepository propertyImagesRepository;
    private final PropertyRepository propertyRepository;

    public ImageUploadService(PropertyRepository propertyRepository,
                              PropertyImagesRepository propertyImagesRepository,
                              AmazonS3 amazonS3) {
        this.propertyRepository = propertyRepository;
        this.propertyImagesRepository = propertyImagesRepository;
        this.amazonS3 = amazonS3;
    }

    //upload images to S3
//    public String uploadImages(Long propertyId, MultipartFile file, String bucketName) throws IOException {
//        // URL fileUrl =null;
//        if (file.isEmpty()) {
//            throw new IllegalStateException("cannot upload empty file");
//        }
//
//        File conFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
//        file.transferTo(conFile);
//
//        try {
//            amazonS3.putObject(bucketName, conFile.getName(), conFile);
//
//            URL fileUrl = amazonS3.getUrl(bucketName, conFile.getName());
//
//        } catch (AmazonS3Exception e) {
//            throw new RemoteException("Unable to upload file:" + e.getMessage());
//
//        }
//        return amazonS3.getUrl(bucketName, conFile.getName()).toString();
//    }

    //uploading multiple images for a property to s3 and adding the s3 url in database
    public String uploadMultipleImages(Long propertyId, MultipartFile file, String bucketName) throws IOException {
        System.out.println("Image upload starting......");

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("fie is empty or null");
        }
        String originalFile = file.getOriginalFilename();
        if (originalFile == null) {
            throw new IllegalArgumentException("file name is null");
        }
       // String newFileName = UUID.randomUUID() + "-" + originalFile;
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + originalFile);
        file.transferTo(tempFile);

        try {
            amazonS3.putObject(bucketName, tempFile.getName(), tempFile);
            String imageUrl = amazonS3.getUrl(bucketName, tempFile.getName()).toString();
            System.out.println("upload image to s3");

            Optional<Property> property1 = propertyRepository.findById(propertyId);
            if (property1.isEmpty()) {
                throw new IllegalArgumentException("property id not found");
            }

            PropertyImages propertyImages = new PropertyImages();
            propertyImages.setUrl(imageUrl);
            propertyImages.setProperty(property1.get());
            try {

                PropertyImages saved = propertyImagesRepository.save(propertyImages);
                System.out.println("image url saved in db");

            } catch (Exception e) {
                System.out.println("error saving db");
                e.printStackTrace();
            }
            return "image uploaded" + imageUrl;
        } finally {
            tempFile.delete();
            System.out.println("temp file deleted");
        }

    }

}
