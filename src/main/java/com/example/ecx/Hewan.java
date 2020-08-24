/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecx;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Hanafi-PC
 */
@Entity
public class Hewan {

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getTipe() {
        return tipe;
    }

    public String getNama() {
        return nama;
    }

    @Id
    private int id;
    private String jenis;

    private String tipe;

    private String nama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
