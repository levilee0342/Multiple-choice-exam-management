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

public class DiemSinhVien extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblDS;
	private DefaultTableModel model;
	private JComboBox<String> cbMaMH;
    private DefaultComboBoxModel<String> cbModellop = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> cbModelmon = new DefaultComboBoxModel<>();

	public DiemSinhVien() {
		
		setLayout(null);
		
		JLabel lblimThiTheo = new JLabel(" Điểm thi theo môn");
		lblimThiTheo.setBounds(538, 63, 389, 44);
		lblimThiTheo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblimThiTheo.setIcon(new ImageIcon(DiemSinhVien.class.getResource("/images/score.png")));
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
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(192, 192, 192));
		btnTim.setBounds(768, 269, 107, 44);
		btnTim.setIcon(new ImageIcon(DiemSinhVien.class.getResource("/images/tim.png")));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnTim);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 401, 1480, 259);
		add(scrollPane);
		
		model = new DefaultTableModel(
			new Object[] {
					"STT", "MSSV", "Họ", "Tên", "CMND", "Điểm thi"
			},0
		);
		tblDS = new JTable(model);
		scrollPane.setViewportView(tblDS);
		
		JLabel lblMaLop = new JLabel(DangNhap.sinhVien.getMaLop());
		lblMaLop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaLop.setBounds(588, 205, 316, 31);
		add(lblMaLop);
		
		btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	model.setRowCount(0);
                initTable();
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
	    String selectedMaMon = (String) cbMaMH.getSelectedItem(); 

	    try {
	        DSDiemDAO stfDAO = new DSDiemDAO();
	            list = stfDAO.getDiemSinhVien(DangNhap.sinhVien.getMaLop(), selectedMaMon,DangNhap.sinhVien.getMaSV());

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
