package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
//        System.out.println(usersRepository.findAll());
//        System.out.println(usersRepository.findByEmail("igor1@gmail.com"));
//        usersRepository.delete(1l);
//        System.out.println(usersRepository.findById(2l));
//        usersRepository.update(new User(2l, "romanbinel@gmail.com"));
//        usersRepository.save(new User(null, "rrrrrrrrrr@gmail.com"));
//
//        System.out.println("----------------------------------------");

        UsersRepository usersRepository  = context.getBean("usersRepositoryJdbcImpl", UsersRepository.class);
        UsersService usersService = context.getBean("usersServiceImpl", UsersService.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersService.signUp("romankajdhfdhasd"));
//        System.out.println(usersRepository.findByEmail("igor3@gmail.com"));
//        usersRepository.delete(5l);
//        System.out.println(usersRepository.findById(9l));
//        usersRepository.update(new User(9l, "ddddddddd@gmail.com"));


    }
}
