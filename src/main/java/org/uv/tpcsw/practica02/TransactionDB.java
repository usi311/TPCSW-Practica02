/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcsw.practica02;

import java.sql.Connection;

/**
 *
 * @author iker
 */
public abstract class TransactionDB <T>{
    
    protected T pojo;

    protected TransactionDB(T pojo) {
        this.pojo = pojo;
    }

    public abstract boolean execute(Connection con);
}
