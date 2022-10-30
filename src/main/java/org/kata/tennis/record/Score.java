package org.kata.tennis.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.kata.tennis.utils.ConstantesUtils.JOUEUR_WINS_THE_GAME;

public record Score(IPartie partie) {

    private static List<Joueur> joueurChain;

    private static Joueur joueur1;

    private static Joueur joueur2;

    public Score(IPartie partie){
        this.partie = partie;
        var joueur = new ArrayList<Joueur>();
        handle(partie, joueur);
        joueurChain = joueur;
        joueur1 = getFirstJoueur.get();
        joueur2 = getSecondJoueur.get();
    }

    public List<String> buildTableauDeScore() {
        var tableauDeScore = new ArrayList<String>();
        joueurChain.forEach(a -> {
                    winner.apply(a).ifPresent(x -> tableauDeScore.add(String.format(JOUEUR_WINS_THE_GAME, x.nom)));
                    if (winner.apply(a).isEmpty()) {
                        toScoreAndIncrement.accept(a);
                        tableauDeScore.add(printScore.get());
                    }
                });
        return tableauDeScore;
    }

    private static final Consumer<Joueur> toScoreAndIncrement = joueur -> {
        var score = joueur.score+1;
        var scoreTennis = switch (score) {
            case 1 -> 15;
            case 16 -> 30;
            case 31 -> 40;
            default -> 0;
        };
        joueur.setScore(scoreTennis);
    };

    private static final Function<Joueur, Optional<Joueur>> winner = joueur -> joueur.getScore() >= 40 ? Optional.of(joueur) : Optional.empty();

    private static final Supplier<String> printScore = () -> joueur1.nom + " : " + joueur1.score + " | " + joueur2.nom + " : " + joueur2.score;

    private static final Supplier<Joueur> getFirstJoueur = () -> joueurChain.get(0);

    private static final Supplier<Joueur> getSecondJoueur = () -> joueurChain.stream()
            .filter(a -> !a.nom.equalsIgnoreCase(getFirstJoueur.get().nom))
            .findFirst()
            .orElse(null);

    private void handle(IPartie coup, List<Joueur> joueur) {
        if (coup instanceof Joueur m) {
            joueur.add(m);
        }
        else if (coup instanceof Chain a) {
            handle(a.a(), joueur);
            handle(a.b(), joueur);
        }
    }
}
