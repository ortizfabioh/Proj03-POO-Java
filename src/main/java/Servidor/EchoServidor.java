package Servidor;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class EchoServidor extends Thread {
    private DatagramSocket socket;
    private String recebido;
    private InetAddress ip;
    private int porta;
    
    public EchoServidor() throws SocketException {
        socket = new DatagramSocket(80);
    }
    
    @Override
    public void run() {
        boolean running = true;
        while(running) {
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            ip = packet.getAddress();
            porta = packet.getPort();
            byte[] buf = packet.getData();
            
            recebido = new String(packet.getData(), 0, packet.getLength());

            // NESSE PONTO AS MENSAGENS ESTÃO CHEGANDO
            if(recebido.equals("ON")) {
                System.out.println("### "+ip+":"+porta+" >>> ON");
                System.out.println("Cliente adicionou semáforo de identificação --> "+ip+":"+porta);
            }
            
            if(recebido.equals("OFF")) {
                System.out.println("### "+ip+":"+porta+" >>> OFF");
                System.out.println("Cliente removeu semáforo de identificação --> "+ip+":"+porta);
                running = false;
                continue;
            }
            System.out.println("*** "+ip+":"+porta+" >>> "+recebido);
        }
        socket.close();
    }
    
    public InetAddress receberIp() {
        return ip;
    }
    
    public int receberPorta() {
        return porta;
    }
    
    public String receberMensagem() {
        return recebido;
    }
}