package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.Lop;

public class LopDAO {
    
    public List<Lop> getAll() throws Exception {
        List<Lop> dsLop = new ArrayList<>();
        String sql = "SELECT * FROM Lop";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Lop lop = new Lop();
                lop.setMaLop(rs.getString("maLop"));
                lop.setTenLop(rs.getString("tenLop"));
                lop.setNienKhoa(rs.getString("nienKhoa"));
                dsLop.add(lop);
            }
        }
        
        return dsLop;
    }
      
    public boolean addLop(Lop lop) throws Exception {      
        String sql = "INSERT INTO Lop (maLop, tenLop, nienKhoa) VALUES (?, ?, ?)";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, lop.getMaLop());
            pstmt.setString(2, lop.getTenLop());
            pstmt.setString(3, lop.getNienKhoa());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteLop(String maLop) throws Exception {
        
        String sql = "DELETE FROM Lop WHERE maLop = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maLop);
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean updateLop(Lop lop) throws Exception {
        String sql = "UPDATE Lop SET tenLop = ?, nienKhoa = ? WHERE maLop = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, lop.getTenLop());
            pstmt.setString(2, lop.getNienKhoa());
            pstmt.setString(3, lop.getMaLop());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public Lop getLopByMaLop(String maLop) throws Exception{
    	Lop lop = null;
        String sql = "SELECT * FROM Lop WHERE maLop = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maLop);
            try (ResultSet rs = pstmt.executeQuery()) {
            	while (rs.next()) {
                    lop = new Lop();
                    lop.setMaLop(rs.getString("maLop"));
                    lop.setTenLop(rs.getString("tenLop"));
                    lop.setNienKhoa(rs.getString("nienKhoa"));
                }  
            }
        }
        return lop;
    }
    
    public List<Lop> searchLop(String keyword) throws Exception {
        List<Lop> listLop = new ArrayList<>();
        String sql = "SELECT * FROM Lop WHERE maLop LIKE ? OR tenLop LIKE ? OR nienKhoa LIKE ?";

        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            String queryParam = "%" + keyword + "%";
            pstmt.setString(1, queryParam);
            pstmt.setString(2, queryParam);
            pstmt.setString(3, queryParam);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Lop lop = new Lop(
                        rs.getString("maLop"),
                        rs.getString("tenLop"),
                        rs.getString("nienKhoa")
                    );
                    listLop.add(lop);
                }
            }
        }

        return listLop;
    }

    public boolean checkExist(String maLop) throws Exception {
        String sql = "SELECT COUNT(*) FROM Lop WHERE maLop = ?";
        try {
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, maLop);
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

