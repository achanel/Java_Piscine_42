package ex04;

public class UserIdsGenerator {
    private Integer id;

    private static UserIdsGenerator userIdsGenerator;

    private UserIdsGenerator() { id = 0;}

    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null)
            userIdsGenerator = new UserIdsGenerator();
        return userIdsGenerator;
    }

    public Integer generateId() { return id++;}
}
