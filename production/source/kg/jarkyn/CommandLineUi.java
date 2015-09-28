package kg.jarkyn;

import java.util.List;
import java.util.stream.Collectors;

public class CommandLineUi implements Ui {
    public  static final String GREETING       = "Welcome to Tictactoe!";
    public  static final String INVALID_OPTION = "Invalid input, please try again";
    private static final String DRAW_STATUS    = "It's a draw";
    private static final String GAME_OVER = "Game over";
    public  static final String GAME_OPTIONS   = "Please select your opponent:\n"  +
                                                 "1 - computer plays first (X)\n"  +
                                                 "2 - computer plays second (O)\n" +
                                                 "3 - play against your friend "   +
                                                 "(first to go plays X)";
    private CommandLine commandLine;

    public CommandLineUi(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    @Override
    public void greet() {
        commandLine.show(GREETING);
    }

    @Override
    public int selectGame(List<Integer> validOptions) {
        return getValidInput(GAME_OPTIONS, validOptions);
    }

    @Override
    public void displayBoard(Board board) {
        Mark[] moves = board.getMoves();
        String[] readableMoves = readableMoves(moves);

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
        commandLine.show(result);
    }

    @Override
    public int getMove(Mark mark, List<Integer> available) {
        return getValidInput(promptForMove(mark), offset(available)) - 1;
    }

    @Override
    public void notifyOfInvalidInput() {
        commandLine.show(INVALID_OPTION);
    }

    @Override
    public void announceWinner(Mark mark) {
       commandLine.show(String.format("Player %s has won this game", mark));
    }

    @Override
    public void announceDraw() {
        commandLine.show(DRAW_STATUS);
    }

    @Override
    public void announceGameOver() {
        commandLine.show(GAME_OVER);
    }

    private int getValidInput(String message, List<Integer> validOptions) {
        commandLine.show(message);
        try {
            int input = Integer.parseInt(commandLine.getInput());
            if (validOptions.contains(input)) {
                return input;
            } else {
                notifyOfInvalidInput();
                return getValidInput(message, validOptions);
            }
        } catch (NumberFormatException e) {
            notifyOfInvalidInput();
            return getValidInput(message, validOptions);
        }
    }

    private String[] readableMoves(Mark[] moves) {
        String[] readableMoves = new String[moves.length];
        for (int index = 0; index < moves.length; index++) {
            if (moves[index] != Mark.NONE) {
                readableMoves[index] = moves[index].toString();
            } else {
                readableMoves[index] = "" + (index + 1);
            }
        }
        return readableMoves;
    }

    private String promptForMove(Mark mark) {
        return String.format("Player %s, please select your move", mark);
    }

    private List<Integer> offset(List<Integer> available) {
        return available.stream().map(move -> move + 1).collect(Collectors.toList());
    }
}