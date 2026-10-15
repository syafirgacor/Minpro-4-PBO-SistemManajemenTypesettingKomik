/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class ProyekTypeset {
    private final String idProyek; 
    private Komik komik;
    private Typesetter typesetter; 
    private int chapter;
    private String status;

    public ProyekTypeset(String idProyek, Komik komik, Typesetter typesetter, int chapter, String status) {
        this.idProyek = idProyek;
        this.komik = komik;
        this.typesetter = typesetter;
        this.chapter = chapter;
        this.status = status;
    }

    public String getIdProyek() { return idProyek; }
    public Komik getKomik() { return komik; }
    public Typesetter getTypesetter() { return typesetter; }
    public int getChapter() { return chapter; }
    public String getStatus() { return status; }

    public void setKomik(Komik komik) { this.komik = komik; }
    public void setTypesetter(Typesetter typesetter) { this.typesetter = typesetter; }
    public void setChapter(int chapter) { this.chapter = chapter; }
    public void setStatus(String status) { this.status = status; }
}
