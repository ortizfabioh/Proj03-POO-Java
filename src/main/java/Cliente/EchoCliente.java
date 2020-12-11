package Cliente;

import java.io.IOException;
import java.net.*;

public class EchoCliente extends Thread{
    private MulticastSocket socket;

    public EchoCliente() throws IOException {
        socket = new MulticastSocket();
    }
  
    public void enviarEcho(String msg) throws UnknownHostException, SocketException, IOException {
        byte[] msgBuf = msg.getBytes();
        InetAddress enderecoDestino = InetAddress.getByName("255.255.255.255");

        DatagramPacket packet = new DatagramPacket(msgBuf, msgBuf.length, enderecoDestino, 80);
        socket.send(packet);
    }
    
    public void login() throws SocketException, IOException {
        enviarEcho("ON");
    }
    
    public void fechar() throws SocketException, IOException {
        enviarEcho("OFF");
        socket.close();
    }
    
    public int tempoExecucao() {
        return 2*1000;
    }
}

