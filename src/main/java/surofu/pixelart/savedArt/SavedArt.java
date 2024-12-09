package surofu.pixelart.savedArt;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import surofu.pixelart.art.Art;
import surofu.pixelart.user.User;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "saved_arts")
public class SavedArt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "art_id")
    private Art art;

    @Column(name = "map", nullable = false)
    private String map;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_saved_arts",
            joinColumns = @JoinColumn(name = "art_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;

    @Column(name = "is_complete", nullable = false)
    private Boolean isComplete;

    @Column(name = "last_modified", nullable = false)
    private ZonedDateTime lastModified;

    SavedArt(Long id, String map, Boolean isComplete, Art art, ZonedDateTime lastModified) {
        this.id = id;
        this.art = art;
        this.map = map;
        this.user = null;
        this.isComplete = isComplete;
        this.lastModified = lastModified;
    }
}
