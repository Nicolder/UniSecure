package Unisecure.dao;

import Unisecure.model.Totem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TotemDAO {

    public void inserir(Totem t) {
        String sql = "INSERT INTO totens (localidade) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getLocalidade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Totem> listarTodos() {
        List<Totem> totens = new ArrayList<>();
        String sql = "SELECT * FROM totens";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                totens.add(new Totem(rs.getInt("id"), rs.getString("localidade")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totens;
    }
}