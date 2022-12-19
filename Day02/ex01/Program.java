package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Wrong number of arguments!");
            System.exit(-1);
        }
        BufferedReader bufferedReader = null;
        String lineOne = null;
        String lineTwo = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(args[0]), 10000000);
            lineOne = bufferedReader.readLine();
            bufferedReader = new BufferedReader(new FileReader(args[1]), 10000000);
            lineTwo = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }

        Set<String> wordListAll = new HashSet<>();
        List<String> wordList1 = null;
        List<String> wordList2 = null;

        try {
            assert lineOne != null;
            wordList1 = Arrays.asList(lineOne.split("\\s+"));
            assert lineTwo != null;
            wordList2 = Arrays.asList(lineTwo.split("\\s+"));
        } catch (NullPointerException exception) {
            System.err.println("File is empty..");
            System.exit(-1);
        }
        wordListAll.addAll(wordList1);
        wordListAll.addAll(wordList2);

        List<Integer> frequencyFile1 = new ArrayList<>(wordListAll.size());
        List<Integer> frequencyFile2 = new ArrayList<>(wordListAll.size());
        for(String word : wordListAll) {
            frequencyFile1.add(Collections.frequency(wordList1, word));
            frequencyFile2.add(Collections.frequency(wordList2, word));
        }

        int numerator = 0;
        for (int i = 0; i < wordListAll.size(); i++) {
            numerator += (frequencyFile1.get(i) * frequencyFile2.get(i));
        }

        double denominator;
        int denominator1 = 0;
        int denominator2 = 0;

        for (Integer n : frequencyFile1) {
            denominator1 += n * n;
        }
        for (Integer n : frequencyFile2) {
            denominator2 += n * n;
        }
        denominator = Math.sqrt(denominator1) * Math.sqrt(denominator2);
        System.out.println("Similarity = " + numerator/denominator);
    }
}
