package Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Khoi
 */
public class GiaoVien {

    private String maGiaoVien;
    private String hoTen;
    private int namSinh;
    private double danhGia;
    private String tenKhoa;

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String maKhoa) {
        this.tenKhoa = maKhoa;
    }

    public double getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(double danhGia) {
        this.danhGia = danhGia;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public GiaoVien(String maGiaoVien, String hoTen, int namSinh, double danhGia, String maKhoa) {
        this.maGiaoVien = maGiaoVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.danhGia = danhGia;
        this.tenKhoa = maKhoa;
    }

}
