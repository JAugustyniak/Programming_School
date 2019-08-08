import classes.Solution;
import DAO.ExerciseDAO;
import DAO.GroupDAO;
import DAO.SolutionDAO;
import DAO.UserDAO;

import java.util.Scanner;

import static java.time.LocalDate.now;

//przypisywanie zadań

public class Program4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista wszystkich grup: ");

        GroupDAO groupDAO = new GroupDAO();
        groupDAO.findAll();

        System.out.println();
        System.out.println("Wybierz jedną z opcji: \n * add \n * view \n * quit");
        String operation = scanner.next();
        if (operation.equals("add")) {
            UserDAO userDAO = new UserDAO();
            System.out.println("Lista wszystkich uczestników: ");
            userDAO.findAll();
            System.out.println("Wprowadź id użytkownika");
            int userId = scanner.nextInt();
            System.out.println("Lista wszystkich zadań: ");
            ExerciseDAO exerciseDAO = new ExerciseDAO();
            exerciseDAO.findAll();
            System.out.println("Wprowadź id zadania");
            int exerciseId = scanner.nextInt();
            Solution solution = new Solution();
            solution.setCreated(now());
            solution.setExerciseId(exerciseId);
            solution.setUsersId(userId);
            SolutionDAO solutionDAO = new SolutionDAO();
            solutionDAO.create(solution);
            solution.printSolutionInfo();
        } else if (operation.equals("view")) {
            System.out.println("Lista wszystkich rozwiązań: ");
            SolutionDAO solutionDAO = new SolutionDAO();
            solutionDAO.findAll();
            System.out.println("Wprowadź id rozwiązania");
            int userId = scanner.nextInt();
            solutionDAO.findAllByUserId(userId);
        } else if (operation.equals("quit")) {
        }
    }
}
