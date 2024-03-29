package kekstarter.configs;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DropboxConfig {

    @Value("${DROPBOX.TOKEN}")
    private String accessToken;

    @Bean
    public DbxClientV2 dropboxClient() {
        final DbxRequestConfig config = DbxRequestConfig.newBuilder("example-app").build();
        return new DbxClientV2(config, accessToken);
    }
}
