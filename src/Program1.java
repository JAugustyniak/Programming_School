import classes.User;
import DAO.UserDAO;
import java.util.Scanner;

//zarządzanie uczestnikami -> tabela users
public class Program1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista wszystkich uczestników: ");

        UserDAO userDAO = new UserDAO();
        userDAO.findAll();

        System.out.println();
        System.out.println("Wybierz jedną z opcji: \n * add \n * edit \n * delete \n * quit");
        String operation = scanner.next();


        if (operation.equals("add")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź userName");
            String name = scanner.next();
            System.out.println("Wprowadź email");
            String email = scanner.next();
            System.out.println("Wprowadź password");
            String pass = scanner.next();
            User u = new User(name, email, pass);
            UserDAO uDAO = new UserDAO();
            uDAO.create(u);
            uDAO.findAll();

        } else if (operation.equals("edit")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź id");
            int id = scanner.nextInt();
            System.out.println("Wprowadź userName");
            String name = scanner.next();
            System.out.println("Wprowadź email");
            String email = scanner.next();
            System.out.println("Wprowadź password");
            String pass = scanner.next();
            User u = new User();
            u.setId(id);
            u.setUserName(name);
            u.setEmail(email);
            u.setPassword(pass);
            UserDAO uDAO = new UserDAO();
            uDAO.update(u);
            uDAO.findAll();

        } else if (operation.equals("delete")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź id");
            int id = scanner.nextInt();
            User u = new User();
            u.setId(id);
            UserDAO uDAO = new UserDAO();
            uDAO.delete(id);
            uDAO.findAll();
        }
    }

}
