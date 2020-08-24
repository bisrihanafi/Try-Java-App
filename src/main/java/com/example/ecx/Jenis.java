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
public class Jenis {

    public void setNo(String no) {
        this.no = no;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo() {
        return no;
    }

    public String getNama() {
        return nama;
    }
    @Id
    private String no;
    private String nama;
}
