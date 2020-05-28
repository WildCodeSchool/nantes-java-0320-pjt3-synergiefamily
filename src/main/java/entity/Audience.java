package entity;

public class Audience {

    private int id;
    private String ageBracket;

    public Audience(int id, String ageBracket) {
        this.id = id;
        this.ageBracket = ageBracket;
    }

    public Audience() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(String ageBracket) {
        this.ageBracket = ageBracket;
    }
}
