package main.java.Journal_Management_System.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class ImagePanel2 extends JPanel {
    private Image image;

    public ImagePanel2(String imagePath) {
        image = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

public class UserDashboard extends JFrame {
    private DefaultTableModel diaryModel; // ���ڴ洢��־��Ϣ�ı��ģ��
    private JTable table; // ��Ϊ��Ա��������

    public UserDashboard() {
        createUI();
        createDiaryModel();
        showWelcomeDialog("�û���", "src/main/java/resources/imges/avatar.jpg");
    }

    private void createUI() {
        setTitle("�û���ҳ");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton viewDiariesButton = new JButton("�鿴�ҵ���־");
        viewDiariesButton.addActionListener(e -> viewAllDiaries());

        JButton publishDiaryButton = new JButton("������־");
        publishDiaryButton.addActionListener(e -> showPublishDialog());

        JPanel topPanel = new JPanel();
        topPanel.add(viewDiariesButton);
        topPanel.add(publishDiaryButton);

        ImagePanel2 imagePanel = new ImagePanel2("src/main/java/resources/imges/BackGround_Dashboard.jpg");
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);
    }

    private void createDiaryModel() {
        String[] columnNames = {"����", "����", "����ʱ��", "��󷢱�ʱ��"};

        diaryModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // ʹ��񲻿ɱ༭
                return false;
            }
        };

        table = new JTable(diaryModel);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    private void viewAllDiaries() {
        JDialog diariesDialog = new JDialog(this, "�����û���־", true);
        diariesDialog.setLayout(new BorderLayout());
        diariesDialog.setSize(800, 600);
        diariesDialog.setLocationRelativeTo(this);

        JButton editButton = new JButton("�༭��־");
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                // �򿪱༭��־�Ĵ��ڻ�Ի���
                editDiary(selectedRow);
            } else {
                JOptionPane.showMessageDialog(diariesDialog, "��ѡ��һ����־���б༭");
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(editButton);

        diariesDialog.add(topPanel, BorderLayout.NORTH);
        diariesDialog.add(new JScrollPane(table), BorderLayout.CENTER);
        diariesDialog.setVisible(true);
    }

    private void editDiary(int selectedRow) {
        // TODO: ʵ�ֱ༭��־���߼�
        // ���磬��һ���µĴ��ڻ�Ի������ڱ༭ѡ�е���־
    }

    private void showPublishDialog() {
        JDialog publishDialog = new JDialog(this, "������־", true);
        publishDialog.setLayout(new BorderLayout());
        publishDialog.setSize(400, 300);
        publishDialog.setLocationRelativeTo(this);

        JTextArea contentArea = new JTextArea();
        JButton publishButton = new JButton("����");

        publishButton.addActionListener(e -> {
            String content = contentArea.getText();
            String title = "��־����"; // ����������һ��������������������ʽ��ȡ
            String createTime = "����ʱ��"; // ��ȡ��ǰʱ��
            String lastPublishTime = "��󷢱�ʱ��"; // ��ȡ��ǰʱ��
            diaryModel.addRow(new Object[]{title, content, createTime, lastPublishTime});
            publishDialog.dispose();
        });

        publishDialog.add(new JScrollPane(contentArea), BorderLayout.CENTER);
        publishDialog.add(publishButton, BorderLayout.SOUTH);

        publishDialog.setVisible(true);
    }

    private void showWelcomeDialog(String userName, String imagePath) {
        JDialog welcomeDialog = new JDialog(this, "��ӭ", true);
        welcomeDialog.setLayout(new BorderLayout());
        welcomeDialog.setSize(300, 200);
        welcomeDialog.setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("��ӭ, " + userName + "!", JLabel.CENTER);
        welcomeDialog.add(welcomeLabel, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeDialog.add(imageLabel, BorderLayout.CENTER);

        welcomeDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDashboard userDashboard = new UserDashboard();
//            userDashboard.showWelcomeDialog("�û���", "src/main/java/resources/imges/avatar.jpg");
            userDashboard.setVisible(true);
        });
    }
}
