package com.nuist.entity;

/**
 * @author LwolveJ
 */
public class Score {

    private final String userName;

    private final Integer number;

    private final String time;

    private final Integer rank;

    public static class Builder {
        private String userName;

        private Integer number;

        private String time;

        private Integer rank;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setRank(Integer rank) {
            this.rank = rank;
            return this;
        }

        public Score build() {
            return new Score(this);
        }
    }

    private Score(Builder builder) {
        this.userName = builder.userName;
        this.time = builder.time;
        this.rank = builder.rank;
        this.number = builder.number;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    public Integer getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Score{" +
                "userName='" + userName + '\'' +
                ", number=" + number +
                ", time='" + time + '\'' +
                ", rank=" + rank +
                '}';
    }
}
