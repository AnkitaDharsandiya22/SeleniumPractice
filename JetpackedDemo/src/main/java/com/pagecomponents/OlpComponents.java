package com.pagecomponents;

public enum OlpComponents {
    LIGHTSAIL("Lightsail"),
    LEXIA("Lexia"),
    STMATH("STMath"),
    FRECKLE("Freckle"),
    FRECKLEFLUENCY("Freckle Fluency"),
    LEXIAENGLISH("Lexia English");
    private final String olp;

    public String getOlp() {
        return olp;
    }

    OlpComponents(String olp) {
        this.olp = olp;
    }

}
