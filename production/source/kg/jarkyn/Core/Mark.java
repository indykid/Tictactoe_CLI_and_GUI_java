package kg.jarkyn.Core;

public enum Mark {
    X, O, NONE;

    public Mark opponent() {
        return this == Mark.X ? Mark.O : Mark.X;
    }
}
