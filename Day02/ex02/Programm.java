package ex02;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Programm {
        public static String currentPath = null;

        public static long getFolderSize(File f) {
            long len = 0;
            File[] files = f.listFiles();
            assert files != null;
            int count = files.length;

            for (int i = 0; i < count; i++) {
                if (files[i].isFile()) {
                    len += files[i].length();
                } else {
                    len += getFolderSize(files[i]);
                }
            }
            return len;
        }

        public static String formatFileSize(long size) {
            Double number = BigDecimal.valueOf(size / 1024.0)
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue();
            return (number + " KB");
        }

        public static void executeLs() {
            Set<File> currentDir = Stream.of(Objects.requireNonNull(new File(currentPath).listFiles()))
                    .collect(Collectors.toSet());

            for (File f : currentDir) {
                if (f.isDirectory()) {
                    System.out.println(f.getName() + " " + formatFileSize(getFolderSize(f)));
                }
            }
            for (File f : currentDir)
                if (f.isFile())
                    System.out.println(f.getName() +  " " + formatFileSize(f.length()));
        }

        public static void executeCd(String newDir) throws IOException {
            File file = new File(currentPath + "/" + newDir);
            if (file.isDirectory()) {
                currentPath = file.getCanonicalPath();
                System.out.println(currentPath);
            } else {
                System.out.println("Error. Executing cd");
            }
        }

        public static void renameFile(String name1, String name2) throws IOException {
            File file1 = new File(currentPath + "/" + name1);
            File file2 = new File(currentPath + "/" + name2);

            if (file2.isDirectory()) {
                if (file2.exists()) {
                    file1.renameTo(new File(file2.getCanonicalPath() + "/" + file1.getName()));
                } else {
                    System.out.println("Directory don't found.");
                }
            } else {
                if (!file1.renameTo(file2)) {
                    System.out.println("Error. File don't rename.");
                }
            }
        }

        public static void main(String[] args) throws IOException{
            if (args.length > 0 && args[0].startsWith("--current-folder=")) {
                currentPath = args[0].substring(args[0].indexOf("=") + 1);
            } else {
                System.out.println("Wrong argument.");
                System.exit(-1);
            }
            Scanner sc = new Scanner(System.in);

            while (true) {
                String input = sc.nextLine();
                String[] command = input.split("\\s+");
                if (command.length == 0){
                    continue;
                } else if (command[0].equals("exit")) {
                    System.exit(0);
                } else if (command[0].equals("ls")) {
                    executeLs();
                } else if (command[0].equals("cd") && command.length > 1) {
                    executeCd(command[1]);
                } else if (command[0].equals("mv") && command.length > 2) {
                    renameFile(command[1], command[2]);
                } else {
                    System.out.println("Unknown command");
                }
            }
    }
}
