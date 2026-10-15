/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class TypesetterMagang extends Typesetter {
    private int durasiMagangBulan;

    public TypesetterMagang(String idTypesetter, String nama, int durasiMagangBulan) {
        super(idTypesetter, nama);
        this.durasiMagangBulan = durasiMagangBulan;
    }

    public int getDurasiMagangBulan() { return durasiMagangBulan; }
    public void setDurasiMagangBulan(int durasiMagangBulan) { this.durasiMagangBulan = durasiMagangBulan; }

    @Override
    public String getPeran() {
        return "Typesetter Magang (" + durasiMagangBulan + " Bulan)";
    }

    @Override
    public double hitungBonus(int jumlahChapter) {
        return jumlahChapter * 8000.0;
    }
}
