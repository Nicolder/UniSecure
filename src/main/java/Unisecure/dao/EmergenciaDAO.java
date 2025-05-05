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
        List<Emergencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM emergencias ORDER BY data_hora DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emergencia e = new Emergencia(
                        rs.getInt("id"),
                        rs.getString("localidade"),
                        rs.getString("tipos_emergencia"),
                        rs.getTimestamp("data_hora").toLocalDateTime()
                );
                lista.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
