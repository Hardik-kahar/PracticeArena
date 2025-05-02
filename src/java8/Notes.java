package java8;

public class Notes {

    Long id;
    String name;
    int number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Notes(Long id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
}
