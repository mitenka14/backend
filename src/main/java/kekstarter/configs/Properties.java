package kekstarter.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Properties {

    @Value("${DROPBOX.TOKEN}")
    private String accessToken;
}
