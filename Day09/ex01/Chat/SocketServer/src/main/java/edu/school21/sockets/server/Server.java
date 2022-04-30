package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

import static edu.school21.sockets.app.Main.servers;
import static edu.school21.sockets.app.Main.story;

public class Server extends Thread {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final UsersService usersService;

    public Server(Socket port) throws IOException {
        this.socket = port;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        welcomeServer();
        story.printStory(out);
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        usersService = context.getBean(UsersServiceImpl.class);
        start();
    }

    @Override
    public void run() {
        String message;
        try {
            message = in.readLine();
            try {
                out.write(message + "\n");
                out.flush();
            } catch (IOException e) {}
            try {
                while (true) {
                    message = in.readLine();
                    if (message.equals("stop")) {
                        this.closeConnections();
                        break;
                    }
                    System.out.println(message);
                    story.addStory(message);
                    for (Server server : servers) {
                        server.sendMessage(message);
                    }
                    if (!message.equals(""))
                        usersService.saveMessage(message);
                }
            } catch (NullPointerException e) {}
        } catch (IOException e) {
            this.closeConnections();
        }
    }

    private void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {}
    }

    private void welcomeServer() {
        String cmd;
        try {
            cmd = in.readLine();
            System.out.println(cmd);
            if (cmd.equals("signUp"))
                registerUser();
            else if (cmd.equals("signIn"))
                loginUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerUser() {
        try {
            out.write("Enter username:\n");
            out.flush();
            String username = in.readLine();
            out.write("Enter password:\n");
            out.flush();
            String password = in.readLine();
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            UsersService usersService = context.getBean(UsersService.class);
            System.out.println(username + " " + password);
            if (usersService.signUp(username, password)) {
                out.write("start\n");
            } else {
                out.write("error\n");
            }
            out.flush();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            this.closeConnections();
        }
    }

    private void loginUser() {
        try {
            out.write("Enter username\n");
            out.flush();
            String username = in.readLine();
            out.write("Enter password\n");
            out.flush();
            String password = in.readLine();
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            UsersService usersService = context.getBean(UsersService.class);
            System.out.println(username + " " + password);
            if (usersService.signIn(username, password)) {
                out.write("start\n");
            } else {
                out.write("errorExist\n");
            }
            out.flush();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnections() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (Server server : servers) {
                    if (server.equals(this)) server.interrupt();
                    servers.remove(this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
