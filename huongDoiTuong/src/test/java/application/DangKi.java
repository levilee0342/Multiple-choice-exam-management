package application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import DAO.GiaoVienDAO;
import DAO.SinhVienDAO;
import DAO.TaiKhoanDAO;
import helper.EditHelper;
import model.GiaoVien;
import model.SinhVien;
import model.TaiKhoan;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
public class DangKi extends JPanel {

    private static final long serialVersionUID = 1L;
    public static JTextField txtDangKi;
    public static JPasswordField txtMatKhau, txtMatKhau2;
    public static SinhVien sinhVien = new SinhVien();
    public static GiaoVien giaoVien = new GiaoVien();;

    public DangKi() {
        setLayout(null); // Use null layout for absolute positioning

        JLabel lblNewLabel = new JLabel("HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(0, 10, 800, 50); // Set bounds according to your layout
        add(lblNewLabel);

        JLabel lblCSTi = new JLabel("CƠ SỞ TẠI TP HỒ CHÍ MINH");
        lblCSTi.setHorizontalAlignment(SwingConstants.CENTER);
        lblCSTi.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblCSTi.setBounds(0, 60, 800, 50); // Adjust bounds
        add(lblCSTi);

        JLabel lblThiTrcNghim = new JLabel("TẠO TÀI KHOẢN");
        lblThiTrcNghim.setHorizontalAlignment(SwingConstants.CENTER);
        lblThiTrcNghim.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblThiTrcNghim.setBounds(0, 121, 800, 50); // Adjust bounds
        add(lblThiTrcNghim);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboBox.setBounds(291, 228, 368, 30);
        add(comboBox);
        comboBox.addItem("Giáo viên");
        comboBox.addItem("Sinh viên");
        
        JLabel txtMaSV = new JLabel("Tên đăng nhập:");
        txtMaSV.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMaSV.setBounds(99, 284, 150, 30);
        add(txtMaSV);

        txtDangKi = new JTextField();
        txtDangKi.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtDangKi.setBounds(291, 284, 368, 30);
        add(txtDangKi);

        JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(99, 334, 150, 30);
        add(lblNewLabel_1_1);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMatKhau.setBounds(291, 334, 368, 30);
        add(txtMatKhau);

        txtMatKhau2 = new JPasswordField();
        txtMatKhau2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMatKhau2.setBounds(291, 389, 368, 30);
        add(txtMatKhau2);
        
        JButton btnDangKi = new JButton("Đăng kí");
        btnDangKi.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnDangKi.setBounds(329, 478, 150, 40);
        btnDangKi.addActionListener(e -> {
            try {
            	String matKhau = new String(txtMatKhau.getPassword());
            	String matKhau2 = new String(txtMatKhau2.getPassword());
            	if (!matKhau.equals(matKhau2)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu không khớp, vui lòng nhập lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            	else if (EditHelper.isUsed("TaiKhoan", "maTK", txtDangKi.getText()) || TaiKhoanDAO.getGiaoVienByMaTK(txtDangKi.getText())!=null||TaiKhoanDAO.getSinhVienByMaTK(txtDangKi.getText())!=null) {
                    JOptionPane.showMessageDialog(this, "Tên đăng nhập đã được sử dụng, vui lòng chọn tên khác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            	else {            		
            		TaiKhoan tk = new TaiKhoan(txtDangKi.getText(),matKhau,comboBox.getSelectedItem().toString());
            		TaiKhoanDAO.insertTaiKhoan(tk);
            		 if (TaiKhoanDAO.getVaiTroByMaTK(txtDangKi.getText()).equals("Sinh viên")) {
            			 SinhVien sv = TaiKhoanDAO.getSinhVienChuaDangKiByMaTK(txtDangKi.getText());
            			 sv.setMaTK(txtDangKi.getText());
            			 SinhVienDAO.updateSinhVien(sv);
            		 }
            		 else if (TaiKhoanDAO.getVaiTroByMaTK(txtDangKi.getText()).equals("Giáo viên")) {
            			 GiaoVien gv = TaiKhoanDAO.getGiaoVienChuaDangKiByMaTK(txtDangKi.getText());
            			 gv.setMaTK(txtDangKi.getText());
            			 GiaoVienDAO.updateGiaoVien(gv);
            		 }
            		 JOptionPane.showMessageDialog(DangKi.this, "Đăng kí thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            	}
            	
            	        	
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        add(btnDangKi);
        
        
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Nhập lại mật khẩu:");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(99, 389, 182, 30);
        add(lblNewLabel_1_1_1);
        
        
        
        JLabel lblVaiTr = new JLabel("Vai trò:");
        lblVaiTr.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblVaiTr.setBounds(99, 228, 150, 30);
        add(lblVaiTr);
    }
}

