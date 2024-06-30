package course.game.chessGame.pieces;

import course.game.boardGame.Board;
import course.game.boardGame.Position;
import course.game.chessGame.ChessMatch;
import course.game.chessGame.ChessPiece;
import course.game.chessGame.enums.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        boolean hasWhite = getColor() == Color.WHITE;

        aux.setValues(hasWhite ? position.getRow() - 1 : position.getRow() + 1, position.getColumn());

        if(getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) { // Verifica a posição top
            moves[aux.getRow()][aux.getColumn()] = true;

            aux.setValues(hasWhite ? position.getRow() - 2 : position.getRow() + 2, position.getColumn());

            if(getMoveCount() == 0 && getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
        }

        aux.setValues(hasWhite ? position.getRow() - 1 : position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().positionExists(aux) && isThereOpponentPiece(aux)) { // Verifica a diagonal da esquerda
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(hasWhite ? position.getRow() - 1 : position.getRow() + 1, position.getColumn() + 1);
        if(getBoard().positionExists(aux) && isThereOpponentPiece(aux)) { // Verifica a diagonal da direita
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        Position aux2 = null;

        if(position.getRow() == 3 || position.getRow() == 4) {
           aux.setValues(position.getRow(), position.getColumn() - 1);
           aux2 = new Position(position.getRow(), position.getColumn() + 1);

           boolean checkAux = getBoard().positionExists(aux) && isThereOpponentPiece(aux) && getBoard().piece(aux) == chessMatch.getEnPassantVulnerable();
           boolean checkAux2 = getBoard().positionExists(aux2) && isThereOpponentPiece(aux2) && getBoard().piece(aux2) == chessMatch.getEnPassantVulnerable();

           if(checkAux) {
               moves[hasWhite ? aux.getRow() - 1 : aux.getRow() + 1][aux.getColumn()] = true;
           } else if (checkAux2) {
               moves[hasWhite ? aux2.getRow() - 1 : aux2.getRow() + 1][aux2.getColumn()] = true;
           }
        }

        return moves;
    }
}
