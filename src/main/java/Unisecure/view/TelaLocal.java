package Unisecure.view;

import Unisecure.dao.EmergenciaDAO;
import Unisecure.model.Emergencia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaLocal extends JFrame {

    private List<String> ocorrenciasSelecionadas;
    private List<JCheckBox> blocosCheckboxes = new ArrayList<>();
    private List<JCheckBox> andaresCheckboxes = new ArrayList<>();

    public TelaLocal(List<String> ocorrenciasSelecionadas) {
        this.ocorrenciasSelecionadas = ocorrenciasSelecionadas;

        setTitle("Local do Ocorrido");
        setSize(700, 635);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(183, 230, 255));

        JPanel header = new JPanel();
        header.setBackground(new Color(255, 105, 97));
        header.setBounds(0, 0, 700, 50);
        header.setLayout(null);

        JButton voltarBtn = new JButton("voltar");
        voltarBtn.setBounds(600, 8, 80, 30);
        voltarBtn.setBackground(new Color(48, 71, 157));
        voltarBtn.setForeground(Color.WHITE);
        voltarBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        voltarBtn.setFocusPainted(false);
        voltarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(voltarBtn);
        add(header);

        JLabel titulo = new JLabel("LOCAL DO OCORRIDO");
        titulo.setBounds(0, 75, 700, 50);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(48, 71, 157));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo);

        JLabel blocoLabel = new JLabel("BLOCO:");
        blocoLabel.setBounds(60, 140, 200, 30);
        blocoLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        blocoLabel.setForeground(new Color(128, 0, 0));
        add(blocoLabel);

        String[] blocos = {"A", "B", "C", "D", "E/F"};
        int bx = 60;
        for (String bloco : blocos) {
            JCheckBox checkbox = criarCheckbox("Bloco " + bloco);
            checkbox.setBounds(bx, 200, 100, 45);
            blocosCheckboxes.add(checkbox);
            add(checkbox);
            bx += 120;
        }

        JLabel andarLabel = new JLabel("ANDAR:");
        andarLabel.setBounds(60, 280, 200, 30);
        andarLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        andarLabel.setForeground(new Color(128, 0, 0));
        add(andarLabel);

        String[] andares = {"1º", "2º", "3º", "4º", "CO"};
        int ax = 60;
        for (String andar : andares) {
            JCheckBox checkbox = criarCheckbox("Andar " + andar);
            checkbox.setBounds(ax, 340, 100, 45);
            andaresCheckboxes.add(checkbox);
            add(checkbox);
            ax += 120;
        }

        JButton confirmar = new JButton("Confirmar");
        confirmar.setBounds(250, 480, 200, 50);
        confirmar.setFont(new Font("SansSerif", Font.BOLD, 18));
        confirmar.setForeground(Color.WHITE);
        confirmar.setBackground(new Color(48, 71, 157));
        confirmar.setFocusPainted(false);
        confirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmar.addActionListener(e -> registrarEmergencia());
        add(confirmar);
    }

    private JCheckBox criarCheckbox(String texto) {
        JCheckBox checkbox = new JCheckBox(texto);
        checkbox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        checkbox.setBackground(Color.WHITE);
        checkbox.setFocusPainted(false);
        checkbox.setHorizontalAlignment(SwingConstants.LEFT);
        checkbox.setBorderPainted(false);
        checkbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return checkbox;
    }

    private void registrarEmergencia() {
        List<String> locaisSelecionados = new ArrayList<>();

        for (JCheckBox cb : blocosCheckboxes) {
            if (cb.isSelected()) {
                locaisSelecionados.add(cb.getText());
            }
        }

        for (JCheckBox cb : andaresCheckboxes) {
            if (cb.isSelected()) {
                locaisSelecionados.add(cb.getText());
            }
        }

        if (locaisSelecionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione pelo menos um bloco ou andar.");
            return;
        }

        String localFinal = String.join(", ", locaisSelecionados);
        String tiposConcatenados;

        if (ocorrenciasSelecionadas == null || ocorrenciasSelecionadas.isEmpty()) {
            tiposConcatenados = "Não especificado";
        } else {
            tiposConcatenados = String.join(", ", ocorrenciasSelecionadas);
        }

        Emergencia emergencia = new Emergencia(localFinal, tiposConcatenados);
        EmergenciaDAO dao = new EmergenciaDAO();
        boolean sucesso = dao.registrar(emergencia);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Emergência registrada com sucesso!");
            this.dispose();
            // Abre a tela de aguardo após o registro
            new TelaAguardo().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao registrar emergência.");
        }
    }
}