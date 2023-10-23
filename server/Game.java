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
    public void PlaceBoatBoard1 (int x, int y, int type, int position) {

        switch (type) {
            case 1 :
                if (position == 1) {

                }
        }
    }
}