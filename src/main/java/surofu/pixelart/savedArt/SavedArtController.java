package surofu.pixelart.savedArt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/saved_arts")
public class SavedArtController {
    private final SavedArtService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<FindSavedArtRTO> arts = service.findAll();

        return new ResponseEntity<>(arts, HttpStatus.OK);
    }
}
