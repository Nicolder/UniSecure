package Unisecure.dao; //Data Acess Object

import Unisecure.model.Emergencia;

import java.sql.*;
import java.time.LocalDateTime;


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
}
