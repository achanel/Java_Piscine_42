package edu.school21.reflection.app;

import edu.school21.reflection.orm.User;
import edu.school21.reflection.orm.OrmManager;
public class Program {
    public static void main(String[] args) {
        OrmManager ormManager = new OrmManager("gtaggana", "edu.school21.reflection.orm");

        User usr1 = new User("qwerty", "qaz", 10);
        User usr2 = new User("asdfg", "plmko", 10);
        User usr3 = new User("zxcvb", "ygvbhuhb", 66);

        System.out.println("_________________________CREATED_USERS______________________________");
        System.out.println(usr1);
        System.out.println(usr2);
        System.out.println(usr3);
        System.out.println("_________________________ADD_USERS_IN_BD____________________________");
        ormManager.save(usr1);
        ormManager.save(usr2);
        ormManager.save(usr3);
        System.out.println(usr1);
        System.out.println(usr2);
        System.out.println(usr3);
        System.out.println("_______________________UPDATE_INFORN_ABOUT_USER_____________________");


        usr1.setFirstName("0123456789");
        usr2.setAge(null);
        usr3.setLastName(null);
        ormManager.update(usr1);
        ormManager.update(usr2);
        ormManager.update(usr3);

        System.out.println(usr1);
        System.out.println(usr2);
        System.out.println(usr3);
        System.out.println("____________________FIND_USER_BY_ID_________________________________");

        User tmp = ormManager.findById(3L, User.class);
        System.out.println("FIND 3: " + tmp);
        tmp = ormManager.findById(2L, User.class);
        System.out.println("FIND 2: " + tmp);
        tmp = ormManager.findById(1L, User.class);
        System.out.println("FIND 1: " + tmp);
    }
}
