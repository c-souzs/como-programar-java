package course.game.boardGame;

// Controla a posicao na matriz de pecas
public class Position {
    private int row, column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("%d, %d", row, column);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
