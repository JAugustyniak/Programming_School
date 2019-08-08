import classes.User;
import DAO.UserDAO;

public class TestMain {

    public static void main(String[] args) {

        User user = new User("Jan", "test@gmail.com", "haslo123");
        user.printUserInfo();

        User user2 = new User();
        user2.setUserName("Grzes");
        user2.setEmail("grzes@gmail.com");
        user2.setPassword("grzes123");

        UserDAO userDAO = new UserDAO();
        userDAO.create(user);
        userDAO.create(user2);
        userDAO.read(18);
        user.setId(100);
        user.setUserName("Marek");
        user.setEmail("marek@gmail.com");
        user.setPassword("kappa1");
        userDAO.update(user);

        System.out.println("read id = 18");
        userDAO.read(18);
        System.out.println();
        userDAO.findAll();
/*
        userDAO.delete(9);
        userDAO.delete(10);
        userDAO.delete(11);
*/




    }

}
