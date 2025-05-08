package Unisecure;

import Unisecure.controller.EmergenciaController;

public class RegistroEmergencia {
    public static void main(String[] args) {
        EmergenciaController controller = new EmergenciaController();

        //SocorristaController socorristaController = new SocorristaController();

        //nova emergência
        controller.registrarEmergencia("Bloco X, X andar", "Lesão física");
    }
}