package kg.jarkyn.Core;

public abstract class Player {
    protected final Mark mark;

    public Player(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    public abstract int pickPosition(Board board);
}
