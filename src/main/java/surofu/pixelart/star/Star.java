package surofu.pixelart.star;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StarId.class)
@Table(name = "stars")
public class Star implements Serializable {
    @Id
    @Column(name = "art_id")
    private Long artId;

    @Id
    @Column(name = "user_id")
    private Long userId;
}

