package course.game.chessGame.pieces;

import course.game.boardGame.Board;
import course.game.boardGame.Position;
import course.game.chessGame.ChessPiece;
import course.game.chessGame.enums.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);

        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position[] positionsAux = {
            new Position(position.getRow() - 2, position.getColumn() + 1),
            new Position(position.getRow() -1, position.getColumn() + 2),
            new Position(position.getRow() + 1, position.getColumn() + 2),
            new Position(position.getRow() + 2, position.getColumn() + 1),
            new Position(position.getRow() + 2, position.getColumn() - 1),
            new Position(position.getRow() + 1, position.getColumn() - 2),
            new Position(position.getRow() - 1, position.getColumn() -2),
            new Position(position.getRow() - 2, position.getColumn() - 1)
        };

        for (Position positionAux: positionsAux) {
            if(getBoard().positionExists(positionAux) && canMove(positionAux)) {
                moves[positionAux.getRow()][positionAux.getColumn()] = true;
            }
        }

        return moves;
    }
}
