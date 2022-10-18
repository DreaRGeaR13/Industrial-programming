package kz.proffix4.spring;

/**
 * Класс, соответсвующий записи в таблице Animal
 *
 */
public class Animal {

    int id;            
    String NickName; 
    String AnimalKind;   
    int AnimalAge;         

    public Animal() {
        this.id = 0;
        this.NickName = "";
        this.AnimalKind = "";
        this.AnimalAge = 0;
    }

    public Animal(String nickName, String AnimalKind, int AnimalAge) {
        this.id = 0;
        this.NickName = nickName;
        this.AnimalKind = AnimalKind;
        this.AnimalAge = AnimalAge;
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return NickName;
    }

    public String getAnimalKind() {
        return AnimalKind;
    }

    public int getAge() {
        return AnimalAge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public void setAnimalKind(String AnimalKind) {
        this.AnimalKind = AnimalKind;
    }

    public void setAge(int AnimalAge) {
        this.AnimalAge = AnimalAge;
    }

    @Override
    public String toString() {
        return String.format("Кличка=%s, Вид=%s, Возраст=%d", NickName, AnimalKind, AnimalAge);
    }

}
