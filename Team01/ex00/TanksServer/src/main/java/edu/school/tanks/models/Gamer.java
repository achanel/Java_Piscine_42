package edu.school.tanks.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.StringJoiner;

@Data
@AllArgsConstructor
public class Gamer {
    private Integer gamerId;
    private Integer shot;
    private Integer hit;
    public Gamer() {}
    @Override
    public String toString() {
        return new StringJoiner(", ", "Gamer{", "}")
                .add("gamerId=" + gamerId)
                .add("shot=" + shot)
                .add("hit=" + hit)
                .toString();
    }
}
