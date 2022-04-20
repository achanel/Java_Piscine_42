package ex02;

public interface UsersList {
    void add(User user);
    User retrieveById(Integer id) throws ThrowableClass;
    User retrieveByIndex(Integer index) throws ThrowableClass;
    Integer retrieveCount();
}
