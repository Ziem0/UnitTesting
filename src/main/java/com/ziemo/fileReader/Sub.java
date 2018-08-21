package com.ziemo.fileReader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Sub extends AdvLog{

    public Sub(String name, int age) throws IOException {
        super(name, age);
    }

    public int multipleAge() {
        int result = age * 5;
        log.info("Subclass log");
        return result;
    }

    public static void main(String[] args) {
        try {
            Sub advLog2 = new Sub("ola", 123);
            advLog2.multipleAge();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
