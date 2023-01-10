package edu.school.tanks.app;

import edu.school.tanks.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {
    public static void main(String[] args) {
        int port = 9000;

        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school.tanks");
        Server server = context.getBean(Server.class);
        server.start(port);
    }
}