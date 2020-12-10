package Servidor;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class EchoServidor extends Thread {
    private DatagramSocket socket;
    private String recebido;
    private InetAddress ip;
    private int porta;
    private boolean running = true;
    
    public EchoServidor() throws SocketException, IOException {
        socket = new DatagramSocket(80);
    }
    
    @Override
    public void run() {
        while(running) {
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

            /*
            Pacotes recebidos não se agrupam na msm lista;
            Nova thread deve ou não ser outro arquivo?
            */
            
            // NESSE PONTO AS MENSAGENS ESTÃO CHEGANDO E EXECUTA O TEMPO TODO
            if(recebido.equals("ON")) {  // Cliente se conectou
                recebido = "###"+ip+":"+porta+" >>> ON\n"+"ADICIONADO --> "+ip+":"+porta+"\n";
            } else if(recebido.equals("OFF")) {  // Cliente se desconectou
                recebido = "###"+ip+":"+porta+" >>> OFF\n"+"REMOVIDO --> "+ip+":"+porta+"\n";
                running = false;
            } else {  // A mensagem é comum
                recebido = "***"+ip+":"+porta+" >>> "+recebido+"\n";
            }
            recebido += "\n";
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
    
    public boolean receberRunning() {
        return running;
    }
}