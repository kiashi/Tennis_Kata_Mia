package org.kata.tennis.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class Joueur implements IPartie{
    String nom;
    int score;
}
