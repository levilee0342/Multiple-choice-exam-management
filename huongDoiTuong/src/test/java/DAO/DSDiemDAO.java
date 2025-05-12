package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.SinhVien;

public class DSDiemDAO {
	
	public List<String> getAllMaLop() throws Exception {
	    List<String> maLopList = new ArrayList<>();

	    String sqlMaLop = "SELECT DISTINCT maLop FROM SinhVien";

	    try (Connection con = DatabaseHelper.openConnection();
	         PreparedStatement pstmtMaLop = con.prepareStatement(sqlMaLop)) {

	        try (ResultSet rsMaLop = pstmtMaLop.executeQuery()) {
	            while (rsMaLop.next()) {
	                maLopList.add(rsMaLop.getString("maLop"));
	            }
	        }
	    }
	    return maLopList;
	}
	

		public List<String> getAllMaMon() throws Exception {
		    List<String> maMonList = new ArrayList<>();
	
		    String sqlMaMon = "SELECT DISTINCT maMH FROM BaiThi"; 
		    
		    try (Connection con = DatabaseHelper.openConnection();
		         PreparedStatement pstmtMaMon = con.prepareStatement(sqlMaMon)) {	
	
		        try (ResultSet rsMaMon = pstmtMaMon.executeQuery()) {
		            while (rsMaMon.next()) {
		                maMonList.add(rsMaMon.getString("maMH"));
		            }
		        }
		    }
	
		    return maMonList;
		}
		
		public List<SinhVien> getSinhVienByMaLopAndMaMon(String maLop, String maMH) throws Exception {
		    List<SinhVien> list = new ArrayList<>();
		    String sql = "SELECT sv.*, bt.diem FROM SinhVien sv " +
		                 "JOIN BaiThi bt ON sv.maSV = bt.maSV " +
		                 "WHERE sv.maLop = ? AND bt.maMH = ?";
		    try (Connection con = DatabaseHelper.openConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setString(1, maLop);
		        pstmt.setString(2, maMH);
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
		                sv.setDiem(rs.getFloat("diem"));
		                list.add(sv);
		            }
		        }
		    }
		    return list;
		}
		
		public List<SinhVien> searchSinhVienByMaLopAndMaMon(String keyword, String maLop, String maMH) throws Exception {
		    List<SinhVien> listSinhVien = new ArrayList<>();
		    String sql = "SELECT sv.*, bt.diem FROM SinhVien sv " +
		                 "JOIN BaiThi bt ON sv.maSV = bt.maSV " +
		                 "WHERE (sv.maSV LIKE ? OR sv.ho LIKE ? OR sv.ten LIKE ? OR sv.CMND LIKE ? OR sv.maTK LIKE ? " +
		                 "OR sv.ngaySinh LIKE ? OR sv.SDT LIKE ? OR sv.email LIKE ?) " +
		                 "AND sv.maLop = ? AND bt.maMH = ?";
		    
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
		        pstmt.setString(9, maLop);
		        pstmt.setString(10, maMH);
		        
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
		                sv.setDiem(rs.getFloat("diem"));
		                listSinhVien.add(sv);
		            }
		        }
		    }
		    
		    return listSinhVien;
		}
		
		public List<SinhVien> getDiemSinhVien(String maLop, String maMH, String maSV) throws Exception {
		    List<SinhVien> list = new ArrayList<>();
		    String sql = "SELECT sv.*, bt.diem FROM SinhVien sv " +
		                 "JOIN BaiThi bt ON sv.maSV = bt.maSV " +
		                 "WHERE sv.maLop = ? AND bt.maMH = ? AND sv.maSV = ?";
		    try (Connection con = DatabaseHelper.openConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setString(1, maLop);
		        pstmt.setString(2, maMH);
		        pstmt.setString(3, maSV);
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
		                sv.setDiem(rs.getFloat("diem"));
		                list.add(sv);
		            }
		        }
		    }
		    return list;
		}
}
