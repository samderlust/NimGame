/*
    Ngoc Sang Nguyen
    313
    Howard Rosenblum
    Assignment 2
    This Nim Class handle all the move of player and computer as well as initialize the piles
 */
package com.samderlust;

import java.util.Random;
import java.util.Scanner;

public class Nim {
    private Pile pileA;
    private Pile pileB;
    private Pile pileC;
    private Random rnd = new Random();
    private Scanner input = new Scanner(System.in);

    public Nim() {
            this.pileA = new Pile(rnd.nextInt(10) + 10);
            this.pileB = new Pile(rnd.nextInt(10) + 10);
            this.pileC = new Pile(rnd.nextInt(10) + 10);
    }

    public boolean PlayerMove() {
        int playerAmount;
        String playerPile;
        Pile theChoosen;
        System.out.print("Select a pile: ");
        playerPile = input.next().toLowerCase();

        switch (playerPile) {
        case "a":
            if (pileA.getSize() == 0) {
                System.out.print("Pile A is empty, pick another");
                printPile();
                return false;
            } else {
                theChoosen = pileA;
                break;
            }
        case "b":
            if (pileB.getSize() == 0) {
                System.out.print("Pile B is empty, pick another");
                printPile();
                return false;
            } else {
                theChoosen = pileB;
                break;
            }
        case "c":
            if (pileC.getSize() == 0) {
                System.out.print("Pile C is empty, pick another");
                printPile();
                return false;
            } else {
                theChoosen = pileC;
                break;
            }
        default:
            System.out.print("Invalid Letter, select a, b or c : ");
            printPile();
            return false;
        }

        System.out.print("How many do you want to remove? ");
        playerAmount = input.nextInt();

        if (playerAmount <= 0 || playerAmount > theChoosen.getSize()) {
            System.out.print("Pick a number between 1 and " + theChoosen.getSize());
            printPile();
            return false;
        } else
            theChoosen.remove(playerAmount);

        if (done()) {
            System.out.println("Game over. You lose");
            return false;
        }
        printPile();
        return true;
    }

    public void computerMove() {
        int a1 = pileA.getSize();
        int a2 = pileB.getSize();
        int a3 = pileC.getSize();
        int xor = a1 ^ a2 ^ a3;
        int amount = 0;
        String thePile = null;
        if (xor != 0)
        {

            if (pileA.getSize() != 0 && pileB.getSize() != 0 && pileC.getSize() != 0) {
                if ((a1 ^ xor) < a1) {
                    amount = a1 - (a1 ^ xor);
                    thePile = "Pile A";
                    pileA.remove(amount);
                } else if ((a2 ^ xor) < a2) {
                    amount = a2 - (a2 ^ xor);
                    thePile = "Pile B";
                    pileB.remove(amount);
                } else if ((a3 ^ xor) < a3) {
                    amount = a3 - (a3 ^ xor);
                    thePile = "Pile c";
                    pileC.remove(amount);
                } else if (a1 == a2) {
                    amount = a3;
                    thePile = "pile C";
                    pileC.remove(amount);
                } else if (a1 == a3) {
                    amount = a2;
                    thePile = "pile B";
                    pileB.remove(amount);
                } else if (a3 == a2) {
                    amount = a1;
                    thePile = "pile A";
                    pileA.remove(amount);
                }
            } else if (a1 == 0 && a2 == 0) {
                if (a3 == 1) amount = 1;
                else if (a3 > 1) amount = a3 - 1;
                thePile = "Pile C";
                pileC.remove(amount);
            } else if (a1 == 0 && a3 == 0) {
                if (a2 == 1) amount = 1;
                else if (a2 > 1) amount = a2 - 1;
                thePile = "Pile B";
                pileB.remove(amount);
            } else if (a3 == 0 && a2 == 0) {
                if (a1 == 1) amount = 1;
                else if (a1 > 1) amount = a1 - 1;
                thePile = "Pile A";
                pileA.remove(amount);
            } else if (a1 == 0) {
                if (a2 == 1) {
                    amount = a3;
                    thePile = "Pile C";
                    pileC.remove(amount);
                } else if (a3 == 1) {
                    amount = a2;
                    thePile = "Pile B";
                    pileB.remove(amount);
                } else {
                    amount = a2 - 1;
                    thePile = "pile B";
                    pileB.remove(amount);
                }
            } else if (a2 == 0) {
                if (a1 == 1) {
                    amount = a3;
                    thePile = "Pile C";
                    pileC.remove(amount);
                } else if (a3 == 1) {
                    amount = a1;
                    thePile = "Pile A";
                    pileA.remove(amount);
                } else {
                    amount = a1 - 1;
                    thePile = "pile A";
                    pileA.remove(amount);
                }
            } else if (a3 == 0) {
                if (a1 == 1) {
                    amount = a2;
                    thePile = "Pile B";
                    pileB.remove(amount);
                } else if (a2 == 1) {
                    amount = a1;
                    thePile = "Pile A";
                    pileA.remove(amount);
                } else {
                    amount = a2 - 1;
                    thePile = "pile B";
                    pileB.remove(amount);
                }
            }

            System.out.println("Computer remove " + amount + " from " + thePile);
        } else  computerRandomMove();
        if(done()) System.out.println("Game over. You win");
        else printPile();
    }

    private void computerRandomMove() {
        System.out.println("this is random");
        int chosePile = rnd.nextInt(3) + 1;
        boolean selectTrue = false;
        while (!selectTrue) {
            if (chosePile == 1 && pileA.getSize() == 0) {
                chosePile = rnd.nextInt(3) + 1;
            } else if (chosePile == 2 && pileB.getSize() == 0) {
                chosePile = rnd.nextInt(3) + 1;
            } else if (chosePile == 3 && pileC.getSize() == 0) {
                chosePile = rnd.nextInt(3) + 1;
            } else
                selectTrue = true;
        }

        int amount;

        switch (chosePile) {
        case 1: {
            amount = rnd.nextInt(pileA.getSize()) + 1;
            System.out.println("Computer takes " + amount + " from pile A");
            pileA.remove(amount);
            break;
        }
        case 2: {
            amount = rnd.nextInt(pileB.getSize()) + 1;
            System.out.println("Computer takes " + amount + " from pile B");
            pileB.remove(amount);
            break;
        }
        case 3: {
            amount = rnd.nextInt(pileC.getSize()) + 1;
            System.out.println("Computer takes " + amount + " from pile C");
            pileC.remove(amount);
            break;
        }
        }
//        if(done()) System.out.println("Game over. You win");
//        else printPile();
    }

    public boolean done() {
        boolean finish = false;
        if (pileA.getSize() > 0 || pileB.getSize() > 0 || pileC.getSize() > 0)
            finish = false;
        if (pileA.getSize() == 0 && pileB.getSize() == 0 && pileC.getSize() == 0)
            finish = true;
        return finish;
    }

    public void printPile() {
        System.out.println("\nA         B        C");
        System.out.println(pileA.getSize() + "        " + pileB.getSize() + "       " + pileC.getSize());
    }

}
