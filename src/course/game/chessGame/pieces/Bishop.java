package course.game.chessGame.pieces;

import course.game.boardGame.Board;
import course.game.boardGame.Position;
import course.game.chessGame.ChessPiece;
import course.game.chessGame.enums.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        // top right
        aux.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() - 1, aux.getColumn() - 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
        }

        // top left
        aux.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() - 1, aux.getColumn() + 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
        }

        // bottom left
        aux.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() + 1, aux.getColumn() + 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
        }

        // bottom right
        aux.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() + 1, aux.getColumn() - 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            mat[aux.getRow()][aux.getColumn()] = true;
        }

        return mat;
    }
}
