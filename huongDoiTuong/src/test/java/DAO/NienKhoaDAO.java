package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;

public class NienKhoaDAO {
	public static List<String> getAllNienKhoa() throws Exception {
        List<String> listNienKhoa = new ArrayList<>();
        String sql = "SELECT * FROM NienKhoa";
        
        try (
            Connection con = DatabaseHelper.openConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                listNienKhoa.add(rs.getString("nienKhoa"));
            }
        }
        
        return listNienKhoa;
    }
}
