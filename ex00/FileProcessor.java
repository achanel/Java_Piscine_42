package ex00;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private List<Line> line;

    public FileProcessor() {
        this.line = new ArrayList<>();
    }

    public boolean readFile(String path) {
        try (FileInputStream in = new FileInputStream(path)) {
            while (in.available() != 0) {
                line.add(readLine(in));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private Line readLine(FileInputStream in) throws IOException {
        StringBuilder str = new StringBuilder();

        int letter;
        while ((letter = in.read()) != -1 && (char) letter != '\n' ) {
            if (letter >= 'a' && letter <= 'f') {
                letter = letter - 'a' + 'A';
            }
            str.append((char) letter);
        }
        return new Line(str.toString());
    }

    public String define(String path) {
        byte bytes[] = new byte[8];

        try (FileInputStream in = new FileInputStream(path)) {
            if (in.read(bytes) == -1) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

        for (Line line : line) {
            boolean isIn = false;
            List<Byte> hex = line .getHex();
            for (int i = 0; i < hex.size(); i++) {
                if (bytes[i] != hex.get(i)) {
                    isIn = true;
                    break;
                }
            }
            if (!isIn) {
                return line.getFormat();
            }
        }
        return null;
    }
}
