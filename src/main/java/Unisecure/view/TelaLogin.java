package Unisecure.view; // PROTÓTIPO DE REDIMENSIONAMENTO

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import Unisecure.controller.PollingEmergencias;
import Unisecure.controller.SocorristaController;
import Unisecure.model.Socorrista;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel TelaPadrão;
    private JTextField UsuarioBox;
    private JPasswordField BoxSenha;
    private SocorristaController controller = new SocorristaController();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaLogin frame = new TelaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaLogin() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(JLogin.class.getResource("Logo.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(863, 640)); // Tamanho mínimo inicial
        setLocationRelativeTo(null);

        // Configuração do painel principal
        TelaPadrão = new JPanel(new BorderLayout());
        TelaPadrão.setBackground(new Color(173, 216, 230));
        TelaPadrão.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(TelaPadrão);

        // Cabeçalho (superior)
        JPanel Cabeçalho = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Cabeçalho.setBackground(new Color(255, 105, 97));
        Cabeçalho.setPreferredSize(new Dimension(getWidth(), 40));

        JButton VoltarButao = new JButton("Voltar");
        VoltarButao.setFont(new Font("Tahoma", Font.BOLD, 13));
        VoltarButao.setForeground(Color.WHITE);
        VoltarButao.setFocusPainted(false);
        VoltarButao.setBackground(new Color(73, 73, 200));
        VoltarButao.addActionListener(e -> {
            dispose();
            new TelaInicial().setVisible(true);
        });
        Cabeçalho.add(VoltarButao);
        TelaPadrão.add(Cabeçalho, BorderLayout.NORTH);

        // Painel central com GridBagLayout para melhor controle
        JPanel PainelMenor = new JPanel(new GridBagLayout());
        PainelMenor.setBackground(new Color(123, 164, 213));
        PainelMenor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Bem-vindo
        JLabel BemVindoTxt = new JLabel("Bem-Vindo", SwingConstants.CENTER);
        BemVindoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        PainelMenor.add(BemVindoTxt, gbc);

        // Imagem/Logo (se houver)
        JPanel panelLogo = new JPanel();
        panelLogo.setPreferredSize(new Dimension(164, 143));
        panelLogo.setBackground(PainelMenor.getBackground());
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        PainelMenor.add(panelLogo, gbc);

        // Usuário
        JLabel UsuarioTxt = new JLabel("Usuario");
        UsuarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 13));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PainelMenor.add(UsuarioTxt, gbc);

        UsuarioBox = new JTextField();
        UsuarioBox.setColumns(20);
        UsuarioBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridy = 3;
        PainelMenor.add(UsuarioBox, gbc);

        // Senha
        JLabel SenhaTxt = new JLabel("Senha");
        SenhaTxt.setFont(new Font("Tahoma", Font.PLAIN, 13));
        gbc.gridy = 4;
        PainelMenor.add(SenhaTxt, gbc);

        BoxSenha = new JPasswordField();
        BoxSenha.setPreferredSize(new Dimension(200, 30));
        gbc.gridy = 5;
        PainelMenor.add(BoxSenha, gbc);

        // Botão Acessar
        JButton BotaoAcessar = new JButton("Acessar");
        BotaoAcessar.setForeground(Color.WHITE);
        BotaoAcessar.setBackground(new Color(73, 73, 200));
        BotaoAcessar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        BotaoAcessar.addActionListener(e -> autenticar());
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        BotaoAcessar.setPreferredSize(new Dimension(150, 46));
        PainelMenor.add(BotaoAcessar, gbc);

        // Adiciona o painel central com bordas vazias para espaçamento
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(TelaPadrão.getBackground());
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        centerWrapper.add(PainelMenor);
        TelaPadrão.add(centerWrapper, BorderLayout.CENTER);

        // Torna a janela redimensionável
        setResizable(true);
    }

    private void autenticar() {
        String nome = UsuarioBox.getText();
        String senha = new String(BoxSenha.getPassword());

        Socorrista logado = controller.autenticar(nome, senha);

        if (logado != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem-vindo, " + logado.getNome());
            dispose();
            new PollingEmergencias(new TelaEmergencia());
        } else {
            JOptionPane.showMessageDialog(this, "Nome ou senha incorretos.");
        }
    }
}