package application;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JButton;

import DAO.BaiThiDAO;
import DAO.CauHoiThiDAO;
import DAO.MonHocDAO;
import DAO.NienKhoaDAO;
import helper.EditHelper;
import model.CauHoiThi;
import model.MonHoc;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
public class ChonBaiThi extends JPanel {

	private static final long serialVersionUID = 1L;
	private int soLuongCau = 0;
	private int thoiGian = 0;
	public static String maMonSelected = "";
	public static String maDotThi ="";
	private String kyThi="";
	private String hocKy="";
	private String nam = "";
	public static JTextField txtLop;
	public static JTextField txtMSSV;
	public static JTextField txtHoTen;
	public static String maBaiThi;

	public ChonBaiThi() {

		
		JLabel lblThiTrcNghim = new JLabel("THI TRẮC NGHIỆM");
		lblThiTrcNghim.setBounds(611, 59, 253, 42);
		lblThiTrcNghim.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Môn học:");
		lblNewLabel_1_3_1_1_1.setBounds(197, 141, 102, 27);
		lblNewLabel_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox cbMamon = new JComboBox();
		cbMamon.setBounds(317, 138, 249, 33);
		cbMamon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbMamon.addItem("Vui lòng chọn môn học");
		try {
			for(MonHoc mh : MonHocDAO.getAllMonHoc()) {
				cbMamon.addItem(mh.getTenMH());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JLabel lblNewLabel_1_3_1_1_1_1 = new JLabel("Kỳ thi:");
		lblNewLabel_1_3_1_1_1_1.setBounds(584, 141, 73, 27);
		lblNewLabel_1_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox cbKythi = new JComboBox();
		cbKythi.setBounds(661, 138, 249, 33);
		cbKythi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbKythi.addItem("Vui lòng chọn bài kiểm tra");
		cbKythi.addItem("Test");
		cbKythi.addItem("15 phút");
		cbKythi.addItem("Giữa kỳ - 45 phút");
		cbKythi.addItem("Cuối kỳ - 90 phút");
		cbKythi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedTime = (String) cbKythi.getSelectedItem();
				switch (selectedTime) {
					case "Test":
						thoiGian = 1;
						soLuongCau = 1;
						kyThi="test";
						break;
					case "15 phút":
						thoiGian = 15;
						soLuongCau = 15;
						kyThi ="15";
						break;
					case "Giữa kỳ - 45 phút":
						thoiGian = 45;
						soLuongCau = 45;
						kyThi="gk";
						break;
					case "Cuối kỳ - 90 phút":
						thoiGian = 90;
						soLuongCau = 90;
						kyThi="ck";
						break;
					case "Vui lòng chọn bài kiểm tra":
						thoiGian = 0;
						soLuongCau = 0;
						kyThi="";
						break;
				}
			}
		});
		
		
		
		JButton btnThi = new JButton("Bắt đầu thi");
		btnThi.setBounds(527, 478, 163, 41);
		btnThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thoiGian != 0 && soLuongCau != 0) {
					Thi frame = null;
					try {						
						List<CauHoiThi> listCauHoi = CauHoiThiDAO.getRandomCauHoiThi(soLuongCau, EditHelper.timMaMonHoc(cbMamon.getSelectedItem().toString(), MonHocDAO.getAllMonHoc()));
						frame = new Thi(listCauHoi, thoiGian);
						Thi.maMonSelected = EditHelper.timMaMonHoc((String) cbMamon.getSelectedItem(), MonHocDAO.getAllMonHoc());
						Thi.maDotThi = kyThi + "_"+hocKy+"_"+ nam;
						maBaiThi = BaiThiDAO.generateUniqueMaBaiThi(); 
						
						Home.panelSV.removeAll();
						Home.panelSV.setLayout(new BorderLayout()); 
						Home.panelSV.add(frame, BorderLayout.CENTER);
						Home.panelSV.revalidate();
						Home.panelSV.repaint();
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnThi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox cbHocKy = new JComboBox();
		cbHocKy.setBounds(928, 138, 196, 33);
		cbHocKy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbHocKy.addItem("Chọn học kỳ");
		cbHocKy.addItem("Học kỳ 1");
		cbHocKy.addItem("Học kỳ 2");
		cbHocKy.addItem("Học kỳ hè");
		cbHocKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedHK = (String) cbHocKy.getSelectedItem();
				switch (selectedHK) {
					case "Học kỳ 1":
						hocKy ="hk1";
						break;
					case "Học kỳ 2":
						hocKy ="hk2";
						break;
					case "Học kỳ hè":
						hocKy ="hkh";
						break;
					case "Chọn học kỳ":
						hocKy ="";
						break;
				}
			}
		});
		
		JComboBox cbNienKhoa = new JComboBox();
		cbNienKhoa.setBounds(1134, 138, 171, 33);
		cbNienKhoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbNienKhoa.addItem("Chọn niên khóa");
		try {
			for(String nk : NienKhoaDAO.getAllNienKhoa()) {
				cbNienKhoa.addItem(nk);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cbNienKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nam = (String) cbNienKhoa.getSelectedItem();
			}
		});
		
		JLabel lblNewLabel_1_3_1_1_1_2_2 = new JLabel("Lớp:");
		lblNewLabel_1_3_1_1_1_2_2.setBounds(470, 421, 102, 27);
		lblNewLabel_1_3_1_1_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_3_1_1_1_2_1 = new JLabel("MSSV:");
		lblNewLabel_1_3_1_1_1_2_1.setBounds(470, 367, 102, 27);
		lblNewLabel_1_3_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_3_1_1_1_2 = new JLabel("Họ và tên:");
		lblNewLabel_1_3_1_1_1_2.setBounds(470, 311, 102, 27);
		lblNewLabel_1_3_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtHoTen = new JTextField(DangNhap.sinhVien.getHo() + " " +DangNhap.sinhVien.getTen());
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHoTen.setColumns(10);
		
		txtMSSV = new JTextField(DangNhap.sinhVien.getMaSV());
		txtMSSV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMSSV.setColumns(10);
		
		txtLop = new JTextField(DangNhap.sinhVien.getMaLop());
		txtLop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLop.setColumns(10);
		txtHoTen.setBounds(700, 308, 249, 33);
		txtMSSV.setBounds(700, 364, 249, 33);
		txtLop.setBounds(700, 418, 249, 33);
		
		JLabel lblThngTinSinh = new JLabel("Thông tin sinh viên");
		lblThngTinSinh.setBounds(601, 243, 238, 31);
		lblThngTinSinh.setFont(new Font("Tahoma", Font.BOLD, 25));
		setLayout(null);
		add(lblNewLabel_1_3_1_1_1_2_2);
		add(lblNewLabel_1_3_1_1_1_2_1);
		add(lblNewLabel_1_3_1_1_1_2);
		add(txtLop);
		add(txtMSSV);
		add(txtHoTen);
		add(lblThngTinSinh);
		add(btnThi);
		add(lblNewLabel_1_3_1_1_1);
		add(cbMamon);
		add(lblNewLabel_1_3_1_1_1_1);
		add(cbKythi);
		add(cbHocKy);
		add(cbNienKhoa);
		add(lblThiTrcNghim);
		
	}
}
