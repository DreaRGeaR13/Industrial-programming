package kz.proffix4.spring;

import javax.sql.DataSource;
import java.util.List;

/**
 * Интерфейс работы с таблицой Animal
 *
 */
public interface IAnimalDAO {
    void setDataSource(DataSource ds);                                    //1 Установка связи с данныими
    void insert(Animal animal);                                           //2 Вставка новой записи
    void append(String NickName, String AnimalKind, int AnimalAge);       //3 Добавление новой записи
    void deleteByAnimalKind(String AnimalKind);                           //4 Удаление записи по виду
    void deleteByNickName(String NickName);                               //5 Удаление записи по кличке
    void deleteByAge(int AnimalAge);                                      //6 Удаление записи по возрасту
    void delete(String NickName, String AnimalKind);                      //7 Удаление записи с указанной кличкой и видом
    void deleteByNickAge(String NickName, int Age);                       //8 Удаление записи по указанной кличке и возрасту
    void deleteByAll(String NickName, String AnimalKind, int AnimalAge);  //9 удаление записи по всем параметрам 
    void deleteAll();                                                     //10 Удаление всех запией
    void update(String oldAnimalAges, String newAnimalAges);              //11 Изменение записей возраста в таблице
    Animal findByAge(int age);                                            //12 Получение записи с заданным возрастом
    List<Animal> findByAnimalKind(String AnimalKind);                     //13 Поиск записи по виду животного
    List<Animal> findByNickName(String NickName);                         //14 Получение записей с заданной кличкой
    List<Animal> select(String NickName, String AninalKind);              //15 Получение записей с заданной кличкой и видом
    List<Animal> selectAll();                                             //16 Получение всех записей
  
}
