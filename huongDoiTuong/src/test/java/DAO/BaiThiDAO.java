package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import helper.DatabaseHelper;
import model.BaiThi;

public class BaiThiDAO {

    public List<BaiThi> getAllBaiThi() throws Exception {
        List<BaiThi> listBaiThi = new ArrayList<>();
        String sql = "SELECT * FROM BaiThi";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                BaiThi bt = new BaiThi(
                    rs.getString("maBaiThi"),
                    rs.getString("maSV"),
                    rs.getString("maMH"),
                    rs.getString("maDotThi"),
                    rs.getInt("soPhutThi"),
                    rs.getFloat("diem")
                );
                listBaiThi.add(bt);
            }
        }
        
        return listBaiThi;
    }

    public static boolean insertBaiThi(BaiThi bt) throws Exception {        
        String sql = "INSERT INTO BaiThi (maBaiThi, maSV, maMH, maDotThi, soPhutThi, diem) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, bt.getMaBaiThi());
            pstmt.setString(2, bt.getMaSV());
            pstmt.setString(3, bt.getMaMH());
            pstmt.setString(4, bt.getMaDotThi());
            pstmt.setInt(5, bt.getSoPhutThi());
            pstmt.setFloat(6, bt.getDiem());
            return pstmt.executeUpdate() > 0;
        }
    }

    public static boolean updateBaiThi(BaiThi bt) throws Exception {
        String sql = "UPDATE BaiThi SET maSV = ?, maMH = ?, maDotThi = ?, soPhutThi = ?, diem = ? WHERE maBaiThi = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, bt.getMaSV());
            pstmt.setString(2, bt.getMaMH());
            pstmt.setString(3, bt.getMaDotThi());
            pstmt.setInt(4, bt.getSoPhutThi());
            pstmt.setFloat(5, bt.getDiem());
            pstmt.setString(6, bt.getMaBaiThi());
            return pstmt.executeUpdate() > 0;
        }
    }


    public List<BaiThi> searchBaiThi(String keyword) throws Exception {
        List<BaiThi> listBaiThi = new ArrayList<>();
        String sql = "SELECT * FROM BaiThi WHERE maBaiThi LIKE ? OR maSV LIKE ? OR maMH LIKE ? OR maDotThi LIKE ?";

        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            String queryParam = "%" + keyword + "%";
            for (int i = 1; i <= 4; i++) {
                pstmt.setString(i, queryParam);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BaiThi bt = new BaiThi(
                        rs.getString("maBaiThi"),
                        rs.getString("maSV"),
                        rs.getString("maMH"),
                        rs.getString("maDotThi"),
                        rs.getInt("soPhutThi"),
                        rs.getFloat("diem")
                    );
                    listBaiThi.add(bt);
                }
            }
        }

        return listBaiThi;
    }
    
    public static float tinhDiemBaiThi(String maBaiThi) throws Exception {
        float diem = 0.0f;
        String sql = "{call dbo.sp_TinhDiemBaiThi(?, ?)}";

        try (
            Connection con = DatabaseHelper.openConnection();
            CallableStatement cstmt = con.prepareCall(sql)
        ) {
            // Đặt tham số đầu vào
            cstmt.setString(1, maBaiThi);
            // Đặt tham số đầu ra
            cstmt.registerOutParameter(2, java.sql.Types.FLOAT);
            
            // Thực hiện stored procedure
            cstmt.execute();
            
            // Lấy giá trị điểm từ tham số đầu ra
            diem = cstmt.getFloat(2);
            System.out.println("Điểm từ stored procedure: " + diem);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi tính điểm bài thi.", e);
        }

        return diem;
    }


    
    private static boolean isMaBaiThiExist(String maBaiThi) throws Exception {
        String sql = "SELECT 1 FROM BaiThi WHERE maBaiThi = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, maBaiThi);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); 
            }
        }
    }

    public static String generateUniqueMaBaiThi() throws Exception {
        String maBaiThi;
        do {
            maBaiThi = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        } while (isMaBaiThiExist(maBaiThi));
        
        return maBaiThi;
    }

	
}
