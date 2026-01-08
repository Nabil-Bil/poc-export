package poc_export.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MailController {
    @GetMapping
    public ResponseEntity<?> helloWorldController() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
