package com.durgasoft.Controller;

import com.durgasoft.Service.ImageUploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/vi/api/images")
public class ImageController {

    private ImageUploadService imageUploadService;

    public ImageController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }
    //upload images to S3
//    @PostMapping(path="/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//
//    public ResponseEntity<?> uploadImages(@RequestParam("file") MultipartFile file, @PathVariable String bucketName,
//                                          @PathVariable Long propertyId) throws IOException {
//        String  imageUrl= imageUploadService.uploadImages(propertyId,file,bucketName);
//        return ResponseEntity.ok(imageUrl);
//    }


    //uploading multiple images for a property to s3 and adding the s3 url in database
    @PostMapping("/upload/file/{bucketName}/property/{propertyId}")
    public ResponseEntity<String> uploadPropertyImages(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable Long propertyId) throws IOException {

        String  imageUrl= imageUploadService.uploadMultipleImages(propertyId,file,bucketName);
        return ResponseEntity.ok(imageUrl);


    }

}
