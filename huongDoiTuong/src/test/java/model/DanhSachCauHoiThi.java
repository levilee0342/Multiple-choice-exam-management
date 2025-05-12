package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachCauHoiThi {
	private List<CauHoiThi> dsCauHoiThi;

    public DanhSachCauHoiThi() {
        dsCauHoiThi = new ArrayList<>();
    }

    public void themCauHoiThi(CauHoiThi cauHoiThi) {
        dsCauHoiThi.add(cauHoiThi);
    }

    public List<CauHoiThi> getDsCauHoiThi() {
        return dsCauHoiThi;
    }
}
