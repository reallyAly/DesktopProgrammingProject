/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repository;

import java.util.ArrayList;

/**
 *
 * @author alysson
 */
public interface Repository {
    
    public Object findById(int id) throws Exception;
    
    public ArrayList<?> get();
    
    public boolean save(Object o) throws Exception;
}
