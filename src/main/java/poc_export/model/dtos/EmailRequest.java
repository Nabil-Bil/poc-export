package poc_export.model.dtos;

public record EmailRequest(
        EmailRecipient recipient,
        String subject,
        String textContent,
        String htmlContent
) {
}