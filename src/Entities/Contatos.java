package Entities;

public class Contatos {
    private String name;
    private String number;
    private Integer id;

    public Contatos(String name, String number, int id){
        this.name = name;
        this.number = number;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return id + " | " + name + " | " + number;
    }



}
