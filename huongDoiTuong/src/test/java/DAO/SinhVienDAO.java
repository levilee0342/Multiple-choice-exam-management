package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.SinhVien;

public class SinhVienDAO {

	public List<SinhVien> getAllSinhVien() throws Exception {
	    List<SinhVien> listSinhVien = new ArrayList<>();
	    String sql = "SELECT * FROM SinhVien";
	    
	    try (
	        Connection con = DatabaseHelper.openConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql)
	    ) {
	        while (rs.next()) {
	            SinhVien sv = new SinhVien(
	                rs.getNString("ho"),
	                rs.getNString("ten"),
	                rs.getString("cMND"),
	                rs.getDate("ngaySinh"),
	                rs.getNString("gioiTinh"),
	                rs.getNString("queQuan"),
	                rs.getString("maSV"),
	                rs.getString("maLop"),
	                rs.getString("maTK"),
	                rs.getString("SDT"),
	                rs.getString("email")
	            );
	            listSinhVien.add(sv);
	        }
	    }
	    
	    return listSinhVien;
	}

    public boolean insertSinhVien(SinhVien sv) throws Exception {
        String sql = "INSERT INTO SinhVien (maSV, ho, ten, CMND, ngaySinh, gioiTinh, queQuan ,SDT, email,maLop, maTK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
        	pstmt.setString(1, sv.getMaSV());
        	pstmt.setString(2, sv.getHo());
        	pstmt.setString(3, sv.getTen());
        	pstmt.setString(4, sv.getCMND());
        	pstmt.setDate(5, sv.getNgaySinh());
        	pstmt.setString(6, sv.getGioiTinh());
        	pstmt.setString(7, sv.getQueQuan());
        	pstmt.setString(8, sv.getSDT());
        	pstmt.setString(9, sv.getEmail());
        	pstmt.setString(10, sv.getMaLop());
        	pstmt.setString(11, sv.getMaTK());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteSinhVien(String maSV) throws Exception {
        String sql = "DELETE FROM SinhVien WHERE maSV = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maSV);
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public static boolean updateSinhVien(SinhVien sv) throws Exception {
        String sql = "UPDATE SinhVien SET ho = ?, ten = ?, CMND = ?, ngaySinh = ?, gioiTinh = ?, queQuan = ?, SDT = ?, email = ?, maLop = ?, maTK = ? WHERE maSV = ?";        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
        	pstmt.setString(1, sv.getHo()); 
            pstmt.setString(2, sv.getTen());
            pstmt.setString(3, sv.getCMND());
            pstmt.setDate(4, sv.getNgaySinh()); 
            pstmt.setString(5, sv.getGioiTinh());
        	pstmt.setString(6, sv.getQueQuan());
        	pstmt.setString(7, sv.getSDT());
        	pstmt.setString(8, sv.getEmail());
        	pstmt.setString(9, sv.getMaLop());
        	pstmt.setString(10, sv.getMaTK());
        	pstmt.setString(11, sv.getMaSV());
            
            return pstmt.executeUpdate() > 0;               
        }
    }

    public List<SinhVien> searchSinhVien(String keyword, String maLop) throws Exception {
        List<SinhVien> listSinhVien = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien WHERE (maSV LIKE ? OR ho LIKE ? OR ten LIKE ? OR CMND LIKE ? OR gioiTinh LIKE ? OR queQuan LIKE ? "
        		+ "OR maTK LIKE ? OR ngaySinh LIKE ? OR SDT LIKE ? OR email LIKE ?) AND maLop = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            String queryParam = "%" + keyword + "%";
            pstmt.setString(1, queryParam);
            pstmt.setString(2, queryParam);
            pstmt.setString(3, queryParam);
            pstmt.setString(4, queryParam);
            pstmt.setString(5, queryParam);
            pstmt.setString(6, queryParam);
            pstmt.setString(7, queryParam);
            pstmt.setString(8, queryParam);
            pstmt.setString(9, queryParam);
            pstmt.setString(10, queryParam);
            pstmt.setString(11, maLop);
            
            try (ResultSet rs = pstmt.executeQuery()) {
            	while (rs.next()) {
    	            SinhVien sv = new SinhVien(
    	                rs.getNString("ho"),
    	                rs.getNString("ten"),
    	                rs.getString("cMND"),
    	                rs.getDate("ngaySinh"),
    	                rs.getNString("gioiTinh"),
    	                rs.getNString("queQuan"),
    	                rs.getString("maSV"),
    	                rs.getString("maLop"),
    	                rs.getString("maTK"),
    	                rs.getString("SDT"),
    	                rs.getString("email")
    	            );
                    listSinhVien.add(sv);
                }
            }
        }
        
        return listSinhVien;
    }
    
    public SinhVien getSinhVienByMaSV(String maSV) throws Exception {
        SinhVien sv = null;
        String sql = "SELECT * FROM SinhVien WHERE maSV = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maSV);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sv = new SinhVien(
                        rs.getNString("ho"),
                        rs.getNString("ten"),
                        rs.getString("cMND"),
                        rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"),
                        rs.getNString("queQuan"),
                        rs.getString("maSV"),
                        rs.getString("maLop"),
                        rs.getString("maTK"),
                        rs.getString("SDT"),
                        rs.getString("email")
                    );
                }
            }
        }
        return sv;
    }
    
    public List<String> getAllMaLop() throws Exception {
        List<String> maLopList = new ArrayList<>();
        
        String sql = "SELECT DISTINCT maLop FROM SinhVien"; 
        try (Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                maLopList.add(rs.getString("maLop"));
            }
        }
        return maLopList;
    }

    public List<SinhVien> getSinhVienByMaLop(String maLop) throws Exception {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien WHERE maLop = ?";
        try (Connection con = DatabaseHelper.openConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, maLop);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SinhVien sv = new SinhVien(
                        rs.getNString("ho"),
                        rs.getNString("ten"),
                        rs.getString("cMND"),
                        rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"),
                        rs.getNString("queQuan"),
                        rs.getString("maSV"),
                        rs.getString("maLop"),
                        rs.getString("maTK"),
                        rs.getString("SDT"),
                        rs.getString("email")
                    );
                    list.add(sv);
                }
            }
        }
        return list;
    }
    
    public boolean checkExist(String maSV) throws Exception {
        String sql = "SELECT COUNT(*) FROM SinhVien WHERE maSV = ?";
        try {
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, maSV);
            ResultSet rs = pstmt.executeQuery(); 

            if (rs.next()) { 
                int count = rs.getInt(1);
                return count > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

}

