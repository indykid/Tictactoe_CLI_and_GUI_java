package kg.jarkyn;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineUi implements Ui {
    public  static final String GREETING       = "Welcome to Tictactoe!";
    public  static final String INVALID_OPTION = "Invalid input, please try again";
    public  static final String DRAW_STATUS    = "It's a draw";
    public  static final String GAME_OVER      = "GAME OVER";
    private static final String NEW_LINE       = "\n";
    private static final LinkedHashMap<Integer, String>
                                GAME_OPTIONS   = new LinkedHashMap<>();

    static
            {
                GAME_OPTIONS.put(1, " - computer plays first");
                GAME_OPTIONS.put(2, " - computer plays second");
                GAME_OPTIONS.put(3, " - play against your friend (first to go plays X)");
            }
    public  static final String GAME_SELECTION_MESSAGE = gameSelectionMessage();

    private CommandLine commandLine;

    public CommandLineUi(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    @Override
    public void greet() {
        show(GREETING);
    }

    private void show(String message) {
        commandLine.show(message + NEW_LINE);
    }

    @Override
    public int selectGame() {
        return getValidInput(gameSelectionMessage(), validGameOptions());
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
        show(result);
    }

    @Override
    public int getMove(Mark mark, List<Integer> available) {
        return getValidInput(promptForMove(mark), offset(available)) - 1;
    }

    @Override
    public void notifyOfInvalidInput() {
        show(INVALID_OPTION);
    }

    @Override
    public void announceWinner(Mark mark) {
        show(String.format("Player %s has won this game", mark));
    }

    @Override
    public void announceDraw() {
        show(DRAW_STATUS);
    }

    @Override
    public void announceGameOver() {
        show(GAME_OVER);
    }

    private static String gameSelectionMessage() {
        ArrayList<String> gameSelectionEntries = new ArrayList<>();
        gameSelectionEntries.add("Please select your opponent:");
        gameSelectionEntries.addAll(GAME_OPTIONS.entrySet()
                .stream()
                .map(entry -> entry.getKey().toString() + entry.getValue())
                .collect(Collectors.toList()));
        return String.join("\n", gameSelectionEntries);
    }

    private List<Integer> validGameOptions() {
        return new ArrayList<>(GAME_OPTIONS.keySet());
    }

    private int getValidInput(String message, List<Integer> validOptions) {
        show(message);
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
        return String.format("Player %s, please select your move:", mark);
    }

    private List<Integer> offset(List<Integer> available) {
        return available.stream().map(move -> move + 1).collect(Collectors.toList());
    }
}