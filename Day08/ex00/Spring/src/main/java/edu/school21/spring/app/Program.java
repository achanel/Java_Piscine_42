package edu.school21.spring.app;

import edu.school21.spring.printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer1 = context.getBean("printerWithPrefix", Printer.class);
        printer1.print("Hello!");

        Printer printer2 = context.getBean("printerWithDateTime", Printer.class);
        printer2.print("Hello!");
    }

}

