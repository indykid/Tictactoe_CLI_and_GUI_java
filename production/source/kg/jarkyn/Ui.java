package kg.jarkyn;

public class Ui {
    private Cli cli;

    public Ui(Cli cli){
        this.cli = cli;
    }

    public void greet() {
        cli.show("Welcome to Ticatactoe!\n"             +
                 "This version requires two players.\n" +
                 "First player is assigned mark X,\n"   +
                 "second player is assigned mark O");
    }

    public void displayBoard(Board board) {
        String[] moves = board.getMoves();
        String[] readableMoves = new String[moves.length];
        for (int index = 0; index < moves.length; index++) {
           if (moves[index] != null) {
               readableMoves[index] = moves[index];
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

    public int getMove(String mark) {
        cli.show(String.format("Player %s, please select your move", mark));
        return Integer.parseInt(cli.getInput()) - 1;
    }
}
