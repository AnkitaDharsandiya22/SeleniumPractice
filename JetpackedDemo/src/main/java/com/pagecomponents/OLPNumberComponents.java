package com.pagecomponents;

import lombok.Getter;

public enum OLPNumberComponents {
   @Getter
    Lightsail("3"),
    LEXIA("4"),
    STMATH("5"),
    FRECKLE("6"),
    FRECKLEFLUENCY("7"),
    LEXIAENGLISH("8");
    private final String olp;
    OLPNumberComponents(String olp) {
      this.olp=olp;
    }
}
