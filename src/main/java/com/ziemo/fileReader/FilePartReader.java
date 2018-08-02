package com.ziemo.fileReader;

import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Data
public class FilePartReader {

    private String filePath;
    private int fromLine;
    private int toLine;

    public FilePartReader() {
        this.filePath = "";
        this.fromLine = 0;
        this.toLine = 0;
    }

//    public String getFilePath() {
//        return filePath;
//    }
//
//    public int getFromLine() {
//        return fromLine;
//    }
//
//    public int getToLine() {
//        return toLine;
//    }

    public void setup(String filePath, int fromLine, int toLine) throws IllegalArgumentException {
        if (toLine < fromLine || fromLine < 1) {
            throw new IllegalArgumentException("Incorrect input");
        }
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    private String read() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        return text;
    }


    public String readLine() throws FileNotFoundException {
        String content = "";
        try {
            content = read();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        List<String> lines = Arrays.asList(content.split("\r\n"));
        return lines.stream().filter(a -> lines.indexOf(a) >= fromLine - 1 && lines.indexOf(a) <= toLine - 1)
                .collect(Collectors.joining("\r\n"));
    }

    public static void main(String[] args) throws FileNotFoundException {
        FilePartReader f = new FilePartReader();
        System.out.println(f.getToLine());
        f.setup("src/main/resources/test_data.txt", 1, 2);
        System.out.println(f.readLine());
    }

}
