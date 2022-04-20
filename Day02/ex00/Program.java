package ex00;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Program {
    public static void main(String[] args) {
        FileProcessor file = new FileProcessor();

        if (file.readFile("signatures.txt")) {
            System.err.println("Wrong file name!");
            System.exit(-1);
        }
        for(;;) {
            String path = scanLine(System.in);
            if (path.equals("42")) {
                return;
            }
            String type = file.define(path);
            if (type != null) {
                System.out.println("PROCESSED");
                addResult(type);
            } else {
                System.err.println("UNDEFINED");
            }
        }
    }

    private static String scanLine(InputStream in) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            int character;
            while ((character = in.read()) != -1 && character != '\n') {
                stringBuilder.append(String.format("%c", character));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static void addResult(String type) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("result.txt")) {
            fileOutputStream.write(type.getBytes());
            fileOutputStream.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
