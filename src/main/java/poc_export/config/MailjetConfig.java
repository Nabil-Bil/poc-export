package poc_export.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mailjet.api")
@Data
public class MailjetConfig {
    private String key;
    private String secretKey;
    private String senderEmail;
    private String senderName;
}
