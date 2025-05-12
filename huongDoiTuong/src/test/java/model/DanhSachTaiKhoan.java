package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachTaiKhoan {
	private List<TaiKhoan> dsTaiKhoan;

    public DanhSachTaiKhoan() {
        dsTaiKhoan = new ArrayList<>();
    }

	public List<TaiKhoan> getDsTaiKhoan() {
		return dsTaiKhoan;
	}

	public void setDsTaiKhoan(List<TaiKhoan> dsTaiKhoan) {
		this.dsTaiKhoan = dsTaiKhoan;
	}
    
}
