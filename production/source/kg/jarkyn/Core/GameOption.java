package kg.jarkyn.Core;

import java.util.LinkedHashMap;

public enum GameOption {
    AI_FIRST(1), AI_SECOND(2), HUMAN_ONLY(3);

    public static final LinkedHashMap<Integer, String> OPTIONS = new LinkedHashMap<>();
    static {
        OPTIONS.put(AI_FIRST.numericOption, " - computer plays first");
        OPTIONS.put(AI_SECOND.numericOption, " - computer plays second");
        OPTIONS.put(HUMAN_ONLY.numericOption, " - play against your friend (first to go plays X)");
    }

    public int numericOption;

    GameOption(int value) {
        this.numericOption = value;
    }

    public int getNumericOption() {
        return numericOption;
    }

    public String readableOption() {
        return numericOption + OPTIONS.get(numericOption);
    }

    public static GameOption parse(int numericOption) {
        GameOption parsed = AI_FIRST;
        for (GameOption gameOption : GameOption.values()) {
            if (gameOption.getNumericOption() == numericOption) {
                parsed = gameOption;
            }
        }
        return parsed;
    }
}
