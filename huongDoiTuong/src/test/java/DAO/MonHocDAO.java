package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.MonHoc;

public class MonHocDAO {

    public static List<MonHoc> getAllMonHoc() throws Exception {
        List<MonHoc> listMonHoc = new ArrayList<>();
        String sql = "SELECT * FROM MonHoc";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
            	MonHoc monhoc = new MonHoc(sql, sql);
            	monhoc.setMaMH(rs.getString("maMH"));
            	monhoc.setTenMH(rs.getString("tenMH"));
            	listMonHoc.add(monhoc);
            }
        } 
        return listMonHoc;
    }

    public MonHoc getLopByMaMH(String maMH) throws Exception {
        MonHoc mh = null;
        String sql = "SELECT * FROM MonHoc WHERE maMH = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maMH);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    mh = new MonHoc(sql, sql);
                    mh.setMaMH(rs.getString("maMH"));
                    mh.setTenMH(rs.getString("tenMH"));
                }
            }
        }
        return mh;
    }
    
    public boolean insertMonHoc(MonHoc mh) throws Exception {
        String sql = "INSERT INTO MonHoc (maMH, tenMH) VALUES (?, ?)";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, mh.getMaMH());
            pstmt.setString(2, mh.getTenMH());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteMonHoc(String maMH) throws Exception {
        String sql = "DELETE FROM MonHoc WHERE maMH = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maMH);
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateMonHoc(MonHoc mh) throws Exception {
        String sql = "UPDATE MonHoc SET tenMH = ? WHERE maMH = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, mh.getTenMH());
            pstmt.setString(2, mh.getMaMH());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<MonHoc> searchMonHoc(String keyword) throws Exception {
        List<MonHoc> listMonHoc = new ArrayList<>();
        String sql = "SELECT * FROM MonHoc WHERE maMH LIKE ? OR tenMH LIKE ?";

        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            String queryParam = "%" + keyword + "%";
            pstmt.setString(1, queryParam);
            pstmt.setString(2, queryParam);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MonHoc mh = new MonHoc(
                        rs.getString("maMH"),
                        rs.getString("tenMH")
                    );
                    listMonHoc.add(mh);
                }
            }
        }
        return listMonHoc;
    }
    
    public static MonHoc getMonHocByMaMH(String maMH) throws Exception {
        String sql = "SELECT * FROM MonHoc WHERE maMH = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maMH);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MonHoc(
                        rs.getString("maMH"),
                        rs.getString("tenMH")
                    );
                }
            }
        }
        
        return null; 
    }
    
    public boolean checkExist(String maMH) throws Exception {
        String sql = "SELECT COUNT(*) FROM MonHoc WHERE maMH = ?";
        try {
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, maMH);
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
