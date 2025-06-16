package Unisecure.controller;

import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import Unisecure.dao.Conexao;
import Unisecure.view.TelaEmergencia;

public class PollingEmergencias {
    private TelaEmergencia tela;
    private String ultimaEmergenciaId = "";
    private boolean primeiraVerificacao = true;

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
        }, 0, 5000); // a cada 5 segundos
    }

    private void verificarNovaEmergencia() {
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, localidade, tipos_emergencia FROM emergencias ORDER BY id DESC LIMIT 1")) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String idAtual = rs.getString("id");

                final String local = rs.getString("localidade");
                final String descricao = rs.getString("tipos_emergencia");

                if (primeiraVerificacao) {
                    ultimaEmergenciaId = idAtual;
                    primeiraVerificacao = false;
                    // Se for a primeira verificação e já houver uma emergência, adiciona-a à lista
                    // A tela já carrega as emergências existentes, então não precisamos fazer isso aqui para a primeira.
                    return;
                }

                if (!idAtual.equals(ultimaEmergenciaId)) {
                    ultimaEmergenciaId = idAtual;
                    SwingUtilities.invokeLater(() -> {
                        tela.adicionarNovaEmergencia(local, descricao); // Chama o novo método
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar emergências: " + e.getMessage());
        }
    }
}