package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.GiaoVien;

public class GiaoVienDAO {
	public boolean addGiaoVien(GiaoVien gv) throws Exception {
	    if (isMaGVExist(gv.getMaGV())) {
	        return false; 
	    }
	    
	    String sql = "INSERT INTO GiaoVien (maGV, hocVi, maTK, ho, ten, cMND, ngaySinh, gioiTinh, queQuan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (
	        Connection con = DatabaseHelper.openConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)
	    ) {
	        pstmt.setString(1, gv.getMaGV());
	        pstmt.setString(2, gv.getHocVi());
	        pstmt.setString(3, gv.getMaTK());
	        pstmt.setNString(4, gv.getHo());
	        pstmt.setNString(5, gv.getTen());
	        pstmt.setString(6, gv.getCMND());
	        pstmt.setObject(7, gv.getNgaySinh());
	        pstmt.setNString(8, gv.getGioiTinh());
	        pstmt.setNString(9, gv.getQueQuan());
	        
	        return pstmt.executeUpdate() > 0;
	    }
	}

	private boolean isMaGVExist(String maGV) throws Exception {
	    String sql = "SELECT * FROM GiaoVien WHERE maGV = ?";
	    
	    try (
	        Connection con = DatabaseHelper.openConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)
	    ) {
	        pstmt.setString(1, maGV);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next(); 
	        }
	    }
	}

	public boolean deleteGiaoVien(String maGV) throws Exception {
	    if (isMaTKUsedInTaiKhoan(maGV)) {
	        return false; 
	    }
	    
	    String sql = "DELETE FROM GiaoVien WHERE maGV = ?";
	    
	    try (
	        Connection con = DatabaseHelper.openConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)
	    ) {
	        pstmt.setString(1, maGV);
	        
	        return pstmt.executeUpdate() > 0;
	    }
	}

	private boolean isMaTKUsedInTaiKhoan(String maGV) throws Exception {
	    String sql = "SELECT 1 FROM TaiKhoan WHERE maTK IN (SELECT maTK FROM GiaoVien WHERE maGV = ?)";
	    
	    try (
	        Connection con = DatabaseHelper.openConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)
	    ) {
	        pstmt.setString(1, maGV);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            return rs.next(); 
	        }
	    }
	}

	public static boolean updateGiaoVien(GiaoVien gv) throws Exception {
	    String sql = "UPDATE GiaoVien SET hocVi = ?, maTK = ?, ho = ?, ten = ?, cMND = ?, ngaySinh = ?, gioiTinh = ?, queQuan = ? WHERE maGV = ?";

	    try (
	        Connection con = DatabaseHelper.openConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql)
	    ) {
	        pstmt.setString(1, gv.getHocVi());
	        pstmt.setString(2, gv.getMaTK());
	        pstmt.setNString(3, gv.getHo());
	        pstmt.setNString(4, gv.getTen());
	        pstmt.setString(5, gv.getCMND());
	        pstmt.setObject(6, gv.getNgaySinh());
	        pstmt.setNString(7, gv.getGioiTinh());
	        pstmt.setNString(8, gv.getQueQuan());
	        pstmt.setString(9, gv.getMaGV());

	        return pstmt.executeUpdate() > 0;
	    }
	}

    public List<GiaoVien> getAllGiaoVien() throws Exception {
        List<GiaoVien> listGiaoVien = new ArrayList<>();
        String sql = "SELECT * FROM GiaoVien";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {

                GiaoVien gv = new GiaoVien(
                    rs.getNString("ho"),
                    rs.getNString("ten"),
                    rs.getString("cMND"),
                    rs.getDate("ngaySinh"),
                    rs.getNString("gioiTinh"),
                    rs.getNString("queQuan"),
                    rs.getString("maGV"),
                    rs.getString("hocVi"),
                    rs.getString("maTK")
                );
                listGiaoVien.add(gv);
            }
        }
        
        return listGiaoVien;
    }

    public List<GiaoVien> searchGiaoVien(String keyword) throws Exception {
        List<GiaoVien> listGiaoVien = new ArrayList<>();
        String sql = "SELECT * FROM GiaoVien WHERE maGV LIKE ? OR hocVi LIKE ? OR maTK LIKE ? OR ho LIKE ? OR ten LIKE ? OR cMND LIKE ? OR gioiTinh LIKE ? OR queQuan LIKE ?";

        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            String queryParam = "%" + keyword + "%";
            for (int i = 1; i <= 8; i++) {
                pstmt.setString(i, queryParam);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    GiaoVien gv = new GiaoVien(
                        rs.getNString("ho"),
                        rs.getNString("ten"),
                        rs.getString("cMND"),
                        rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"),
                        rs.getNString("queQuan"),
                        rs.getString("maGV"),
                        rs.getString("hocVi"),
                        rs.getString("maTK")
                    );
                    listGiaoVien.add(gv);
                }
            }
        }

        return listGiaoVien;
    }
    
    public static GiaoVien getGiaoVienByMaGV(String maGV) throws Exception {
        String sql = "SELECT * FROM GiaoVien WHERE maGV = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maGV);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {                   
                    return new GiaoVien(
                        rs.getNString("ho"),
                        rs.getNString("ten"),
                        rs.getString("cMND"),
                        rs.getDate("ngaySinh"),
                        rs.getNString("gioiTinh"),
                        rs.getNString("queQuan"),
                        rs.getString("maGV"),
                        rs.getString("hocVi"),
                        rs.getString("maTK")
                    );
                }
            }
        }
        
        return null; 
    }
    
}
