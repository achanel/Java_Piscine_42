package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private Socket socket;
    private int port;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader reader;
    private String username;
    private String password;
    private Date time;
    private String dTime;

    public Client(String hostname, int port) throws IOException {
        this.socket = new Socket(hostname, port);
    }

    public void connection() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.registration();
        new ReadMessage().start();
        new WriteMessage().start();
    }

    private void registration() throws IOException {
        try {
            String cmd = "";
            while (!cmd.equals("signIn") && !cmd.equals("signUp")) {
                System.out.println("Error! <signIn> or <signUp> only!");
                cmd = reader.readLine();
            }
            out.write(cmd + "\n");
            out.flush();

            String helloMsg = in.readLine();
            System.out.println(helloMsg);
            username = reader.readLine();
            out.write(username + "\n");
            out.flush();

            String getPassword = in.readLine();
            System.out.println(getPassword);
            password = reader.readLine();
            out.write(password + "\n");
            out.flush();

            String access = in.readLine();
            if (access.equals("start")) {
                System.out.println("Chat started!");
            } else if (access.equals("error")) {
                System.out.println("Username already exist!");
                this.closeConnection();
                System.exit(1);
            } else {
                System.out.println("Bad username or password!");
                this.closeConnection();
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
            in.close();
            out.close();
            reader.close();
        }
    }

    private class ReadMessage extends Thread {
        @Override
        public void run() {
            String message;
            try {
                while (true) {
                    message = in.readLine();
                    if (message.equals("Exit")) {
                        Client.this.closeConnection();
                        break;
                    }
                    System.out.println(message);
                }
            } catch (IOException e) {
                try {
                    Client.this.closeConnection();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class WriteMessage extends Thread {
        @Override
        public void run() {
            while (true) {
                String clientText;
                try {
                    time = new Date();
                    dTime = new SimpleDateFormat("HH:mm:ss").format(time);
                    clientText = reader.readLine();
                    if (clientText.equals("Exit")) {
                        out.write("Exit" + "\n");
                        Client.this.closeConnection();
                        break;
                    } else {
                        out.write("(" + dTime + ") " + username + ": " + clientText + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    try {
                        Client.this.closeConnection();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
