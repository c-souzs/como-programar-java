package course.game.chessGame;

import course.game.boardGame.Board;
import course.game.boardGame.Piece;
import course.game.boardGame.Position;
import course.game.chessGame.enums.Color;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);

        return piece != null && piece.getColor() != color;
    }

    public Color getColor() {
        return color;
    }

    protected void increaseMoveCount() {
        moveCount++;
    }

    protected void decreaseMoveCount() {
        moveCount--;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
