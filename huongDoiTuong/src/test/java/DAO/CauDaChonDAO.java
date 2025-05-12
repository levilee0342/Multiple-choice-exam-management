package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.CauDaChon;

public class CauDaChonDAO {

    public List<CauDaChon> getAllCauDaChon() throws Exception {
        List<CauDaChon> listCauDaChon = new ArrayList<>();
        String sql = "SELECT * FROM CauDaChon";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                CauDaChon cdc = new CauDaChon(
                    rs.getString("maBaiThi"),
                    rs.getString("id"),
                    rs.getString("cauDaChon")
                );
                listCauDaChon.add(cdc);
            }
        }
        
        return listCauDaChon;
    }

    public static boolean insertCauDaChon(CauDaChon cdc) throws Exception {
        String sql = "INSERT INTO CauDaChon (maBaiThi, id, cauDaChon) VALUES (?, ?, ?)";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, cdc.getMaSV());
            pstmt.setString(2, cdc.getId());
            pstmt.setString(3, cdc.getCauDaChon());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteCauDaChon(String maBaiThi, String id) throws Exception {
        String sql = "DELETE FROM CauDaChon WHERE maBaiThi = ? AND id = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maBaiThi);
            pstmt.setString(2, id);
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateCauDaChon(CauDaChon cdc) throws Exception {
        String sql = "UPDATE CauDaChon SET cauDaChon = ? WHERE maBaiThi = ? AND id = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, cdc.getCauDaChon());
            pstmt.setString(2, cdc.getMaSV());
            pstmt.setString(3, cdc.getId());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<CauDaChon> searchCauDaChon(String keyword) throws Exception {
        List<CauDaChon> listCauDaChon = new ArrayList<>();
        String sql = "SELECT * FROM CauDaChon WHERE maBaiThi LIKE ? OR id LIKE ? OR cauDaChon LIKE ?";

        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            String queryParam = "%" + keyword + "%";
            for (int i = 1; i <= 3; i++) {
                pstmt.setString(i, queryParam);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CauDaChon cdc = new CauDaChon(
                        rs.getString("maBaiThi"),
                        rs.getString("id"),
                        rs.getString("cauDaChon")
                    );
                    listCauDaChon.add(cdc);
                }
            }
        }

        return listCauDaChon;
    }
}
