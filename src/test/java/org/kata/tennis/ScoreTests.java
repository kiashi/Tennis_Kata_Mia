package org.kata.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kata.tennis.record.Joueur;
import org.kata.tennis.record.Score;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("#- Test calcul score tennis")
public class ScoreTests {

    Joueur joueur1;

    Joueur joueur2;

    @BeforeEach
    void init() {
        joueur1 = new Joueur("Player 1", 0);
        joueur2 = new Joueur("Player 2", 0);
    }

    @Test
    @DisplayName("Player 1 sould win the game")
    public void testPlayer1(){
        var score = joueur1 //A
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur1);     //A

        var tableauDeScore = new Score(score).buildTableauDeScore();

        assertEquals(tableauDeScore.get(0), "Player 1 : 15 | Player 2 : 0");
        assertEquals(tableauDeScore.get(1), "Player 1 : 15 | Player 2 : 15");
        assertEquals(tableauDeScore.get(2), "Player 1 : 30 | Player 2 : 15");
        assertEquals(tableauDeScore.get(3), "Player 1 : 30 | Player 2 : 30");
        assertEquals(tableauDeScore.get(4), "Player 1 : 40 | Player 2 : 30");
        assertEquals(tableauDeScore.get(5), "Player 1 wins the game.");
    }

    @Test
    @DisplayName("Player 2 sould win the game")
    public void testPlayer2(){
        var score = joueur2 //B
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur2);     //B

        var tableauDeScore = new Score(score).buildTableauDeScore();

        assertEquals(tableauDeScore.get(0), "Player 2 : 15 | Player 1 : 0");
        assertEquals(tableauDeScore.get(1), "Player 2 : 30 | Player 1 : 0");
        assertEquals(tableauDeScore.get(2), "Player 2 : 30 | Player 1 : 15");
        assertEquals(tableauDeScore.get(3), "Player 2 : 40 | Player 1 : 15");
        assertEquals(tableauDeScore.get(4), "Player 2 : 40 | Player 1 : 30");
        assertEquals(tableauDeScore.get(5), "Player 2 wins the game.");
    }

    @Test
    @DisplayName("Player 2 sould win the game with deuce and advantage")
    public void testPlayer2Deuce(){
        var score = joueur1 //A
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur2)      //B
                .then(joueur2);     //B

        var tableauDeScore = new Score(score).buildTableauDeScore();

        assertEquals(tableauDeScore.get(0), "Player 1 : 15 | Player 2 : 0");
        assertEquals(tableauDeScore.get(1), "Player 1 : 15 | Player 2 : 15");
        assertEquals(tableauDeScore.get(2), "Player 1 : 30 | Player 2 : 15");
        assertEquals(tableauDeScore.get(3), "Player 1 : 30 | Player 2 : 30");
        assertEquals(tableauDeScore.get(4), "Player 1 : 40 | Player 2 : 30");
        assertEquals(tableauDeScore.get(5), "Player 1 : 40 | Player 2 : 40"); //DEUCE with advantage Player 2
        assertEquals(tableauDeScore.get(6), "Player 2 wins the game."); //Player 2 win the ball
    }
}
