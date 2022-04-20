package ex04;

import ex02.User;

public interface UsersList {
    void add(User user);
    User retrieveById(Integer id) throws ThrowableClass;
    User retrieveByIndex(Integer index) throws ThrowableClass;
    Integer retrieveCount();
}
