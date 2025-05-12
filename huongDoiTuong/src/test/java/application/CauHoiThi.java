package application;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import DAO.CauHoiThiDAO;
import DAO.MonHocDAO;
import DAO.UndoManager;
import model.MonHoc;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import java.awt.SystemColor;

public class CauHoiThi extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTable tblCauhoi;
	List<model.MonHoc> listMonHoc;
	int check = 0;
	private DefaultTableModel model;
	private UndoManager undoManager = new UndoManager();
	
	public CauHoiThi() {

		
		JLabel lblThngTinCu = new JLabel("Thông tin câu hỏi");
		lblThngTinCu.setBounds(643, 10, 186, 25);
		lblThngTinCu.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Mã môn học:");
		lblNewLabel_1_3_1_1.setBounds(215, 41, 102, 27);
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox cbMamon = new JComboBox();
		cbMamon.setBounds(321, 43, 364, 23);
		cbMamon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		listMonHoc = new ArrayList<>();
        try {
            MonHocDAO mhDAO = new MonHocDAO();
            listMonHoc = mhDAO.getAllMonHoc();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        cbMamon.addItem("Tất cả");
        for(MonHoc mh : listMonHoc)
        {
        	cbMamon.addItem(mh.getMaMH() + " - "+mh.getTenMH());
        }
        cbMamon.addActionListener(e -> {
        	if(check != 1 && check != 2) {
        		String selectedValue = (String) cbMamon.getSelectedItem();
                if (selectedValue != null) {
                    String maMH = selectedValue.split(" - ")[0];
                    filterCauHoiByMaMH(maMH);
                }
        	}           
        });

		
		JLabel lblNewLabel_1_3_1_2 = new JLabel("Đáp án:");
		lblNewLabel_1_3_1_2.setBounds(845, 41, 67, 27);
		lblNewLabel_1_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 72, 1504, 423);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_3_1_1_1_1_1 = new JLabel("ID:");
		lblNewLabel_1_3_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbA = new JRadioButton("A.");
		rdbA.setBackground(new Color(255, 255, 255));
		rdbA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextPane txtA = new JTextPane();
		txtA.setBackground(SystemColor.inactiveCaptionBorder);
		txtA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbB = new JRadioButton("B.");
		rdbB.setBackground(new Color(255, 255, 255));
		rdbB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextPane txtB = new JTextPane();
		txtB.setBackground(SystemColor.inactiveCaptionBorder);
		txtB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbC = new JRadioButton("C.");
		rdbC.setBackground(new Color(255, 255, 255));
		rdbC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextPane txtC = new JTextPane();
		txtC.setBackground(SystemColor.inactiveCaptionBorder);
		txtC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbD = new JRadioButton("D.");
		rdbD.setBackground(new Color(255, 255, 255));
		rdbD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextPane txtD = new JTextPane();
		txtD.setBackground(SystemColor.inactiveCaptionBorder);
		txtD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		 ButtonGroup group = new ButtonGroup();
	        group.add(rdbA);
	        group.add(rdbB);
	        group.add(rdbC);
	        group.add(rdbD);
	        
		txtID = new JTextField();
		txtID.setBackground(new Color(255, 255, 255));
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("Câu hỏi:");
		lblNewLabel_1_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextArea txtCauhoi = new JTextArea();
		txtCauhoi.setBackground(SystemColor.inactiveCaptionBorder);
		txtCauhoi.setForeground(new Color(0, 0, 0));
		txtCauhoi.setEditable(false);
		txtCauhoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1_3_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(9)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(rdbA)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtA, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(rdbB)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtB, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)))
									.addGap(253)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(rdbC)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtC, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(rdbD)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtD, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtID, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblNewLabel_1_3_1_1_1_1)
						.addComponent(txtCauhoi, GroupLayout.PREFERRED_SIZE, 1315, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_3_1_1_1_1_1)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addComponent(lblNewLabel_1_3_1_1_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCauhoi, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbA)
						.addComponent(rdbC)
						.addComponent(txtA, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtC, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(txtD, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbD)
						.addComponent(txtB, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbB))
					.addGap(40))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblDapAn = new JLabel("");
		lblDapAn.setBounds(918, 41, 204, 22);
		lblDapAn.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		 rdbA.setActionCommand("A");
	        rdbB.setActionCommand("B");
	        rdbC.setActionCommand("C");
	        rdbD.setActionCommand("D");

	        rdbA.addActionListener(e -> lblDapAn.setText("A"));
	        rdbB.addActionListener(e -> lblDapAn.setText("B"));
	        rdbC.addActionListener(e -> lblDapAn.setText("C"));
	        rdbD.addActionListener(e -> lblDapAn.setText("D"));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 364, 35);
		
		model = new DefaultTableModel(
			new Object[] {"STT", "ID", "Mã môn học", "Câu hỏi"
			},0
		);
		tblCauhoi = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(tblCauhoi);
		scrollPane.setBounds(10, 501, 1504, 169);
		
		tblCauhoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblCauhoi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

		TableColumn indexColumn = tblCauhoi.getColumnModel().getColumn(0);
        TableColumn idColumn = tblCauhoi.getColumnModel().getColumn(1);
        TableColumn maMonColumn = tblCauhoi.getColumnModel().getColumn(2);
        TableColumn cauHoiColumn = tblCauhoi.getColumnModel().getColumn(3);

        indexColumn.setPreferredWidth(100); 
        idColumn.setPreferredWidth(100); 
        maMonColumn.setPreferredWidth(200); 
        cauHoiColumn.setPreferredWidth(1085); 

		scrollPane.setViewportView(tblCauhoi);
		
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
		
		
		btnThem.setEnabled(true);
        btnSua.setEnabled(true);
        btnLuu.setEnabled(false);
        btnXoa.setEnabled(true);
        tblCauhoi.setEnabled(true);
        txtID.setEditable(false);
        txtA.setEditable(false);
        txtB.setEditable(false);
        txtC.setEditable(false);
        txtD.setEditable(false);
        rdbA.setEnabled(false);
        rdbB.setEnabled(false);
        rdbC.setEnabled(false);
        rdbD.setEnabled(false);
        setLayout(null);
        add(lblThngTinCu);
        add(lblNewLabel_1_3_1_1);
        add(cbMamon);
        add(lblNewLabel_1_3_1_2);
        add(lblDapAn);
        add(panel_1);
        add(scrollPane);
        add(toolBar);
        
		btnThem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        check = 1;
		        btnThem.setEnabled(false);
		        btnSua.setEnabled(false);
		        btnLuu.setEnabled(true);
		        btnXoa.setEnabled(false);
		        btnReload.setEnabled(false);
		        
		        tblCauhoi.setEnabled(false);

		        txtID.setText("");
		        txtCauhoi.setText("");
		        txtA.setText("");
		        txtB.setText("");
		        txtC.setText("");
		        txtD.setText("");
		        lblDapAn.setText("");
		        cbMamon.setSelectedIndex(1);

		        txtID.setEditable(true);
		        txtCauhoi.setEditable(true);
		        txtA.setEditable(true);
		        txtB.setEditable(true);
		        txtC.setEditable(true);
		        txtD.setEditable(true);
		        rdbA.setEnabled(true);
		        rdbB.setEnabled(true);
		        rdbC.setEnabled(true);
		        rdbD.setEnabled(true);
		        cbMamon.setEnabled(true);
		    }
		});

		btnLuu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String id = txtID.getText().trim();
		            String noiDung = txtCauhoi.getText().trim();
		            String a = txtA.getText().trim();
		            String b = txtB.getText().trim();
		            String c = txtC.getText().trim();
		            String d = txtD.getText().trim();
		            String dapAn = lblDapAn.getText().trim();
		            String maMH = ((String) cbMamon.getSelectedItem()).split(" - ")[0];

		            if (check == 1) {
		                if (!id.isEmpty() && !noiDung.isEmpty() && !a.isEmpty() && !b.isEmpty() && !c.isEmpty() && !d.isEmpty() && !dapAn.isEmpty() && !maMH.isEmpty()) {
		                	boolean isExist = CauHoiThiDAO.checkExist(id);
		                    if (isExist) {
		                        JOptionPane.showMessageDialog(null, "ID đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                        return;
		                    }
		                	model.CauHoiThi ch = new model.CauHoiThi(maMH, id, noiDung, a, b, c, d, dapAn);
		                    System.out.println(ch.getMaMH());
		                    CauHoiThiDAO.insertCauHoiThi(ch);
		                    DefaultTableModel model = (DefaultTableModel) tblCauhoi.getModel();
		                    model.setRowCount(0);
		                    importDataToTable();

		                    check = -1;
		                    
		                    btnThem.setEnabled(true);
                            btnSua.setEnabled(true);
                            btnLuu.setEnabled(false);
                            btnXoa.setEnabled(true);
                            btnReload.setEnabled(true);
                            tblCauhoi.setEnabled(true);
                            txtID.setEditable(false);
            		        txtCauhoi.setEditable(false);
            		        txtA.setEditable(false);
            		        txtB.setEditable(false);
            		        txtC.setEditable(false);
            		        txtD.setEditable(false);
            		        rdbA.setEnabled(false);
            		        rdbB.setEnabled(false);
            		        rdbC.setEnabled(false);
            		        rdbD.setEnabled(false);
		                    
		                } else {
		                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
		                }
		            } else if (check == 2) {
		                if (!id.isEmpty() && !noiDung.isEmpty() && !a.isEmpty() && !b.isEmpty() && !c.isEmpty() && !d.isEmpty() && !dapAn.isEmpty() && !maMH.isEmpty()) {		                
		                    model.CauHoiThi ch = new model.CauHoiThi(maMH, id, noiDung, a, b, c, d, dapAn);
		                    System.out.println(ch.getMaMH());
		                    CauHoiThiDAO.updateCauHoiThi(ch);
		                    DefaultTableModel model = (DefaultTableModel) tblCauhoi.getModel();
		                    model.setRowCount(0);
		                    importDataToTable();

		                    check = -1;
		                    
		                    btnThem.setEnabled(true);
                            btnSua.setEnabled(true);
                            btnLuu.setEnabled(false);
                            btnXoa.setEnabled(true);
                            btnReload.setEnabled(true);
                            tblCauhoi.setEnabled(true);
                            txtID.setEditable(false);
            		        txtCauhoi.setEditable(false);
            		        txtA.setEditable(false);
            		        txtB.setEditable(false);
            		        txtC.setEditable(false);
            		        txtD.setEditable(false);
            		        rdbA.setEnabled(false);
            		        rdbB.setEnabled(false);
            		        rdbC.setEnabled(false);
            		        rdbD.setEnabled(false);
            		        
		                } else {
		                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
		                }
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnSua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tblCauhoi.getSelectedRow();
		        if (selectedRow != -1) {
		            check = 2;
		            btnThem.setEnabled(false);
		            btnSua.setEnabled(false);
		            btnLuu.setEnabled(true);
		            btnXoa.setEnabled(false);
		            btnReload.setEnabled(false);
		            tblCauhoi.setEnabled(false);

		            cbMamon.setSelectedItem(tblCauhoi.getValueAt(selectedRow, 2).toString());
		            txtID.setEditable(false);
		            txtCauhoi.setEditable(true);
		            txtA.setEditable(true);
		            txtB.setEditable(true);
		            txtC.setEditable(true);
		            txtD.setEditable(true);
		            rdbA.setEnabled(true);
		            rdbB.setEnabled(true);
		            rdbC.setEnabled(true);
		            rdbD.setEnabled(true);
		            cbMamon.setEnabled(true);
		        }
		    }
		});

		btnXoa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tblCauhoi.getSelectedRow();
		        if (selectedRow != -1) {
		        	performSaveState(); 
		            int confirm = JOptionPane.showConfirmDialog(
		                null,
		                "Bạn có chắc chắn muốn xóa câu hỏi đã chọn?",
		                "Xác nhận xóa",
		                JOptionPane.YES_NO_OPTION,
		                JOptionPane.QUESTION_MESSAGE
		            );

		            if (confirm == JOptionPane.YES_OPTION) {
		                try {
		                    String id = (String) tblCauhoi.getValueAt(selectedRow, 1);
		                    CauHoiThiDAO.deleteCauHoiThi(id);
		                    DefaultTableModel model = (DefaultTableModel) tblCauhoi.getModel();
		                    model.setRowCount(0);
		                    importDataToTable();
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(
		                null,
		                "Vui lòng chọn một câu hỏi để xóa.",
		                "Không có câu hỏi được chọn",
		                JOptionPane.WARNING_MESSAGE
		            );
		        }
		    }
		});

		btnReload.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	performSaveState();
		        DefaultTableModel model = (DefaultTableModel) tblCauhoi.getModel();
		        model.setRowCount(0);
		        importDataToTable();
		    }
		});

		
		tblCauhoi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent event) {
	            if (!event.getValueIsAdjusting()) {
	                int selectedRow = tblCauhoi.getSelectedRow();
	                if (selectedRow != -1) {
	                    String id = tblCauhoi.getValueAt(selectedRow, 1).toString();
	                    String maMH = tblCauhoi.getValueAt(selectedRow, 2).toString();
	                    String noiDung = tblCauhoi.getValueAt(selectedRow, 3).toString();
	                    model.CauHoiThi cauHoi = null;
						try {
							cauHoi = CauHoiThiDAO.getCauHoiThiById(id);
						} catch (Exception e) {
							e.printStackTrace();
						}
	                    
	                    txtID.setText(id);
	                    txtCauhoi.setText(noiDung);
	                    txtA.setText(cauHoi.getA());
	                    txtB.setText(cauHoi.getB());
	                    txtC.setText(cauHoi.getC());
	                    txtD.setText(cauHoi.getD());
	                    lblDapAn.setText(cauHoi.getDapAn());
	                    switch (cauHoi.getDapAn()) {
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
		                    default:
		                        group.clearSelection();
		                        break;
	                    }
	                }
	            }
	        }
	    });
		
		importDataToTable();
	}
	private void filterCauHoiByMaMH(String maMH) {
	    List<model.CauHoiThi> filteredCauHoi = new ArrayList<>();
	    try {	    	
	        CauHoiThiDAO chDAO = new CauHoiThiDAO();
	        if(maMH.equals("Tất cả")) {
	        	filteredCauHoi = chDAO.getAllCauHoiThi();
	        }
	        else filteredCauHoi = chDAO.getCauHoiThiByMaMH(maMH);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, e.getMessage());
	    }

	    DefaultTableModel model = (DefaultTableModel) tblCauhoi.getModel();
	    model.setRowCount(0); 
	    int index = 0;
	    for (model.CauHoiThi ch : filteredCauHoi) {
	        model.addRow(new Object[]{++index, ch.getId(), ch.getMaMH() + " - "+ch.getTenMH(), ch.getNoiDung()});
	    }
	}
	
	private void importDataToTable() {
		
		List<model.CauHoiThi> listCauHoi = new ArrayList<>();
        try {
            CauHoiThiDAO chDAO = new CauHoiThiDAO();
            listCauHoi = chDAO.getAllCauHoiThi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        DefaultTableModel model = (DefaultTableModel) tblCauhoi.getModel();
        int index = 0;
        for (model.CauHoiThi ch : listCauHoi) {
            model.addRow(new Object[]{++index, ch.getId(), ch.getMaMH()+" - "+ch.getTenMH(), ch.getNoiDung()});
        }
    }
	
    private void performSaveState() {
        undoManager.saveState(model);
    }
    
}
