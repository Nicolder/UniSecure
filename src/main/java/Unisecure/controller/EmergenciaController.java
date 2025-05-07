package Unisecure.controller;

import Unisecure.dao.EmergenciaDAO;
import Unisecure.model.Emergencia;

import java.time.LocalDateTime;
import java.util.List;

public class EmergenciaController {
    private EmergenciaDAO emergenciaDAO;

    public EmergenciaController() {
        this.emergenciaDAO = new EmergenciaDAO();
    }

    // Cadastrar emergência
    public void registrarEmergencia(String localidade, String tipos) {
        Emergencia emergencia = new Emergencia(localidade, tipos);
        emergenciaDAO.registrar(emergencia);
    }
}

