package Unisecure.controller;

import Unisecure.dao.EmergenciaDAO;
import Unisecure.model.Emergencia;

public class EmergenciaController {
    private EmergenciaDAO emergenciaDAO; //Dependencia

    public EmergenciaController() {
        this.emergenciaDAO = new EmergenciaDAO();
    }

    // Cadastrar emergência
    public void registrarEmergencia(String localidade, String tipos) {
        Emergencia emergencia = new Emergencia(localidade, tipos);
        emergenciaDAO.registrar(emergencia);
    }
}

