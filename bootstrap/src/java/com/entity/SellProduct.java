/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sale")
public class SellProduct implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Id
    private int id;
    
    @Column(name="s_name")
    private String s_name;
    
     @Column(name="s_uprice")
    private int s_uprice;
     
      @Column(name="s_qty")
    private int s_qty;
      
       @Column(name="s_amount")
    private int s_amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public int getS_uprice() {
        return s_uprice;
    }

    public void setS_uprice(int s_uprice) {
        this.s_uprice = s_uprice;
    }

    public int getS_qty() {
        return s_qty;
    }

    public void setS_qty(int s_qty) {
        this.s_qty = s_qty;
    }

    public int getS_amount() {
        return s_amount;
    }

    public void setS_amount(int s_amount) {
        this.s_amount = s_amount;
    }
       
       
}
