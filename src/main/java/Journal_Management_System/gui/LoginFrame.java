package main.java.Journal_Management_System.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister, btnReset;
    private JLabel lblAvatar;

    public LoginFrame() throws IOException {
        setTitle("��֤ - ������־����ϵͳ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // ���ھ���
        setLayout(new BorderLayout(10, 10)); // ʹ��BorderLayout

        lblAvatar = new JLabel();
        lblAvatar.setHorizontalAlignment(JLabel.CENTER);
        lblAvatar.setBorder(new EmptyBorder(100, 0, 0, 0)); // �ϱ߾�Ϊ10����

        // ���������ͷ���ǩ������
        BufferedImage originalImage = ImageIO.read(new File("src/main/java/resources/imges/avatar.jpg")); // ����ԭʼͼƬ
        // ����ͷ���С
        Image scaledImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // ����ͼƬ
        lblAvatar.setIcon(new ImageIcon(scaledImage));
        add(lblAvatar, BorderLayout.NORTH);

        // �������������õ�¼��
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // �û��������
        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(new JLabel("Username:"), constraints);
        txtUsername = new JTextField(20);
        constraints.gridx = 1;
        centerPanel.add(txtUsername, constraints);

        // ���������
        constraints.gridx = 0;
        constraints.gridy = 1;
        centerPanel.add(new JLabel("Password:"), constraints);
        txtPassword = new JPasswordField(20);
        constraints.gridx = 1;
        centerPanel.add(txtPassword, constraints);

        // ��¼��ť
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        btnLogin = new JButton("Login");
        centerPanel.add(btnLogin, constraints);

        add(centerPanel, BorderLayout.CENTER);

        // ��������������������ť
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnRegister = new JButton("Register");
        btnReset = new JButton("Reset");
        southPanel.add(btnRegister);
        southPanel.add(btnReset);
        add(southPanel, BorderLayout.SOUTH);

        // �¼�������
        // TODO: ��Ӱ�ť���¼������߼�
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new LoginFrame().setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
