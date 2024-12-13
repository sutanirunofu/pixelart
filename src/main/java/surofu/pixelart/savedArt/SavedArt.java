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
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "map", columnDefinition = "TEXT")
    private String map;

    @Column(name = "is_complete", nullable = false)
    private boolean isComplete;

    @Column(name = "modified_date", nullable = false)
    private ZonedDateTime modifiedDate;
}
