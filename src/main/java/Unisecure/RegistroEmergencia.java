package Unisecure;

import Unisecure.controller.EmergenciaController;

public class RegistroEmergencia {
    public static void main(String[] args) {
        EmergenciaController controller = new EmergenciaController();

        //nova emergência
        controller.registrarEmergencia("Bloco C, 1 Andar", "Ataque cardíaco");
    }
}