package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Server server = new Server((Integer.parseInt(args[0].split("=")[1])));
        server.startServer();
    }
}
