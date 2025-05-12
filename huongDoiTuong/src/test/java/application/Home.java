package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import model.GiaoVien;
import model.SinhVien;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JPanel panel;
    public static JPanel panelSV;
    public static JTabbedPane tabbedPane;
    public static JButton btnCauhoi,btnDiem, btnMon, btnLop, btnSinhvien, btnThi, btnDiem1;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Home() {

        setTitle("HỆ THỐNG THI TRẮC NGHIỆM\r\n");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/images/logo.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setBounds(100, 100, 1536, 864);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("File");
        mnNewMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuBar.add(mnNewMenu);

        JMenuItem mnDangxuat = new JMenuItem("Đăng xuất ");
        mnDangxuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        mnDangxuat.setIcon(new ImageIcon(Home.class.getResource("/images/logout.png")));
        mnNewMenu.add(mnDangxuat);
        mnDangxuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                	DangNhap.sinhVien = new SinhVien();
                    DangNhap.giaoVien = new GiaoVien();
                    Home.btnCauhoi.setEnabled(false);
                    Home.btnDiem.setEnabled(false);
                    Home.btnLop.setEnabled(false);
                    Home.btnMon.setEnabled(false);
                    Home.btnSinhvien.setEnabled(false);
                    Home.btnThi.setEnabled(false);
                    Home.btnDiem1.setEnabled(false);
                    DangNhap.txtDangNhap.setText("");
                    DangNhap.txtMatKhau.setText("");
                    panel.removeAll();
                    panelSV.removeAll();
                    JOptionPane.showMessageDialog(null, "Đã đăng xuất thành công!");
                    
                }
            }
        });

        JSeparator separator = new JSeparator();
        mnNewMenu.add(separator);

        JSeparator separator_1 = new JSeparator();
        mnNewMenu.add(separator_1);

        JMenuItem mnExit = new JMenuItem("Exit");
        mnExit.setIcon(new ImageIcon(Home.class.getResource("/images/exit.png")));
        mnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        mnNewMenu.add(mnExit);

        JMenu mnNewMenu_1 = new JMenu("Help");
        mnNewMenu_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuBar.add(mnNewMenu_1);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel Quanli = new JPanel();
        ImageIcon iconQL = new ImageIcon(Home.class.getResource("/images/quanli.png"));
        ImageIcon iconLG = new ImageIcon(Home.class.getResource("/images/login.png"));
        ImageIcon iconSU = new ImageIcon(Home.class.getResource("/images/signup.png"));
        
        JPanel DangNhap = new DangNhap();
        tabbedPane.addTab("ĐĂNG NHẬP", iconLG, DangNhap, null);
        
        JPanel DangKi = new DangKi();
        tabbedPane.addTab("ĐĂNG KÍ", iconSU, DangKi, null);
        tabbedPane.addTab(" QUẢN LÍ ", iconQL, Quanli, null);

        JToolBar toolBar = new JToolBar();
        
        panel = new JPanel();

        GroupLayout gl_Quanli = new GroupLayout(Quanli);
        gl_Quanli.setHorizontalGroup(
        	gl_Quanli.createParallelGroup(Alignment.LEADING)
        		.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1525, Short.MAX_VALUE)
        		.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1525, Short.MAX_VALUE)
        );
        gl_Quanli.setVerticalGroup(
        	gl_Quanli.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_Quanli.createSequentialGroup()
        			.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
        );

        btnSinhvien = new JButton(" SINH VIÊN ");
        btnSinhvien.setIcon(new ImageIcon(Home.class.getResource("/images/group.png")));
        toolBar.add(btnSinhvien);

        btnLop = new JButton(" LỚP HỌC ");
        btnLop.setIcon(new ImageIcon(Home.class.getResource("/images/presentation.png")));
        toolBar.add(btnLop);

        btnMon = new JButton(" MÔN HỌC ");
        btnMon.setIcon(new ImageIcon(Home.class.getResource("/images/books.png")));
        toolBar.add(btnMon);

        btnDiem = new JButton(" ĐIỂM THI ");
        btnDiem.setIcon(new ImageIcon(Home.class.getResource("/images/score.png")));
        toolBar.add(btnDiem);

        btnCauhoi = new JButton(" CÂU HỎI ");
        btnCauhoi.setIcon(new ImageIcon(Home.class.getResource("/images/question.png")));
        toolBar.add(btnCauhoi);
        Quanli.setLayout(gl_Quanli);

        JPanel Sinhvien = new JPanel();
        ImageIcon icon1 = new ImageIcon(Home.class.getResource("/images/sinhvien.png"));
        tabbedPane.addTab(" SINH VIÊN ", icon1, Sinhvien, null);        
        JToolBar toolBar_1 = new JToolBar();
        
        panelSV = new JPanel();
        GroupLayout gl_Sinhvien = new GroupLayout(Sinhvien);
        gl_Sinhvien.setHorizontalGroup(
        	gl_Sinhvien.createParallelGroup(Alignment.LEADING)
        		.addComponent(toolBar_1, GroupLayout.DEFAULT_SIZE, 1525, Short.MAX_VALUE)
        		.addGroup(gl_Sinhvien.createSequentialGroup()
        			.addGap(10)
        			.addComponent(panelSV, GroupLayout.PREFERRED_SIZE, 1525, GroupLayout.PREFERRED_SIZE))
        );
        gl_Sinhvien.setVerticalGroup(
        	gl_Sinhvien.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_Sinhvien.createSequentialGroup()
        			.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panelSV, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
        );

        btnThi = new JButton(" BẮT ĐẦU THI ");
        btnThi.setIcon(new ImageIcon(Home.class.getResource("/images/test.png")));
        toolBar_1.add(btnThi);

        btnDiem1 = new JButton(" ĐIỂM THI");      
        btnDiem1.setIcon(new ImageIcon(Home.class.getResource("/images/score.png")));
        toolBar_1.add(btnDiem1);

        Sinhvien.setLayout(gl_Sinhvien);
        
        mnExit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    }
		});

        btnSinhvien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DanhSachSinhVien dssv = new DanhSachSinhVien();
                panel.removeAll();
                panel.setLayout(new BorderLayout()); 
                panel.add(dssv, BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
            }
        });
        
        btnLop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DanhSachLop dsl = new DanhSachLop();
                panel.removeAll();
                panel.setLayout(new BorderLayout()); 
                panel.add(dsl, BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
            }
        });
        
        btnMon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DanhSachMon dsm = new DanhSachMon();
                panel.removeAll();
                panel.setLayout(new BorderLayout()); 
                panel.add(dsm, BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
            }
        });
        
        btnDiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DanhSachDiem dsd = new DanhSachDiem();
                panel.removeAll();
                panel.setLayout(new BorderLayout()); 
                panel.add(dsd, BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
            }
        });   
        
        btnCauhoi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CauHoiThi cht = new CauHoiThi();
                panel.removeAll();
                panel.setLayout(new BorderLayout()); 
                panel.add(cht, BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
        	}
        });
        
        btnThi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ChonBaiThi cbt = new ChonBaiThi();
        		panelSV.removeAll();
        		panelSV.setLayout(new BorderLayout()); 
        		panelSV.add(cbt, BorderLayout.CENTER);
        		panelSV.revalidate();
        		panelSV.repaint();
        	}
        });
        
        btnDiem1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DiemSinhVien dsv = new DiemSinhVien();
        		panelSV.removeAll();
        		panelSV.setLayout(new BorderLayout()); 
        		panelSV.add(dsv, BorderLayout.CENTER);
        		panelSV.revalidate();
        		panelSV.repaint();
        	}
        });
        Home.btnCauhoi.setEnabled(false);
        Home.btnDiem.setEnabled(false);
        Home.btnLop.setEnabled(false);
        Home.btnMon.setEnabled(false);
        Home.btnSinhvien.setEnabled(false);
        Home.btnThi.setEnabled(false);
        Home.btnDiem1.setEnabled(false);
    }
}
