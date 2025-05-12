package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import DAO.DSDiemDAO;
import DAO.LopDAO;
import DAO.MonHocDAO;
import model.Lop;
import model.MonHoc;
import model.SinhVien;
import java.awt.Color;

public class DanhSachDiem extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTim;
	private JTable tblDS;
	private DefaultTableModel model;
	private JComboBox<String> cbMalop;
	private JComboBox<String> cbMaMH;
    private DefaultComboBoxModel<String> cbModellop = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> cbModelmon = new DefaultComboBoxModel<>();

	public DanhSachDiem() {
		
		setLayout(null);
		
		JLabel lblimThiTheo = new JLabel(" Điểm thi theo môn");
		lblimThiTheo.setBounds(538, 63, 389, 44);
		lblimThiTheo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblimThiTheo.setIcon(new ImageIcon(DanhSachDiem.class.getResource("/images/score.png")));
		add(lblimThiTheo);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Mã môn học:");
		lblNewLabel_1_3_1_1_1.setBounds(421, 143, 107, 31);
		lblNewLabel_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_3_1_1_1);
		
		cbMaMH = new JComboBox<>(cbModelmon);
		cbMaMH.setBounds(588, 143, 316, 31);
		cbMaMH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbMaMH);
		
		JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("Mã lớp:");
		lblNewLabel_1_3_1_1_1_1.setBounds(421, 203, 107, 35);
		lblNewLabel_1_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_3_1_1_1_1);
		
		cbMalop = new JComboBox<>(cbModellop);
		cbMalop.setBounds(588, 205, 316, 31);
		cbMalop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbMalop);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(192, 192, 192));
		btnTim.setBounds(768, 269, 107, 44);
		btnTim.setIcon(new ImageIcon(DanhSachDiem.class.getResource("/images/tim.png")));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnTim);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Tìm kiếm sinh viên:");
		lblNewLabel_1_2_1_1.setBounds(31, 367, 153, 24);
		lblNewLabel_1_2_1_1.setIcon(new ImageIcon(DanhSachDiem.class.getResource("/images/tim.png")));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNewLabel_1_2_1_1);
		
		txtTim = new JTextField();
		txtTim.setBounds(201, 364, 247, 32);
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTim.setColumns(10);
		add(txtTim);

		model = new DefaultTableModel(
			new Object[] {
					"STT", "MSSV", "Họ", "Tên", "CMND", "Điểm thi"
			},0
		);
		tblDS = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(tblDS);		
		scrollPane.setViewportView(tblDS);
		scrollPane.setBounds(10, 401, 1498, 260);
		add(scrollPane);
		
		btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initTable();
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
	}
	
	private void initcbMalop() {
		LopDAO  stfDAO = new LopDAO();
		MonHocDAO  stfDAOMH = new MonHocDAO();
        try {
        	List<Lop> maLopList = stfDAO.getAll();
            cbModellop.removeAllElements();
            for (Lop l : maLopList) {
                cbModellop.addElement(l.getMaLop());
            }
            

            List<MonHoc> maMonList = stfDAOMH.getAllMonHoc();
            cbModelmon.removeAllElements();
            for (MonHoc mh : maMonList) {
                cbModelmon.addElement(mh.getMaMH());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải mã lớp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

	private void initTable() {
	    List<SinhVien> list = new ArrayList<>();
	    String keyword = txtTim.getText(); 
	    String selectedMaLop = (String) cbMalop.getSelectedItem();
	    String selectedMaMon = (String) cbMaMH.getSelectedItem(); 

	    try {
	        DSDiemDAO stfDAO = new DSDiemDAO();

	        if (keyword != null && !keyword.trim().isEmpty()) {
	            list = stfDAO.searchSinhVienByMaLopAndMaMon(keyword, selectedMaLop, selectedMaMon);
	        } else {
	            list = stfDAO.getSinhVienByMaLopAndMaMon(selectedMaLop, selectedMaMon);
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
	            sv.getDiem()
	        });
	    }
    }
	
	
}
