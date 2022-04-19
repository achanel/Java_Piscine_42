package ex03;

import ex02.User;

public interface UsersList {
    void add(ex02.User user);
    ex02.User retrieveById(Integer id) throws ThrowableClass;
    User retrieveByIndex(Integer index) throws ThrowableClass;
    Integer retrieveCount();
}
