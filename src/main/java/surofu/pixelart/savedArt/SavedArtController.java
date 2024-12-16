package surofu.pixelart.savedArt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surofu.pixelart.art.ArtNotFoundException;
import surofu.pixelart.user.UserNotFoundException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/saved_arts")
public class SavedArtController {
    private final SavedArtService service;

    @GetMapping
    public ResponseEntity<?> findAll(Principal principal) {
        try {
            List<FindSavedArtRTO> arts = service.findAll(principal.getName());
            return new ResponseEntity<>(arts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(Principal principal, @PathVariable long id) {
        try {
            Optional<FindSavedArtRTO> art = service.findById(principal.getName(), id);

            if (art.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(art.get(), HttpStatus.OK);
        } catch (SavedArtNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> save(Principal principal, @PathVariable("id") Long id) {
        try {
            service.save(principal.getName(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException | ArtNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(Principal principal, @PathVariable("id") Long id, @RequestBody UpdateSavedArtDTO updateSavedArtDTO) {
        try {
            FindSavedArtRTO art = service.updateByArtId(principal.getName(), id, updateSavedArtDTO);
            return new ResponseEntity<>(art, HttpStatus.OK);
        } catch (SavedArtNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
