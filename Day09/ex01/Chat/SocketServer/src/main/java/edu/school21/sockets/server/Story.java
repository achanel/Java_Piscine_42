package edu.school21.sockets.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Story {
    private LinkedList<String> storyList = new LinkedList<>();

    public void addStory(String message) {
        if (storyList.size() >= 10) {
            storyList.removeFirst();
            storyList.add(message);
        } else {
            storyList.add(message);
        }
    }

    public void printStory(BufferedWriter writer) {
        if (storyList.size() > 0) {
            try {
                writer.write("Message story\n");
                for (String i : storyList) {
                    writer.write(i + "\n");
                }
                writer.write(".....\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
