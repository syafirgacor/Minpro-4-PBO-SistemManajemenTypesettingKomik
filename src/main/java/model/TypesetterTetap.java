/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class TypesetterTetap extends Typesetter {
    private double gajiPokok;

    public TypesetterTetap(String idTypesetter, String nama, double gajiPokok) {
        super(idTypesetter, nama);
        this.gajiPokok = gajiPokok;
    }

    public double getGajiPokok() { return gajiPokok; }
    public void setGajiPokok(double gajiPokok) { this.gajiPokok = gajiPokok; }

    @Override
    public String getPeran() {
        return "Typesetter Tetap (Gaji: Rp " + (long) gajiPokok + ")";
    }

    @Override
    public double hitungBonus(int jumlahChapter) {
        return jumlahChapter * 15000.0;
    }
}

