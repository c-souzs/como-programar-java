package course.game;

import course.game.boardGame.Board;
import course.game.chessGame.ChessMatch;
import course.game.chessGame.ChessPiece;
import course.game.chessGame.ChessPosition;
import course.game.chessGame.exceptions.ChessException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (!chessMatch.getCheckmate()) {
            try {
                UI.printMatch(chessMatch, capturedPieces);
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMove(source);
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece captured = chessMatch.performChessMove(source, target);
                if (captured != null) {
                    capturedPieces.add(captured);
                }
                if (chessMatch.getPromoted() != null) {
                    System.out.println("Enter piece for promotion (B/N/R/Q): ");
                    char type = sc.next().charAt(0);
                    while (type != 'B' && type != 'N' && type != 'R' && type != 'Q') {
                        System.out.println("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                        type = sc.next().charAt(0);
                    }
                    chessMatch.replacePromotePiece(type);
                }
            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Press enter to continue.");
                sc.nextLine();
            }
        }

        UI.printMatch(chessMatch, capturedPieces);
    }
}
