package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;
import edu.school21.sockets.server.Story;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Main {
    public static LinkedList<Server> servers = new LinkedList<>();
    public static Story story;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0].split("=")[1]));
        story = new Story();
        System.out.println("Server started!");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    servers.add(new Server(socket));
                } catch (Exception e) {
                    socket.close();
                }
            }
        } finally {
            serverSocket.close();
        }
    }
}
