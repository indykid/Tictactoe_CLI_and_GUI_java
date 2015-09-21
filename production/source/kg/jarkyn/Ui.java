package kg.jarkyn;

public class Ui {
    public static final String GREETING = "Welcome to Tictactoe!";
    public static final String INVALID_OPTION = "Invalid input, please try again";
    private Cli cli;
    public static final String GAME_OPTIONS = "Please select your opponent:\n" +
            "1 - computer plays first (X)\n" +
            "2 - computer plays second (O)\n" +
            "3 - play against your friend (first to go plays X)";

    public Ui(Cli cli){
        this.cli = cli;
    }

    public void greet() {
        cli.show(GREETING);
    }

    public void displayBoard(Board board) {
        Mark[] moves = board.getMoves();
        String[] readableMoves = new String[moves.length];
        for (int index = 0; index < moves.length; index++) {
           if (moves[index] != Mark.NONE) {
               readableMoves[index] = moves[index].toString();
           } else {
               readableMoves[index] = "" + (index + 1);
           }
        }

        String result = String.format("  %s |  %s |  %s \n" +
                                      "--------------\n"    +
                                      "  %s |  %s |  %s \n" +
                                      "--------------\n"    +
                                      "  %s |  %s |  %s \n", readableMoves[0],
                                                             readableMoves[1],
                                                             readableMoves[2],
                                                             readableMoves[3],
                                                             readableMoves[4],
                                                             readableMoves[5],
                                                             readableMoves[6],
                                                             readableMoves[7],
                                                             readableMoves[8]);

        cli.show(result);
    }

    public int getMove(Mark mark) {
        return getValidInput(promptForMove(mark)) - 1;
    }

    public int selectGame() {
        return getValidInput(GAME_OPTIONS);
    }

    public void notifyOfInvalidInput() {
        cli.show(INVALID_OPTION);
    }

    private String promptForMove(Mark mark) {
        return String.format("Player %s, please select your move", mark);
    }

    private int getValidInput(String message) {
        cli.show(message);
        try {
            return Integer.parseInt(cli.getInput());
        } catch (NumberFormatException e) {
            cli.show(INVALID_OPTION);
            return getValidInput(message);
        }
    }
}
