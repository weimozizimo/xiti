package concurrent;


import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AutomicReferenceFieldUpdaterTest {
    public static void main(String[] args) {
        AtomicReferenceFieldUpdater<Dog, String> updater = AtomicReferenceFieldUpdater.newUpdater(Dog.class, String.class, "name");
        Dog dog = new Dog();
        System.out.println(dog.getName());
        updater.compareAndSet(dog,"miki","dog2");
        System.out.println(dog.getName());
        System.out.println(updater.getAndSet(dog,"dog3"));
        System.out.println(dog.getName());
    }
}
class Dog {
    public volatile String name = "miki";
    public String sex = "男";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
