package com.foneom;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static final int row = 3;
    static final int column = 3;
    static char[][] field = new char[row][column];
    static String msg;
    static boolean run = true;
    static final char PLAYER_1 = 'X';
    static final char PLAYER_2 = '0';
    static final char DEFAULT_VALUE = '*';


    public static void initGameField() {
        System.out.println("This is field of the game. Try put X or 0");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                field[i][j] = '*';
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initSelect(int x, int y, char var) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                field[x][y] = var;
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void turnPC() {
        Random random = new Random();
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        if (!fieldFull() || !validCell(x, y)) {
            initSelect(x, y, PLAYER_2);
        } else {
            System.out.println("Field is full");
        }
    }

    public static void turnHuman() {
        System.out.println("Enter 'X' coordinates" +
                "\nx: ");
        int x = scanner.nextInt();
        System.out.println("y: ");
        int y = scanner.nextInt();
        if (!fieldFull() || !validCell(x, y)) {
            initSelect(x, y, PLAYER_1);
        } else {
            System.out.println("Field is full");
        }
    }

    public static boolean fieldFull() {
        boolean flag = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (field[i][j] == '*') {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static boolean validCell(int x, int y) {
        boolean flag = true;
        if (x > row || y > column || x < 0 || y < 0) {
            System.out.println("Coordinates are incorrect");
            return false;
        }
          return field[x][y] == '*';
        }


        public static void startGame () {
            initGameField();
            while (run) {
                turnHuman();
                if (checkWin(PLAYER_1)) {
                    System.out.println("X wins!!");
                    break;
                }
                System.out.println("Enter '0' coordinates");
                turnPC();
                if (checkWin(PLAYER_2)) {
                    System.out.println("0 wins!!");
                    break;
                }
            }
        }

        public static boolean checkWin ( char var){
            boolean flag = false;
            for (int i = 0; i < 3; i++) {
                if (
                        (field[0][i] == var && field[1][i] == var && field[2][i] == var)
                                || (field[i][0] == var && field[i][1] == var && field[i][2] == var)
                                || (field[0][0] == var && field[1][1] == var && field[2][2] == var)
                                || (field[0][2] == var && field[1][1] == var && field[2][0] == var)
                ) {
                    flag = true;
                }
            }
            return flag;
        }

        public static void initMenu () {
            while (run) {
                System.out.println("Welcome to the TicTacTue game!!"
                        + "\nMenu:" +
                        "\n1.Start game" +
                        "\n2.Exit game");
                msg = scanner.nextLine();
                if (msg.equals("1")) {
                    startGame();
                    break;
                } else if (msg.equals("2")) {
                    run = false;
                    break;
                } else {
                    System.out.println("Your select is incorrect. Try again!");
                }
            }
        }


        public static void main (String[]args){
            initMenu();

        }


    }
