package Unisecure;

import Unisecure.controller.PollingEmergencias;
import Unisecure.view.TelaEmergencia;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        TelaEmergencia tela = new TelaEmergencia();
        new PollingEmergencias(tela);
    }
}
