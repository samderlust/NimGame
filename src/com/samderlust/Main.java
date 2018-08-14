/*
    Ngoc Sang Nguyen
    313
    Howard Rosenblum
    Assignment 2
    This is the bonus version
 */

package com.samderlust;

public class Main {

    public static void main(String[] args) {

        Nim newGame = new Nim();

        System.out.println("Welcome to the Nim game");
        System.out.println("We play by the misère rules");
        newGame.printPile();
            while (!newGame.done()) {
                boolean lastMove;
                 lastMove = newGame.PlayerMove();
                     if(lastMove) {
                     newGame.computerMove();
                 }
            }
        }

    }

