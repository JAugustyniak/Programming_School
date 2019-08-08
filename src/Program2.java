import classes.Exercise;
import DAO.ExerciseDAO;

import java.util.Scanner;

//zarządzanie zadaniami - tabela exercise
public class Program2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista wszystkich zadań: ");

        ExerciseDAO exerciseDAO = new ExerciseDAO();
        exerciseDAO.findAll();

        System.out.println();
        System.out.println("Wybierz jedną z opcji: \n * add \n * edit \n * delete \n * quit");
        String operation = scanner.next();


        if (operation.equals("add")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź title");
            String title = scanner.next();
            System.out.println("Wprowadź description");
            String description = scanner.next();
            Exercise ex = new Exercise(title, description);
            ExerciseDAO exDAO = new ExerciseDAO();
            exDAO.create(ex);
            exDAO.findAll();

        } else if (operation.equals("edit")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź id");
            int id = scanner.nextInt();
            System.out.println("Wprowadź title");
            String title = scanner.next();
            System.out.println("Wprowadź description");
            String description = scanner.next();
            Exercise ex = new Exercise();
            ex.setId(id);
            ex.setTitle(title);
            ex.setDescription(description);
            ExerciseDAO exDAO = new ExerciseDAO();
            exDAO.update(ex);
            exDAO.findAll();

        } else if (operation.equals("delete")) {
            System.out.println("wybrano " + operation);
            System.out.println("Wprowadź id");
            int id = scanner.nextInt();
            Exercise ex = new Exercise();
            ex.setId(id);
            ExerciseDAO exDAO = new ExerciseDAO();
            exDAO.delete(id);
            exDAO.findAll();
        }
    }
}
