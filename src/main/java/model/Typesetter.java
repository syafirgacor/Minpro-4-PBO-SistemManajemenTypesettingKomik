/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public abstract class Typesetter {
    protected String idTypesetter;
    protected String nama;

    public Typesetter(String idTypesetter, String nama) {
        this.idTypesetter = idTypesetter;
        this.nama = nama;
    }

    public String getIdTypesetter() { return idTypesetter; }
    public String getNama() { return nama; }

    public void setIdTypesetter(String idTypesetter) { this.idTypesetter = idTypesetter; }
    public void setNama(String nama) { this.nama = nama; }

    public abstract String getPeran();
    public abstract double hitungBonus(int jumlahChapter);

    public void tampilkanInfo() {
        System.out.println("ID: " + idTypesetter + " | Nama: " + nama);
    }

    public void tampilkanInfo(String status) {
        System.out.println("ID: " + idTypesetter + " | Nama: " + nama + " (" + status + ")");
    }
}