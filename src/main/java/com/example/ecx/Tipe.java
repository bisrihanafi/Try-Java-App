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
public class Tipe {
    @Id
    private String no;
    private String tipe;

    public String getNo() {
        return no;
    }

    public String getTipe() {
        return tipe;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
}
