package kg.jarkyn;

public enum Mark {
    X, O;

    public Mark opponent() {
        return this == Mark.X ? Mark.O : Mark.X;
    }
}
