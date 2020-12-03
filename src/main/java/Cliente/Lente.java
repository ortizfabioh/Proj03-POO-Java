package Cliente;

import java.awt.*;

public class Lente extends Canvas {
    private Color ligado;
    private Color desligado = Color.black;
    private Color corAtual;

    private final static int SIZE = 100;
    private final static int OFFSET = 20;

    public Lente(Color color) {
        this.setBackground(Color.black);
        this.ligado = color;
        this.setSize(SIZE, SIZE);
        this.desligar();
    }

    public void paint(Graphics g) {
      g.setColor(this.corAtual);
      g.fillOval(OFFSET, OFFSET, SIZE - OFFSET*2, SIZE - OFFSET*2);
    }

    public void ligar() {
      corAtual = ligado;
      this.repaint();
    }
    
    public void desligar() {
      corAtual = desligado;
      this.repaint();
    }
}
