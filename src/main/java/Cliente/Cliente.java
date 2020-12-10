package Cliente;

import Comum.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class Cliente extends javax.swing.JFrame {
    EchoCliente cliente;
    Timer t;
    TimerTask tt;
    
    private int corAtual=2;
    String msg;
    
    private final Lente vermelho = new Lente(Color.red);
    private final Lente amarelo = new Lente(Color.yellow);
    private final Lente verde = new Lente(Color.green);
    
    public void setVermelho() {
        vermelho.ligar();
        amarelo.desligar();
        verde.desligar();
        corAtual = 0;
        msg = "VERMELHO";
    }
    public void setVerde() {
        vermelho.desligar();
        amarelo.desligar();
        verde.ligar();
        corAtual = 1;
        msg = "VERDE";
    }
    public void setAmarelo() {
        vermelho.desligar();
        amarelo.ligar();
        verde.desligar();
        corAtual = 2;
        msg = "AMARELO";
    }
    public void proximo(int c) {
        switch(c) {
            case 0:
                setVerde(); break;
            case 1:
                setAmarelo(); break;
            case 2:
                setVermelho(); break;
        }
    }
    
    public Cliente() throws SocketException {
        initComponents();
        
        semaforo.setLayout(new GridLayout(1, 3));
        semaforo.add(vermelho);
        semaforo.add(amarelo);
        semaforo.add(verde);
        this.add(BorderLayout.NORTH, semaforo);
        
        cliente = new EchoCliente();
        t = new Timer();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        semaforo = new javax.swing.JPanel();
        iniciar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuItemSair = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        menuItemAjuda = new javax.swing.JMenuItem();
        menuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente - Semáforo");

        semaforo.setBackground(new java.awt.Color(153, 153, 153));
        semaforo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        semaforo.setForeground(new java.awt.Color(51, 51, 51));
        semaforo.setMinimumSize(new java.awt.Dimension(320, 100));
        semaforo.setPreferredSize(new java.awt.Dimension(320, 100));

        javax.swing.GroupLayout semaforoLayout = new javax.swing.GroupLayout(semaforo);
        semaforo.setLayout(semaforoLayout);
        semaforoLayout.setHorizontalGroup(
            semaforoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        semaforoLayout.setVerticalGroup(
            semaforoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        iniciar.setText("Iniciar");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });

        menuArquivo.setText("Arquivo");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 296, Short.MAX_VALUE)
                        .addComponent(iniciar))
                    .addComponent(semaforo, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(semaforo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(iniciar)
                .addGap(9, 9, 9))
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
        try {
            cliente.fechar();
            
            t.cancel();
            tt.cancel();
            t.purge();
            
            this.dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_menuItemSairActionPerformed
    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        try {
            cliente.login();
            
            tt = new TimerTask() {  // Timer para envio de mensagem
                @Override
                public void run() {
                    proximo(corAtual);
                    
                    // Implementar aqui o envio de mensagem para o servidor
                    try {
                        cliente.enviarEcho(msg);
                    } catch (SocketException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            };
            t.schedule(tt, 2*1000, 2*1000); // Primeiro tempo, período
            
            iniciar.setEnabled(false);  // Desabilitar botão
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_iniciarActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Cliente().setVisible(true);
                } catch (SocketException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton iniciar;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemAjuda;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JPanel semaforo;
    // End of variables declaration//GEN-END:variables
}
