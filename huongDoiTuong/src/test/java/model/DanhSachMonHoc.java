package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMonHoc {
	private List<MonHoc> dsMonHoc;

    public DanhSachMonHoc() {
        dsMonHoc = new ArrayList<>();
    }

    public void themMonHoc(MonHoc monHoc) {
        dsMonHoc.add(monHoc);
    }

    public List<MonHoc> getDsMonHoc() {
        return dsMonHoc;
    }
}
