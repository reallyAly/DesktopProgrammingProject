/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import dao.StudentDAO;
import java.util.ArrayList;

/**
 *
 * @author aly
 */
public class StudentsComboBoxModel extends AbstractListModel implements ComboBoxModel{
    
  ArrayList<Student> students = new ArrayList<>();

  Student selection = null;
  
  public StudentsComboBoxModel() {
      
      StudentDAO studentDAO = new StudentDAO();
      
      this.students = studentDAO.get(null);

  }

  public Object getElementAt(int index) {
    return students.get(index);
  }

  public int getSize() {
    return students.size();
  }

  public void setSelectedItem(Object anItem) {
    selection = (Student) anItem; // to select and register an
  } 
  
  // Methods implemented from the interface ComboBoxModel
  public Object getSelectedItem() {
    return selection; // to add the selection to the combo box
  }
}

