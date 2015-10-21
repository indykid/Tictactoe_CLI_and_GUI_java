package kg.jarkyn.CLI;

import kg.jarkyn.Core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineUi implements Ui {
    public  static final String GREETING       = "Welcome to Tictactoe!";
    public  static final String INVALID_OPTION = "Invalid input, please try again";
    public  static final String DRAW_STATUS    = "It's a draw";
    public  static final String GAME_OVER      = "GAME OVER";
    public  static final String GAME_SELECTION_MESSAGE = gameSelectionMessage();
    private static final String NEW_LINE = "\n";
    private CommandLine commandLine;
    private Game game;

    public CommandLineUi(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public static void main(String[] args) {
        Ui ui = new CommandLineUi(new CommandLine(System.in, System.out));
        GameOption gameOption = GameOption.parse(ui.selectGame());

        ui.setGame(GameMaker.makeGame(gameOption, ui));
        ui.playGame();
    }

    @Override
    public void playGame() {
        while (gameIsActive()) {
            game.playTurn();
        }
        announceGameOver();
        announceResult();
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int getMove(List<Integer> available) {
        displayBoard(game.getBoard());
        return getValidInput(promptForMove(game.getCurrentPlayer().getMark()), offset(available)) - 1;
    }

    @Override
    public int selectGame() {
        greet();
        return getValidInput(GAME_SELECTION_MESSAGE, validGameOptions());
    }

    @Override
    public void announceDraw() {
        show(DRAW_STATUS);
    }

    @Override
    public void announceGameOver() {
        displayBoard(game.getBoard());
        show(GAME_OVER);
    }

    @Override
    public boolean hasHumanMove() {
        return true;
    }

    private void greet() {
        show(GREETING);
    }

    private void show(String message) {
        commandLine.show(message + NEW_LINE);
    }

    private void displayBoard(Board board) {
        String[] readableMoves = readableMoves(board.getMoves());

        String result = String.format(
                "  %s |  %s |  %s \n" +
                "--------------\n" +
                "  %s |  %s |  %s \n" +
                "--------------\n" +
                "  %s |  %s |  %s \n",
                readableMoves[0],
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

    private static String gameSelectionMessage() {
        ArrayList<String> gameSelectionEntries = new ArrayList<>();
        gameSelectionEntries.add("Please select your opponent:");
        gameSelectionEntries.addAll(GameOption.OPTIONS.entrySet()
                .stream()
                .map(entry -> entry.getKey().toString() + entry.getValue())
                .collect(Collectors.toList()));
        return String.join("\n", gameSelectionEntries);
    }

    private List<Integer> validGameOptions() {
        return new ArrayList<>(GameOption.OPTIONS.keySet());
    }

    private int getValidInput(String message, List<Integer> validOptions) {
        int input = readInt(message);
        while (!validOptions.contains(input)) {
            notifyOfInvalidInput();
            input = getValidInput(message, validOptions);
        }
        return input;
    }

    private int readInt(String prompt) {
        show(prompt);
        try {
            return Integer.parseInt(commandLine.getInput());
        } catch (NumberFormatException e) {
            notifyOfInvalidInput();
            return readInt(prompt);
        }
    }

    private void notifyOfInvalidInput() {
        show(INVALID_OPTION);
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

    private void announceWinner(Mark mark) {
        show(String.format("Player %s has won this game", mark));
    }

    private void announceResult() {
        if (game.isWon()) {
            announceWinner(game.winnerMark());
        } else {
            announceDraw();
        }
    }

    public boolean gameIsActive() {
        return !game.isOver();
    }
}