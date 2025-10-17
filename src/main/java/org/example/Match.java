package org.example;

public class Match {
    private final int id;
    private Player winner;
    private Player playerA;
    private Player playerB;
    private Status bracketStatus;

    private Match(Builder builder) {
        this.id = builder.id;
        this.playerA = builder.playerA;
        this.playerB = builder.playerB;
        this.bracketStatus = builder.bracketStatus;
    }

    public static class Builder {
        private int id;
        private Player playerA;
        private Player playerB;
        private Status bracketStatus;

        public Builder playerA(Player playerA) {
            this.playerA = playerA;
            return this;
        }
        public Builder playerB(Player playerB) {
            this.playerB = playerB;
            return this;
        }
        public Builder bracketStatus(Status status) {
            this.bracketStatus = status;
            return this;
        }
        public Match build() {
            return new Match(this);
        }
    }
}

