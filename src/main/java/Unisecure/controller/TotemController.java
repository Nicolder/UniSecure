package Unisecure.controller;

import Unisecure.dao.TotemDAO;
import Unisecure.model.Totem;

import java.util.List;

public class TotemController {
    private TotemDAO totemDAO;

    public TotemController() {
        this.totemDAO = new TotemDAO();
    }

    public void cadastrarTotem(String localidade) {
        Totem totem = new Totem(localidade);
        totemDAO.inserir(totem);
    }
}
