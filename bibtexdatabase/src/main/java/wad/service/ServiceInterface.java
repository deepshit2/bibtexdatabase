/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;


/**
 *
 * @author santeri
 */
public interface ServiceInterface<T> {
    
    public String getBibtex(T obj);
    
    public List<T> list();
}
