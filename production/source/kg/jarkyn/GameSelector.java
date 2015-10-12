package kg.jarkyn;

public class GameSelector {
    public static Game makeGame(GameOption gameOption) {
        Game game;
        if (gameOption == GameOption.AI_FIRST) {
            game = new AiFirstGame(new Board());
        } else if (gameOption == GameOption.AI_SECOND) {
            game = new AiSecondGame(new Board());
        } else {
            game = new HumanOnlyGame(new Board());
        }
        return game;
    }
}
