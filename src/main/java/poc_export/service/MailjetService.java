package poc_export.service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import org.springframework.stereotype.Service;
import poc_export.config.MailjetConfig;
import poc_export.model.dtos.EmailRequest;

@Service
public class MailjetService {
    private final MailjetClient client;
    private final MailjetConfig mailjetConfig;

    public MailjetService(MailjetConfig mailjetConfig) {
        this.mailjetConfig = mailjetConfig;
        ClientOptions options = ClientOptions.builder()
                .apiKey(mailjetConfig.getKey())
                .apiSecretKey(mailjetConfig.getSecretKey())
                .build();
        client = new MailjetClient(options);
    }

    public MailjetResponse sendEmailWithImage(EmailRequest request) throws MailjetException {
        return null;
    }

    public MailjetResponse sendHtmlEmail(EmailRequest request) throws MailjetException {
        // TODO: Implement HTML email
        return null;
    }
}
