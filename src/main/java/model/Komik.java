/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Komik {
    private final String idKomik;
    private String judul;
    private String genre;

    public Komik(String idKomik, String judul, String genre) {
        this.idKomik = idKomik;
        this.judul = judul;
        this.genre = genre;
    }

    public String getIdKomik() { return idKomik; }
    public String getJudul() { return judul; }
    public String getGenre() { return genre; }

    public void setJudul(String judul) { this.judul = judul; }
    public void setGenre(String genre) { this.genre = genre; }
}