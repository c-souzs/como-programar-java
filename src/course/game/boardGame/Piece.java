package course.game.boardGame;

public abstract class Piece {
    protected Position position;
    private final Board board;

    public Piece(Board board) { // Relacionamento de associacao
        this.board = board;
    }

    // Cada classe filha implementa sua forma de validar as posicoes possiveis
    public abstract boolean[][] possibleMoves();

    // Dada uma posicao de matriz, retorna se eh possivel se mover para ela
    public boolean possibleMove(Position position) {
        // Chama o metodo possibleMoves implementado por alguma subclasse que implemente o mesmo
        return possibleMoves()[position.getRow()][position.getColumn()]; // hook methods
    }

    // Verifica se a peca esta travada, ou seja, sem movimentos possiveis
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Board getBoard() {
        return board;
    }
}
