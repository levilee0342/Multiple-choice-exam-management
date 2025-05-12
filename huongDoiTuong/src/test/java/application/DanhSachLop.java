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
import javax.swing.table.DefaultTableModel;

import DAO.LopDAO;
import DAO.UndoManager;
import model.Lop;
import javax.swing.JToolBar;

public class DanhSachLop extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenlop;
	private JTextField txtNienkhoa;
	private JTextField txtTim;
	private JTable dsLop;
	private JTextField txtMalop;
	private DefaultTableModel model;
    private int selectedIndex = -1;
    private UndoManager undoManager = new UndoManager();
    
	public DanhSachLop() {
		setLayout(null);
	
		JLabel lblNewLabel = new JLabel(" Thông tin lớp");
		lblNewLabel.setBounds(501, 87, 239, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon(DanhSachLop.class.getResource("/images/presentation.png")));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã lớp:");
		lblNewLabel_1.setBounds(421, 146, 53, 31);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1);
		
		txtTenlop = new JTextField();
		txtTenlop.setBounds(588, 189, 316, 31);
		txtTenlop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenlop.setColumns(10);
		add(txtTenlop);
		
		txtNienkhoa = new JTextField();
		txtNienkhoa.setBounds(588, 236, 316, 31);
		txtNienkhoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNienkhoa.setColumns(10);
		add(txtNienkhoa);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên lớp:");
		lblNewLabel_1_1.setBounds(421, 187, 53, 35);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Niên khóa:");
		lblNewLabel_1_2.setBounds(423, 243, 68, 17);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tìm kiếm:");
		lblNewLabel_1_2_1.setBounds(31, 337, 91, 24);
		lblNewLabel_1_2_1.setIcon(new ImageIcon(DanhSachLop.class.getResource("/images/tim.png")));
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_2_1);
		
		txtTim = new JTextField();
		txtTim.setBounds(130, 334, 239, 32);
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTim.setColumns(10);
		add(txtTim);			
		txtMalop = new JTextField();
		txtMalop.setBounds(588, 146, 316, 31);
		txtMalop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMalop.setColumns(10);
		add(txtMalop);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 0, 1492, 52);
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
			new Object[] {"STT", "Mã lớp", "Tên lớp", "Niên khóa"
			},0
		);
		dsLop = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(dsLop);
		scrollPane.setViewportView(dsLop);
		scrollPane.setBounds(10, 371, 1492, 289);
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
		            txtMalop.setEditable(false);
		            btnThem.setEnabled(false);
		            btnSua.setEnabled(false);
		            btnLuu.setEnabled(true);
		            btnXoa.setEnabled(false);
		            btnReload.setEnabled(false);
		        } else {
		            JOptionPane.showMessageDialog(DanhSachLop.this, "Vui lòng chọn lớp để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
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
                
		        String maLop = txtMalop.getText();
		        String tenLop = txtTenlop.getText();
		        String nienKhoa = txtNienkhoa.getText();
		        
		        if (maLop.isEmpty() || tenLop.isEmpty() || nienKhoa.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		        	LopDAO dao = new LopDAO();
		            Lop lop = new Lop(maLop, tenLop, nienKhoa);
		            	            
		            boolean success;
		            if (selectedIndex == -1) {
				    	performSaveState();
				    	if (dao.checkExist(maLop)) {
			                JOptionPane.showMessageDialog(null, "Mã lớp đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			                return;
			            }
		                // Thêm 
		                success = dao.addLop(lop);
		                if (success) {
		                    JOptionPane.showMessageDialog(DanhSachLop.this, "Thêm thành công");
		                } else {
		                    JOptionPane.showMessageDialog(DanhSachLop.this, "Thêm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
				    	performSaveState();
		                // Cập nhật 
		                success = dao.updateLop(lop);
		                if (success) {
		                    JOptionPane.showMessageDialog(DanhSachLop.this, "Cập nhật thành công");
		                } else {
		                    JOptionPane.showMessageDialog(DanhSachLop.this, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            initTable();
		            clearInputFields();
		            editable(false);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(DanhSachLop.this, "Lỗi khi lưu dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		 btnXoa.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = dsLop.getSelectedRow();
	                if (selectedRow == -1) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lớp cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                performSaveState(); 
	                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa lớp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	                try {
	                    if (confirm == JOptionPane.YES_OPTION) {
	                        String malop = (String) model.getValueAt(selectedRow, 1);
	                        LopDAO dao = new LopDAO();
	                        dao.deleteLop(malop);
	                        model.removeRow(selectedRow);
	                        JOptionPane.showMessageDialog(null, "Xóa lớp thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
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

		dsLop.addMouseListener((MouseListener) new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedIndex = dsLop.getSelectedRow();
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
		editable(false);
		btnLuu.setEnabled(false);
	}
	
	private void initTable() {
		List<Lop> list = new ArrayList<>();
		String keyword = txtTim.getText();
		try {
			LopDAO  stfDAO = new LopDAO();
			if (keyword != null && !keyword.trim().isEmpty()) {
				list = stfDAO.searchLop(keyword);
			} else {
				list = stfDAO.getAll();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		model.setRowCount(0);
		int stt = 1;
	    for (Lop dsLop : list) {
	        model.addRow(new Object[]{
	        		stt++,
	        		dsLop.getMaLop(),
	        		dsLop.getTenLop(),
	        		dsLop.getNienKhoa()
	        });
	    }
	    dsLop.setModel(model);
	}
	
	private void loadSelectedRowData() {
	    if (selectedIndex != -1) {
	        String maLop = (String) dsLop.getValueAt(selectedIndex, 1);

	        try {
	            LopDAO dao = new LopDAO();
	            Lop lop = dao.getLopByMaLop(maLop);
        
	            txtMalop.setText(lop.getMaLop());
	            txtTenlop.setText(lop.getTenLop());
	            txtNienkhoa.setText(lop.getNienKhoa());
	            txtMalop.setEditable(false); 

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	        editable(false);
	    }
	}
	
	private void clearInputFields() {
		txtMalop.setText("");
		txtTenlop.setText("");
		txtNienkhoa.setText("");
        selectedIndex = -1;
        txtMalop.setEditable(true);
    }
	
	private void editable(Boolean check) {
        txtMalop.setEditable(check);
        txtTenlop.setEditable(check);
        txtNienkhoa.setEditable(check);
	}
	
    private void performSaveState() {
        undoManager.saveState(model);
    }
}
