import classes.Group;
import DAO.GroupDAO;
import java.util.Scanner;

//zarządzanie zadaniami - tabela group
public class Program3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz jedną z opcji: \n * add \n * edit \n * delete \n * quit");
        String operation = scanner.next();


        if (operation.equals("add")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź name");
            String name = scanner.next();
            Group gr = new Group(name);
            GroupDAO grDAO = new GroupDAO();
            grDAO.create(gr);
            grDAO.findAll();

        } else if (operation.equals("edit")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź id");
            int id = scanner.nextInt();
            System.out.println("Wprowadź name");
            String name = scanner.next();
            Group gr = new Group();
            gr.setId(id);
            gr.setName(name);
            GroupDAO grDAO = new GroupDAO();
            grDAO.update(gr);
            grDAO.findAll();

        } else if (operation.equals("delete")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź id");
            int id = scanner.nextInt();
            Group gr = new Group();
            gr.setId(id);
            GroupDAO grDAO = new GroupDAO();
            grDAO.delete(id);
            grDAO.findAll();
        }
    }

}

