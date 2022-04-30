/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ManagementBookController;
import controller.file.FileBookController;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;

/**
 *
 * @author alysson
 */
public class AddNewBookView extends javax.swing.JFrame {
    
    private int librarianId;
    
    private Book book;
    
    private FileBookController fileBookController;
    
    private ManagementBookController managementBookController;

    /**
     * Creates new form AddNewBook
     * @param librarianId
     * @param bookId
     */
    public AddNewBookView(int librarianId, int bookId) {
        this.librarianId = librarianId;
        this.managementBookController = new ManagementBookController(librarianId);
        this.fileBookController = new FileBookController();
        initComponents();
        this.fillFields(bookId);
    }
        
    private void fillFields(int bookId){
        if(bookId > 0){
            Book book = this.fileBookController.getBookById(bookId);
            
            this.bookNameField.setText(book.getName());
            this.isbnField.setText(book.getIsbn());
            this.authorField.setText(book.getAuthor());
            this.book = book;
            this.jLabel1.setText("Update the book");
        }
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
        bookNameField = new javax.swing.JTextField();
        bookNameLabel = new javax.swing.JLabel();
        isbnField = new javax.swing.JTextField();
        isbnLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        authorField = new javax.swing.JTextField();
        authorLabel = new javax.swing.JLabel();
        saveBookButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Uroob", 1, 48)); // NOI18N
        titleLabel.setText("LIBRARY APP");

        bookNameLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        bookNameLabel.setText("Name");

        isbnLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        isbnLabel.setText("ISBN");

        jLabel1.setText("Add New Book");

        authorLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        authorLabel.setText("Author");

        saveBookButton.setText("SAVE");
        saveBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBookButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(274, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(authorLabel)
                            .addComponent(authorField, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isbnField, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isbnLabel)
                            .addComponent(bookNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookNameLabel))
                        .addGap(271, 271, 271))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(495, 495, 495))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(487, 487, 487))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(547, 547, 547))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(90, 90, 90)
                .addComponent(bookNameLabel)
                .addGap(18, 18, 18)
                .addComponent(bookNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(isbnLabel)
                .addGap(18, 18, 18)
                .addComponent(isbnField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(authorLabel)
                .addGap(18, 18, 18)
                .addComponent(authorField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(saveBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBookButtonActionPerformed

        try{
            
            if(this.book != null){
                this.managementBookController.updateBook(
                    this.book,
                    this.bookNameField.getText(), 
                    this.isbnField.getText(), 
                    this.authorField.getText()
                );
                
                this.jOptionPane1.showMessageDialog(this,
                "Your book has been updated!!",
                "Book Status",
                jOptionPane1.INFORMATION_MESSAGE);
                
            }else{
                
                this.managementBookController.createBook(
                    this.bookNameField.getText(), 
                    this.isbnField.getText(), 
                    this.authorField.getText()
                );
                
                this.jOptionPane1.showMessageDialog(this,
                "Your book has been saved!!",
                "Book Status",
                jOptionPane1.INFORMATION_MESSAGE);
                
            }

            new LibrarianDashboardView(this.librarianId).setVisible(true);
            dispose();
            
        }catch(IllegalArgumentException e){
            
            this.jOptionPane1.showMessageDialog(this,
                e.getMessage(),
                "Invalid Fields",
                jOptionPane1.WARNING_MESSAGE);
            
        }catch (Exception e) {
             this.jOptionPane1.showMessageDialog(this,
                e.getMessage(),
                "Error trying to save the bookss",
                jOptionPane1.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_saveBookButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorField;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JTextField bookNameField;
    private javax.swing.JLabel bookNameLabel;
    private javax.swing.JTextField isbnField;
    private javax.swing.JLabel isbnLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JButton saveBookButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
