package chapterSix;

import java.security.SecureRandom;

public class Craps {
    private enum Status { CONTINUE, WON, LOST };
    private Status statusGame;
    private int myPoint;
    private static final SecureRandom randomNumbers = new SecureRandom();

    public Craps() {
        statusGame = Status.LOST;
        myPoint = 0;
    }

    /*
    * Joga ambos os dados exibindo, respectivamente, seus valores e a soma deles.
    *
    * @return Retorna a soma de ambos os dados
    * */
    private int rollDices() {
        int value1 = 1 + randomNumbers.nextInt(6);
        System.out.println("Primeiro dado: " + value1);
        int value2 = 1 + randomNumbers.nextInt(6);
        System.out.println("Segundo dado: " + value2);
        int amount = value1 + value2;

        System.out.println("----------- Valor da pontuacao: " + amount + " -----------");

        return amount;
    }

    /*
    * @params amountFirst Valor da soma dos dados na primeira rodada
    *
    * Ele verifica os valores da primeira rodada e define a sequencia do jogo
    * */
    private void checkFirstRound(int amountFirst) {
        switch (amountFirst) {
            case 7:
            case 11:
                System.out.println("Voce perdeu na primeira rodada :(");
                statusGame = Status.WON;
                break;
            case 2:
            case 3:
            case 12:
                System.out.println("Voce ganhou na primeira rodada :)");
                statusGame = Status.LOST;
                break;
            default:
                myPoint = amountFirst;
                System.out.println("*********** Para ganhar voce precisa atingir a pontuacao de " + amountFirst + " ***********");
                statusGame = Status.CONTINUE;
                break;
        }
    }

    /*
    * Joga os dados para as proxima rodadas ate que o jogo chegue no final
    * */
    private void nextRounds() {
        int amountNextRounds;

        amountNextRounds = rollDices();

        if(amountNextRounds == myPoint) {
            System.out.println("Você atingiu sua pontuacao apos a primeira rodada :)");
            statusGame = Status.WON;
        } else if (amountNextRounds == 7) {
            System.out.println("Voce atingiu a pontuacao 7 antes da sua pontuacao inicial :(");
            statusGame = Status.LOST;
        }
    }
    public void play() {
        statusGame = Status.CONTINUE;

        boolean firstRound = true;

        do {
            if(firstRound) {
                int amount = rollDices();
                checkFirstRound(amount);
                firstRound = false;
            } else {
                nextRounds();
            }
        } while (statusGame == Status.CONTINUE);

    }
}
