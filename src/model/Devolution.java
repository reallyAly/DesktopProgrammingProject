/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alysson
 */
public class Devolution {
    
    private int entityId;
    
    private String devolutionDate;

    public Devolution(int entityId, String devolutionDate) {
        this.entityId = entityId;
        this.devolutionDate = devolutionDate;
    }
    
    public Devolution() {
        
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }
    
}
