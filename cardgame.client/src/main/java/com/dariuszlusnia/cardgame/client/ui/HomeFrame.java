/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.dariuszlusnia.cardgame.client.ui;

import com.dariuszlusnia.cardgame.client.common.CollectionFunctions;
import com.dariuszlusnia.cardgame.client.common.MessageType;
import com.dariuszlusnia.cardgame.client.common.RequestProcessor;
import com.dariuszlusnia.cardgame.client.ui.events.CombatCreatedEvent;
import com.dariuszlusnia.cardgame.client.ui.events.CombatEvent;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author dariu
 */
public class HomeFrame extends javax.swing.JFrame {

    private RequestProcessor processor;
    
    /**
     * Creates new form HomeFrame
     */
    public HomeFrame() {
        initComponents();
        this.messageLabel.setText("");
    }
    
    public boolean connectToServer() {
         try {
            var socket = new Socket("localhost", 2002);
            var writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            this.processor = new RequestProcessor(writer, reader);
            
            return true;
            
        } catch (Exception ex) {
            return false;
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

        signInAsPlayerButton = new javax.swing.JButton();
        signInAsAdminButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        signInAsPlayerButton.setText("Sign In as Player");
        signInAsPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInAsPlayerButtonActionPerformed(evt);
            }
        });

        signInAsAdminButton.setText("Sign In as Admin");
        signInAsAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInAsAdminButtonActionPerformed(evt);
            }
        });

        messageLabel.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(195, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(signInAsAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(signInAsPlayerButton)
                        .addGap(201, 201, 201))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(messageLabel)
                        .addGap(230, 230, 230))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(signInAsAdminButton)
                .addGap(18, 18, 18)
                .addComponent(signInAsPlayerButton)
                .addGap(18, 18, 18)
                .addComponent(messageLabel)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signInAsAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInAsAdminButtonActionPerformed
        this.messageLabel.setText("Signing In..");
        
        if (!connectToServer()) {
            this.messageLabel.setText("Server not running");
            return;
        }
        
        this.processor.write(MessageType.SIGN_IN_ADMIN);
        if (! this.processor.isSuccess()) {
            this.messageLabel.setText("Sign In Failed");
            return;
        }
        
        var frame = new AdminFrame();
        frame.init(this.processor);
        frame.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_signInAsAdminButtonActionPerformed

    private void signInAsPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInAsPlayerButtonActionPerformed
        this.messageLabel.setText("Signing In..");
        
        if (!connectToServer()) {
            this.messageLabel.setText("Server not running");
            return;
        }
        
        this.processor.write(MessageType.SIGN_IN_PLAYER);

        var frame = new CombatFrame();
        frame.init(this.processor);
        frame.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_signInAsPlayerButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton signInAsAdminButton;
    private javax.swing.JButton signInAsPlayerButton;
    // End of variables declaration//GEN-END:variables
}
