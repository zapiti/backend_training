package br.com.team.appx.convinience.model;

public enum Role {
    ADM(1),
    USER(2),
    SUP(3);

    private final int value;

    private Role(int value) {
        this.value = value;
    }
}
