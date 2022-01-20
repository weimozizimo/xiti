package other.lesson1;

public class Instance {

    private String name;

    private String id;

    public Instance() {
    }

    public Instance(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
