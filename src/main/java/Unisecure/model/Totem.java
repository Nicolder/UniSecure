package Unisecure.model;

public class Totem {
    private int id;
    private String localidade;

    public Totem() {}

    public Totem(int id, String localidade) {
        this.id = id;
        this.localidade = localidade;
    }

    public Totem(String localidade) {
        this.localidade = localidade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
