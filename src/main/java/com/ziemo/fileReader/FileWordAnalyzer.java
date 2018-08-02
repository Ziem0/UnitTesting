package com.ziemo.fileReader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileWordAnalyzer {

    FilePartReader reader;

    public FileWordAnalyzer(FilePartReader reader) {
        this.reader = reader;
        reader.setup("src/main/resources/test_data.txt", 1, 7);

    }

    public List<String> wordsByABC() throws FileNotFoundException {
        String lines = reader.readLine();
        return Arrays.stream(lines.split(" ")).sorted().collect(Collectors.toList());
    }

    public String forMock(FilePartReader r) throws FileNotFoundException {
        r.readLine();
        return "test mock";
    }


//    public static void main(String[] args) {
//        FileWordAnalyzer w = new FileWordAnalyzer(new FilePartReader());
//        try {
//            System.out.println(w.wordsByABC());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

}
