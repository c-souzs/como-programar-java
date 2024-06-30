package course.game.chessGame.exceptions;

import course.game.boardGame.exceptions.BoardException;

public class ChessException extends BoardException {
    public ChessException(String message) {
        super(message);
    }
}
