package Unisecure;

import Unisecure.controller.EmergenciaController;
import Unisecure.controller.SocorristaController;
import Unisecure.model.Emergencia;

public class Main {
    public static void main(String[] args) {
        EmergenciaController controller = new EmergenciaController();

        SocorristaController socorristaController = new SocorristaController();

        //socorristaController.cadastrarSocorrista("nico", "12345");

        //nova emergência
        controller.registrarEmergencia("Bloco A, 2 andar", "Trauma Físico");

    }
}