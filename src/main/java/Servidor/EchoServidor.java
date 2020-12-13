package Servidor;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class EchoServidor extends Thread {
    private MulticastSocket socket;
    
    private String recebido;
    private String msg;
    private LinkedHashSet<String> lista = new LinkedHashSet<>();  // Não aceita duplicatas
    
    public EchoServidor() throws IOException {
        socket = new MulticastSocket(80);  // Fica ouvindo a porta 80
    }
    
    // Fica executando eternamente para ouvir por mensagens
    @Override
    public void run() {        
        while(true) {
            
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            long fim = System.currentTimeMillis() + 2000;  // Momento exatamente daqui 2 segundos
            while(System.currentTimeMillis() < fim) {  // Executar por 2 segundos
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                InetAddress ip = packet.getAddress();
                int porta = packet.getPort();
                byte[] buf = packet.getData();

                recebido = new String(packet.getData(), 0, packet.getLength());

                if(recebido.equals("ON")) {  // Cliente se conectou
                    msg = "###"+ip+":"+porta+" >>> ON\n"+"ADICIONADO --> "+ip+":"+porta+"\n";
                } else if(recebido.equals("OFF")) {  // Cliente se desconectou
                    msg = "###"+ip+":"+porta+" >>> OFF\n"+"REMOVIDO --> "+ip+":"+porta+"\n";
                } else {  // A mensagem é uma cor
                    msg = "***"+ip+":"+porta+" >>> "+recebido+"\n";
                }

                lista.add(msg);
            }
        }
    }
    
    public LinkedHashSet<String> receberLista() {
        LinkedHashSet clone = new LinkedHashSet<>();  // Uma lista clone é criada a cada chamada
        clone = (LinkedHashSet)lista.clone();  // Cria um clone da lista
        lista.clear();  // Limpa a lista
        return clone;
    }
}