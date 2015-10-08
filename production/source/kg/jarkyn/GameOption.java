package kg.jarkyn;

import java.util.LinkedHashMap;

public enum GameOption {
    AI_FIRST(1), AI_SECOND(2), HUMAN_ONLY(3);

    public int value;
    public static final LinkedHashMap<Integer, String> OPTIONS = new LinkedHashMap<>();
    static {
        OPTIONS.put(AI_FIRST.value, " - computer plays first");
        OPTIONS.put(AI_SECOND.value, " - computer plays second");
        OPTIONS.put(HUMAN_ONLY.value, " - play against your friend (first to go plays X)");
    }

    GameOption(int value) {
        this.value = value;
    }

    public String readableOption() {
        return value + OPTIONS.get(value);
    }
}
