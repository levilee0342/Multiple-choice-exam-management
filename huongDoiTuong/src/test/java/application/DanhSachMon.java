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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import DAO.MonHocDAO;
import DAO.UndoManager;
import model.MonHoc;

public class DanhSachMon extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMamon;
	private JTextField txtTenmon;
	private JTextField txtTim;
	private JTable dsMon;
	private DefaultTableModel model;
    private int selectedIndex = -1;
    private UndoManager undoManager = new UndoManager();
    
	public DanhSachMon() {
		
		setLayout(null);
		
		JLabel lblThngTinMn = new JLabel(" Thông tin môn học");
		lblThngTinMn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThngTinMn.setBounds(518, 96, 239, 37);
		lblThngTinMn.setIcon(new ImageIcon(DanhSachMon.class.getResource("/images/books.png")));
		add(lblThngTinMn);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Mã môn học:");
		lblNewLabel_1_3_1.setBounds(421, 170, 107, 31);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_3_1);
		
		txtMamon = new JTextField();
		txtMamon.setBounds(588, 170, 316, 31);
		txtMamon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMamon.setColumns(10);
		add(txtMamon);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Tên môn học:");
		lblNewLabel_1_3_2.setBounds(421, 223, 107, 35);
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_3_2);
		
		txtTenmon = new JTextField();
		txtTenmon.setBounds(588, 225, 316, 31);
		txtTenmon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenmon.setColumns(10);
		add(txtTenmon);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Tìm kiếm:");
		lblNewLabel_1_2_1_1.setBounds(31, 337, 91, 24);
		lblNewLabel_1_2_1_1.setIcon(new ImageIcon(DanhSachMon.class.getResource("/images/tim.png")));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_2_1_1);
		
		txtTim = new JTextField();
		txtTim.setBounds(132, 334, 239, 32);
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTim.setColumns(10);
		add(txtTim);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 0, 1526, 52);
		add(toolBar);
		JButton btnThem = new JButton("Thêm");
				btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnThem.setIcon(new ImageIcon(DanhSachMon.class.getResource("/images/add.png")));
				toolBar.add(btnThem);
				
				JButton btnSua = new JButton("Sửa");
				btnSua.setIcon(new ImageIcon(DanhSachMon.class.getResource("/images/update.png")));
				btnSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
				toolBar.add(btnSua);
				
				JButton btnLuu = new JButton("Lưu");
				btnLuu.setIcon(new ImageIcon(DanhSachMon.class.getResource("/images/luu.png")));
				btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				toolBar.add(btnLuu);
				
				JButton btnXoa = new JButton("Xóa");
				btnXoa.setIcon(new ImageIcon(DanhSachMon.class.getResource("/images/xoa.png")));
				btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
				toolBar.add(btnXoa);
				
				JButton btnReload = new JButton("Reload");
				btnReload.setIcon(new ImageIcon(DanhSachMon.class.getResource("/images/reload.png")));
				btnReload.setFont(new Font("Tahoma", Font.PLAIN, 14));
				toolBar.add(btnReload);
		
		model = new DefaultTableModel(
			new Object[] {"STT", "Mã môn học", "Tên môn học"
			},0	
		);
		dsMon = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(dsMon);		
		scrollPane.setViewportView(dsMon);
		scrollPane.setBounds(10, 382, 1511, 278);
		add(scrollPane);		
		
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
		            txtMamon.setEditable(false);
		            btnThem.setEnabled(false);
		            btnSua.setEnabled(false);
		            btnLuu.setEnabled(true);
		            btnXoa.setEnabled(false);
		            btnReload.setEnabled(false);

		        } else {
		            JOptionPane.showMessageDialog(DanhSachMon.this, "Vui lòng chọn môn học để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		
		btnLuu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
 
		    	btnThem.setEnabled(true);
                btnSua.setEnabled(true);
                btnLuu.setEnabled(false);
                btnXoa.setEnabled(true);
                btnReload.setEnabled(true);
		    	
		        String maMH = txtMamon.getText();
		        String tenMH = txtTenmon.getText();

		        
		        if (maMH.isEmpty() || tenMH.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		        	MonHocDAO dao = new MonHocDAO();
		            MonHoc mh = new MonHoc(maMH, tenMH);		            
		            
		            boolean success;
		            if (selectedIndex == -1) {
				    	performSaveState();
				    	if (dao.checkExist(maMH)) {
			                JOptionPane.showMessageDialog(null, "Mã môn học đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
		                // Thêm 
		                success = dao.insertMonHoc(mh);
		                if (success) {
		                    JOptionPane.showMessageDialog(DanhSachMon.this, "Thêm thành công");
		                } else {
		                    JOptionPane.showMessageDialog(DanhSachMon.this, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
				    	performSaveState();
		                // Cập nhật 
		                success = dao.updateMonHoc(mh);
		                if (success) {
		                    JOptionPane.showMessageDialog(DanhSachMon.this, "Cập nhật thành công");
		                } else {
		                    JOptionPane.showMessageDialog(DanhSachMon.this, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            initTable();
		            clearInputFields();
		            editable(false);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(DanhSachMon.this, "Lỗi khi lưu dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		 btnXoa.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = dsMon.getSelectedRow();
	                if (selectedRow == -1) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng chọn môn học cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                performSaveState(); 
	                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa môn học này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	                try {
	                    if (confirm == JOptionPane.YES_OPTION) {
	                        String maMH = (String) model.getValueAt(selectedRow, 1);
	                        MonHocDAO dao = new MonHocDAO();
	                        dao.deleteMonHoc(maMH);
	                        model.removeRow(selectedRow);
	                        JOptionPane.showMessageDialog(null, "Xóa môn học thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                        initTable();
	                        clearInputFields();
	                        editable(false);
	                    }
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
				
		dsMon.addMouseListener((MouseListener) new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedIndex = dsMon.getSelectedRow();
                if (selectedIndex != -1) {
                    loadSelectedRowData();
                }
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
		
		initTable();
		 btnLuu.setEnabled(false);
	}
	
	private void initTable() {
		List<MonHoc> list = new ArrayList<>();
		String keyword = txtTim.getText(); 
		try {
			MonHocDAO  stfDAO = new MonHocDAO();
			if (keyword != null && !keyword.trim().isEmpty()) {
				list = stfDAO.searchMonHoc(keyword);
			} else {
				list = stfDAO.getAllMonHoc();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		model.setRowCount(0);
		int stt = 1;
	    for (MonHoc listMonHoc : list) {
	        model.addRow(new Object[]{
	        		stt++,
	        		listMonHoc.getMaMH(),
	        		listMonHoc.getTenMH(),
	        });
	    }
	    dsMon.setModel(model);
	    editable(false);
	}
	
	private void loadSelectedRowData() {
	    if (selectedIndex != -1) {
	        String maMH = (String) dsMon.getValueAt(selectedIndex, 1);

	        try {
	            MonHocDAO dao = new MonHocDAO();
	            MonHoc mh = dao.getLopByMaMH(maMH);
	    
	            if (mh != null) {
	                txtMamon.setText(mh.getMaMH());
	                txtTenmon.setText(mh.getTenMH());
	                txtMamon.setEditable(false);
	            } else {
	                JOptionPane.showMessageDialog(this, "Không tìm thấy môn học.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            }

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	        editable(false);
	    }
	}
	
	private void clearInputFields() {
		txtMamon.setText("");
		txtTenmon.setText("");
        selectedIndex = -1;
        txtMamon.setEditable(true);
    }
	
	private void editable(Boolean check) {
        txtMamon.setEditable(check);
        txtTenmon.setEditable(check);
	}
	
    private void performSaveState() {
        undoManager.saveState(model);
    }
}
