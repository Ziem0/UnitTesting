package com.ziemo.fileReader;

import lombok.Getter;

@Getter
public enum En {

    DUPA(Ex.class),
    CYCKI(Ex.class);

    public Class<Ex> class_;

    En(Class<Ex> class_) {
    }

    public static void main(String[] args) {
        System.out.println(DUPA.getClass_());

    }

}
