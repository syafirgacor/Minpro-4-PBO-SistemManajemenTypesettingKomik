/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author user
 */
public class ProyekDAOImpl implements IProyekDAO {

    @Override
    public boolean tambahProyek(ProyekTypeset proyek) {
        String sqlTypesetter = "INSERT INTO typesetter (id_typesetter, nama, jenis, gaji_pokok, durasi_magang_bulan) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE nama=?, jenis=?, gaji_pokok=?, durasi_magang_bulan=?";
        String sqlKomik = "INSERT INTO komik (id_komik, judul, genre) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE judul=?, genre=?";
        String sqlProyek = "INSERT INTO proyek_typeset (id_proyek, id_komik, id_typesetter, chapter, status) VALUES (?, ?, ?, ?, ?)";

        Connection conn = DatabaseConfig.getConnection();
        if (conn == null) return false;

        try {
            conn.setAutoCommit(false);

            // 1. Insert/Update Typesetter
            try (PreparedStatement psT = conn.prepareStatement(sqlTypesetter)) {
                Typesetter t = proyek.getTypesetter();
                psT.setString(1, t.getIdTypesetter());
                psT.setString(2, t.getNama());
                psT.setString(3, t instanceof TypesetterTetap ? "TETAP" : "MAGANG");
                psT.setDouble(4, t instanceof TypesetterTetap ? ((TypesetterTetap) t).getGajiPokok() : 0);
                psT.setInt(5, t instanceof TypesetterMagang ? ((TypesetterMagang) t).getDurasiMagangBulan() : 0);
                
                psT.setString(6, t.getNama());
                psT.setString(7, t instanceof TypesetterTetap ? "TETAP" : "MAGANG");
                psT.setDouble(8, t instanceof TypesetterTetap ? ((TypesetterTetap) t).getGajiPokok() : 0);
                psT.setInt(9, t instanceof TypesetterMagang ? ((TypesetterMagang) t).getDurasiMagangBulan() : 0);
                psT.executeUpdate();
            }

            // 2. Insert/Update Komik
            try (PreparedStatement psK = conn.prepareStatement(sqlKomik)) {
                Komik k = proyek.getKomik();
                psK.setString(1, k.getIdKomik());
                psK.setString(2, k.getJudul());
                psK.setString(3, k.getGenre());
                psK.setString(4, k.getJudul());
                psK.setString(5, k.getGenre());
                psK.executeUpdate();
            }

            // 3. Insert Proyek
            try (PreparedStatement psP = conn.prepareStatement(sqlProyek)) {
                psP.setString(1, proyek.getIdProyek());
                psP.setString(2, proyek.getKomik().getIdKomik());
                psP.setString(3, proyek.getTypesetter().getIdTypesetter());
                psP.setInt(4, proyek.getChapter());
                psP.setString(5, proyek.getStatus());
                psP.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.err.println("[ERROR DAO] Gagal Tambah Data: " + e.getMessage());
            return false;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }

    @Override
    public List<ProyekTypeset> getAllProyek() {
        List<ProyekTypeset> list = new ArrayList<>();
        String sql = "SELECT p.id_proyek, p.chapter, p.status, " +
                     "k.id_komik, k.judul, k.genre, " +
                     "t.id_typesetter, t.nama, t.jenis, t.gaji_pokok, t.durasi_magang_bulan " +
                     "FROM proyek_typeset p " +
                     "JOIN komik k ON p.id_komik = k.id_komik " +
                     "JOIN typesetter t ON p.id_typesetter = t.id_typesetter";

        Connection conn = DatabaseConfig.getConnection();
        if (conn == null) return list;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Komik komik = new Komik(
                    rs.getString("id_komik"),
                    rs.getString("judul"),
                    rs.getString("genre")
                );

                Typesetter typesetter;
                String jenis = rs.getString("jenis");
                if ("TETAP".equalsIgnoreCase(jenis)) {
                    typesetter = new TypesetterTetap(
                        rs.getString("id_typesetter"),
                        rs.getString("nama"),
                        rs.getDouble("gaji_pokok")
                    );
                } else {
                    typesetter = new TypesetterMagang(
                        rs.getString("id_typesetter"),
                        rs.getString("nama"),
                        rs.getInt("durasi_magang_bulan")
                    );
                }

                ProyekTypeset proyek = new ProyekTypeset(
                    rs.getString("id_proyek"),
                    komik,
                    typesetter,
                    rs.getInt("chapter"),
                    rs.getString("status")
                );

                list.add(proyek);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR DAO] Gagal Ambil Data: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updateProyek(ProyekTypeset proyek) {
        String sql = "UPDATE proyek_typeset SET id_komik=?, id_typesetter=?, chapter=?, status=? WHERE id_proyek=?";
        Connection conn = DatabaseConfig.getConnection();
        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, proyek.getKomik().getIdKomik());
            ps.setString(2, proyek.getTypesetter().getIdTypesetter());
            ps.setInt(3, proyek.getChapter());
            ps.setString(4, proyek.getStatus());
            ps.setString(5, proyek.getIdProyek());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[ERROR DAO] Gagal Update Data: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean hapusProyek(String idProyek) {
        String sql = "DELETE FROM proyek_typeset WHERE id_proyek=?";
        Connection conn = DatabaseConfig.getConnection();
        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idProyek);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[ERROR DAO] Gagal Hapus Data: " + e.getMessage());
            return false;
        }
    }
}