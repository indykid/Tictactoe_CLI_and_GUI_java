package kg.jarkyn;

public enum GameOption {
    AI_FIRST(1), AI_SECOND(2), HUMAN_ONLY(3);

    public int value;

    GameOption(int value) {
        this.value = value;
    }
}
