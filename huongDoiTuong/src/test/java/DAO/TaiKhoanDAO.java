package DAO;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.GiaoVien;
import model.SinhVien;
import model.TaiKhoan;

public class TaiKhoanDAO {
    public List<TaiKhoan> getAllTaiKhoan() throws Exception {
        List<TaiKhoan> listTaiKhoan = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                    rs.getString("maTK"),
                    rs.getString("password"),
                    rs.getString("vaiTro")
                );
                listTaiKhoan.add(tk);
            }
        }
        
        return listTaiKhoan;
    }

    public static boolean insertTaiKhoan(TaiKhoan tk) throws Exception { 
        String sql = "INSERT INTO TaiKhoan (maTK, password, vaiTro) VALUES (?, ?, ?)";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, tk.getMaTK());
            pstmt.setString(2, tk.getPassword());
            pstmt.setString(3, tk.getVaiTro());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteTaiKhoan(String maTK) throws Exception {      
        String sql = "DELETE FROM TaiKhoan WHERE maTK = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maTK);
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public static boolean updateTaiKhoan(TaiKhoan tk) throws Exception {
        String sql = "UPDATE TaiKhoan SET password = ?, vaiTro = ? WHERE maTK = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, tk.getPassword());
            pstmt.setString(2, tk.getVaiTro());
            pstmt.setString(3, tk.getMaTK());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<TaiKhoan> searchTaiKhoan(String keyword) throws Exception {
        List<TaiKhoan> listTaiKhoan = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan WHERE maTK LIKE ? OR vaiTro LIKE ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            String queryParam = "%" + keyword + "%";
            pstmt.setString(1, queryParam);
            pstmt.setString(2, queryParam);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TaiKhoan tk = new TaiKhoan(
                        rs.getString("maTK"),
                        rs.getString("password"),
                        rs.getString("vaiTro")
                    );
                    listTaiKhoan.add(tk);
                }
            }
        }
        
        return listTaiKhoan;
    }
    
    public static boolean checkLogin(String maTK, String password) throws Exception {
        String sql = "SELECT * FROM TaiKhoan WHERE maTK = ? AND password = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maTK);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); 
            }
        }
    }
    public static String getVaiTroByMaTK(String maTK) throws Exception {
        String sql = "SELECT vaiTro FROM TaiKhoan WHERE maTK = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maTK);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("vaiTro");
                }
            }
        }
        
        return null; 
    }
    public static SinhVien getSinhVienByMaTK(String maTK) throws Exception {
        String sql = "SELECT * FROM SinhVien WHERE maTK = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maTK);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return new SinhVien(
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
        
        return null; 
    }
    public static GiaoVien getGiaoVienByMaTK(String maTK) throws Exception {
        String sql = "SELECT * FROM GiaoVien WHERE maTK = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maTK);
            
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
    
    public static SinhVien getSinhVienChuaDangKiByMaTK(String maTK) throws Exception {
        String sql = "SELECT * FROM SinhVien WHERE maSV = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maTK);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return new SinhVien(
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
        
        return null; 
    }
    public static GiaoVien getGiaoVienChuaDangKiByMaTK(String maTK) throws Exception {
        String sql = "SELECT * FROM GiaoVien WHERE maGV = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maTK);
            
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
