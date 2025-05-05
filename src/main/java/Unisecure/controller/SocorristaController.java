package Unisecure.controller;

import Unisecure.dao.SocorristaDAO;
import Unisecure.model.Socorrista;

public class SocorristaController {
    private SocorristaDAO socorristaDAO;

    public SocorristaController() {
        this.socorristaDAO = new SocorristaDAO();
    }

    // Autentica nome e senha
    public Socorrista autenticar(String nome, String senha) {
        return socorristaDAO.autenticar(nome, senha);
    }

    // Cadastra nome e senha
    public void cadastrarSocorrista(String nome, String senha) {
        Socorrista socorrista = new Socorrista(nome, senha);
        socorristaDAO.inserir(socorrista);
    }
}