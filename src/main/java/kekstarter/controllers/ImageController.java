package kekstarter.controllers;

import kekstarter.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "images",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public String addImage(@RequestParam("file") MultipartFile image) {
        return imageService.uploadImage(image);
    }
}
