package main.java.Journal_Management_System.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel(String imagePath) {
        image = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

public class AdminDashboard extends JFrame {

    private DefaultTableModel diaryModel; // ���ڴ洢��־��Ϣ�ı��ģ��

    public AdminDashboard() {
        createUI();
        createDiaryModel();
    }

    private void createUI() {
        setTitle("����Ա��ҳ");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton viewDiariesButton = new JButton("�鿴�����û���־");
        viewDiariesButton.addActionListener(e -> viewAllDiaries());

        JButton publishDiaryButton = new JButton("������־");
        publishDiaryButton.addActionListener(e -> showPublishDialog());

        JPanel topPanel = new JPanel();
        topPanel.add(viewDiariesButton);
        topPanel.add(publishDiaryButton);
        // ��Ӵ�ͼƬ�����
        ImagePanel imagePanel = new ImagePanel("src/main/java/resources/imges/BackGround_Dashboard.jpg"); // �滻ΪͼƬ��·��
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);



        getContentPane().add(topPanel, BorderLayout.NORTH);
    }

    private void createDiaryModel() {
        String[] columnNames = {"�û���", "����", "����", "�����˻�ʱ��", "��󷢱�ʱ��"};
        diaryModel = new DefaultTableModel(columnNames, 0);
    }

    private void viewAllDiaries() {
        JDialog diariesDialog = new JDialog(this, "�����û���־", true);
        diariesDialog.setLayout(new BorderLayout());
        diariesDialog.setSize(800, 600);
        diariesDialog.setLocationRelativeTo(this);

        // �����༭��ť������¼�������
        JButton editButton = new JButton("�༭��־");
        editButton.addActionListener(e -> {
            // TODO: ʵ����ת���༭ҳ����߼�
            // �����һ���µ�JDialog��JFrame
            new JournalEditor().setVisible(true);
        });

        // ����ť��ӵ��������
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(editButton);

        JTable table = new JTable(diaryModel);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        diariesDialog.add(topPanel, BorderLayout.NORTH);
        diariesDialog.add(new JScrollPane(table), BorderLayout.CENTER);
        diariesDialog.setVisible(true);
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
            String username = "��ǰ�û���"; // ʾ�����滻Ϊʵ���û���
            String title = "��־����"; // �������һ��������������������ʽ��ȡ
            String createTime = "����ʱ��"; // ��ȡ��ǰʱ��
            String lastPublishTime = "��󷢱�ʱ��"; // ��ȡ��ǰʱ��

            diaryModel.addRow(new Object[]{username, title, content, createTime, lastPublishTime});
            publishDialog.dispose();
        });

        publishDialog.add(new JScrollPane(contentArea), BorderLayout.CENTER);
        publishDialog.add(publishButton, BorderLayout.SOUTH);

        publishDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminDashboard adminFrame = new AdminDashboard();
            adminFrame.setVisible(true);
        });
    }
}
