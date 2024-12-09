package surofu.pixelart.savedArt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surofu.pixelart.art.ArtNotFoundException;
import surofu.pixelart.exception.BadRequestExceptionRTO;
import surofu.pixelart.user.UserNotFoundException;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/saved_arts")
public class SavedArtController {
    private final SavedArtService service;

    @GetMapping
    public ResponseEntity<?> findAll(Principal principal) {
        try {
            System.out.println(principal.getName());
            List<FindSavedArtRTO> arts = service.findAllByUsername(principal.getName());
            return new ResponseEntity<>(arts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> save(Principal principal, @PathVariable("id") Long id) {
        try {
            service.save(id, principal.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ArtNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateById(Principal principal, @PathVariable Long id, @RequestBody UpdateSavedArtDTO updateSavedArtDTO) {
        try {
            boolean isUserOwnedArt = service.isUserOwnedArt(principal.getName(), id);
            if (!isUserOwnedArt) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            service.updateById(id, updateSavedArtDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new BadRequestExceptionRTO("User not found", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
