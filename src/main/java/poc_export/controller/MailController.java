package poc_export.controller;

import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc_export.model.dtos.EmailRequest;
import poc_export.service.MailjetService;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class MailController {
    private final MailjetService mailjetService;

    @PostMapping("/html")
    public ResponseEntity<?> sendHtmlEmail(@RequestBody EmailRequest request) {
        try {
            MailjetResponse response = mailjetService.sendHtmlEmail(request);

            return ResponseEntity.ok(response.getData());

        } catch (MailjetException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> helloWorldController() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
