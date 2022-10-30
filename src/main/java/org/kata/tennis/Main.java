package org.kata.tennis;

import org.kata.tennis.record.Joueur;
import org.kata.tennis.record.Score;

public class Main {
    public static void main(String[] args) {

        System.out.println("--------------START MAIN TENNIS KATA---------------");

        var joueur1 = new Joueur("BForBank", 0);
        var joueur2 = new Joueur("Mia", 0);

        //Equivalent "ABABAA"
        var score = joueur1 //A
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur2)      //B
                .then(joueur1)      //A
                .then(joueur1);     //A

        var tableauDeScore = new Score(score);

        tableauDeScore.buildTableauDeScore().forEach(System.out::println);

        /*RESULT :
        BForBank : 15 | Mia : 0
        BForBank : 15 | Mia : 15
        BForBank : 30 | Mia : 15
        BForBank : 30 | Mia : 30
        BForBank : 40 | Mia : 30
        BForBank wins the game.
        */

        System.out.println("--------------X---------------");


        var joueur10 = new Joueur("Nadal", 0);
        var joueur20 = new Joueur("Roger", 0);

        //Equivalent "ABABABB"
        var scoreWithDeuceWithAdvantagePlayer2 = joueur10 //A
                .then(joueur20)                                   //B
                .then(joueur10)                                   //A
                .then(joueur20)                                   //B
                .then(joueur10)                                   //A
                .then(joueur20)                                   //B
                .then(joueur20);                                  //B

        var tableauDeScore2 = new Score(scoreWithDeuceWithAdvantagePlayer2);

        tableauDeScore2.buildTableauDeScore().forEach(System.out::println);

        /*RESULT :
        Nadal : 15 | Roger : 0
        Nadal : 15 | Roger : 15
        Nadal : 30 | Roger : 15
        Nadal : 30 | Roger : 30
        Nadal : 40 | Roger : 30
        Nadal : 40 | Roger : 40
        Roger wins the game.
        */

        System.out.println("--------------THANKS---------------");
    }
}