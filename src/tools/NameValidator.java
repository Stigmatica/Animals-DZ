package tools;

import java.util.regex.Pattern;

public class NameValidator {
    public static boolean nameValidator(String name) {
        if (name == null) {
            return false;
        }

        if (name.length() < 2 || name.length() > 50) {
            return false;
        }

        // Регулярное выражение для проверки:
        // ^[А-ЯA-Z] - начинается с заглавной буквы (русской или английской)
        // [а-яА-Яa-zA-Z-]* - содержит буквы и дефисы
        // (?<!-)$ - не заканчивается на дефис
        String regex = "^[А-ЯA-Z][а-яА-Яa-zA-Z]*(?:-[а-яА-Яa-zA-Z]+)*$";

        if (!Pattern.matches(regex, name)) {
            return false;
        }
        if (name.contains("--")) {
            return false;
        }
        return true;
    }
}
