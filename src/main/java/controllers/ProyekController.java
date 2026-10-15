/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.IProyekDAO;
import dao.ProyekDAOImpl;
import java.util.List;
import model.ProyekTypeset;

/**
 *
 * @author user
 */
public class ProyekController {
    private final IProyekDAO proyekDAO;

    public ProyekController() {
        this.proyekDAO = new ProyekDAOImpl();
    }

    public boolean tambahProyek(ProyekTypeset proyek) {
        return proyekDAO.tambahProyek(proyek);
    }

    public List<ProyekTypeset> getAllProyek() {
        return proyekDAO.getAllProyek();
    }

    public boolean updateProyek(ProyekTypeset proyek) {
        return proyekDAO.updateProyek(proyek);
    }

    public boolean hapusProyek(String idProyek) {
        return proyekDAO.hapusProyek(idProyek);
    }
}
