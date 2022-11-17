package singletonPattern;

public class SingleObject {
    // В этой статической(!) переменной будет хранится единственный объект класса
    private static SingleObject instance;

    private String value;

    public String getValue() {
        return value;
    }

    public SingleObject(String value) {
        this.value = value;
    }

    public static SingleObject getInstance(String value) {
        // если ранее не был создан единственный объект
        if (instance == null) {
            // код здесь выполняется только в момент первого вызова метода getInstance()

            // создаем объект один раз
            instance = new SingleObject(value);
        }

        // все остальные вызовы метода getInstance() пользователю возвращается тот же самый объект
        return instance;
    }
}

//Тестирование

class SingletonTest {
    public static void main(String[] args) {
        SingleObject first = SingleObject.getInstance("Hello!");
        SingleObject second = SingleObject.getInstance("Goodbye!");

        // true
        System.out.println(first == second);

        // Hello!
        System.out.println(first.getValue());

        // Hello!
        System.out.println(second.getValue());
    }
}