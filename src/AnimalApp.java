import animals.Animal;
import animals.Color;
import factory.AnimalFactory;
import factory.AnimalType;
import tools.NameValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalApp {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        AnimalFactory animalFactory = new AnimalFactory();
        Scanner scanner = new Scanner(System.in);
        Command currentCommand = null;

        while (currentCommand !=Command.EXIT) {
            currentCommand = getCommand(scanner);

            if (currentCommand == Command.LIST) {
                if (animals.isEmpty()) {
                    System.out.println("Список пуст");
                }
                for (Animal animal : animals) {
                    System.out.println(animal);
                }
            } else if (currentCommand == Command.ADD) {

                AnimalType animalType = null;
                while (animalType == null) {
                    System.out.print("Какое животное (cat/dog/duck)? ");
                    String animalTypeInput = scanner.next().toUpperCase();

                    try {
                        animalType = AnimalType.valueOf(animalTypeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.print("Неверный тип животного. Попробуйте снова. ");
                    }
                }

                // Имя
                boolean isNameValid;
                String name;
                do {
                    System.out.print("Введите имя: ");
                    name = scanner.next().trim();

                    isNameValid = NameValidator.nameValidator(name);

                    if (isNameValid == false) {
                        System.out.println("""
                    Некорректное имя. Имя должно:
                    - содержать только буквы и дефисы
                    - начинаться с заглавной буквы
                    - быть длиной от 2 до 50 символов
                    """);
                    }
                } while (isNameValid == false);

                int age = 0;
                while (true) {
                    try {
                        System.out.print("Введите возраст (полных лет): ");
                        age = Integer.parseInt(scanner.next());

                        if (age >= 0) {
                            break;
                        } else {
                            System.out.print("Возраст не может быть меньше 0. Повторите ввод ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Возраст должен быть в числовом формате. ");
                    }
                }

                // Вес
                int weight = 0;
                while (true) {
                    try {
                        System.out.print("Введите вес (в кг): ");
                        weight = Integer.parseInt(scanner.next());

                        if (weight >= 0 && weight <= 99) {
                            break;
                        } else {
                            System.out.print("Вес не может быть меньше 0 и больше 99. Повторите ввод. ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Вес должен быть в числовом формате. ");
                    }
                }

                // Цвет
                Color color = Color.UNDEFINED;
                System.out.print("Введите цвет (WHITE/BLACK или другой): ");
                String colorInput = scanner.next().toUpperCase();
                try {
                    color = Color.valueOf(colorInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Цвет не распознан, будет использован цвет по умолчанию");
                }
                Animal animal = animalFactory.create(animalType);

                animal.setName(name);
                animal.setAge(age);
                animal.setWeight(weight);
                animal.setColor(color);

                animals.add(animal);
                animal.say();

                System.out.println("Животное успешно добавлено!\n");
            }
        }
        scanner.close();
    }
        private static Command getCommand(Scanner scanner) {
        String commandInput = null;
        while (Command.doesNotContain(commandInput)) {
            if (commandInput != null) {
                System.out.print("Введена неверная команда. ");
            }
            System.out.printf("Введите одну из команд (%s): ", String.join("/", Command.NAMES));
            commandInput = scanner.next();
        }
        return  Command.fromString(commandInput);
    }

}
