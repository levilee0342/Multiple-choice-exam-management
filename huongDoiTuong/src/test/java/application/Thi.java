package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.BaiThiDAO;
import DAO.CauDaChonDAO;
import model.BaiThi;
import model.CauDaChon;
import model.CauHoiThi;

import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;

public class Thi extends JPanel {

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
    
    static Map<Integer, CauDaChon> answers= new HashMap<>(); 
    ButtonGroup group = null;
    private Timer timer;
    private boolean check = false;
    
    
    public Thi(List<CauHoiThi> ListCauHoi, int thoiGianPhut) {
    	check = false;
        this.listCauHoi = ListCauHoi;
        this.thoiGianGoc = thoiGianPhut;
        this.thoiGian = thoiGianPhut * 60;
        answers= new HashMap<>();
        initialize();
        startCountdown();      
        loadCauHoi(currentIndex);
    }

    private void initialize() {
    	currentIndex = 0;
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 1486, 82);
        panel.setBackground(new Color(72, 209, 204));
        add(panel);
        panel.setLayout(null);

        JLabel lbTenMon = new JLabel("Môn học: "+ listCauHoi.get(0).getTenMH());
        lbTenMon.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbTenMon.setBounds(77, 30, 266, 25);
        panel.add(lbTenMon);

        lbCountdown = new JLabel("Thời gian: " + formatTime(thoiGian));
        lbCountdown.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbCountdown.setBounds(483, 30, 223, 25);
        panel.add(lbCountdown);

        JButton btnNop = new JButton("Nộp bài");
        btnNop.setBounds(1095, 32, 107, 25);
        btnNop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitExam();
            }
        });
        panel.add(btnNop);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 90, 1486, 494);
        add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 10, 1486, 484);
        panel_1.add(panel_3);
        panel_3.setLayout(null);

        txtCauHoi = new JTextArea();
        txtCauHoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtCauHoi.setBounds(10, 10, 1466, 302);
        panel_3.add(txtCauHoi);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(10, 317, 1466, 165);
        panel_3.add(panel_4);
        panel_4.setLayout(null);

        rdbA = new JRadioButton("A. ");
        rdbA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbA.setBounds(121, 6, 374, 55);
        rdbA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAnswer("A");
            }
        });
        panel_4.add(rdbA);

        rdbB = new JRadioButton("B. ");
        rdbB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbB.setBounds(606, 6, 346, 55);
        rdbB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAnswer("B");
            }
        });
        panel_4.add(rdbB);

        rdbC = new JRadioButton("C. ");
        rdbC.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbC.setBounds(121, 78, 374, 55);
        rdbC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAnswer("C");
            }
        });
        panel_4.add(rdbC);

        rdbD = new JRadioButton("D. ");
        rdbD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbD.setBounds(606, 78, 346, 55);
        rdbD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAnswer("D");
            }
        });
        panel_4.add(rdbD);
        group = new ButtonGroup();     
        group.add(rdbA);
        group.add(rdbB);
        group.add(rdbC);
        group.add(rdbD);

        

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(UIManager.getColor("Button.background"));
        panel_2.setBounds(10, 584, 1486, 50);
        add(panel_2);

        JButton btnTruoc = new JButton("Trước");
        btnTruoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPreviousQuestion();
            }
        });
        btnTruoc.setBounds(281, 10, 92, 27);
        panel_2.add(btnTruoc);

        JButton btnSau = new JButton("Sau");
        btnSau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showNextQuestion();
            }
        });
        btnSau.setBounds(474, 10, 92, 27);
        panel_2.add(btnSau);

        lbIndex = new JLabel("1 / " + listCauHoi.size());
        lbIndex.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbIndex.setBounds(1309, 8, 82, 27);
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
            
            CauDaChon selectedAnswer = answers.get(index);
            if (selectedAnswer != null) {
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
        }
    }
     
    private void importCauHoiVaoList() {
    	for (int i =0; i < (listCauHoi.size()); i++) {
    		CauHoiThi currentCauHoi = listCauHoi.get(i);
            String id = currentCauHoi.getId();
            CauDaChon existingAnswer = answers.get(i);

            if (existingAnswer != null) {
                existingAnswer.setCauDaChon(null);
            } else {
                answers.put(currentIndex, new CauDaChon(ChonBaiThi.maBaiThi, id, null));
            }
        }
    }
    
    private void saveAnswer(String answer) {
        CauHoiThi currentCauHoi = listCauHoi.get(currentIndex);
        String id = currentCauHoi.getId();
        CauDaChon existingAnswer = answers.get(currentIndex);

        if (existingAnswer != null) {
            existingAnswer.setCauDaChon(answer);
        } else {
            answers.put(currentIndex, new CauDaChon(ChonBaiThi.maBaiThi, id, answer));
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

    private void submitExam() {
    	BaiThi bt = null;
    	float diem = -999;
    	check = true;
		try {
			bt = new BaiThi(ChonBaiThi.maBaiThi,DangNhap.sinhVien.getMaSV(), maMonSelected, maDotThi, thoiGianGoc, diem);
			BaiThiDAO.insertBaiThi(bt);	
			++currentIndex;
			while(currentIndex < listCauHoi.size()) {
				saveAnswer(null);
				++currentIndex;
			}
			for (Map.Entry<Integer, CauDaChon> entry : answers.entrySet()) {
	            CauDaChon answer = entry.getValue();
	            CauDaChonDAO.insertCauDaChon(answer);
	        }
			diem = BaiThiDAO.tinhDiemBaiThi(ChonBaiThi.maBaiThi);			
			bt = new BaiThi(ChonBaiThi.maBaiThi,DangNhap.sinhVien.getMaSV(), maMonSelected, maDotThi, thoiGianGoc, diem);
			BaiThiDAO.updateBaiThi(bt);	
			JOptionPane.showMessageDialog(this, "Bạn đạt dược " + diem +" điểm");
	        
	        CTBaiThi ctbt = new CTBaiThi(listCauHoi,thoiGianGoc);
	        Home.panelSV.removeAll();
	        Home.panelSV.setLayout(new BorderLayout()); 
	        Home.panelSV.add(ctbt, BorderLayout.CENTER);
	        Home.panelSV.revalidate();
	        Home.panelSV.repaint();			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    private void startCountdown() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thoiGian--;
                lbCountdown.setText("Thời gian: " + formatTime(thoiGian));
                if (thoiGian <= 0 && check == false) {
                    timer.stop();
                    JOptionPane.showMessageDialog(Thi.this, "Hết thời gian!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    submitExam();
                }
            }
        });
        timer.start();
    }

    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
    
}
