package org.kata.tennis.record;

public sealed interface IPartie permits Joueur, Chain {
    default IPartie then(IPartie next) {
        return new Chain(this, next);
    }
}