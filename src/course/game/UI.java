package course.game;

import course.game.chessGame.ChessMatch;
import course.game.chessGame.ChessPiece;
import course.game.chessGame.ChessPosition;
import course.game.chessGame.enums.Color;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String position = sc.next();
            sc.nextLine();
            char column = position.charAt(0);
            int row = Integer.parseInt(position.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 at to h8.");
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println();
        System.out.printf("Turn: %d %n", chessMatch.getTurn());
        if(!chessMatch.getCheckmate()) {
            System.out.printf("Waiting player: %s %n", chessMatch.getCurrentPlayer());
            if(chessMatch.getCheck()) {
                System.out.println("CHECK!");
            }
        } else {
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }

    }

    private static void printCapturedPieces(List<ChessPiece> capturedPieces) {
        List<ChessPiece> white = capturedPieces.stream().filter(pW -> pW.getColor() == Color.WHITE).toList();
        List<ChessPiece> black = capturedPieces.stream().filter(pB -> pB.getColor() == Color.BLACK).toList();
        System.out.println("Captured pieces:");
        System.out.print(ANSI_WHITE);
        System.out.print("White: ");
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print(ANSI_YELLOW);
        System.out.print("Black: ");
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }

    public static void printBoard(ChessPiece[][] chessPieces){
        for (int i = 0; i < chessPieces.length; i++) {
            System.out.print(8 - i + " ");

            for (int j = 0; j < chessPieces.length; j++) {
                printChessPiece(chessPieces[i][j], false);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h \n");
    }

    public static void printBoard(ChessPiece[][] chessPieces, boolean[][] possibleMoves) {
        for (int i = 0; i < chessPieces.length; i++) {
            System.out.print(8 - i + " ");

            for (int j = 0; j < chessPieces.length; j++) {
                printChessPiece(chessPieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h \n");
    }

    private static void printChessPiece(ChessPiece chessPiece, boolean background) {
        if(background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (chessPiece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (chessPiece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + chessPiece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + chessPiece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
