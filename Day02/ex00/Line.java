package ex00;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String format;
    private List<Byte> hex;


    public Line(String str) {
        String[] array = str.split(",");

        format = array[0];
        String[] bytes = array[1].trim().split(" +");
        hex = new ArrayList<>();
        for (String string: bytes) {
            hex.add(getByte(string));
        }
    }

    public String getFormat() {
        return format;
    }

    public List<Byte> getHex() {
        return hex;
    }

    public Byte getByte(String string) {
        byte b = translate(string.charAt(0));
        b <<= 4;
        b |= translate(string.charAt(1));
        return b;
    }

    private byte translate(char character) {
        if (character >= '0' && character <= '9') {
            return (byte) (character - '0');
        } else if (character >= 'A' && character <= 'F') {
            return (byte) (character - 'A' + 0xA);
        }

        return 0;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(format).append(" ");

        for (Byte s: hex) {
            ret.append(String.format("%02x", s)).append(" ");
        }
        return ret.toString();
    }
}
