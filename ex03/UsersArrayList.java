package ex03;

import ex02.User;

public class UsersArrayList implements UsersList {
    private ex02.User[] array;
    private Integer usersCount;

    public UsersArrayList() {
        this.array = new ex02.User[10];
        this.usersCount = 0;
    }

    @Override
    public void add(ex02.User user) {
        if (array.length == usersCount) {
            ex02.User[] newArray = new ex02.User[usersCount + usersCount];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[usersCount] = user;
        usersCount++;
    }

    @Override
    public ex02.User retrieveById(Integer id) throws ThrowableClass {
        for (int i = 0; i < usersCount; i++){
            if (id.equals((array[i].getIdentifier()))) {
                return array[i];
            }
        }
        throw new ThrowableClass("User with a non-existent ID!");
    }

    @Override
    public User retrieveByIndex(Integer index) throws ThrowableClass {
        if (index < 0 || index > usersCount)
            throw new ThrowableClass("User with a non-existent index!");
        return array[index];
    }

    @Override
    public Integer retrieveCount() { return this.usersCount;}
}
