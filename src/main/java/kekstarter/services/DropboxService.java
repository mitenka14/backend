package kekstarter.services;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import kekstarter.dto.JsonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DropboxService {

    private final DbxClientV2 client;

    public String uploadImage(MultipartFile image) {
        try (InputStream in = new FileInputStream(convert(image))) {
            String keyName = UUID.randomUUID().toString();
            this.client.files().uploadBuilder("/" + keyName).uploadAndFinish(in);
            return getPublicUrl(keyName);
        } catch (IOException e) {
            throw new JsonException("Cannot upload image file");
        } catch (UploadErrorException e) {
            throw new RuntimeException("Cannot upload image file");
        } catch (DbxException e) {
            throw new RuntimeException("Could not connect to storage!");
        }
    }

    public String getPublicUrl(String fileName) {
        try {
            SharedLinkMetadata slm = client.sharing().createSharedLinkWithSettings("/" + fileName);
            String url = slm.getUrl();
            return url.replace("?dl=0", "?raw=1");
        } catch (DbxException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
