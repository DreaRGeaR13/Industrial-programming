package kz.proffix4.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;

class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с бинами

            AnimalDAO animalDAO = (AnimalDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице клиентов 

            animalDAO.deleteAll(); // Удаление всех записей

            Animal animal = new Animal("Lory", "cat", 2); // Создание нового объекта таблицы 
            animalDAO.insert(animal);                     // Вставить новый объект в таблицу

            animalDAO.insert(new Animal("Brain", "rat", 2));  // Вставить новый объект в таблицу
            animalDAO.insert(new Animal("Baloon", "dog", 4));
            animalDAO.insert(new Animal("Volt", "dog", 3));
            animalDAO.insert(new Animal("Vlad", "rabbit", 3));
            animalDAO.insert(new Animal("stepan", "hamster", 2));
            animalDAO.insert(new Animal("Leonardo", "turtle", 99));
            animalDAO.insert(new Animal("Alex", "lion", 7));
            Animal animal1 = animalDAO.findByAge(4);                      // Поиск записи по возрасту 
            System.out.println(animal1 != null ? animal1 : "Нет данных"); // Вывод на экран найденной записи

            animalDAO.deleteByAnimalKind("br");      // Удаление записей по фрагменту клички
            animalDAO.delete("Brain", "rat");       // Удалениезаписи по кличке и виду
            animalDAO.deleteByNickAge("Alex", 7);   // Удалениезаписи по кличке и возрасту
            animalDAO.deleteByAnimalKind("rat");    // Удаление по виду
            animalDAO.deleteByNickName("Stepan");   // Удаление по кличке
            animalDAO.deleteByAge(99);              // Удаление по возрасту
            animalDAO.deleteByAll("volt", "dog", 3); // удаление записи по всем параметрам

            List<Animal> animals = animalDAO.findByNickName("al"); // Поиск записей по фрагменту клички
            System.out.println(animals != null ? animals : "Нет данных");

            animalDAO.append("Alf", "cat", 3);    // Добавлние записей
            animalDAO.append("Jimbo", "dog", 3);
            animalDAO.append("Sparky", "dog", 4);
            animalDAO.append("Adun", "fish", 1);

            animalDAO.update("4", "5");          // Изменение данных записей в таблице

            System.out.println("Данные в таблице:");

            List<Animal> list = animalDAO.selectAll();
            for (Animal myAnimal : list) {
                System.out.println(myAnimal.getNickName() + " " + myAnimal.getAnimalKind() + " " + myAnimal.getAge());
            }

            System.out.println("Вывод записей кошек с кличкой Alf:");

            list = animalDAO.select("Alf", "cat");
            for (Animal myAnimal : list) {
                System.out.println(myAnimal.getNickName() + " " + myAnimal.getAnimalKind());
            }
            List<Animal> animalcat = animalDAO.findByAnimalKind("cat");         //вывод данных о всех кошках
            System.out.println(animalcat != null ? animalcat : "Нет данных");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
}
