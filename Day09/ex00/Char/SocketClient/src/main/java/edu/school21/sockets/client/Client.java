package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private int port;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader reader;

    public Client(int port) {
        this.port = port;
    }

    public void connection() throws IOException {
        socket = new Socket("localhost", port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String hello = in.readLine();
            System.out.println(hello);
            String message = reader.readLine();
            out.write(message + "\n");
            out.flush();

            String getUsername = in.readLine();
            System.out.println(getUsername);
            String username = reader.readLine();
            out.write(username + "\n");
            out.flush();
            String getPassword = in.readLine();
            System.out.println(getPassword);
            String password = reader.readLine();
            out.write(password + "\n");
            out.flush();

            String result = in.readLine();
            System.out.println(result);
        } finally {
            socket.close();
            in.close();
            out.close();
        }
    }
}
