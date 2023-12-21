package main.java.Journal_Management_System.gui;

import main.java.Journal_Management_System.server.ClientHandler;
import main.java.Journal_Management_System.server.Server;
import main.java.Journal_Management_System.util.Request;
import main.java.Journal_Management_System.util.Response;

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
    private static final int serverPort = 9999; // �滻Ϊ���ķ����������Ķ˿�

    public LoginFrame() throws IOException {
        setTitle("��֤ - ������־����ϵͳ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        //TODO: ��˴���ͷ��
//        txtUsername.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = txtUsername.getText();
//                String avatarPath = getAvatarPathByUsername(username);
//
//                if (avatarPath != null) {
//                    // ���ز���ʾͷ��
//                    try {
//                        BufferedImage avatarImage = ImageIO.read(new File(avatarPath));
//                        Image scaledImage = avatarImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//                        lblAvatar.setIcon(new ImageIcon(scaledImage));
//                    } catch (IOException ex) {
//                        lblAvatar.setText("Avatar not found");
//                    }
//                } else {
//                    lblAvatar.setIcon(null); // ���û��ͷ�����֮ǰ����ʾ
//                    lblAvatar.setText("No avatar");
//                }
//            }
//        });


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
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                Request request = new Request();
                request.setRequestType("login");
                request.addData("username", username);
                request.addData("password", password);

                try {
                    ClientHandler clientHandler = new ClientHandler("localhost", serverPort);
                    clientHandler.sendRequest(request);
                    Response response = clientHandler.receiveResponse();

                    if (response.getStatusCode() == 200) {
                        String role = (String) response.getData().get("role");
                        if ("admin".equals(role)) {
                            // �� AdminDashboard
                           //new AdminDashboard().setVisible(true);
                        } else {
                            // �� UserDashboard
                            //new UserDashboard().setVisible(true);
                        }
                        dispose(); // �رյ�¼����
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, response.getMessage());
                    }

                    clientHandler.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Error connecting to the server");
                    ex.printStackTrace();
                }
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                Request request = new Request();
                request.setRequestType("register");
                request.addData("username", username);
                request.addData("password", password);

                try {
                    ClientHandler clientHandler = new ClientHandler("localhost", serverPort);
                    clientHandler.sendRequest(request);
                    Response response = clientHandler.receiveResponse();

                    if (response.getStatusCode() == 200) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Registration successful");
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, response.getMessage());
                    }

                    clientHandler.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Error connecting to the server");
                    ex.printStackTrace();
                }
            }
        });


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
