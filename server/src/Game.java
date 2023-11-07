public class Game {

    // Board with the boats of Player 1
    int[][] BoardPlayerOne = new int [10][10];

    // Hidden board for attack
    int[][] BoardAttackPlayerOne = new int [10][10];
    // Board with the boats of Player 2
    int[][] BoardPlayerTwo = new int [10][10];

    // Hidden board for attack
    int[][] BoardAttackPlayerTwo = new int [10][10];

    // Pour tout initialiser
    private void initializeBoards() {
        int i, j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                BoardPlayerOne[j][i] = 0;
                BoardPlayerTwo[j][i] = 0;
                BoardAttackPlayerOne[j][i] = 0;
                BoardAttackPlayerTwo[j][i] = 0;
            }
        }
    }

/*
    // Pour montrer les plateaux de jeu (pour les programmeurs)
    private void printBoard(int[][] board) {
        int i, j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                System.out.print("[ " + board[j][i] + " ] ");
            }
            System.out.println();
        }
    }
*/

    // x -> colonne / y -> ligne
    // position 1 -> vertical / position 2 -> horizontal
    public void PlaceBoatBoard1 (int x, int y, int type, int position) {

        int i, resultat;

        resultat = 0;

        switch (type) {
            case 1 :
                if (position == 1) {
                    if (y > 5) System.exit (0);
                    else {
                        for (i = 0; i < 5; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 5; i++) BoardPlayerOne[x][y + i] = 1;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 5) System.exit (0);
                    else {
                        for (i = 0; i < 5; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 5; i++) BoardPlayerOne[x + i][y] = 1;
                        else System.exit (0);
                    }
                }
                break;

            case 2 :
                if (position == 1) {
                    if (y > 6) System.exit (0);
                    else {
                        for (i = 0; i < 4; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 4; i++) BoardPlayerOne[x][y + i] = 2;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 6) System.exit (0);
                    else {
                        for (i = 0; i < 4; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 4; i++) BoardPlayerOne[x + i][y] = 2;
                        else System.exit (0);
                    }
                }
                break;

            case 3 :
                if (position == 1) {
                    if (y > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x][y + i] = 3;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x + i][y] = 3;
                        else System.exit (0);
                    }
                }
                break;

            case 4 :
                if (position == 1) {
                    if (y > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x][y + i] = 4;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x + i][y] = 4;
                        else System.exit (0);
                    }
                }
                break;
            
            case 5 :
                if (position == 1) {
                    if (y > 8) System.exit (0);
                    else {
                        for (i = 0; i < 2; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 2; i++) BoardPlayerOne[x][y + i] = 5;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 8) System.exit (0);
                    else {
                        for (i = 0; i < 2; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 2; i++) BoardPlayerOne[x + i][y] = 5;
                        else System.exit (0);
                    }
                }
                break;

            default :
                System.exit (0);
        }
    }

    public void PlaceBoatBoard2 (int x, int y, int type, int position) {

        int i, resultat;

        resultat = 0;

        switch (type) {
            case 1 :
                if (position == 1) {
                    if (y > 5) System.exit (0);
                    else {
                        for (i = 0; i < 5; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 5; i++) BoardPlayerOne[x][y + i] = 1;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 5) System.exit (0);
                    else {
                        for (i = 0; i < 5; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 5; i++) BoardPlayerOne[x + i][y] = 1;
                        else System.exit (0);
                    }
                }
                break;

            case 2 :
                if (position == 1) {
                    if (y > 6) System.exit (0);
                    else {
                        for (i = 0; i < 4; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 4; i++) BoardPlayerOne[x][y + i] = 2;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 6) System.exit (0);
                    else {
                        for (i = 0; i < 4; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 4; i++) BoardPlayerOne[x + i][y] = 2;
                        else System.exit (0);
                    }
                }
                break;

            case 3 :
                if (position == 1) {
                    if (y > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x][y + i] = 3;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x + i][y] = 3;
                        else System.exit (0);
                    }
                }
                break;

            case 4 :
                if (position == 1) {
                    if (y > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x][y + i] = 4;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 7) System.exit (0);
                    else {
                        for (i = 0; i < 3; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 3; i++) BoardPlayerOne[x + i][y] = 4;
                        else System.exit (0);
                    }
                }
                break;

            case 5 :
                if (position == 1) {
                    if (y > 8) System.exit (0);
                    else {
                        for (i = 0; i < 2; i++) resultat = BoardPlayerOne[x][y + i];
                        if (resultat == 0) for (i = 0; i < 2; i++) BoardPlayerOne[x][y + i] = 5;
                        else System.exit (0);
                    }
                }
                else if (position == 2) {
                    if (x > 8) System.exit (0);
                    else {
                        for (i = 0; i < 2; i++) resultat = BoardPlayerOne[x + i][y];
                        if (resultat == 0) for (i = 0; i < 2; i++) BoardPlayerOne[x + i][y] = 5;
                        else System.exit (0);
                    }
                }
                break;

            default :
                System.exit (0);
        }
    }


}