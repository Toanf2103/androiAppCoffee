package Model;



public class SinhVien {
    int id;
    String lop;
    String ten;

    public SinhVien() {
    }

    public SinhVien(int id, String lop, String ten) {
        this.id = id;
        this.lop = lop;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
