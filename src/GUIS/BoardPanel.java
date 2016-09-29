package GUIS;

import Objects.Cell;
import Controllers.BoardController;
import Controllers.MainController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BoardPanel extends Observable implements Observer {

    BufferedImage BG;
    ArrayList<Integer> PointsToPaint = new ArrayList<Integer>();

    public static void main(String[] args) {
        //new BoardPanel(400,400);
    }

    public BoardPanel(int width, int height, BufferedImage img, int Rows, int Columns, BoardController BC) {
        BG = img;
        TestPane TP = new TestPane(width, height, Rows, Columns, BC);
        TestPane TP2 = new TestPane(width, height, Rows, Columns, BC);
        TP2.GameMaster = true;
        InitPanelLaunch(TP, "Board");
        InitPanelLaunch(TP2, "GameMaster");
    }

    public BoardPanel(int width, int height, BufferedImage img, BoardController BC) {
        BG = img;
        TestPane TP = new TestPane(width, height, BC);
        TestPane TP2 = new TestPane(width, height, BC);
        TP2.GameMaster = true;
        InitPanelLaunch(TP, "Board");
        InitPanelLaunch(TP2, "GameMaster");
    }

    public void InitPanelLaunch(TestPane TP, String title) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }*/
                addObserver(TP);
                JFrame frame = new JFrame(title);
                frame.setLayout(new BorderLayout());
                frame.add(TP);
//                frame.setUndecorated(true);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                if (TP.GameMaster) {
                    frame.setLocation(300, 300);
                } else {
                    frame.setLocation(350 + TP.width, 300);
                }
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        TP.BC.closeWindows();
                    }
                });
                TP.jf = frame;
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
//        Object[] nArg = (Object[]) arg;
//        String State = (String) nArg[0];
//        PointsToPaint = (ArrayList<Integer>) nArg[1];
        setChanged();
        notifyObservers(arg);
    }

    public class TestPane extends JPanel implements Observer {

        JFrame jf;
        BoardController BC;
        ArrayList<Integer> PointsToPaint = new ArrayList<Integer>();
        BoardPanel BP;
        private int columnCount;
        private int rowCount;
        private List<Cell> cells;
        private Point selectedCell;
        private Point ClickedCell;
        private int width;
        private int height;
        String State = "";
        boolean ShowUnits = true;
        boolean ShowInvisUnits = false;
        boolean SetInvisUnit = false;
        boolean GameMaster = false;

        public TestPane(int width, int height, int Rows, int Columns, BoardController BC) {
            this.width = width;
            this.height = height;
            columnCount = Columns;
            rowCount = Rows;
            this.BC = BC;
            InitPanel();
        }

        public TestPane(int width, int height, BoardController BC) {
            this.width = width;
            this.height = height;
            columnCount = 25;
            rowCount = 25;
            this.BC = BC;
            InitPanel();
        }

        public void InitPanel() {
            cells = new ArrayList<>();
            MouseAdapter mouseHandler;
            mouseHandler = new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    Point point = e.getPoint();

                    int width = getWidth();
                    int height = getHeight();

                    int cellWidth = width / columnCount;
                    int cellHeight = height / rowCount;

                    int xOffset = 0;
                    int yOffset = 0;

                    selectedCell = null;

                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;

                    if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {

                        selectedCell = new Point(column, row);

                    }
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    int width = getWidth();
                    int height = getHeight();

                    int cellWidth = width / columnCount;
                    int cellHeight = height / rowCount;

                    int xOffset = 0;
                    int yOffset = 0;

                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;

                    if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
                        switch (e.getButton()) {
                            case 1:
                                Cell c = new Cell();
                                c.setPlace(selectedCell.x + (selectedCell.y * columnCount));
                                c.setColor(BC.CurrUnitColor);
                                if (SetInvisUnit) {
                                    c.setColor(c.getColor().brighter().brighter().brighter());
                                    BC.InvisUnits.add(c);
                                } else {
                                    BC.Units.add(c);
                                }
                                break;
                            case 2: {
                                Integer i = selectedCell.x + (selectedCell.y * columnCount);
                                Cell curr = BC.FindUnitByIndex(i);
                                BC.CurrUnitColor = curr.getColor();
                                BC.changeCurrUnitColor();
                                break;
                            }
                            case 3: {
                                Integer i = selectedCell.x + (selectedCell.y * columnCount);
                                BC.RemoveUnitByIndex(i);
                                break;
                            }
                            default:
                                break;
                        }

                    }
                }
            };
            addMouseMotionListener(mouseHandler);
            addMouseListener(mouseHandler);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }

        @Override
        public void invalidate() {
            cells.clear();
            selectedCell = null;
            super.invalidate();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            g.drawImage(BG, 0, 0, null);

            int width = getWidth();
            int height = getHeight();

            int cellWidth = width / columnCount;
            int cellHeight = height / rowCount;

            int xOffset = (width - (columnCount * cellWidth)) / 2;
            int yOffset = (height - (rowCount * cellHeight)) / 2;

            if (cells.isEmpty()) {
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < columnCount; col++) {
                        Rectangle rec = new Rectangle(
                                xOffset + (col * cellWidth),
                                yOffset + (row * cellHeight),
                                cellWidth,
                                cellHeight
                        );
                        Cell c = new Cell();
                        c.setR(rec);
                        cells.add(c);
                    }
                }
            }

            if (ShowUnits || GameMaster) {
                for (int i = 0; i < BC.Units.size(); i++) {
                    Rectangle cell = cells.get(BC.Units.get(i).getPlace()).getR();
                    g2d.setColor(BC.Units.get(i).getColor());
                    g2d.fill(cell);
                }
            }

            if (ShowInvisUnits || GameMaster) {
                for (int i = 0; i < BC.InvisUnits.size(); i++) {
                    Rectangle cell = cells.get(BC.InvisUnits.get(i).getPlace()).getR();
                    g2d.setColor(BC.InvisUnits.get(i).getColor());
                    g2d.fill(cell);
                }
            }

            if (selectedCell != null) {

                int index = selectedCell.x + (selectedCell.y * columnCount);
                Rectangle cell = cells.get(index).getR();
                g2d.setColor(Color.GRAY);
                g2d.fill(cell);

                if (State == "Ring") {
                    for (int i = 0; i < PointsToPaint.size(); i++) {
                        int j = PointsToPaint.get(i);
                        try {
                            cell = cells.get(index + j).getR();
                        } catch (Exception e) {
                        }
                        g2d.setColor(Color.RED);
                        g2d.fill(cell);
                    }
                }

            }

            g2d.setColor(Color.GRAY);
            for (Cell c : cells) {
                g2d.draw(c.getR());
            }

            g2d.dispose();
        }

        @Override
        public void update(Observable o, Object arg) {
            Object[] nArg = (Object[]) arg;
            State = (String) nArg[0];
            PointsToPaint = (ArrayList<Integer>) nArg[1];
            StateHandler(State);
        }

        public void StateHandler(String state) {
            switch (state) {
                case "Units Check":
                    ShowUnits = !ShowUnits;
                    break;
                case "Invis Units Check":
                    ShowInvisUnits = !ShowInvisUnits;
                    break;
                case "Set Invis Units Check":
                    SetInvisUnit = !SetInvisUnit;
                    break;
                case "Close":
                    jf.setVisible(false);
                    jf.dispose();
                    break;
            }
        }
    }
}
