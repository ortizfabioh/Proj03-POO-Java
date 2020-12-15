package Cliente;

import java.io.IOException;
import java.net.*;

public class EchoCliente extends Thread{
    private MulticastSocket socket;

    public EchoCliente() throws IOException {
        socket = new MulticastSocket();
    }
  
    public void enviarEcho(String msg) throws IOException {  // Função que envia a mensagem
        byte[] msgBuf = msg.getBytes();
        InetAddress enderecoDestino = InetAddress.getByName("255.255.255.255");

        DatagramPacket packet = new DatagramPacket(msgBuf, msgBuf.length, enderecoDestino, 80);  // Envia a mensagem na porta 80
        socket.send(packet);
    }
    
    public void login() throws IOException {  // "Estou me conectando"
        enviarEcho("ON");
    }
    
    public void fechar() throws IOException {  // "To saindo"
        enviarEcho("OFF");
        socket.close();
    }
}

