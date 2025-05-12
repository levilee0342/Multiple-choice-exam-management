package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import DAO.SinhVienDAO;
import DAO.UndoManager;
import model.SinhVien;
import javax.swing.JToolBar;

public class DanhSachSinhVien extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMSSV;
	private JTextField txtHo;
	private JTextField txtCMND;
	private JTextField txtSDT;
	private JTextField txtTen;
	private JTextField txtNgaysinh;
	private JTextField txtEmail;
	private JTextField txtTim;
	private JTable tblSV;
	private DefaultTableModel model;
    private int selectedIndex = -1;
    private JComboBox<String> cbMalop;
    private DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<>();
    private JTextField txtGioitinh;
    private JTextField txtQuequan;
    private UndoManager undoManager = new UndoManager();
	public DanhSachSinhVien() {
		
		setLayout(null);
		
		JLabel lblThngTinSinh = new JLabel(" Thông tin sinh viên");
		lblThngTinSinh.setBounds(507, 57, 232, 35);
		lblThngTinSinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThngTinSinh.setIcon(new ImageIcon(DanhSachSinhVien.class.getResource("/images/group.png")));
		add(lblThngTinSinh);
		
		JLabel lblNewLabel_1_3 = new JLabel("MSSV:");
		lblNewLabel_1_3.setBounds(173, 115, 77, 27);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Họ:");
		lblNewLabel_1_4.setBounds(173, 166, 84, 27);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("CMND:");
		lblNewLabel_1_5.setBounds(173, 217, 84, 27);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Số ĐT:");
		lblNewLabel_1_6.setBounds(173, 268, 84, 27);
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Mã lớp:");
		lblNewLabel_1_7.setBounds(730, 115, 82, 27);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel("Tên:");
		lblNewLabel_1_8.setBounds(730, 166, 82, 27);
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_9 = new JLabel("Ngày sinh:");
		lblNewLabel_1_9.setBounds(729, 217, 83, 27);
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_9);
		
		JLabel lblNewLabel_1_10 = new JLabel("Email:");
		lblNewLabel_1_10.setBounds(730, 318, 74, 27);
		lblNewLabel_1_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_10);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Tìm kiếm:");
		lblNewLabel_1_2_1_1.setBounds(17, 404, 113, 27);
		lblNewLabel_1_2_1_1.setIcon(new ImageIcon(DanhSachSinhVien.class.getResource("/images/tim.png")));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_2_1_1);
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(267, 112, 249, 33);
		txtMSSV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMSSV.setColumns(10);
		add(txtMSSV);
		
		txtHo = new JTextField();
		txtHo.setBounds(267, 163, 249, 33);
		txtHo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHo.setColumns(10);
		add(txtHo);
		
		txtCMND = new JTextField();
		txtCMND.setBounds(267, 214, 249, 33);
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCMND.setColumns(10);
		add(txtCMND);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(267, 265, 249, 33);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSDT.setColumns(10);
		add(txtSDT);
		
		txtTen = new JTextField();
		txtTen.setBounds(822, 163, 249, 33);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTen.setColumns(10);
		add(txtTen);
		
		txtNgaysinh = new JTextField();
		txtNgaysinh.setBounds(819, 214, 249, 33);
		txtNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNgaysinh.setColumns(10);
		add(txtNgaysinh);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(822, 315, 249, 33);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		add(txtEmail);
		
		cbMalop = new JComboBox<>(cbModel);
		cbMalop.setBounds(819, 112, 249, 33);
	    cbMalop.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    add(cbMalop);
	    
		txtTim = new JTextField();
		txtTim.setBounds(140, 401, 249, 33);
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTim.setColumns(10);
		add(txtTim);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Giới tính:");
		lblNewLabel_1_6_1.setBounds(173, 318, 74, 27);
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_6_1);
		
		JLabel lblNewLabel_1_6_2 = new JLabel("Quê quán:");
		lblNewLabel_1_6_2.setBounds(730, 268, 74, 27);
		lblNewLabel_1_6_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_6_2);
		
		txtGioitinh = new JTextField();
		txtGioitinh.setBounds(267, 315, 249, 33);
		txtGioitinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtGioitinh.setColumns(10);
		add(txtGioitinh);
		
		txtQuequan = new JTextField();
		txtQuequan.setBounds(819, 265, 249, 33);
		txtQuequan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQuequan.setColumns(10);
		add(txtQuequan);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 0, 1498, 54);
		add(toolBar);
			
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setIcon(new ImageIcon(DanhSachSinhVien.class.getResource("/images/add.png")));
		toolBar.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(DanhSachSinhVien.class.getResource("/images/update.png")));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(btnSua);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon(DanhSachSinhVien.class.getResource("/images/luu.png")));
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(btnLuu);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(DanhSachSinhVien.class.getResource("/images/xoa.png")));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(btnXoa);
		
		JButton btnReload = new JButton("Reload");
		btnReload.setIcon(new ImageIcon(DanhSachSinhVien.class.getResource("/images/reload.png")));
		btnReload.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(btnReload);
				
		model = new DefaultTableModel(
			new Object[] {"STT", "MSSV", "Họ", "Tên", "CMND", "Giới tính", "Quê quán", "Ngày Sinh", "SĐT", "Email"
			}, 0
		);
		tblSV = new JTable(model);
		
		JScrollPane scrollPane_1 = new JScrollPane(tblSV);	
		scrollPane_1.setViewportView(tblSV);
		scrollPane_1.setBounds(10, 441, 1498, 223);
		add(scrollPane_1);

		btnThem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	clearInputFields();
		    	selectedIndex = -1;
		        editable(true);
		        btnThem.setEnabled(false);
		        btnSua.setEnabled(false);
		        btnLuu.setEnabled(true);
		        btnXoa.setEnabled(false);
		        btnReload.setEnabled(false);
		        }
		});

		btnSua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (selectedIndex != -1) {
		        	editable(true);
		            txtMSSV.setEditable(false);
		            btnThem.setEnabled(false);
		            btnSua.setEnabled(false);
		            btnLuu.setEnabled(true);
		            btnXoa.setEnabled(false);
		            btnReload.setEnabled(false);
		        } else {
		            JOptionPane.showMessageDialog(DanhSachSinhVien.this, "Vui lòng chọn sinh viên để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		
		btnLuu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        String maSV = txtMSSV.getText();
		        String ho = txtHo.getText();
		        String ten = txtTen.getText();
		        String CMND = txtCMND.getText();
		        String gioiTinh = txtGioitinh.getText();
		        String queQuan = txtQuequan.getText();
		        String ngaySinhStr = txtNgaysinh.getText();
		        String soDT = txtSDT.getText();
		        String email = txtEmail.getText();
		        String maLop = (String) cbMalop.getSelectedItem();
		        
		        if (maSV.isEmpty() || ho.isEmpty() || ten.isEmpty() || CMND.isEmpty() || gioiTinh.isEmpty() || queQuan.isEmpty() || ngaySinhStr.isEmpty() || soDT.isEmpty() || email.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        java.sql.Date ngaySinh = null;
		        try {
		            ngaySinh = java.sql.Date.valueOf(ngaySinhStr);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        try {
		        	SinhVienDAO dao = new SinhVienDAO();
		        	
		        	
		        	
		        	SinhVien sv = new SinhVien(ho, ten, CMND, ngaySinh, gioiTinh, queQuan, maSV, maLop, null, soDT, email);
		        	
		            boolean success;
		            if (selectedIndex == -1) {
		            	performSaveState();
		            	if (dao.checkExist(maSV)) {
			                JOptionPane.showMessageDialog(null, "Mã sinh viên đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
		                // Thêm mới SinhVien
		                success = dao.insertSinhVien(sv);
		                if (success) {
		                    JOptionPane.showMessageDialog(DanhSachSinhVien.this, "Thêm thành công");
		                } else {
		                    JOptionPane.showMessageDialog(DanhSachSinhVien.this, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		            	performSaveState();
		                // Cập nhật SinhVien hiện tại
		                success = dao.updateSinhVien(sv);
		                if (success) {
		                    JOptionPane.showMessageDialog(DanhSachSinhVien.this, "Cập nhật thành công");
		                } else {
		                    JOptionPane.showMessageDialog(DanhSachSinhVien.this, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            initTable();
		            clearInputFields();
		            editable(false);
		            btnThem.setEnabled(true);
	                btnSua.setEnabled(true);
	                btnLuu.setEnabled(false);
	                btnXoa.setEnabled(true);
	                btnReload.setEnabled(true);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(DanhSachSinhVien.this, "Lỗi khi lưu dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		
		btnReload.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        initTable();
		        clearInputFields();
		        editable(false);
		    }
		});

		 btnXoa.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = tblSV.getSelectedRow();
	                if (selectedRow == -1) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sinh viên cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                performSaveState(); 	                
	                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sinh viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	                try {
	                    if (confirm == JOptionPane.YES_OPTION) {
	                        String maSV = (String) model.getValueAt(selectedRow, 1);
	                        SinhVienDAO dao = new SinhVienDAO();
	                        dao.deleteSinhVien(maSV);
	                        model.removeRow(selectedRow);
	                        JOptionPane.showMessageDialog(null, "Xóa sinh viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                        initTable();
	                        clearInputFields();
	                        editable(false);
	                    }
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });		
		
		tblSV.addMouseListener((MouseListener) new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedIndex = tblSV.getSelectedRow();
                if (selectedIndex != -1) {
                    loadSelectedRowData();
                }
            }
        });

		cbMalop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initTable();
                clearInputFields();
                editable(false);
            }
        });
		
		txtTim.addKeyListener (new KeyAdapter() {
	            public void keyReleased(KeyEvent e) {
	            	
                    try {
                    	initTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	            }
	        });

        initcbMalop();
        btnLuu.setEnabled(false);
	}

	private void loadSelectedRowData() {
		if (selectedIndex != -1) {
            String maSV = (String) tblSV.getValueAt(selectedIndex, 1);
            try {
                SinhVienDAO dao = new SinhVienDAO();
                SinhVien sv = dao.getSinhVienByMaSV(maSV);
                
                txtMSSV.setText(sv.getMaSV());
                txtHo.setText(sv.getHo());
                txtTen.setText(sv.getTen());
                txtCMND.setText(sv.getCMND());
                txtGioitinh.setText(sv.getGioiTinh());
                txtQuequan.setText(sv.getQueQuan());
                txtSDT.setText(sv.getSDT());
                txtEmail.setText(sv.getEmail());
                txtNgaysinh.setText(sv.getNgaySinh().toString());
                txtMSSV.setEditable(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            editable(false);
        }
	}
	
	private void clearInputFields() {
        txtMSSV.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtCMND.setText("");
        txtGioitinh.setText("");
        txtQuequan.setText("");
        txtNgaysinh.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        selectedIndex = -1;
        txtMSSV.setEditable(true);
    }
		
	private void editable(Boolean check) {
        txtMSSV.setEditable(check);
        txtHo.setEditable(check);
        txtTen.setEditable(check);
        txtCMND.setEditable(check);
        txtGioitinh.setEditable(check);
        txtQuequan.setEditable(check);
        txtNgaysinh.setEditable(check);
        txtSDT.setEditable(check);
        txtEmail.setEditable(check);
	}
	
	private void initcbMalop() {
        try {
            SinhVienDAO stfDAO = new SinhVienDAO();
            List<String> maLopList = stfDAO.getAllMaLop(); 

            cbModel.removeAllElements();
            for (String maLop : maLopList) {
                cbModel.addElement(maLop);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải mã lớp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initTable() {
    	List<SinhVien> list = new ArrayList<>();
        String keyword = txtTim.getText(); 
        String selectedMaLop = (String) cbMalop.getSelectedItem();

        try {
            SinhVienDAO stfDAO = new SinhVienDAO();

            if (keyword != null && !keyword.trim().isEmpty()) {
            	list = stfDAO.searchSinhVien(keyword, selectedMaLop);
            } else {
                list = stfDAO.getSinhVienByMaLop(selectedMaLop);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        model.setRowCount(0);
        int stt = 1;
        for (SinhVien sv : list) {
            model.addRow(new Object[]{
                stt++,
                sv.getMaSV(),
                sv.getHo(),
                sv.getTen(),
                sv.getCMND(),  
                sv.getGioiTinh(),
                sv.getQueQuan(),
                sv.getNgaySinh(),
                sv.getSDT(),
                sv.getEmail()
            });
        }
    }
    
    private void performSaveState() {
        undoManager.saveState(model);
    }
}
