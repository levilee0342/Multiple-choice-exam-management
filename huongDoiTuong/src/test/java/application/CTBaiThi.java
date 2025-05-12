package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import model.CauDaChon;
import model.CauHoiThi;

import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;

public class CTBaiThi extends JPanel {

    private static final long serialVersionUID = 1L;
    private List<CauHoiThi> listCauHoi;
    private int thoiGian;
    private int thoiGianGoc;
    private int currentIndex = 0; // Lưu chỉ số câu hỏi hiện tại
    private JTextArea txtCauHoi;
    private JRadioButton rdbA, rdbB, rdbC, rdbD;
    private JLabel lbIndex, lbCountdown;
    public static String maDotThi;
    public static String maMonSelected;
    public static String maBaiThi;
    ButtonGroup group = null;
    private Timer timer;
    private boolean check = false;
    private JLabel lbDapAn;
    
    
    public CTBaiThi(List<CauHoiThi> ListCauHoi, int thoiGianPhut) {
    	check = false;
        this.listCauHoi = ListCauHoi;
        this.thoiGianGoc = thoiGianPhut*60;
        initialize();
        loadCauHoi(currentIndex);
    }

    private void initialize() {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 0, 1500, 58);
        panel.setBackground(new Color(72, 209, 204));
        add(panel);
        panel.setLayout(null);

        JLabel lbTenMon = new JLabel("Môn học: "+ listCauHoi.get(0).getTenMH());
        lbTenMon.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbTenMon.setBounds(104, 14, 266, 25);
        panel.add(lbTenMon);

        lbCountdown = new JLabel("Thời gian: " + formatTime(thoiGianGoc));
        lbCountdown.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbCountdown.setBounds(416, 14, 223, 25);
        panel.add(lbCountdown);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.setBounds(1237, 16, 94, 25);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ChonBaiThi cbt = new ChonBaiThi();
        		Home.panelSV.removeAll();
        		Home.panelSV.setLayout(new BorderLayout()); 
        		Home.panelSV.add(cbt, BorderLayout.CENTER);
        		Home.panelSV.revalidate();
        		Home.panelSV.repaint();
            }
        });
        panel.add(btnThoat);

        lbDapAn = new JLabel("Đáp án: ");
        lbDapAn.setBackground(Color.WHITE);
        lbDapAn.setForeground(Color.BLACK);
        lbDapAn.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbDapAn.setBounds(623, 0, 223, 58);
        panel.add(lbDapAn);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 59, 1500, 512);
        add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 0, 1510, 512);
        panel_1.add(panel_3);
        panel_3.setLayout(null);

        txtCauHoi = new JTextArea();
        txtCauHoi.setFont(new Font("Tahoma", Font.BOLD, 11));
        txtCauHoi.setBounds(10, 10, 1479, 341);
        panel_3.add(txtCauHoi);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(10, 360, 1479, 142);
        panel_3.add(panel_4);
        panel_4.setLayout(null);

        rdbA = new JRadioButton("A. ");
        rdbA.setBounds(121, 6, 374, 55);
        panel_4.add(rdbA);

        rdbB = new JRadioButton("B. ");
        rdbB.setBounds(606, 6, 346, 55);
        panel_4.add(rdbB);

        rdbC = new JRadioButton("C. ");
        rdbC.setBounds(121, 78, 374, 55);
        panel_4.add(rdbC);

        rdbD = new JRadioButton("D. ");
        rdbD.setBounds(606, 78, 346, 55);
        panel_4.add(rdbD);
        group = new ButtonGroup();     
        group.add(rdbA);
        group.add(rdbB);
        group.add(rdbC);
        group.add(rdbD);

        rdbA.setEnabled(false);
        rdbB.setEnabled(false);
        rdbC.setEnabled(false);
        rdbD.setEnabled(false);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(UIManager.getColor("Button.background"));
        panel_2.setBounds(10, 570, 1500, 47);
        add(panel_2);

        JButton btnTruoc = new JButton("Trước");
        btnTruoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPreviousQuestion();
            }
        });
        btnTruoc.setBounds(420, 10, 92, 27);
        panel_2.add(btnTruoc);

        JButton btnSau = new JButton("Sau");
        btnSau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showNextQuestion();
            }
        });
        btnSau.setBounds(572, 10, 92, 27);
        panel_2.add(btnSau);

        lbIndex = new JLabel("1 / " + listCauHoi.size());
        lbIndex.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbIndex.setBounds(1316, 8, 82, 27);
        panel_2.add(lbIndex);
    }

    private void loadCauHoi(int index) {
        if (index >= 0 && index < listCauHoi.size()) {
            CauHoiThi cauHoi = listCauHoi.get(index);
            txtCauHoi.setText("Câu hỏi: " + cauHoi.getNoiDung());
            rdbA.setText("A. " + cauHoi.getA());
            rdbB.setText("B. " + cauHoi.getB());
            rdbC.setText("C. " + cauHoi.getC());
            rdbD.setText("D. " + cauHoi.getD());
            lbIndex.setText((index + 1) + " / " + listCauHoi.size());
            
            CauDaChon selectedAnswer = Thi.answers.get(index);
            if (selectedAnswer != null && selectedAnswer.getCauDaChon() != null) {
                switch (selectedAnswer.getCauDaChon()) {
                    case "A":
                        rdbA.setSelected(true);
                        break;
                    case "B":
                        rdbB.setSelected(true);
                        break;
                    case "C":
                        rdbC.setSelected(true);
                        break;
                    case "D":
                        rdbD.setSelected(true);
                        break;
                }
            } else {
                group.clearSelection(); 
            }
            lbDapAn.setText("Đáp án: "+listCauHoi.get(currentIndex).getDapAn());
        }
    }
    

    private void showPreviousQuestion() {
        if (currentIndex > 0) {
            currentIndex--;
            loadCauHoi(currentIndex);
        }
    }

    private void showNextQuestion() {
        if (currentIndex < listCauHoi.size() - 1) {
            currentIndex++;
            loadCauHoi(currentIndex);
        }
    }


  

    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
    
}
