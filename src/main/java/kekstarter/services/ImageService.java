package kekstarter.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    String uploadImage(MultipartFile image);

    String getPublicUrl(String fileName);

    File convert(MultipartFile file) throws IOException;
}
