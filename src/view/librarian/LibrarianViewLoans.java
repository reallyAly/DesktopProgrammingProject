/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.librarian;

import controller.ReturnBookController;
import dao.BookDAO;
import dao.LoanDAO;
import dao.StudentDAO;
import dao.DevolutionDAO;
import exception.LoanNotExistException;
import javax.naming.CannotProceedException;
import java.util.ArrayList;
import model.Book;
import model.Loan;
import model.Student;
import model.Devolution;

/**
 *
 * @author alysson
 */
public class LibrarianViewLoans extends javax.swing.JFrame {
    
    private BookDAO bookDAO;
    
    private LoanDAO loanDAO;
    
    private StudentDAO studentDAO;
    
    private DevolutionDAO devolutionDAO;
    
    private ReturnBookController returnBookController;
    
    private int librarianId;
    
    /**
     * Creates new form LibrarianViewLoans
     * @param librarianId
     */
    public LibrarianViewLoans(int librarianId) {
        this.librarianId = librarianId;
        this.studentDAO = new StudentDAO();
        this.loanDAO = new LoanDAO();
        this.bookDAO = new BookDAO();
        this.devolutionDAO = new DevolutionDAO();
        this.returnBookController = new ReturnBookController(librarianId);
        initComponents();
        fillTable();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        loanTable = new javax.swing.JTable();
        returnButton = new javax.swing.JButton();
        backButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Uroob", 1, 48)); // NOI18N
        titleLabel.setText("LIBRARY APP");

        loanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Loan ID", "Student Name", "Book Name", "Loan Date", "Devolution Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(loanTable);
        if (loanTable.getColumnModel().getColumnCount() > 0) {
            loanTable.getColumnModel().getColumn(0).setResizable(false);
            loanTable.getColumnModel().getColumn(1).setResizable(false);
            loanTable.getColumnModel().getColumn(2).setResizable(false);
            loanTable.getColumnModel().getColumn(3).setResizable(false);
            loanTable.getColumnModel().getColumn(4).setResizable(false);
        }

        returnButton.setText("Return a book");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        backButton2.setText("Back");
        backButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(backButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(titleLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titleLabel)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        
        try{
            
            int lineSelected = this.loanTable.getSelectedRow();
            String loanId = this.loanTable.getModel().getValueAt(lineSelected, 0).toString();
            
            this.returnBookController.returnBook(loanId);
                    
            this.jOptionPane1.showMessageDialog(this,
                "Your book has been returned!!",
                "Book Status",
                jOptionPane1.INFORMATION_MESSAGE);
            
            new LibrarianViewLoans(this.librarianId).setVisible(true);
            dispose();
            
        }catch (LoanNotExistException e) {
             this.jOptionPane1.showMessageDialog(this,
                e.getMessage(),
                "Error trying to return the book",
                jOptionPane1.WARNING_MESSAGE);
        }catch(CannotProceedException e){
             this.jOptionPane1.showMessageDialog(this,
                e.getMessage(),
                "Error trying to return the book",
                jOptionPane1.WARNING_MESSAGE);
        }catch(ArrayIndexOutOfBoundsException e) {
            this.jOptionPane1.showMessageDialog(this,
                "Please, select a loan on the grid",
                "Error trying to edit the book",
                jOptionPane1.WARNING_MESSAGE);
        }catch (Exception e) {
             this.jOptionPane1.showMessageDialog(this,
                e.getMessage(),
                "Error trying to return the book",
                jOptionPane1.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_returnButtonActionPerformed

    private void backButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton2ActionPerformed
        new LibrarianDashboardView(this.librarianId).setVisible(true);
        dispose();
    }//GEN-LAST:event_backButton2ActionPerformed

    private void fillTable(){ 
        
        ArrayList<Loan> loans = this.loanDAO.get(null);
        
        String devolutionDate = "";

        for(int i = 0; i < loans.size(); i++){

            Loan loan = loans.get(i);
            
            Book book = this.bookDAO.findById(loan.getBookId());
            Student stud = this.studentDAO.findById(loan.getStudentId());
            Devolution devolution = this.devolutionDAO.findById(loan.getDevolutionId());
            
            if(devolution.getEntityId() == 0) {
                devolutionDate = "###";
            }else{
                devolutionDate = devolution.getDevolutionDate();
            }

            this.loanTable.setValueAt(loan.getEntityId(), i, 0);
            this.loanTable.setValueAt(stud.getFirstname(), i, 1);
            this.loanTable.setValueAt(book.getName(), i, 2);
            this.loanTable.setValueAt(loan.getLoanDate(), i, 3);
            this.loanTable.setValueAt(devolutionDate, i, 4);
        }
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton2;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable loanTable;
    private javax.swing.JButton returnButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
