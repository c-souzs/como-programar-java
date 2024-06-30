package course.game.chessGame.pieces;

import course.game.boardGame.Board;
import course.game.boardGame.Position;
import course.game.chessGame.ChessMatch;
import course.game.chessGame.ChessPiece;
import course.game.chessGame.enums.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);

        return piece == null || piece.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece piece = (ChessPiece)getBoard().piece(position);

        return piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position[] positionsAux = {
                new Position(position.getRow() - 1, position.getColumn()), // top
                new Position(position.getRow() - 1, position.getColumn() + 1), // top right
                new Position(position.getRow() - 1, position.getColumn() - 1), // top left
                new Position(position.getRow(), position.getColumn() + 1), // right
                new Position(position.getRow(), position.getColumn() - 1), // left
                new Position(position.getRow() + 1, position.getColumn()), // bottom
                new Position(position.getRow() + 1, position.getColumn() + 1), // bottom right
                new Position(position.getRow() + 1, position.getColumn() - 1), // bottom left
        };

        for (Position positionAux: positionsAux) {
            if(getBoard().positionExists(positionAux) && canMove(positionAux)) {
                moves[positionAux.getRow()][positionAux.getColumn()] = true;
            }
        }

        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            Position checkRookSmall = new Position(position.getRow(), position.getColumn() + 3);
            Position checkRookLarge = null;
            Position aux1 = new Position(position.getRow(), position.getColumn() + 1);
            Position aux2 = new Position(position.getRow(), position.getColumn() + 2);
            Position aux3 = null;

            if(testRookCastling(checkRookSmall) && getBoard().piece(aux1) == null && getBoard().piece(aux2) == null) {
                moves[position.getRow()][position.getColumn() + 2] = true;
            }

            checkRookLarge = new Position(position.getRow(), position.getColumn() - 4);
            aux1.setValues(position.getRow(), position.getColumn() - 1);
            aux2.setValues(position.getRow(), position.getColumn() - 2);
            aux3 = new Position(position.getRow(), position.getColumn() - 3);

            if(testRookCastling(checkRookLarge) && getBoard().piece(aux1) == null && getBoard().piece(aux2) == null && getBoard().piece(aux3) == null) {
                moves[position.getRow()][position.getColumn() - 2] = true;
            }
        }

        return moves;
    }
}
