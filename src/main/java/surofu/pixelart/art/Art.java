package surofu.pixelart.art;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arts")
public class Art implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "map", columnDefinition = "TEXT", nullable = false)
    private String map;

    @Column(name = "colors", columnDefinition = "TEXT", nullable = false)
    private String colors;

    @Column(name = "publication_date", nullable = false)
    private ZonedDateTime publicationDate;
}
