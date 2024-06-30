package course.game.boardGame;

import course.game.boardGame.exceptions.BoardException;

public class Board {
    private int rows, columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns < 1) {
            throw new BoardException("Error creating board:there must be at least 1 row and 1 column.");
        }
        this.rows = rows;
        this.columns = columns;

        pieces = new Piece[rows][columns];
    }

    // Dada uma linha e coluna, retorna o valor na matriz de pecas
    public Piece piece(int row, int column) {
        if(!positionExists(row, column)) {
            throw new BoardException("Position not on the board.");
        }
        return pieces[row][column];
    }

    // Dada uma Position, retorna o valor na matriz de pecas
    public Piece piece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board.");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public Piece removePiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        if(piece(position) == null) {
            return null;
        }

        Piece pieceAux = piece(position);
        pieceAux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;

        return pieceAux;
    }

    // Valida de a linha e coluna da Possition eh valida para as dimenssoes do tabuleiro
    public boolean positionExists(Position position) {
        int row = position.getRow();
        int column = position.getColumn();

        return positionExists(row, column);
    }

    public boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    // Dada uma peca e uma possicao, acessa a matriz na Possition passada e atribui a peca
    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position: " + position + ".");
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    // Verifica se em uma dada posicao, existe uma peca
    public boolean thereIsAPiece(Position position) {
        if(!positionExists(position)) {
            throw new BoardException("Position not on the board.");
        }
        return piece(position) != null;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
