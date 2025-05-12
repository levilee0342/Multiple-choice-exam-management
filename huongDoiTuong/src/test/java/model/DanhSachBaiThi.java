package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachBaiThi {
	private List<BaiThi> dsBaiThi;

    public DanhSachBaiThi() {
        dsBaiThi = new ArrayList<>();
    }

	public List<BaiThi> getDsBaiThi() {
		return dsBaiThi;
	}

	public void setDsBaiThi(List<BaiThi> dsBaiThi) {
		this.dsBaiThi = dsBaiThi;
	}
    
}
