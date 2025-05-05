package Unisecure.view;

import Unisecure.controller.SocorristaController;
import Unisecure.model.Socorrista;

import javax.swing.*;
import java.awt.*;

public class LoginSocorrista extends JFrame {
    private JTextField nomeField;
    private JPasswordField senhaField;
    private SocorristaController controller;

    public LoginSocorrista() {
        controller = new SocorristaController();

        setTitle("Login do Socorrista");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        nomeField = new JTextField();
        senhaField = new JPasswordField();

        JButton loginBtn = new JButton("Entrar");
        loginBtn.addActionListener(e -> autenticar());

        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(loginBtn);

        setVisible(true);
    }

    private void autenticar() {
        String nome = nomeField.getText();
        String senha = new String(senhaField.getPassword());

        Socorrista logado = controller.autenticar(nome, senha);

        if (logado != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem-vindo, " + logado.getNome());
            dispose(); // Fecha tela de login
            new TelaEmergencia(); // Abre tela principal do socorrista
        } else {
            JOptionPane.showMessageDialog(this, "Nome ou senha incorretos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginSocorrista::new);
    }
}

