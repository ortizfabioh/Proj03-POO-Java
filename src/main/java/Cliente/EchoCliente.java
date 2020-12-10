package Cliente;

import java.io.IOException;
import java.net.*;

public class EchoCliente extends Thread{
    private DatagramSocket socket;

    public EchoCliente() throws SocketException {
        socket = new DatagramSocket();
    }
    
    public void enviarEcho(String msg) throws UnknownHostException, SocketException, IOException {
        byte[] msgBuf = msg.getBytes();
        InetAddress enderecoDestino = InetAddress.getLocalHost();

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
}

