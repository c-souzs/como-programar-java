package course.game.chessGame.pieces;

import course.game.boardGame.Board;
import course.game.boardGame.Position;
import course.game.chessGame.ChessPiece;
import course.game.chessGame.enums.Color;

public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        // top
        aux.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() - 1, position.getColumn());
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // right
        aux.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(position.getRow(), aux.getColumn() + 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // bottom
        aux.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() + 1, position.getColumn());
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // left
        aux.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(position.getRow(), aux.getColumn() - 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // top right
        aux.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() - 1, aux.getColumn() - 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // top left
        aux.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() - 1, aux.getColumn() + 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // bottom left
        aux.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() + 1, aux.getColumn() + 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // bottom right
        aux.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
            aux.setValues(aux.getRow() + 1, aux.getColumn() - 1);
        }
        if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        return moves;
    }
}
