package kg.jarkyn;

public abstract class Player {
    protected final Mark mark;

    public Player(Mark mark) {
        this.mark = mark;
    }

    public abstract int pickPosition(Board board);

    public Mark getMark() {
        return mark;
    }
}
