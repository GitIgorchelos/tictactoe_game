package com.igorkorolev.spring.tictactoe;

import java.util.Scanner;

public class TicTacToeApplication {
    public static Scanner scanner;
    public static char[][]map;
    public static final int MAP_SIZE = 3;
    public static final char EMPTY_FIELD = '*';
    public static final char X_FIELD = 'X';
    public static final char O_FIELD = 'O';

    public static void main(String[] args) {
        init();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if(checkWin(X_FIELD)){
                System.out.println("Игра завершина. Победил игрок");
                break;
            }
            if(checkDraft()){
                System.out.println("Игра завершина. Ничья");
                break;
            }
            aiTurn();
            printMap();
            if(checkWin(O_FIELD)){
                System.out.println("Игра завершина. Победил компьютер");
                break;
            }
            if (checkDraft()){
                System.out.println("Игра завершина. Ничья");
                break;
            }
        }
    }

    public static boolean checkWin(char playerField) {
        // Проверка горизонтальных линий
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == playerField && map[i][1] == playerField && map[i][2] == playerField) {
                return true;
            }
        }
        // Проверка вертикальных линий
        for (int j = 0; j < 3; j++) {
            if (map[0][j] == playerField && map[1][j] == playerField && map[2][j] == playerField) {
                return true;
            }
        }
        // Проверка диагоналей
        if (map[0][0] == playerField && map[1][1] == playerField && map[2][2] == playerField) {
            return true;
        }
        if (map[0][2] == playerField && map[1][1] == playerField && map[2][0] == playerField) {
            return true;
        }
        // Если ни одна из проверок не выполнилась, возвращаем false
        return false;
    }

    public static boolean checkDraft(){
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if(map[i][j] == EMPTY_FIELD){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCellValid(int x, int y){
        if(x < 0 || y < 0 || x >= MAP_SIZE || y >= MAP_SIZE){
            return false;
        }
        if(map[x][y] != EMPTY_FIELD){
            return false;
        }
        return true;
    }

    public static void aiTurn(){
        int x, y;
        System.out.println("Ход компьютера");
        do {
            x = (int)(Math.random() * MAP_SIZE);
            y = (int) (Math.random() * MAP_SIZE);
        } while (!isCellValid(x, y));
        map[x][y] = O_FIELD;
    }
    public static void humanTurn(){
        int x, y;
        do {
            System.out.println("Ход игрока. Введите координаты вашего хода (X, Y)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = X_FIELD;
    }
    public static void printMap(){
        // 0 1 2 3
        // 1 * * *
        // 2 * * *
        // 3 * * *
        for (int i = 0; i <= MAP_SIZE ; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void init(){
        map = new char[MAP_SIZE][MAP_SIZE];
        for(int i = 0; i < MAP_SIZE; i++){
            for(int j = 0; j < MAP_SIZE; j++){
                map[i][j] = EMPTY_FIELD;
            }
        }
        scanner = new Scanner(System.in);
    }
}
