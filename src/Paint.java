import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Paint extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private JPanel panelPPal;
    private JLabel jlbCoord, jblTitulo;
    private JButton jbnLinea, jbnRectangulo, jbnCirculo;
    private JButton jbnRellRectangulo, jbnRellCirculo, jbnRellTriangulo, jbnRellCuadrado;
    private JButton jbnAzul, jbnVerde, jbnRojo;
    private JButton jbnTraslacion, jbnEscalar, jbnRotacion, jbnSesgar;
    private JButton jbnBorrar, jbnSolido, jbnPunteado, jbnGuiones;
    private JPanel drawingPanel;
    private Image buffer, temporal;
    private int x, y;
    private int figura = 0;
    private Color color = Color.BLACK;
    private BasicStroke stroke = new BasicStroke(2);

    private ArrayList<Figura> figuras = new ArrayList<>();


    public Paint() {
        setTitle("Paint");
        setLayout(getLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(0, 0, 800, 600);

        panelPPal = new JPanel();
        panelPPal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelPPal.setLayout(null);

        jlbCoord = new JLabel("Coordenadas: x, y:");
        jlbCoord.setBounds(600, 30, 200, 20);

        ImageIcon iconLinea = new ImageIcon("src/imagenes/linea.png");
        ImageIcon iconRectangulo = new ImageIcon("src/imagenes/rectangulo.png");
        ImageIcon iconCuadrado = new ImageIcon("src/imagenes/circulo.png");
        ImageIcon iconRellRectangulo = new ImageIcon("src/imagenes/rectanguloR.png");
        ImageIcon iconRellCirculo = new ImageIcon("src/imagenes/circuloR.png");
        ImageIcon iconRellTriangulo = new ImageIcon("src/imagenes/trianguloR.png");
        ImageIcon iconRellCuadrado = new ImageIcon("src/imagenes/cuadradoR.png");
        ImageIcon iconBorrador = new ImageIcon("src/imagenes/borrador.png");
        ImageIcon iconoSolido = new ImageIcon("src/imagenes/solida.png");
        ImageIcon iconoGuion = new ImageIcon("src/imagenes/guion.png");
        ImageIcon iconoPunteado = new ImageIcon("src/imagenes/puntos.png");
        ImageIcon iconoTrasladar = new ImageIcon("src/imagenes/trasladar.png");
        ImageIcon iconoEscalar = new ImageIcon("src/imagenes/escalar.png");
        ImageIcon iconoRotar = new ImageIcon("src/imagenes/rotar.png");
        ImageIcon iconoSesgar = new ImageIcon("src/imagenes/sesgar.png");


        jbnLinea = new JButton(iconLinea);
        jbnLinea.setToolTipText("Dibujar Línea");
        jbnLinea.setBounds(50, 30, 30, 30);
        jbnLinea.addActionListener(e -> figura = 1);
        panelPPal.add(jbnLinea);

        jbnRectangulo = new JButton(iconRectangulo);
        jbnRectangulo.setToolTipText("Dibujar Rectángulo");
        jbnRectangulo.setBounds(80, 30, 30, 30);
        jbnRectangulo.addActionListener(e -> figura = 2);
        panelPPal.add(jbnRectangulo);

        jbnCirculo = new JButton(iconCuadrado);
        jbnCirculo.setToolTipText("Dibujar Círculo");
        jbnCirculo.setBounds(110, 30, 30, 30);
        jbnCirculo.addActionListener(e -> figura = 3);
        panelPPal.add(jbnCirculo);

        jbnRellRectangulo = new JButton(iconRellRectangulo);
        jbnRellRectangulo.setToolTipText("Dibujar Rectángulo Relleno");
        jbnRellRectangulo.setBounds(140, 30, 30, 30);
        jbnRellRectangulo.addActionListener(e -> figura = 6);
        panelPPal.add(jbnRellRectangulo);

        jbnRellCirculo = new JButton(iconRellCirculo);
        jbnRellCirculo.setToolTipText("Dibujar Círculo Relleno");
        jbnRellCirculo.setBounds(170, 30, 30, 30);
        jbnRellCirculo.addActionListener(e -> figura = 7);
        panelPPal.add(jbnRellCirculo);

        jbnRellCuadrado = new JButton(iconRellCuadrado);
        jbnRellCuadrado.setToolTipText("Dibujar Cuadrado Relleno");
        jbnRellCuadrado.setBounds(200, 30, 30, 30);
        jbnRellCuadrado.addActionListener(e -> figura = 8);
        panelPPal.add(jbnRellCuadrado);

        jbnRellTriangulo = new JButton(iconRellTriangulo);
        jbnRellTriangulo.setToolTipText("Dibujar Triángulo Relleno");
        jbnRellTriangulo.setBounds(230, 30, 30, 30);
        jbnRellTriangulo.addActionListener(e -> figura = 9);
        panelPPal.add(jbnRellTriangulo);

        jbnRojo = new JButton();
        jbnRojo.setBackground(Color.RED);
        jbnRojo.setToolTipText("Rojo");
        jbnRojo.setBounds(260, 30, 30, 30);
        jbnRojo.addActionListener(e -> color = Color.RED);
        panelPPal.add(jbnRojo);

        jbnAzul = new JButton();
        jbnAzul.setBackground(Color.BLUE);
        jbnAzul.setToolTipText("Azul");
        jbnAzul.setBounds(290, 30, 30, 30);
        jbnAzul.addActionListener(e -> color = Color.BLUE);
        panelPPal.add(jbnAzul);

        jbnVerde = new JButton();
        jbnVerde.setBackground(Color.GREEN);
        jbnVerde.setToolTipText("Verde");
        jbnVerde.setBounds(320, 30, 30, 30);
        jbnVerde.addActionListener(e -> color = Color.GREEN);
        panelPPal.add(jbnVerde);

        jbnBorrar = new JButton(iconBorrador);
        jbnBorrar.setToolTipText("Borrar");
        jbnBorrar.setBounds(350, 30, 30, 30);
        jbnBorrar.addActionListener(e -> {
            buffer = createImage(drawingPanel.getWidth(), drawingPanel.getHeight());
            Graphics g = buffer.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
            g.dispose();
            figuras.clear();

            drawingPanel.repaint();
        });
        panelPPal.add(jbnBorrar);

        jbnSolido = new JButton(iconoSolido);
        jbnSolido.setToolTipText("Solido");
        jbnSolido.setBounds(380, 30, 30, 30);
        jbnSolido.addActionListener(e -> stroke = new BasicStroke(2));
        panelPPal.add(jbnSolido);

        jbnPunteado = new JButton(iconoPunteado);
        jbnPunteado.setToolTipText("Punteado");
        jbnPunteado.setBounds(410, 30, 30, 30);
        jbnPunteado.addActionListener(e -> {
            float[] dashPattern = {2f, 2f};
            stroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f);
        });
        panelPPal.add(jbnPunteado);

        jbnGuiones = new JButton(iconoGuion);
        jbnGuiones.setToolTipText("Guiones");
        jbnGuiones.setBounds(440, 30, 30, 30);
        jbnGuiones.addActionListener(e -> {
            float[] dashPattern = {10f, 10f};
            stroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f);
        });
        panelPPal.add(jbnGuiones);

        jbnTraslacion = new JButton(iconoTrasladar);
        jbnTraslacion.setToolTipText("Trasladar figuras");
        jbnTraslacion.setBounds(470, 30, 30, 30);
        jbnTraslacion.addActionListener(e -> {
            //mostrarFiguras();
            TrasladarFiguras(figuras, 10);
        });
        panelPPal.add(jbnTraslacion);

        jbnEscalar = new JButton(iconoEscalar);
        jbnEscalar.setToolTipText("Escalar figuras");
        jbnEscalar.setBounds(500, 30, 30, 30);
        jbnEscalar.addActionListener(e -> {
            EscalarFiguras(figuras,5);
        });
        panelPPal.add(jbnEscalar);

        jbnRotacion = new JButton(iconoRotar);
        jbnRotacion.setToolTipText("Rotar figuras");
        jbnRotacion.setBounds(530, 30, 30, 30);
        jbnRotacion.addActionListener(e -> {
            RotarFiguras(figuras,45);
        });
        panelPPal.add(jbnRotacion);

        jbnSesgar = new JButton(iconoSesgar);
        jbnSesgar.setToolTipText("Sesgar figuras");
        jbnSesgar.setBounds(560, 30, 30, 30);
        jbnSesgar.addActionListener(e -> {
            SesgarFiguras(figuras, 0.5, 0.5); // Sesgado vertical hacia abajo
        });
        panelPPal.add(jbnSesgar);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (buffer != null) {
                    g.drawImage(buffer, 0, 0, this);
                }
                g.clearRect(0, 0, getWidth(), getHeight());

                // Redibujar todas las figuras
                for (Figura figura : figuras) {
                    g.setColor(figura.color);
                    switch (figura.tipo) {
                        case 1: // Línea
                            g.drawLine(figura.x1, figura.y1, figura.x2, figura.y2);
                            break;
                        case 2: // Rectángulo
                            g.drawRect(figura.x1, figura.y1, figura.x2, figura.y2);
                            break;
                        case 3: // Círculo
                            int diameter = Math.max(figura.x2, figura.y2);
                            g.drawOval(figura.x1, figura.y1, diameter, diameter);
                            break;
                        case 6: // Rectángulo Relleno
                            g.fillRect(figura.x1, figura.y1, figura.x2, figura.y2);
                            break;
                        case 7: // Círculo Relleno
                            diameter = Math.max(figura.x2, figura.y2);
                            g.fillOval(figura.x1, figura.y1, diameter, diameter);
                            break;
                        case 8: // Cuadrado Relleno
                            g.fillRect(figura.x1, figura.y1, figura.x2, figura.x2);
                            break;
                        case 9: // Triángulo Relleno
                            int[] xPoints = {figura.x1, figura.x2, (figura.x1 + figura.x2) / 2};
                            int[] yPoints = {figura.y1, figura.y1, figura.y2};
                            g.fillPolygon(xPoints, yPoints, 3);
                            break;
                    }
                }
            }
        };
        drawingPanel.setBounds(15, 70, 760, 480);
        drawingPanel.addMouseListener(this);
        drawingPanel.addMouseMotionListener(this);
        panelPPal.add(drawingPanel);
        panelPPal.add(jlbCoord);

        setContentPane(panelPPal);

        SwingUtilities.invokeLater(() -> {
            buffer = createImage(drawingPanel.getWidth(), drawingPanel.getHeight());
            Graphics g = buffer.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
            drawingPanel.repaint();
        });

        setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics2D g = (Graphics2D) temporal.getGraphics();
        g.setColor(color);
        g.setStroke(stroke);

        switch (figura) {
            case 1:
                g.drawLine(x, y, e.getX(), e.getY());
                break;
            case 2:
                g.drawRect(Math.min(x, e.getX()), Math.min(y, e.getY()),
                        Math.abs(e.getX() - x), Math.abs(e.getY() - y));
                break;
            case 3:
                int diameter = Math.max(Math.abs(e.getX() - x), Math.abs(e.getY() - y));
                g.drawOval(Math.min(x, e.getX()), Math.min(y, e.getY()), diameter, diameter);
                break;
            case 6:
                g.fillRect(Math.min(x, e.getX()), Math.min(y, e.getY()),
                        Math.abs(e.getX() - x), Math.abs(e.getY() - y));
                break;
            case 7:
                int diam = Math.max(Math.abs(e.getX() - x), Math.abs(e.getY() - y));
                g.fillOval(Math.min(x, e.getX()), Math.min(y, e.getY()), diam, diam);
                break;
            case 8:
                int side = Math.min(Math.abs(e.getX() - x), Math.abs(e.getY() - y));
                g.fillRect(Math.min(x, e.getX()), Math.min(y, e.getY()), side, side);
                break;
            case 9:
                int[] xPoints = {x, e.getX(), (x + e.getX()) / 2};
                int[] yPoints = {y, y, e.getY()};
                g.fillPolygon(xPoints, yPoints, 3);
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
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        if (buffer == null) {
            buffer = createImage(drawingPanel.getWidth(), drawingPanel.getHeight());
            Graphics2D g2d = (Graphics2D) buffer.getGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
        }

        temporal = createImage(drawingPanel.getWidth(), drawingPanel.getHeight());
        Graphics2D g2 = (Graphics2D) temporal.getGraphics();
        g2.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        g.setColor(color);
        g.setStroke(stroke);

        // Definir las coordenadas de la figura
        int x1 = x;
        int y1 = y;
        int x2 = e.getX();
        int y2 = e.getY();

        switch (figura) {
            case 1: // Línea
                g.drawLine(x1, y1, x2, y2);
                figuras.add(new Figura(1, x1, y1, x2, y2, color));
                break;
            case 2: // Rectángulo
                g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                figuras.add(new Figura(2, Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), color));
                break;
            case 3: // Círculo
                int diameter = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
                g.drawOval(Math.min(x1, x2), Math.min(y1, y2), diameter, diameter);
                figuras.add(new Figura(3, Math.min(x1, x2), Math.min(y1, y2), diameter, diameter, color));
                break;
            case 6: // Rectángulo Relleno
                g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                figuras.add(new Figura(6, Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), color));
                break;
            case 7: // Círculo Relleno
                int diam = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
                g.fillOval(Math.min(x1, x2), Math.min(y1, y2), diam, diam);
                figuras.add(new Figura(7, Math.min(x1, x2), Math.min(y1, y2), diam, diam, color));
                break;
            case 8: // Cuadrado Relleno
                int side = Math.min(Math.abs(x2 - x1), Math.abs(y2 - y1));
                g.fillRect(Math.min(x1, x2), Math.min(y1, y2), side, side);
                figuras.add(new Figura(8, Math.min(x1, x2), Math.min(y1, y2), side, side, color));
                break;
            case 9: // Triángulo Relleno
                int[] xPoints = {x1, x2, (x1 + x2) / 2};
                int[] yPoints = {y1, y1, y2};
                g.fillPolygon(xPoints, yPoints, 3);
                figuras.add(new Figura(9, x1, y1, x2, y2, color));
                break;
        }

        drawingPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());
    }
    public void mostrarFiguras() {
        for (Figura figura : figuras) {
            String tipoFigura = "";
            switch (figura.tipo) {
                case 1: tipoFigura = "Línea"; break;
                case 2: tipoFigura = "Rectángulo"; break;
                case 3: tipoFigura = "Círculo"; break;
                case 6: tipoFigura = "Rectángulo Relleno"; break;
                case 7: tipoFigura = "Círculo Relleno"; break;
                case 8: tipoFigura = "Cuadrado Relleno"; break;
                case 9: tipoFigura = "Triángulo Relleno"; break;
                default: tipoFigura = "Desconocido"; break;
            }
            System.out.println(tipoFigura + " - Coordenadas: (" + figura.x1 + ", " + figura.y1 + ") a (" + figura.x2 + ", " + figura.y2 + "), Color: " + figura.color);
        }
    }

    private void TrasladarFiguras(ArrayList<Figura> figuras, int distancia) {
        for (Figura figura : figuras) {
            figura.x1 += distancia;
        }
        drawingPanel.repaint();
    }
    private void EscalarFiguras(ArrayList<Figura> figuras, int distancia) {
        for (Figura figura : figuras) {
            figura.x2 += distancia;
        }
        drawingPanel.repaint();
    }
    private void RotarFiguras(ArrayList<Figura> figuras, double angulo) {
        double radianes = Math.toRadians(angulo);

        for (Figura figura : figuras) {
            int centroX = (figura.x1 + figura.x2) / 2;
            int centroY = (figura.y1 + figura.y2) / 2;

            int x1Nuevo = (int) (centroX + (figura.x1 - centroX) * Math.cos(radianes) - (figura.y1 - centroY) * Math.sin(radianes));
            int y1Nuevo = (int) (centroY + (figura.x1 - centroX) * Math.sin(radianes) + (figura.y1 - centroY) * Math.cos(radianes));

            int x2Nuevo = (int) (centroX + (figura.x2 - centroX) * Math.cos(radianes) - (figura.y2 - centroY) * Math.sin(radianes));
            int y2Nuevo = (int) (centroY + (figura.x2 - centroX) * Math.sin(radianes) + (figura.y2 - centroY) * Math.cos(radianes));

            figura.x1 = x1Nuevo;
            figura.y1 = y1Nuevo;
            figura.x2 = x2Nuevo;
            figura.y2 = y2Nuevo;
        }

        drawingPanel.repaint();
    }

}