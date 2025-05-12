package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.CauHoiThi;

public class CauHoiThiDAO {

    public static List<CauHoiThi> getAllCauHoiThi() throws Exception {
        List<CauHoiThi> listCauHoiThi = new ArrayList<>();
        String sql = "SELECT * FROM CauHoiThi";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                CauHoiThi cht = new CauHoiThi(
                    rs.getString("maMH"),
                    rs.getString("id"),
                    rs.getString("noiDung"),
                    rs.getString("a"),
                    rs.getString("b"),
                    rs.getString("c"),
                    rs.getString("d"),
                    rs.getString("dapAN")
                );
                listCauHoiThi.add(cht);
            }
        }
        
        return listCauHoiThi;
    }

    public static List<CauHoiThi> getCauHoiThiByMaMH(String maMH) throws Exception {
        List<CauHoiThi> listCauHoiThi = new ArrayList<>();
        String sql = "SELECT * FROM CauHoiThi WHERE maMH = ?";

        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maMH);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CauHoiThi cht = new CauHoiThi(
                        rs.getString("maMH"),
                        rs.getString("id"),
                        rs.getString("noiDung"),
                        rs.getString("a"),
                        rs.getString("b"),
                        rs.getString("c"),
                        rs.getString("d"),
                        rs.getString("dapAN")
                    );
                    listCauHoiThi.add(cht);
                }
            }
        }

        return listCauHoiThi;
    }
    
    public static boolean insertCauHoiThi(CauHoiThi cht) throws Exception {
        String sql = "INSERT INTO CauHoiThi (maMH, id, noiDung, a, b, c, d, dapAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, cht.getMaMH());
            pstmt.setString(2, cht.getId());
            pstmt.setString(3, cht.getNoiDung());
            pstmt.setString(4, cht.getA());
            pstmt.setString(5, cht.getB());
            pstmt.setString(6, cht.getC());
            pstmt.setString(7, cht.getD());
            pstmt.setString(8, cht.getDapAn());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public static boolean deleteCauHoiThi(String id) throws Exception {
        String sql = "DELETE FROM CauHoiThi WHERE id = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, id);
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public static boolean updateCauHoiThi(CauHoiThi cht) throws Exception {
        String sql = "UPDATE CauHoiThi SET noiDung = ?, a = ?, b = ?, c = ?, d = ?, dapAN = ?, maMH = ? WHERE id = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, cht.getNoiDung());
            pstmt.setString(2, cht.getA());
            pstmt.setString(3, cht.getB());
            pstmt.setString(4, cht.getC());
            pstmt.setString(5, cht.getD());
            pstmt.setString(6, cht.getDapAn());
            pstmt.setString(7, cht.getMaMH());
            pstmt.setString(8, cht.getId());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<CauHoiThi> searchCauHoiThi(String keyword) throws Exception {
        List<CauHoiThi> listCauHoiThi = new ArrayList<>();
        String sql = "SELECT * FROM CauHoiThi WHERE maMH LIKE ? OR noiDung LIKE ? OR a LIKE ? OR b LIKE ? OR c LIKE ? OR d LIKE ? OR dapAN LIKE ? OR id LIKE ?";
        
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
                    CauHoiThi cht = new CauHoiThi(
                        rs.getString("maMH"),
                        rs.getString("id"),
                        rs.getString("noiDung"),
                        rs.getString("a"),
                        rs.getString("b"),
                        rs.getString("c"),
                        rs.getString("d"),
                        rs.getString("dapAN")
                    );
                    listCauHoiThi.add(cht);
                }
            }
        }
        
        return listCauHoiThi;
    }
    
    public static List<CauHoiThi> getRandomCauHoiThi(int soLuong, String maMH) throws Exception {
        List<CauHoiThi> listCauHoiThi = new ArrayList<>();
        String sql = "SELECT TOP (?) * FROM CauHoiThi WHERE maMH = ? ORDER BY NEWID()";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setInt(1, soLuong);
            pstmt.setString(2, maMH);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CauHoiThi cht = new CauHoiThi(
                        rs.getString("maMH"),
                        rs.getString("id"),
                        rs.getString("noiDung"),
                        rs.getString("a"),
                        rs.getString("b"),
                        rs.getString("c"),
                        rs.getString("d"),
                        rs.getString("dapAN")
                    );
                    listCauHoiThi.add(cht);
                }
            }
        }
        
        return listCauHoiThi;
    }
    
    public static CauHoiThi getCauHoiThiById(String id) throws Exception {
        String sql = "SELECT * FROM CauHoiThi WHERE id = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CauHoiThi(
                        rs.getString("maMH"),
                        rs.getString("id"),
                        rs.getString("noiDung"),
                        rs.getString("a"),
                        rs.getString("b"),
                        rs.getString("c"),
                        rs.getString("d"),
                        rs.getString("dapAN")
                    );
                }
            }
        }
        
        return null;
    }
    
    public static boolean checkExist(String id) throws Exception {
        String sql = "SELECT COUNT(*) FROM CauHoiThi WHERE id = ?";
        try {
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, id);
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
