package com.play.scoreboard.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends EntityHibernate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "player1", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Match> matchesPlayer1;

    @OneToMany(mappedBy = "player2", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Match> matchesPlayer2;

    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Match> matchesWinner;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
