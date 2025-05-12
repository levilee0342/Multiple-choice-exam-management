package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDiemThi {
	private List<BaiThi> dsDiemThi;

    public DanhSachDiemThi() {
        dsDiemThi = new ArrayList<>();
    }

    public void themDiemThi(BaiThi diemThi) {
        dsDiemThi.add(diemThi);
    }

    public List<BaiThi> getDsDiemThi() {
        return dsDiemThi;
    }
}
