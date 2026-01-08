package poc_export.service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
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

    /**
     * Petit escape HTML minimal pour éviter de casser ton HTML de fallback.
     * (Si tu fournis toujours htmlContent, tu peux enlever ce helper.)
     */
    private static String escapeHtml(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    public MailjetResponse sendEmailWithImage(EmailRequest request) throws MailjetException {
        return null;
    }

    public MailjetResponse sendHtmlEmail(EmailRequest request) throws MailjetException {
        if (request == null || request.recipient() == null) {
            throw new IllegalArgumentException("EmailRequest/recipient is null");
        }
        if (request.recipient().email() == null || request.recipient().email().isBlank()) {
            throw new IllegalArgumentException("Recipient email is required");
        }
        if (request.subject() == null || request.subject().isBlank()) {
            throw new IllegalArgumentException("Subject is required");
        }

        String html = request.htmlContent();
        String text = request.textContent();

        if (text == null || text.isBlank()) {
            text = "Veuillez afficher ce message en HTML.";
        }
        if (html == null || html.isBlank()) {
            html = "<p>" + escapeHtml(text) + "</p>";
        }

        JSONObject message = new JSONObject()
                .put("From", new JSONObject()
                        .put("Email", mailjetConfig.getSenderEmail())
                        .put("Name", mailjetConfig.getSenderName()))
                .put("To", new JSONArray()
                        .put(new JSONObject()
                                .put("Email", request.recipient().email())
                                .put("Name", request.recipient().name() == null ? "" : request.recipient().name())))
                .put("Subject", request.subject())
                .put("TextPart", text)
                .put("HTMLPart", html);

        JSONObject payload = new JSONObject()
                .put("Messages", new JSONArray().put(message));

        return client.post(new com.mailjet.client.MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, payload.getJSONArray("Messages")));
    }
}
