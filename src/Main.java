import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(controlUser("andres23", "Kontakt_r", "Kontakt_r"));
    }

    public static boolean controlUser(String login, String password, String confirmPassword) {
        boolean control;
        try {
            control = controlLogin(login) && controlPassword(password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            control = false;
        } finally {
            System.out.println("Проверка завершина!");
        }
        return control;
    }

    private static boolean controlLogin(String login) throws WrongLoginException {
        Pattern p = Pattern.compile("^[a-z0-9_-]{1,20}$");
        if (!p.matcher(login).matches()) {
            throw new WrongLoginException("Не коректный логин : " + login);
        }
        return true;
    }

    private static boolean controlPassword(String password, String confirmPassword) throws WrongPasswordException {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]{1,19}$");
        if (!p.matcher(password).matches()) {
            throw new WrongPasswordException("Не коректный пароль : " + password);
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
        return true;
    }
}
