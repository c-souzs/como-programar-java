package course.game.chessGame;

import course.game.boardGame.Position;
import course.game.chessGame.exceptions.ChessException;

// Essa classe controla a possicao da peca na partida, que eh dada por uma letra e um numero
public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if(column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
        }
        this.column = column;
        this.row = row;
    }

    // Retorna um Possition baseado na coluna (letra) linha (numero)
    protected Position toPosition() {
        int rowTo = 8 - row;
        int columnTo = column - 'a';

        return new Position(rowTo, columnTo);
    }

    // Retorna uma possicao de partida baseado em uma posicao de matriz
    protected static ChessPosition fromPosition(Position position) { // Eh static pois nao trabalha nenhum informacao do proprio objeto
        char columnTo = (char)('a' + position.getColumn());
        int rowTo = 8 - position.getRow();

        return new ChessPosition(columnTo, rowTo);
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
