package Servidor;

import Cliente.*;
import Comum.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class Servidor extends javax.swing.JFrame {
    private EchoServidor servidor; 
    private ThreadTexto thread;
    private EchoCliente c = new EchoCliente();  // Pra poder usar o método tempoExecucao
    
    class ThreadTexto extends Thread {    
        @Override
        public void run() {
            while(true) {
                LinkedHashSet<String> lista = new LinkedHashSet<>();
                lista = servidor.receberLista();
                
                campoTexto.append("Clientes conectados ("+lista.size()+")\n");  // Fica  mostrando mesmo se não tiver nenhum cliente conectado
                if(lista.size() > 0) {  // Existe cliente conectado
                    for(String i : lista) {
                        campoTexto.append(i);
                    }
                    campoTexto.append("\n");
                }
                
                campoTexto.setCaretPosition(campoTexto.getDocument().getLength());  // Mantém o scroll embaixo
                
                try {
                    Thread.sleep(c.tempoExecucao());  // Tempo de execução *Deve ser igual ao tempo do timer do cliente*
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    
    public Servidor() throws IOException {
        initComponents();
        
        // Receber mensagem
        servidor = new EchoServidor();
        servidor.start();
        
        // Escrever mensagem recebida no campo de texto
        thread = new ThreadTexto();
        thread.setDaemon(true);  // Daemon Thread é pra oferecer serviço à Thread User
        thread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        campoTexto = new javax.swing.JTextArea();
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

        campoTexto.setEditable(false);
        campoTexto.setColumns(20);
        campoTexto.setRows(5);
        jScrollPane1.setViewportView(campoTexto);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
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
    private javax.swing.JTextArea campoTexto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemAjuda;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JMenuItem menuItemTelaCliente;
    // End of variables declaration//GEN-END:variables
}
