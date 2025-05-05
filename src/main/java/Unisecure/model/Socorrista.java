package Unisecure.model;

public class Socorrista extends Usuario{
    public Socorrista() {
        super();
    }

    public Socorrista(int id, String nome, String senha) {
        super(id, nome, senha);
    }

    public Socorrista(String nome, String senha) {
        super(nome, senha);
    }

    public String getTipoUsuario() {
        return "Socorrista";
    }
}