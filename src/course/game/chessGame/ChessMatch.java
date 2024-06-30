package course.game.chessGame;

import course.game.boardGame.Board;
import course.game.boardGame.Piece;
import course.game.boardGame.Position;
import course.game.chessGame.enums.Color;
import course.game.chessGame.exceptions.ChessException;
import course.game.chessGame.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private List<Piece> piecesOnTheBoard;
    private List<Piece> capturedPieces;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public ChessMatch() {
        this.board = new Board(8, 8); // Cria o tabuleiro

        turn = 1;
        currentPlayer = Color.WHITE;
        piecesOnTheBoard = new ArrayList<>();
        capturedPieces = new ArrayList<>();

        initialSetup(); // Cria e posiciona as pecas no tabuleiro
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
    }

    // Ele adiciona uma peca, dada uma posicao de partida
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    // Dada uma posicao inicial e final de tabuleiro, ele trabalha com a convercao para mover e capturar as pecas
    public ChessPiece performChessMove(ChessPosition source, ChessPosition target) {
        Position sourcePosition = source.toPosition();
        Position targetPosition = target.toPosition();

        validateSourcePosition(sourcePosition);
        validateTargetPosition(sourcePosition, targetPosition);

        Piece captured = makeMove(sourcePosition, targetPosition);

        if(testCheck(currentPlayer)) {
            undoMove(sourcePosition, targetPosition, captured);
            throw new ChessException("You can't put yourself in check");
        }

        ChessPiece movedPiece = (ChessPiece)board.piece(targetPosition);

        promoted = null;
        if(movedPiece instanceof Pawn && (movedPiece.getColor() == Color.WHITE && targetPosition.getRow() == 0 || movedPiece.getColor() == Color.BLACK && targetPosition.getRow() == 7)) {
            promoted = movedPiece;

            promoted = replacePromotePiece('Q');
        }

        check = testCheck(opponent(currentPlayer));

        if(testCheckmate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
            enPassantVulnerable = movedPiece;
        } else {
            enPassantVulnerable = null;
        }

        return  (ChessPiece) captured;
    }
    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) { // Verifica se existe uma peca na possicao inicial para serm ovida
            throw new ChessException("There is no piece on source position.");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { // Verifica se o jogador esta movendo uma peca que pertence a ele
            throw new ChessException("The chosen piece is not yours.");
        }
        if(!board.piece(position).isThereAnyPossibleMove()) { // Verifica se a peca tem algum movimento possivel
            throw new ChessException("There is no moves for the chosen piece.");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target)) { // A peca de destino eh um movimento possivel para a peca de origem?
            throw new ChessException("The chosen piece can't move to target position.");
        }
    }

    public Piece makeMove(Position source, Position target) {
        ChessPiece moved = ((ChessPiece) board.removePiece(source));
        moved.increaseMoveCount();

        Piece captured = board.removePiece(target);
        board.placePiece(moved, target);
        if(captured != null) {
            piecesOnTheBoard.remove(captured);
            capturedPieces.add(captured);
        }

        if (moved instanceof King && target.getColumn() == source.getColumn() + 2) {
            Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
            Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
            ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
            board.placePiece(rook, targetRook);
            rook.increaseMoveCount();
        }

        if (moved instanceof King && target.getColumn() == source.getColumn() - 2) {
            Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
            Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
            ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
            board.placePiece(rook, targetRook);
            rook.increaseMoveCount();
        }

        if(moved instanceof Pawn && source.getColumn() != target.getColumn() && captured == null) {
            Position pawnPosition = new Position(
                    moved.getColor() == Color.WHITE ? target.getRow() + 1 : target.getRow() - 1,
                    target.getColumn()
            );

            captured = board.removePiece(pawnPosition);
            capturedPieces.add(captured);
            piecesOnTheBoard.remove(captured);
        }

        return captured;
    }

    public void undoMove(Position source, Position target, Piece captured) {
        ChessPiece initialSource = ((ChessPiece) board.removePiece(target));
        initialSource.decreaseMoveCount();

        board.placePiece(initialSource, source);

        if(captured != null) {
            board.placePiece(captured, target);
            capturedPieces.remove(captured);
            piecesOnTheBoard.add(captured);
        }

        if (initialSource instanceof King && target.getColumn() == source.getColumn() + 2) {
            Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
            Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
            ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
            board.placePiece(rook, sourceRook);
            rook.decreaseMoveCount();
        }

        if (initialSource instanceof King && target.getColumn() == source.getColumn() - 2) {
            Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
            Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
            ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
            board.placePiece(rook, sourceRook);
            rook.decreaseMoveCount();
        }

        if(initialSource instanceof Pawn && source.getColumn() != target.getColumn() && captured == enPassantVulnerable) {
            ChessPiece pawn = (ChessPiece)board.removePiece(target);
            Position pawnPosition = new Position(
                    initialSource.getColor() == Color.WHITE ? 3 : 4,
                    target.getColumn()
            );
            board.placePiece(pawn, pawnPosition);
        }
    }

    private boolean testCheck(Color color) {
        Position king = king(color)
                .getChessPosition()
                .toPosition();

        List<Piece> opponentPieces = piecesOnTheBoard
                .stream()
                .filter(x -> ((ChessPiece)x).getColor() == opponent(color))
                .toList();

        for (Piece piece: opponentPieces) {
            boolean[][] moves = piece.possibleMoves();

            if(moves[king.getRow()][king.getColumn()]) return true; // Existe alguma peca do oponente que eh um movimento possivel para a posicao do rei do jogador atual?
        }

        return false;
    }

    private ChessPiece king(Color color) { // Retorna o Rei de uma determinada cor
        List<Piece> piecesFilter = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).toList();

        for (Piece piece: piecesFilter) {
            if(piece instanceof King) {
                return (ChessPiece) piece;
            }
        }

        throw new IllegalStateException("There is no " + color + " king on the board.");
    }

    private void nextTurn() {
        turn++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public ChessPiece replacePromotePiece(char type) {
        if (promoted == null) {
            throw new IllegalArgumentException("There is no piece to be promoted");
        }
        if(type != 'B' && type != 'N' && type != 'R' && type != 'Q') {
            return promoted;
        }

        Position position = promoted.getChessPosition().toPosition();
        Piece piece = board.removePiece(position);
        piecesOnTheBoard.remove(piece);

        ChessPiece newPiece = newPiece(type, promoted.getColor());
        board.placePiece(newPiece, position);
        piecesOnTheBoard.add(newPiece);

        return newPiece;
    }

    private boolean testCheckmate(Color color) {
        if(!testCheck(color)) return false;

        List<Piece> myPiecesColor = piecesOnTheBoard
                .stream()
                .filter(x -> ((ChessPiece)x).getColor() == color)
                .toList();
        for (Piece piece: myPiecesColor) {
            boolean[][] moves = piece.possibleMoves();

            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if(moves[i][j]) {
                        Position source = ((ChessPiece)piece).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece captured = makeMove(source, target);

                        boolean auxCheck = testCheck(color);
                        undoMove(source, target, captured);

                        if(!auxCheck) return false;
                    }
                }
            }
        }

        return true;
    }

    private ChessPiece newPiece(char type, Color color) {
        ChessPiece piece = switch (type) {
            case 'B' -> new Bishop(board, color);
            case 'N' -> new Knight(board, color);
            case 'R' -> new Rook(board, color);
            default -> {
                yield new Queen(board, color);
            }
        };

        return piece;
    }

    private Color opponent(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public boolean[][] possibleMove(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);

        return board.piece(position).possibleMoves();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] piecesChess = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                piecesChess[i][j] = (ChessPiece) board.piece(i, j); // Downcasting
            }
        }

        return piecesChess;
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckmate() {
        return checkMate;
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public ChessPiece getPromoted() {
        return promoted;
    }
}
