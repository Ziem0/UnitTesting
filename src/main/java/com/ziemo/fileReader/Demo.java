package com.ziemo.fileReader;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Demo {

    private String name;
    private int age;
    private FilePartReader filePartReader;


    public Demo(String name, int age, FilePartReader filePartReader) throws IllegalArgumentException, NullPointerException{
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        if (name == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.age = age;
        this.filePartReader = filePartReader;
    }

    private List<Integer> listAges() {
        ArrayList list = new ArrayList(Arrays.asList(this.age, this.name.length()));
        return list;
    }

    public Integer countList() throws NullPointerException{
        if (listAges().isEmpty()) {
            throw new NullPointerException();
        } else {
            return listAges().size();
        }
    }


    public String getPath() {
        return this.filePartReader.getFilePath();
    }

//    public static void main(String[] args) {
//        Demo demo = new Demo("ziemo", 12);
//        demo.listAges();
//    }

}
