/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Khoi
 */
public class LopHoc {

    private String maLop;
    private String tenLop;
    private String buoiDayTrongTuan;
    private String maGiaoVien;

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getBuoiDayTrongTuan() {
        return buoiDayTrongTuan;
    }

    public void setBuoiDayTrongTuan(String buoiDayTrongTuan) {
        this.buoiDayTrongTuan = buoiDayTrongTuan;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public LopHoc(String maLop, String tenLop, String buoiDayTrongTuan, String maGiaoVien) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.buoiDayTrongTuan = buoiDayTrongTuan;
        this.maGiaoVien = maGiaoVien;
    }

    @Override
    public String toString() {
        return "LopHoc{" + "maLop=" + maLop + ", tenLop=" + tenLop + ", buoiDayTrongTuan=" + buoiDayTrongTuan + ", maGiaoVien=" + maGiaoVien + '}';
    }

}
