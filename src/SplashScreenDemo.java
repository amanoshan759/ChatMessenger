import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class SplashScreenDemo extends javax.swing.JFrame {

    public SplashScreenDemo() {
        try {
            initComponents();
            BufferedImage orgimage = ImageIO.read(new File("C:\\Users\\Akhil\\Documents\\NetBeansProjects\\chat\\src\\main.jpg"));
            BufferedImage resizedImage = resize(orgimage,400,300);
            setSize(302,300);
            //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
          
            new Thread(new progress()).start();
        } catch (IOException ex) {
            Logger.getLogger(SplashScreenDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class progress implements Runnable
    {

        @Override
        public void run() 
        {
            for (int i = 1; i <=100; i++) 
            {
                
                if(i<30)
                {
                    jLabel2.setForeground(Color.red);
                    jLabel2.setText("Initializing Components.....");
                jProgressBar1.setValue(i);
                }
                else if(i<60)
                {
                    jLabel2.setForeground(Color.blue);
                    jLabel2.setText("Loading.....");
                jProgressBar1.setValue(i);
                }
                else if(i<90)
                {
                    jLabel2.setForeground(Color.yellow);
                    jLabel2.setText("Processing.....");
                jProgressBar1.setValue(i);
                }
                else
                {
                    jLabel2.setForeground(Color.green);
                    jLabel2.setText("Launching.....");
                jProgressBar1.setValue(i);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SplashScreenDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
           new clientgui().setVisible(true);
        }
        
    }
    
    public static BufferedImage resize(BufferedImage image, int width, int height) 
    {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Gabriola", 3, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Friends talk");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(0, 255, 51));
        jProgressBar1.setBorderPainted(false);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(0, 286, 300, 10);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 250, 220, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 300, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreenDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
