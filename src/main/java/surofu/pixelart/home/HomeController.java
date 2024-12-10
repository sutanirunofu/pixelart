package surofu.pixelart.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok("<h1 style=\"font-family: sans-serif;\">Hello from PixelArt API!</h1><br><img width=\"400\" height=\"400\" src=\"/images/tree.svg\">");
    }

    @GetMapping("/v1")
    public ResponseEntity<?> greetingV1() {
        return ResponseEntity.ok("<h1 style=\"font-family: sans-serif;\">Hello from PixelArt API V1!</h1><br><img width=\"400\" height=\"400\" src=\"/images/tree.svg\">");
    }
}
