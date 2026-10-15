/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.ProyekTypeset;

/**
 *
 * @author user
 */
public interface IProyekDAO {
    boolean tambahProyek(ProyekTypeset proyek);
    List<ProyekTypeset> getAllProyek();
    boolean updateProyek(ProyekTypeset proyek);
    boolean hapusProyek(String idProyek);
}
