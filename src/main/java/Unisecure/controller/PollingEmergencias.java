package Unisecure.controller;

import Unisecure.dao.Conexao;
import Unisecure.view.TelaEmergencia;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class PollingEmergencias {
    private TelaEmergencia tela;
    private String ultimaEmergenciaId = "";

    public PollingEmergencias(TelaEmergencia tela) {
        this.tela = tela;
        iniciar();
    }

    private void iniciar() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                verificarNovaEmergencia();
            }
        }, 0, 3000); // a cada 3segundos
    }

    private void verificarNovaEmergencia() {
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, localidade, tipos_emergencia FROM emergencias ORDER BY id DESC LIMIT 1")) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String idAtual = rs.getString("id");
                String local = rs.getString("localidade");
                String descricao = rs.getString("tipos_emergencia");

                if (!idAtual.equals(ultimaEmergenciaId)) {
                    ultimaEmergenciaId = idAtual;
                    tela.exibirAlerta(local, descricao);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar emergências: " + e.getMessage());
        }
    }
}