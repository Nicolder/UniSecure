package Unisecure.dao; //Data Acess Object

import Unisecure.model.Emergencia;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EmergenciaDAO {

    public boolean registrar(Emergencia e) {
        String sql = "INSERT INTO emergencias (localidade, tipos_emergencia, data_hora) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getLocalidade());
            stmt.setString(2, e.getTiposEmergencia());
            stmt.setTimestamp(3, Timestamp.valueOf(e.getDataHora()));
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Emergencia> listarTodas() {
        List<Emergencia> emergencias = new ArrayList<>();
        String sql = "SELECT id, localidade, tipos_emergencia, data_hora FROM emergencias ORDER BY data_hora DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String localidade = rs.getString("localidade");
                String tiposEmergencia = rs.getString("tipos_emergencia");
                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();
                emergencias.add(new Emergencia(id, localidade, tiposEmergencia, dataHora));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emergencias;
    }
}
