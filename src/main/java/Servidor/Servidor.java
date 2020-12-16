package Servidor;

import Cliente.*;
import Comum.*;
import java.awt.Color;
import java.io.IOException;
import java.util.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Servidor extends javax.swing.JFrame {
    private EchoServidor servidor; 
    private ThreadTexto thread;
        
    class ThreadTexto extends Thread {    
        @Override
        public void run() {
            Style padrao = pane.addStyle("padrao", null);
            StyleConstants.setFontSize(padrao, 15);
            Style negrito = pane.addStyle("negrito", padrao);
            StyleConstants.setBold(negrito, true);
            Style vermelho = pane.addStyle("vermelho", negrito);
            StyleConstants.setForeground(vermelho, Color.RED);
            Style amarelo = pane.addStyle("amarelo", negrito);
            StyleConstants.setForeground(amarelo, Color.YELLOW);
            Style verde = pane.addStyle("verde", negrito);
            StyleConstants.setForeground(verde, Color.GREEN);

            while(true) {
                LinkedHashSet<String> lista = new LinkedHashSet<>();
                lista = servidor.receberLista();
                StyledDocument doc = (StyledDocument)pane.getDocument();
                
                try {
                    doc.insertString(doc.getLength(), "Clientes conectados ("+lista.size()+")\n", padrao);
                    if(lista.size() > 0) {  // Existe cliente conectado
                        for(String i : lista) {
                            String[] split = i.split("> ");  // Separa a mensgem da identificação
                            doc.insertString(doc.getLength(), "     "+split[0], padrao);  // Printa identificação
                            switch(split[1]) {  // printa mensagem
                                case "VERMELHO\n":
                                    doc.insertString(doc.getLength(), " "+split[1], vermelho); break;
                                case "AMARELO\n":
                                    doc.insertString(doc.getLength(), " "+split[1], amarelo); break;
                                case "VERDE\n":
                                    doc.insertString(doc.getLength(), " "+split[1], verde); break;
                                default:  // ON e OFF
                                    doc.insertString(doc.getLength(), " "+split[1], negrito); break;
                            }
                        }
                        doc.insertString(doc.getLength(), "\n", null);
                    }
                    
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
                
                pane.setCaretPosition(pane.getDocument().getLength());  // Mantém o scroll embaixo
                
                try {
                    Thread.sleep(new Tempo().tempo());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public Servidor() throws IOException {
        initComponents();

        servidor = new EchoServidor();
        servidor.start();
        
        thread = new ThreadTexto();
        thread.setDaemon(true);
        thread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPane1 = new javax.swing.JScrollPane();
        pane = new javax.swing.JTextPane();
        menuBar = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuItemTelaCliente = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemSair = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        menuItemAjuda = new javax.swing.JMenuItem();
        menuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 600));

        pane.setEditable(false);
        pane.setDragEnabled(true);
        pane.setFocusable(false);
        JScrollPane1.setViewportView(pane);

        menuArquivo.setText("Arquivo");

        menuItemTelaCliente.setText("Novo Cliente");
        menuItemTelaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTelaClienteActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemTelaCliente);
        menuArquivo.add(jSeparator1);

        menuItemSair.setText("Sair");
        menuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSairActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemSair);

        menuBar.add(menuArquivo);

        menuAjuda.setText("Ajuda");

        menuItemAjuda.setText("Ajuda");
        menuItemAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAjudaActionPerformed(evt);
            }
        });
        menuAjuda.add(menuItemAjuda);

        menuItemSobre.setText("Sobre");
        menuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(menuItemSobre);

        menuBar.add(menuAjuda);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSobreActionPerformed
        Sobre sobre = new Sobre();
        sobre.setVisible(true);
        sobre.setAlwaysOnTop(true);
    }//GEN-LAST:event_menuItemSobreActionPerformed
    private void menuItemAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAjudaActionPerformed
        Ajuda ajuda = new Ajuda();
        ajuda.setVisible(true);
        ajuda.setAlwaysOnTop(true);
    }//GEN-LAST:event_menuItemAjudaActionPerformed
    private void menuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSairActionPerformed
    private void menuItemTelaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTelaClienteActionPerformed
        try {
            Cliente cliente = new Cliente();
            cliente.setVisible(true);
            cliente.setAlwaysOnTop(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_menuItemTelaClienteActionPerformed

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Servidor().setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemAjuda;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JMenuItem menuItemTelaCliente;
    private javax.swing.JTextPane pane;
    // End of variables declaration//GEN-END:variables
}
