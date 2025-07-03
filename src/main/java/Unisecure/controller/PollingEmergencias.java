package Unisecure.controller;

import javax.swing.SwingUtilities;
import java.sql.SQLException; // Manter import para o catch, caso Conexao lance
import Unisecure.dao.Conexao; // Apenas para referência de erro se Conexao.conectar falhar na base
import Unisecure.dao.EmergenciaDAO; // Usar o novo DAO
import Unisecure.model.Emergencia; // Importar o modelo Emergencia
import Unisecure.view.TelaEmergencia;

// Objetivo: Verificar banco em busca de novas emergências

public class PollingEmergencias {
    private TelaEmergencia tela;
    private String ultimaEmergenciaId;
    private boolean primeiraVerificacao = true;
    private EmergenciaDAO emergenciaDAO;

    private Thread pollingThread;
    private boolean running = true;

    public PollingEmergencias(TelaEmergencia tela) {
        this.tela = tela;
        this.emergenciaDAO = new EmergenciaDAO();
        iniciarPolling();
    }

    private void iniciarPolling() {
        pollingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) { // Loop da thread
                    verificarNovaEmergencia();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Polling de emergências interrompido.");
                        Thread.currentThread().interrupt();
                        running = false;
                    }
                }
            }
        });
        pollingThread.start();
    }

    private void verificarNovaEmergencia() {
        Emergencia ultimaEmergencia = null;

        try {
            ultimaEmergencia = emergenciaDAO.buscarUltimaEmergencia();
        } catch (Exception e) {
            System.err.println("Erro ao verificar emergências: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (ultimaEmergencia != null) {
            String idAtual = String.valueOf(ultimaEmergencia.getId());

            if (primeiraVerificacao) {
                ultimaEmergenciaId = idAtual;
                primeiraVerificacao = false;

                return;
            }

            if (!idAtual.equals(ultimaEmergenciaId)) {
                ultimaEmergenciaId = idAtual;
                final String local = ultimaEmergencia.getLocalidade();
                final String descricao = ultimaEmergencia.getTiposEmergencia();

                SwingUtilities.invokeLater(() -> {
                    tela.adicionarNovaEmergencia(local, descricao);
                });
            }
        }
    }
}