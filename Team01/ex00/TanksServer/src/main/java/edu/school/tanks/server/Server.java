package edu.school.tanks.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import edu.school.tanks.service.GamerService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Server {
    private final GamerService pointsService;
    private final List<Client> clients = new ArrayList<>();
    int num = 0;

    @Autowired
    public Server(GamerService pointsService) {
        this.pointsService = pointsService;
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is run");
            while (num != 2) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket, ++num);
                clients.add(client);
                System.out.println("New player connected! Number of players: " + num);
                pointsService.createGamer(num);
            }
            clients.forEach(Thread::start);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeClient(Client client) {
        clients.remove(client);
        num--;
        System.out.println("The player out.");
    }

    private class Client extends Thread {
        private PrintWriter writer;
        private Scanner reader;
        private Socket socket;
        private int num;


        Client(Socket socket, int num) {
            try {
                this.socket = socket;
                this.num = num;
                reader = new Scanner(socket.getInputStream());
                writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void run() {
            writer.println("start");
            System.out.println("PLayer " + num + " start");
            label:
            while (true) {
                try {
                    if (reader.hasNextLine()) {
                        String input = reader.nextLine();
                        switch (input) {
                            case "outshoot":
                                pointsService.addShot(num);
                                break;
                            case "hit":
                                pointsService.addHit(num);
                                break;
                            case "gameOver":
                                String finalInput2 = input;
                                clients.stream().filter(client -> this.num != client.num).
                                        forEach(c -> c.writer.println(finalInput2));
                                input = pointsService.getStatistics(num);
                                String finalInput = input;
                                writer.println(finalInput);
                                break label;
                        }
                        String finalInput1 = input;
                        clients.stream().filter(client -> this.num != client.num).
                                    forEach(c -> c.writer.println(finalInput1));
                    } else {
                        clients.stream().filter(client -> this.num != client.num).
                                forEach(c -> c.writer.println("enemyLeftGame"));
                        exitClient();
                        return;
                    }
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        private void exitClient() {
            try {
                removeClient(this);
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
