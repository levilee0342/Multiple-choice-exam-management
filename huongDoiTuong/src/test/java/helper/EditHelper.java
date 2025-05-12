package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.MonHoc;

public class EditHelper {
	public static String DateTimeToString(LocalDateTime dt) {        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dt.format(formatter);
		return formattedDate;
    }
	public static boolean isUsed(String tenTable, String tenField, String value) throws Exception {
        String sql = "SELECT 1 FROM " + tenTable + " WHERE " + tenField + " = ?";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, value);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
	public static String timMaMonHoc(String tenMH, List<MonHoc> danhSachMonHoc) {
	    for (MonHoc mh : danhSachMonHoc) {
	        if (mh.getTenMH().equals(tenMH)) {
	            return mh.getMaMH();
	        }
	    }
	    return null; 
	}

}
