import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Paint extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private JPanel panelPPal;
    private JLabel jlbCoord, jblTitulo;
    private JButton jbnLinea, jbnRectangulo, jbnCirculo;
    private JButton jbnAzul, jbnVerde, jbnRojo;
    private JButton jbnBorrar;
    private JPanel drawingPanel;
    private Image buffer, temporal;
    private int x,y;
    private int figura = 0; // 1: Línea, 2: Rectángulo, 3: Circulo
    private Color color = Color.BLACK;

    public Paint() {
        setTitle("Paint");
        setLayout(getLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(0, 0, 800, 600);


        panelPPal = new JPanel();
        panelPPal.setBorder(new EmptyBorder(5, 5, 5, 5));
        //panelPPal.setBackground(new Color(222,255,245));
        panelPPal.setLayout(null);

        // Coordenadas
        jlbCoord = new JLabel("Coordenas: x,y:");
        jlbCoord.setBounds(600, 30, 200, 20);

        ImageIcon iconLinea = new ImageIcon("src/imagenes/linea.png");
        ImageIcon iconRectangulo = new ImageIcon("src/imagenes/rectangulo.png");
        ImageIcon iconCuadrado = new ImageIcon("src/imagenes/circulo.png");
        ImageIcon iconBorrador = new ImageIcon("src/imagenes/borrador.png");

        jbnLinea = new JButton(iconLinea);
        jbnLinea.setToolTipText("Dibujar Línea");
        jbnLinea.setBounds(50, 30, 30, 30);
        jbnLinea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ar0) {
               figura = 1;
            }
        });

        jbnRectangulo = new JButton(iconRectangulo);
        jbnRectangulo.setToolTipText("Dibujar Rectángulo");
        jbnRectangulo.setBounds(80, 30, 30, 30);
        jbnRectangulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                figura = 2;
            }
        });

        jbnCirculo = new JButton(iconCuadrado);
        jbnCirculo.setToolTipText("Dibujar Circulo");
        jbnCirculo.setBounds(110, 30, 30,30);
        jbnCirculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                figura = 3;
            }
        });

        jbnRojo = new JButton();
        jbnRojo.setBackground(Color.red);
        jbnRojo.setToolTipText("Rojo");
        jbnRojo.setBounds(160,30, 30,30);
        jbnRojo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                color = Color.RED;
            }
        });

        jbnAzul = new JButton();
        jbnAzul.setBackground(Color.blue);
        jbnAzul.setToolTipText("Azul");
        jbnAzul.setBounds(190,30, 30,30);
        jbnAzul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                color = Color.BLUE;
            }
        });

        jbnVerde = new JButton();
        jbnVerde.setBackground(Color.green);
        jbnVerde.setToolTipText("Verde");
        jbnVerde.setBounds(220,30, 30,30);
        jbnVerde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                color = Color.GREEN;
            }
        });

        jbnBorrar = new JButton(iconBorrador);
        jbnBorrar.setToolTipText("Borrar");
        jbnBorrar.setBounds(270,30, 30,30);
        jbnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buffer = createImage(panelPPal.getWidth(), panelPPal.getHeight());
                Graphics g = buffer.getGraphics();
                g.setColor(panelPPal.getBackground());
                g.fillRect(0, 0, panelPPal.getWidth(), panelPPal.getHeight());
                panelPPal.repaint();
            }
        });

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (buffer != null) {
                    g.drawImage(buffer, 0, 0, this);
                }
            }
        };
        drawingPanel.setBounds(15, 70, 760, 480);
        drawingPanel.addMouseListener(this);
        drawingPanel.addMouseMotionListener(this);

        panelPPal.add(jbnLinea);
        panelPPal.add(jbnRectangulo);
        panelPPal.add(jbnCirculo);
        panelPPal.add(jbnRojo);
        panelPPal.add(jbnAzul);
        panelPPal.add(jbnVerde);
        panelPPal.add(jbnCirculo);
        panelPPal.add(jbnBorrar);
        panelPPal.add(jlbCoord);
        panelPPal.add(drawingPanel);
        setContentPane(panelPPal);

        SwingUtilities.invokeLater(() -> {
            buffer = createImage(drawingPanel.getWidth(), drawingPanel.getHeight());
            Graphics g = buffer.getGraphics();
            g.setColor(panelPPal.getBackground());
            g.fillRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
        });
        setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = temporal.getGraphics();
        g.setColor(color);
        g.drawImage(buffer, 0, 0, drawingPanel);

        switch (figura) {
            case 1: // Línea
                g.drawLine(x, y, e.getX(), e.getY());
                break;
            case 2: // Rectángulo
                g.drawRect(Math.min(x, e.getX()), Math.min(y, e.getY()),
                        Math.abs(e.getX() - x), Math.abs(e.getY() - y));
                break;
            case 3: // Círculo
                int diameter = Math.max(Math.abs(e.getX() - x), Math.abs(e.getY() - y));
                g.drawOval(Math.min(x, e.getX()), Math.min(y, e.getY()), diameter, diameter);
                break;
            default:
                break;
        }
        drawingPanel.getGraphics().drawImage(temporal, 0, 0, drawingPanel);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        jlbCoord.setText("Coordenadas: x=" + e.getX() + ", y=" + e.getY());
    }

    public static void main(String[] args) {
        new Paint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //no es necesario
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        temporal = createImage(drawingPanel.getWidth(), drawingPanel.getHeight());
        Graphics g = temporal.getGraphics();
        g.drawImage(buffer, 0, 0, drawingPanel);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (buffer != null && temporal != null) {
            Graphics g = buffer.getGraphics();
            g.drawImage(temporal, 0, 0, drawingPanel);
            drawingPanel.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());
    }
}