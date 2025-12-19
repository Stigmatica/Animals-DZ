package animals;

public class Animal {
    private String name;
    private int age;
    private int weight;
    private Color color = Color.UNDEFINED;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;}

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public void say() {
        System.out.println("Я говорю");
    }
    public void go() {
        System.out.println("Я иду");
    }
    public void drink() {
        System.out.println("Я пью");
    }
    public void eat() {
        System.out.println("Я ем");
    }

    @Override
    public String toString() {
        return String.format("Привет! Меня зовут %s, мне %d %s, я вешу %d кг, мой цвет - %s", name, age, getAgeSuffix(), weight, color.getValue());
    }

    private String getAgeSuffix() {
        int reminder10 = age % 10;
        int reminder100 = age % 100;
        if (reminder10 == 1 && reminder100 != 11) {
            return "год";
        }
        if (reminder10 >= 2 && reminder10 <= 4 && reminder100 != 12 && reminder100 != 13 && reminder100 != 14) {
            return "года";
        }
        return  "лет";
    }
}
