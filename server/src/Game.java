// import java.lang.ProcessBuilder.Redirect.Type;

// import javax.swing.text.Position;

// import com.badlogic.gdx.math.Vector2;

public class Game {

    /*
     * création de boards
     * vérifie la position des bateaux
     * __________
     *
     * Missile touché ?
     * Fin de jeu ?      * Score
     * ___________
     *
     * Bateaux -> x / y (en bas gauche) -> int
     *            position              -> int
     *            type                  -> int
     *
     *
     */

    // A changer suivant le nombre de lignes / colonnes qu'on utilise
    static int colonnes = 8;
    static int lignes = 11;

    static int scoreMax = lignes * colonnes;

    // Pour créer les plataux des joueurs
    static int [][] BoardPlayerOne = new int [colonnes] [lignes];
    static int [][] BoardPlayerTwo = new int [colonnes] [lignes];

    // Pour créer les plateaux d'attaques des joueurs
    static int [][] BoardAttackPlayerOne = new int [colonnes] [lignes];
    static int [][] BoardAttackPlayerTwo = new int [colonnes] [lignes];

    // Pour tout initialiser à 0 lors du lancement de la session
    // if case = 0 -> water
    public static void initializeBoards() {
        int i, j;

        for (i = 0; i < lignes; i++) {
            for (j = 0; j < colonnes; j++) {
                BoardPlayerOne[j][i] = 0;
                BoardPlayerTwo[j][i] = 0;
                BoardAttackPlayerOne[j][i] = 0;
                BoardAttackPlayerTwo[j][i] = 0;
            }
        }
    }

    // Pas intéressant
    // Pour montrer les plateaux de jeu (pour les programmeurs)
    public static void printBoard(int[][] board) {
        int i, j;

        for (i = 0; i < lignes; i++) {
            for (j = 0; j < colonnes; j++) {
                System.out.print("[ " + board[j][i] + " ] ");
            }
            System.out.println();
        }
    }

    // Pour placer les bateaux dans le Board du Joueur 1
    public static void CanPlaceBoatBoardOne (int colonneBoat, int ligneBoat, int direction, int type) {
        switch (type) {
            // 1 = Very Small (2 cases)
            case 1:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 1 || ligneBoat >= lignes) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 1] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 2) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }

                break;

            // 2 = Small (4 cases)
            case 2:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 3 || ligneBoat >= lignes) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 2] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 3] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 4) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 2] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 3] [ligneBoat] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }

                break;

            // 3 = Medium (4 cases)
            case 3:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 3 || ligneBoat >= lignes) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 2] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 3] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 4) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 2] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 3] [ligneBoat] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }

                break;

            // 4 = Big (3 cases x 2 cases)
            case 4:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 2 || ligneBoat >= lignes ||
                            colonneBoat < 0 || colonneBoat > colonnes - 2) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 2] != 0 ||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat] != 0||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat + 1] != 0 ||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat + 2] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 3 ||
                            ligneBoat < 1 || ligneBoat >= lignes) return;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerOne [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat + 2] [ligneBoat] != 0 ||
                                BoardPlayerOne [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerOne [colonneBoat + 1] [ligneBoat + 1] != 0 ||
                                BoardPlayerOne [colonneBoat + 2] [ligneBoat + 1] != 0) return;
                        else {
                            PlaceBoatBoardOne (colonneBoat, ligneBoat, direction, type);
                            return;
                        }
                    }
                }

                break;

            default:
                break;
        }

    }

    // Pour placer les bateaux du joueur 1 pour le serveur
    public static void PlaceBoatBoardOne (int x, int y, int direction, int type) {
        int i;

        switch (type) {
            // 1 = Very Small (2 cases)
            case 1:
                // 0 --> Vertical
                if (direction == 0) for (i = 0; i < 2; i++) BoardPlayerOne [x + i] [y] = 1;
                    // 1 --> Horizontal
                else if (direction == 1) for (i = 0; i < 2; i++) BoardPlayerOne [x] [y + i] = 1;

                break;

            // 2 = Small (4 cases)
            case 2:
                // 0 --> Vertical
                if (direction == 0) for (i = 0; i < 4; i++) BoardPlayerOne [x + i] [y] = 2;
                    // 1 --> Horizontal
                else if (direction == 1) for (i = 0; i < 4; i++) BoardPlayerOne [x] [y + i] = 2;

                break;

            // 3 = Medium (4 cases)
            case 3:
                // 0 --> Vertical
                if (direction == 0) for (i = 0; i < 4; i++) BoardPlayerOne [x + i] [y] = 3;
                    // 1 --> Horizontal
                else if (direction == 1) for (i = 0; i < 4; i++) BoardPlayerOne [x] [y + i] = 3;

                break;

            // 4 = Big (3 x 2 cases)
            case 4:
                // 0 --> Vertical
                if (direction == 0) {
                    for (i = 0; i < 3; i++) BoardPlayerOne [x + i] [y] = 4;
                    for (i = 0; i < 2; i++) BoardPlayerOne [x] [y + i] = 4;
                }
                // 1 --> Horizontal
                if (direction == 1) {
                    for (i = 0; i < 3; i++) BoardPlayerOne [x] [y + i] = 4;
                    for (i = 0; i < 2; i++) BoardPlayerOne [x + i] [y] = 4;
                }

                break;

            default:
                break;
        }
    }

    // Pour placer les bateaux dans le Board du Joueur 1
    public static boolean CanPlaceBoatBoardTwo (int colonneBoat, int ligneBoat, int direction, int type) {
        switch (type) {
            // 1 = Very Small (2 cases)
            case 1:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 1 || ligneBoat >= lignes) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 1] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 2) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }

                break;

            // 2 = Small (4 cases)
            case 2:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 3 || ligneBoat >= lignes) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 2] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 3] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 4) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 2] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 3] [ligneBoat] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }

                break;

            // 3 = Medium (4 cases)
            case 3:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 3 || ligneBoat >= lignes) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 2] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 3] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 4) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 2] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 3] [ligneBoat] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }

                break;

            // 4 = Big (3 cases x 2 cases)
            case 4:
                // direction Verticale
                if (direction == 0) {
                    // On vérifie si le bateau peut être placé
                    if (ligneBoat < 2 || ligneBoat >= lignes ||
                            colonneBoat < 0 || colonneBoat > colonnes - 2) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> ligneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 2] != 0 ||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat] != 0||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat + 1] != 0 ||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat + 2] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }
                // direction Horizontale
                else if (direction == 1) {
                    // On vérifie si le bateau peut être placé
                    if (colonneBoat < 0 || colonneBoat > colonnes - 3 ||
                            ligneBoat < 1 || ligneBoat >= lignes) return false;
                    else {
                        // On regarde si autre chose est à l'endroit indiquer
                        // Attention !! --> colonneBoat peut être le problème
                        if (BoardPlayerTwo [colonneBoat] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat + 2] [ligneBoat] != 0 ||
                                BoardPlayerTwo [colonneBoat] [ligneBoat + 1] != 0 ||
                                BoardPlayerTwo [colonneBoat + 1] [ligneBoat + 1] != 0 ||
                                BoardPlayerTwo [colonneBoat + 2] [ligneBoat + 1] != 0) return false;
                        else {
                            PlaceBoatBoardTwo (colonneBoat, ligneBoat, direction, type);
                            return true;
                        }
                    }
                }

                break;

            default:
                break;
        }

        return false;
    }

    // Pour placer les bateaux du joueur 1 pour le serveur
    public static void PlaceBoatBoardTwo (int x, int y, int direction, int type) {
        int i;

        switch (type) {
            // 1 = Very Small (2 cases)
            case 1:
                // 0 --> Vertical
                if (direction == 0) for (i = 0; i < 2; i++) BoardPlayerTwo [x + i] [y] = 1;
                    // 1 --> Horizontal
                else if (direction == 1) for (i = 0; i < 2; i++) BoardPlayerTwo [x] [y + i] = 1;

                break;

            // 2 = Small (4 cases)
            case 2:
                // 0 --> Vertical
                if (direction == 0) for (i = 0; i < 4; i++) BoardPlayerTwo [x + i] [y] = 2;
                    // 1 --> Horizontal
                else if (direction == 1) for (i = 0; i < 4; i++) BoardPlayerTwo [x] [y + i] = 2;

                break;

            // 3 = Medium (4 cases)
            case 3:
                // 0 --> Vertical
                if (direction == 0) for (i = 0; i < 4; i++) BoardPlayerTwo [x + i] [y] = 3;
                    // 1 --> Horizontal
                else if (direction == 1) for (i = 0; i < 4; i++) BoardPlayerTwo [x] [y + i] = 3;

                break;

            // 4 = Big (3 x 2 cases)
            case 4:
                // 0 --> Vertical
                if (direction == 0) {
                    for (i = 0; i < 3; i++) BoardPlayerTwo [x + i] [y] = 4;
                    for (i = 0; i < 2; i++) BoardPlayerTwo [x] [y + i] = 4;
                }
                // 1 --> Horizontal
                if (direction == 1) {
                    for (i = 0; i < 3; i++) BoardPlayerTwo [x] [y + i] = 4;
                    for (i = 0; i < 2; i++) BoardPlayerTwo [x + i] [y] = 4;
                }

                break;

            default:
                break;
        }
    }

    // Voir si le missile est possible
    public static boolean IsMissileTouch (int player, int colonneMissile, int ligneMissile) {
        switch (player) {
            // Joueur 1
            case 1:
                // Tester si le missile a déjà été envoyé ici
                if (BoardAttackPlayerOne [colonneMissile] [ligneMissile] != 0) return false;
                else {
                    // A compléter
                    return true;
                }

                // Joueur 2
            case 2:
                // Tester si le missile a déjà été envoyé ici
                if (BoardAttackPlayerTwo [colonneMissile] [ligneMissile] != 0) return false;
                else {
                    // A compléter
                    return true;
                }

            default:
                break;
        }

        return false;
    }

    public static void Attack (int player, int colonneMissile, int ligneMissile) {
        switch (player) {
            // Tour du joueur 1
            case 1:
                // Missile Missing
                if (BoardPlayerTwo [colonneMissile] [ligneMissile] != 0) BoardAttackPlayerOne [colonneMissile] [ligneMissile] = 1;
                    // Missile Touching Boat
                else BoardAttackPlayerOne [colonneMissile] [ligneMissile] = scoreMax;
                break;

            // Tour opponent
            case 2:
                // Missile Missing
                if (BoardPlayerOne [colonneMissile] [ligneMissile] != 0) BoardAttackPlayerTwo [colonneMissile] [ligneMissile] = 1;
                    // Missile Touching Boat
                else BoardAttackPlayerTwo [colonneMissile] [ligneMissile] = scoreMax;
                break;

            default:
                break;
        }
    }

    public static boolean EndGame () {
        int i, j;
        int scoreJoueur1 = 0;
        int scoreJoueur2 = 0;

        for (i = 0; i < lignes; i++) {
            for (j = 0; j < colonnes; j++) {
                scoreJoueur1 = BoardAttackPlayerOne[j][i];
                scoreJoueur2 = BoardAttackPlayerTwo[j][i];
            }
        }

        if (scoreJoueur1 == 16 * scoreMax) return true;
        if (scoreJoueur2 == 16 * scoreMax) return true;

        return false;
    }


    // Pour faire des tests et ainsi tester comment le programme réagit
    public static void main(String[] args) {

        initializeBoards();
        // System.out.println("Board Attack P1");
        // printBoard(BoardAttackPlayerOne);
        // System.out.println("\nBoard Attack P2");
        // printBoard(BoardAttackPlayerTwo);
        System.out.println("\nBoard P1");
        printBoard(BoardPlayerOne);
        CanPlaceBoatBoardOne (1, 6, 1, 1);
        System.out.println("\nBoard P1");
        printBoard(BoardPlayerOne);
        // System.out.println("\nBoard P2");
        // printBoard(BoardPlayerTwo);
        System.out.println();
    }
}
