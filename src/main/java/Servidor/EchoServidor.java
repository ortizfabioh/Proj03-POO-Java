package Servidor;

import java.io.IOException;
import java.net.*;

public class EchoServidor extends Thread {
    private DatagramSocket socket;
    private String recebido;
    private InetAddress ip;
    private int porta;
    
    public EchoServidor() throws SocketException, IOException {
        socket = new DatagramSocket(80);
    }
    
    @Override
    public void run() {
        while(true) {
            //lista.clear();
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
        }
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