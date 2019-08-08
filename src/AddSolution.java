import classes.Solution;
import DAO.SolutionDAO;
import DAO.UserDAO;

import java.util.Scanner;

public class AddSolution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista wszystkich użytkowników");
        UserDAO userDAO = new UserDAO();
        userDAO.findAll();
        System.out.println("Wprowadź id użytkownika");
        int userId = scanner.nextInt();
        System.out.println("Wybierz jedną z opcji: \n * add \n * view \n * quit");
        String operation = scanner.next();
        if (operation.equals("add")) {
            SolutionDAO solutionDAO = new SolutionDAO();
            System.out.println("Użytkownik o id " + userId + " nie dodał rozwiązania do następujących zadań: ");
            solutionDAO.findAllExerciseWithoutSolution(userId);
            System.out.println("Podaj id zadania, do którego chcesz dodać rozwiązanie");
            int exerciseId = scanner.nextInt();
            if (solutionDAO.getListOfExIdWithoutSol().contains(exerciseId)) {
                Solution solution = new Solution();
                System.out.println("Podaj rozwiązanie zadania");
                String solutionDesc = scanner.next();
                solution.setDescription(solutionDesc);
                solution.setUsersId(userId);
                solution.setExerciseId(exerciseId);
                solutionDAO.create(solution);
            } else {
                System.out.println("Rozwiązanie do zadania " + exerciseId + " zostało już dodane");
            }

        } else if (operation.equals("view")) {
            SolutionDAO solutionDAO = new SolutionDAO();
            solutionDAO.findAllByUserId(userId);
        } else if (operation.equals("quit")) {
        }
    }
}
