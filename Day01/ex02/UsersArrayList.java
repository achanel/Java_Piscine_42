package ex02;

public class UsersArrayList implements UsersList {
    private User[] array;
    private Integer usersCount;

    public UsersArrayList() {
        this.array = new User[10];
        this.usersCount = 0;
    }

    @Override
    public void add(User user) {
        if (array.length == usersCount) {
            User[] newArray = new User[usersCount + usersCount];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[usersCount] = user;
        usersCount++;
    }

    @Override
    public User retrieveById(Integer id) throws ThrowableClass {
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
