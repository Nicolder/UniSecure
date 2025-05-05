package Unisecure.dao;

import Unisecure.model.Socorrista;

import java.sql.*;

public class SocorristaDAO {

    public void inserir(Socorrista s) {
        String sql = "INSERT INTO socorristas (nome, senha) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getNome());
            stmt.setString(2, s.getSenha());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Socorrista autenticar(String nome, String senha) {
        String sql = "SELECT * FROM socorristas WHERE nome = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Socorrista( //cria objeto do socorrista login
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}