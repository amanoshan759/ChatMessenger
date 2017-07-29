import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class clientgui extends javax.swing.JFrame {
    String server_ip;
    searchfriends sf;
    client cl;
    SignUp su;
    LogIn li;
    String username, search, chat_with;
    ArrayList<search> al_search = new ArrayList<>();
    TableModel mtm;
    TableModel2 mtm_onlinef;
    TableModel3 mtm_pending;
    ArrayList<online_friends> al_onlinef = new ArrayList<>();
    online_friends of;
    changepassword cp;
    chatframe cf;
    pendingfriends pf;
    ArrayList<pending_friends> al_pending = new ArrayList<>();
    String pendingfriend;
    ArrayList<chatframe> al_chat = new ArrayList<>();

    public clientgui() {
        initComponents();

        
        setSize(415,410);
        setVisible(true);
        
            //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
        bt_signup.setEnabled(false);
        bt_login.setEnabled(false);
        bt_connect.setEnabled(true);
        bt_search.setEnabled(false);
        bt_getlist.setEnabled(false);
        bt_start.setEnabled(false);
        bt_change.setEnabled(false);
        bt_pending.setEnabled(false);
        mtm_onlinef = new TableModel2();
        JT_getlist.setModel(mtm_onlinef);
    }

    public class LogIn extends javax.swing.JFrame {

        public LogIn() {
            initComponents();
            setSize(360,270);
            setVisible(true);
              //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
           
        }

        @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_inusername = new javax.swing.JTextField();
        pf_inpassword = new javax.swing.JPasswordField();
        bt_submit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Gabriola", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("UserName");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 50, 130, 41);

        jLabel2.setFont(new java.awt.Font("Gabriola", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 90, 130, 41);

        jLabel3.setFont(new java.awt.Font("Gabriola", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("LOGIN");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, -10, 130, 62);

        tf_inusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_inusernameActionPerformed(evt);
            }
        });
        getContentPane().add(tf_inusername);
        tf_inusername.setBounds(180, 50, 150, 30);

        pf_inpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_inpasswordActionPerformed(evt);
            }
        });
        getContentPane().add(pf_inpassword);
        pf_inpassword.setBounds(180, 100, 150, 30);

        bt_submit.setBackground(new java.awt.Color(0, 0, 0));
        bt_submit.setForeground(new java.awt.Color(255, 255, 255));
        bt_submit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconsign.png"))); // NOI18N
        bt_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_submitActionPerformed(evt);
            }
        });
        bt_submit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_submitKeyPressed(evt);
            }
        });
        getContentPane().add(bt_submit);
        bt_submit.setBounds(145, 160, 50, 40);

        jLabel4.setFont(new java.awt.Font("Gabriola", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 400, 300);

        pack();
    }// </editor-fold>                        

        private void bt_submitActionPerformed(java.awt.event.ActionEvent evt) {
            try {
                username = tf_inusername.getText();
                String password = pf_inpassword.getText();
                if (username.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");

                } else {
                    cl.dos.writeBytes("login\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
         private void pf_inpasswordActionPerformed(java.awt.event.ActionEvent evt) {                                              
    
try {
                username = tf_inusername.getText();
                String password = pf_inpassword.getText();
                if (username.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");

                } else {
                    cl.dos.writeBytes("login\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }                                             

    private void tf_inusernameActionPerformed(java.awt.event.ActionEvent evt) {                                              
     try {
                username = tf_inusername.getText();
                String password = pf_inpassword.getText();
                if (username.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");

                } else {
                    cl.dos.writeBytes("login\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

    }                                             

    private void bt_submitKeyPressed(java.awt.event.KeyEvent evt) {                                     
         if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
             try {
                username = tf_inusername.getText();
                String password = pf_inpassword.getText();
                if (username.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");

                } else {
                    cl.dos.writeBytes("login\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }                                    




    private javax.swing.JButton bt_submit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pf_inpassword;
    private javax.swing.JTextField tf_inusername;
    // End of variables declaration               
    }

    public class SignUp extends javax.swing.JFrame {

        public SignUp() {

            initComponents();
            setSize(400, 340);
            setVisible(true);
              //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
        }

        @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_upusername = new javax.swing.JTextField();
        tf_name = new javax.swing.JTextField();
        tf_mobile = new javax.swing.JTextField();
        pf_uppassword = new javax.swing.JPasswordField();
        pf_cpassword = new javax.swing.JPasswordField();
        bt_upsubmit = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Gabriola", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setText("SIGN UP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 0, 170, 44);

        jLabel2.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UserName");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 50, 130, 20);

        jLabel3.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 80, 50, 41);

        jLabel4.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 120, 90, 41);

        jLabel5.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Confirm Password");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 160, 160, 41);

        jLabel6.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mobile No.");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 210, 91, 41);

        tf_upusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_upusernameActionPerformed(evt);
            }
        });
        getContentPane().add(tf_upusername);
        tf_upusername.setBounds(170, 50, 210, 30);

        tf_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nameActionPerformed(evt);
            }
        });
        getContentPane().add(tf_name);
        tf_name.setBounds(170, 90, 210, 30);

        tf_mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_mobileActionPerformed(evt);
            }
        });
        getContentPane().add(tf_mobile);
        tf_mobile.setBounds(170, 210, 210, 30);

        pf_uppassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_uppasswordActionPerformed(evt);
            }
        });
        getContentPane().add(pf_uppassword);
        pf_uppassword.setBounds(170, 130, 210, 30);

        pf_cpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_cpasswordActionPerformed(evt);
            }
        });
        getContentPane().add(pf_cpassword);
        pf_cpassword.setBounds(170, 170, 210, 30);

        bt_upsubmit.setBackground(new java.awt.Color(0, 0, 0));
        bt_upsubmit.setForeground(new java.awt.Color(255, 255, 255));
        bt_upsubmit.setText("Submit");
        bt_upsubmit.setBorderPainted(false);
        bt_upsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_upsubmitActionPerformed(evt);
            }
        });
        bt_upsubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_upsubmitKeyPressed(evt);
            }
        });
        getContentPane().add(bt_upsubmit);
        bt_upsubmit.setBounds(60, 260, 90, 30);

        bt_cancel.setBackground(new java.awt.Color(0, 0, 0));
        bt_cancel.setForeground(new java.awt.Color(255, 255, 255));
        bt_cancel.setText("Cancel");
        bt_cancel.setBorderPainted(false);
        getContentPane().add(bt_cancel);
        bt_cancel.setBounds(190, 260, 100, 30);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 400, 310);

        pack();
    }// </editor-fold>                       

        private void bt_upsubmitActionPerformed(java.awt.event.ActionEvent evt) {
            try {
                String username = tf_upusername.getText();
                String name = tf_name.getText();
                String password = pf_uppassword.getText();
                String cpassword = pf_cpassword.getText();
                String mobile = tf_mobile.getText();

                if (username.equals("") || name.equals("") || password.equals("") || cpassword.equals("") || mobile.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");
                } else if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords didn't match");
                } else {
                    cl.dos.writeBytes("signup\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(name + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(mobile + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         private void bt_upsubmitKeyPressed(java.awt.event.KeyEvent evt) {                                       
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
          try {
                String username = tf_upusername.getText();
                String name = tf_name.getText();
                String password = pf_uppassword.getText();
                String cpassword = pf_cpassword.getText();
                String mobile = tf_mobile.getText();

                if (username.equals("") || name.equals("") || password.equals("") || cpassword.equals("") || mobile.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");
                } else if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords didn't match");
                } else {
                    cl.dos.writeBytes("signup\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(name + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(mobile + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
          private void tf_mobileActionPerformed(java.awt.event.ActionEvent evt) {                                          
                try {
                String username = tf_upusername.getText();
                String name = tf_name.getText();
                String password = pf_uppassword.getText();
                String cpassword = pf_cpassword.getText();
                String mobile = tf_mobile.getText();

                if (username.equals("") || name.equals("") || password.equals("") || cpassword.equals("") || mobile.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");
                } else if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords didn't match");
                } else {
                    cl.dos.writeBytes("signup\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(name + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(mobile + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
            }
                                               
  
        
    }
            private void tf_upusernameActionPerformed(java.awt.event.ActionEvent evt) {                                              
     try {
                String username = tf_upusername.getText();
                String name = tf_name.getText();
                String password = pf_uppassword.getText();
                String cpassword = pf_cpassword.getText();
                String mobile = tf_mobile.getText();

                if (username.equals("") || name.equals("") || password.equals("") || cpassword.equals("") || mobile.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");
                } else if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords didn't match");
                } else {
                    cl.dos.writeBytes("signup\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(name + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(mobile + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
             private void tf_nameActionPerformed(java.awt.event.ActionEvent evt) {                                        
  try {
                String username = tf_upusername.getText();
                String name = tf_name.getText();
                String password = pf_uppassword.getText();
                String cpassword = pf_cpassword.getText();
                String mobile = tf_mobile.getText();

                if (username.equals("") || name.equals("") || password.equals("") || cpassword.equals("") || mobile.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");
                } else if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords didn't match");
                } else {
                    cl.dos.writeBytes("signup\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(name + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(mobile + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }                                       
             private void pf_uppasswordActionPerformed(java.awt.event.ActionEvent evt) {                                              
     try {
                String username = tf_upusername.getText();
                String name = tf_name.getText();
                String password = pf_uppassword.getText();
                String cpassword = pf_cpassword.getText();
                String mobile = tf_mobile.getText();

                if (username.equals("") || name.equals("") || password.equals("") || cpassword.equals("") || mobile.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");
                } else if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords didn't match");
                } else {
                    cl.dos.writeBytes("signup\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(name + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(mobile + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
             private void pf_cpasswordActionPerformed(java.awt.event.ActionEvent evt) {                                             
     try {
                String username = tf_upusername.getText();
                String name = tf_name.getText();
                String password = pf_uppassword.getText();
                String cpassword = pf_cpassword.getText();
                String mobile = tf_mobile.getText();

                if (username.equals("") || name.equals("") || password.equals("") || cpassword.equals("") || mobile.equals("")) {
                    JOptionPane.showMessageDialog(this, "Every field is mandatory");
                } else if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords didn't match");
                } else {
                    cl.dos.writeBytes("signup\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(name + "\r\n");
                    cl.dos.writeBytes(password + "\r\n");
                    cl.dos.writeBytes(mobile + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }          
        

          // Variables declaration - do not modify                     
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_upsubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField pf_cpassword;
    private javax.swing.JPasswordField pf_uppassword;
    private javax.swing.JTextField tf_mobile;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_upusername;
    // End of variables declaration                                
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_connect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bt_login = new javax.swing.JButton();
        bt_signup = new javax.swing.JButton();
        bt_search = new javax.swing.JButton();
        bt_getlist = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JT_getlist = new javax.swing.JTable();
        bt_change = new javax.swing.JButton();
        bt_start = new javax.swing.JButton();
        bt_pending = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        bt_connect.setBackground(new java.awt.Color(0, 0, 0));
        bt_connect.setForeground(new java.awt.Color(255, 255, 255));
        bt_connect.setText("Connect to Server");
        bt_connect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_connectActionPerformed(evt);
            }
        });
        getContentPane().add(bt_connect);
        bt_connect.setBounds(10, 50, 370, 30);

        jLabel1.setFont(new java.awt.Font("Gabriola", 3, 36)); // NOI18N
        jLabel1.setText("                Menu");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 350, 20);

        bt_login.setBackground(new java.awt.Color(0, 0, 0));
        bt_login.setForeground(new java.awt.Color(255, 255, 255));
        bt_login.setText("LOGIN");
        bt_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_loginActionPerformed(evt);
            }
        });
        getContentPane().add(bt_login);
        bt_login.setBounds(190, 90, 90, 30);

        bt_signup.setBackground(new java.awt.Color(0, 0, 0));
        bt_signup.setForeground(new java.awt.Color(255, 255, 255));
        bt_signup.setText("SIGN UP");
        bt_signup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_signupActionPerformed(evt);
            }
        });
        getContentPane().add(bt_signup);
        bt_signup.setBounds(290, 90, 90, 30);

        bt_search.setBackground(new java.awt.Color(0, 0, 0));
        bt_search.setForeground(new java.awt.Color(255, 255, 255));
        bt_search.setText("Search Friends");
        bt_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_searchActionPerformed(evt);
            }
        });
        getContentPane().add(bt_search);
        bt_search.setBounds(10, 90, 170, 30);

        bt_getlist.setBackground(new java.awt.Color(0, 0, 0));
        bt_getlist.setForeground(new java.awt.Color(255, 255, 255));
        bt_getlist.setText("Get List");
        bt_getlist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_getlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_getlistActionPerformed(evt);
            }
        });
        getContentPane().add(bt_getlist);
        bt_getlist.setBounds(10, 170, 180, 30);

        JT_getlist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(JT_getlist);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 210, 370, 150);

        bt_change.setBackground(new java.awt.Color(0, 0, 0));
        bt_change.setForeground(new java.awt.Color(255, 255, 255));
        bt_change.setText("Change Password");
        bt_change.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_changeActionPerformed(evt);
            }
        });
        getContentPane().add(bt_change);
        bt_change.setBounds(10, 130, 180, 30);

        bt_start.setBackground(new java.awt.Color(0, 0, 0));
        bt_start.setForeground(new java.awt.Color(255, 255, 255));
        bt_start.setText("Start Chat");
        bt_start.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_startActionPerformed(evt);
            }
        });
        getContentPane().add(bt_start);
        bt_start.setBounds(210, 130, 170, 30);

        bt_pending.setBackground(new java.awt.Color(0, 0, 0));
        bt_pending.setForeground(new java.awt.Color(255, 255, 255));
        bt_pending.setText("Pending Requests");
        bt_pending.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_pending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pendingActionPerformed(evt);
            }
        });
        getContentPane().add(bt_pending);
        bt_pending.setBounds(210, 170, 170, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 400, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public class searchfriends extends javax.swing.JFrame {

        public searchfriends() {
            initComponents();
            setSize(450, 410);
            setVisible(true);
              //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
            mtm = new TableModel();
            JT.setModel(mtm);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_search = new javax.swing.JTextField();
        bt_search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JT = new javax.swing.JTable();
        bt_send = new javax.swing.JButton();
        bt_reject = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Search");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 0, 80, 22);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setText("Enter Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 50, 90, 17);

        tf_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_searchActionPerformed(evt);
            }
        });
        getContentPane().add(tf_search);
        tf_search.setBounds(110, 50, 190, 30);

        bt_search.setBackground(new java.awt.Color(0, 0, 0));
        bt_search.setForeground(new java.awt.Color(255, 255, 255));
        bt_search.setText("Search");
        bt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_searchActionPerformed(evt);
            }
        });
        bt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_searchKeyPressed(evt);
            }
        });
        getContentPane().add(bt_search);
        bt_search.setBounds(320, 50, 100, 30);

        jScrollPane1.setViewportView(JT);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 380, 170);

        bt_send.setBackground(new java.awt.Color(0, 0, 0));
        bt_send.setForeground(new java.awt.Color(255, 255, 255));
        bt_send.setText("Send Request");
        bt_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sendActionPerformed(evt);
            }
        });
        getContentPane().add(bt_send);
        bt_send.setBounds(20, 320, 160, 30);

        bt_reject.setBackground(new java.awt.Color(0, 0, 0));
        bt_reject.setForeground(new java.awt.Color(255, 255, 255));
        bt_reject.setText("Unfriend");
        bt_reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_rejectActionPerformed(evt);
            }
        });
        getContentPane().add(bt_reject);
        bt_reject.setBounds(200, 320, 170, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 440, 370);

        pack();
    }// </editor-fold>                                

        private void bt_searchActionPerformed(java.awt.event.ActionEvent evt) {
            String searchf = tf_search.getText();
            search = searchf;
            if (searchf.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter some text to search");
            } else {
                try {
                    cl.dos.writeBytes("searchfriends\r\n");
                    cl.dos.writeBytes(searchf + "\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

        private void bt_sendActionPerformed(java.awt.event.ActionEvent evt) {
            String status = al_search.get(JT.getSelectedRow()).status;
            if (JT.getSelectedRow() == -1) {

            }
            if (status.equals("pending")) {
                JOptionPane.showMessageDialog(null, "There is already request pending");
            } else if (status.equals("friends")) {
                JOptionPane.showMessageDialog(null, "Requests cant be sent to friends");
            } else if (status.equals("recieved request")) {
                JOptionPane.showMessageDialog(null, "Already,A request is recieved from the selected user");
            } else {
                try {
                    cl.dos.writeBytes("send request\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(al_search.get(JT.getSelectedRow()).username + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void bt_rejectActionPerformed(java.awt.event.ActionEvent evt) 
        {
        String status = al_search.get(JT.getSelectedRow()).status;
            if (JT.getSelectedRow() == -1) {

            }
            if (status.equals("pending")) {
                JOptionPane.showMessageDialog(null, "You are not friends with this user");
            }  else if (status.equals("recieved request")) {
                JOptionPane.showMessageDialog(null, "You are not friends with this user");
            } else {
                try {
                    cl.dos.writeBytes("unfriend\r\n");
                    cl.dos.writeBytes(username+"\r\n");
                    cl.dos.writeBytes(al_search.get(JT.getSelectedRow()).username + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
          private void bt_searchKeyPressed(java.awt.event.KeyEvent evt) {                                     
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
              String searchf = tf_search.getText();
            search = searchf;
            if (searchf.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter some text to search");
            } else {
                try {
                    cl.dos.writeBytes("searchfriends\r\n");
                    cl.dos.writeBytes(searchf + "\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
           private void tf_searchActionPerformed(java.awt.event.ActionEvent evt) {                                          
          String searchf = tf_search.getText();
            search = searchf;
            if (searchf.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter some text to search");
            } else {
                try {
                    cl.dos.writeBytes("searchfriends\r\n");
                    cl.dos.writeBytes(searchf + "\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
    } 
         // Variables declaration - do not modify                     
    private javax.swing.JTable JT;
    private javax.swing.JButton bt_reject;
    private javax.swing.JButton bt_search;
    private javax.swing.JButton bt_send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tf_search;
    // End of variables declaration                        
    }

    public class changepassword extends javax.swing.JFrame {
    String temp=username;
        public changepassword() {
            initComponents();
            setSize(450, 350);
              //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
        }

        @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_cusername = new javax.swing.JTextField();
        pf_cold = new javax.swing.JPasswordField();
        pf_cnew = new javax.swing.JPasswordField();
        pf_cconfirm = new javax.swing.JPasswordField();
        bt_cok = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Gabriola", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Change Password");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 10, 260, 40);

        jLabel2.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 64, 100, 20);

        jLabel3.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Old Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 110, 130, 20);

        jLabel4.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("New Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 160, 130, 20);

        jLabel5.setFont(new java.awt.Font("Gabriola", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Confirm Password");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 210, 170, 20);

        tf_cusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cusernameActionPerformed(evt);
            }
        });
        getContentPane().add(tf_cusername);
        tf_cusername.setBounds(190, 60, 180, 30);

        pf_cold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_coldActionPerformed(evt);
            }
        });
        getContentPane().add(pf_cold);
        pf_cold.setBounds(190, 110, 180, 30);

        pf_cnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_cnewActionPerformed(evt);
            }
        });
        getContentPane().add(pf_cnew);
        pf_cnew.setBounds(190, 160, 180, 30);

        pf_cconfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_cconfirmActionPerformed(evt);
            }
        });
        getContentPane().add(pf_cconfirm);
        pf_cconfirm.setBounds(190, 210, 180, 30);

        bt_cok.setBackground(new java.awt.Color(0, 0, 0));
        bt_cok.setForeground(new java.awt.Color(255, 255, 255));
        bt_cok.setText("OK");
        bt_cok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cokActionPerformed(evt);
            }
        });
        bt_cok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_cokKeyPressed(evt);
            }
        });
        getContentPane().add(bt_cok);
        bt_cok.setBounds(153, 260, 110, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 0, 500, 340);

        pack();
    }// </editor-fold>                     

        private void bt_cokActionPerformed(java.awt.event.ActionEvent evt) {
            String username = tf_cusername.getText();
            String oldpassword = pf_cold.getText();
            String newpassword = pf_cnew.getText();
            String confirmpassword = pf_cconfirm.getText();
            if(!temp.equals(username))
            {
                JOptionPane.showMessageDialog(null,"Username Incorrect");
            }
            else if (username.equals("") || oldpassword.equals("") || newpassword.equals("") || confirmpassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Every field is mandatory");
            } else if (!newpassword.equals(confirmpassword)) {
                JOptionPane.showMessageDialog(null, "Passwords didn't match");
            } else {
                try {
                    cl.dos.writeBytes("change\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(oldpassword + "\r\n");
                    cl.dos.writeBytes(newpassword + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
          private void pf_cconfirmActionPerformed(java.awt.event.ActionEvent evt) {                                            
    String username = tf_cusername.getText();
            String oldpassword = pf_cold.getText();
            String newpassword = pf_cnew.getText();
            String confirmpassword = pf_cconfirm.getText();
            if(!temp.equals(username))
            {
                JOptionPane.showMessageDialog(null,"Username Incorrect");
            }
            else if (username.equals("") || oldpassword.equals("") || newpassword.equals("") || confirmpassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Every field is mandatory");
            } else if (!newpassword.equals(confirmpassword)) {
                JOptionPane.showMessageDialog(null, "Passwords didn't match");
            } else {
                try {
                    cl.dos.writeBytes("change\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(oldpassword + "\r\n");
                    cl.dos.writeBytes(newpassword + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }       
    }
           private void bt_cokKeyPressed(java.awt.event.KeyEvent evt) {                                  
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
       String username = tf_cusername.getText();
            String oldpassword = pf_cold.getText();
            String newpassword = pf_cnew.getText();
            String confirmpassword = pf_cconfirm.getText();
            if(!temp.equals(username))
            {
                JOptionPane.showMessageDialog(null,"Username Incorrect");
            }
            else if (username.equals("") || oldpassword.equals("") || newpassword.equals("") || confirmpassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Every field is mandatory");
            } else if (!newpassword.equals(confirmpassword)) {
                JOptionPane.showMessageDialog(null, "Passwords didn't match");
            } else {
                try {
                    cl.dos.writeBytes("change\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(oldpassword + "\r\n");
                    cl.dos.writeBytes(newpassword + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }                                 

    private void tf_cusernameActionPerformed(java.awt.event.ActionEvent evt) {                                             
         String username = tf_cusername.getText();
            String oldpassword = pf_cold.getText();
            String newpassword = pf_cnew.getText();
            String confirmpassword = pf_cconfirm.getText();
            if(!temp.equals(username))
            {
                JOptionPane.showMessageDialog(null,"Username Incorrect");
            }
            else if (username.equals("") || oldpassword.equals("") || newpassword.equals("") || confirmpassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Every field is mandatory");
            } else if (!newpassword.equals(confirmpassword)) {
                JOptionPane.showMessageDialog(null, "Passwords didn't match");
            } else {
                try {
                    cl.dos.writeBytes("change\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(oldpassword + "\r\n");
                    cl.dos.writeBytes(newpassword + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
    }                                            

    private void pf_coldActionPerformed(java.awt.event.ActionEvent evt) {                                        
        String username = tf_cusername.getText();
            String oldpassword = pf_cold.getText();
            String newpassword = pf_cnew.getText();
            String confirmpassword = pf_cconfirm.getText();
            if(!temp.equals(username))
            {
                JOptionPane.showMessageDialog(null,"Username Incorrect");
            }
            else if (username.equals("") || oldpassword.equals("") || newpassword.equals("") || confirmpassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Every field is mandatory");
            } else if (!newpassword.equals(confirmpassword)) {
                JOptionPane.showMessageDialog(null, "Passwords didn't match");
            } else {
                try {
                    cl.dos.writeBytes("change\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(oldpassword + "\r\n");
                    cl.dos.writeBytes(newpassword + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }     
    }                                       

    private void pf_cnewActionPerformed(java.awt.event.ActionEvent evt) {                                        
            String username = tf_cusername.getText();
            String oldpassword = pf_cold.getText();
            String newpassword = pf_cnew.getText();
            String confirmpassword = pf_cconfirm.getText();
            if(!temp.equals(username))
            {
                JOptionPane.showMessageDialog(null,"Username Incorrect");
            }
            else if (username.equals("") || oldpassword.equals("") || newpassword.equals("") || confirmpassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Every field is mandatory");
            } else if (!newpassword.equals(confirmpassword)) {
                JOptionPane.showMessageDialog(null, "Passwords didn't match");
            } else {
                try {
                    cl.dos.writeBytes("change\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(oldpassword + "\r\n");
                    cl.dos.writeBytes(newpassword + "\r\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
    }                                       

        

         // Variables declaration - do not modify                     
    private javax.swing.JButton bt_cok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField pf_cconfirm;
    private javax.swing.JPasswordField pf_cnew;
    private javax.swing.JPasswordField pf_cold;
    private javax.swing.JTextField tf_cusername;
    // End of variables declaration                   
    }

    public class chatframe extends javax.swing.JFrame implements WindowListener
    {

        String fname,mychat="";
        File f;
        public chatframe(String fname) {
            initComponents();
            jpb.setStringPainted(true);
            bt_fsend.setEnabled(false);
            this.fname = fname;
            addWindowListener(this);
            ep_chat.setEditable(false);
            ep_chat.setFocusable(false);
            ep_chat.setContentType("text/html");
            setSize(430,477);
            //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
            
            lb_friendname.setText(fname);
            try {
                cl.dos.writeBytes("send previous chat\r\n");
                cl.dos.writeBytes(username+"\r\n");
                cl.dos.writeBytes(fname+"\r\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @SuppressWarnings("unchecked")
           // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lb_friendname = new javax.swing.JLabel();
        tf_msg = new javax.swing.JTextField();
        bt_chatsend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ep_chat = new javax.swing.JEditorPane();
        bt1 = new javax.swing.JButton();
        bt2 = new javax.swing.JButton();
        bt3 = new javax.swing.JButton();
        bt4 = new javax.swing.JButton();
        bt5 = new javax.swing.JButton();
        bt_browse = new javax.swing.JButton();
        bt_fsend = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jpb = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 0, 51));
        jLabel1.setFont(new java.awt.Font("Gabriola", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Chat With");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 0, 160, 62);

        lb_friendname.setFont(new java.awt.Font("Gabriola", 3, 36)); // NOI18N
        lb_friendname.setForeground(new java.awt.Color(0, 0, 255));
        lb_friendname.setText("Name");
        getContentPane().add(lb_friendname);
        lb_friendname.setBounds(230, 10, 150, 40);

        tf_msg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_msgActionPerformed(evt);
            }
        });
        getContentPane().add(tf_msg);
        tf_msg.setBounds(30, 223, 260, 30);

        bt_chatsend.setBackground(new java.awt.Color(0, 0, 0));
        bt_chatsend.setForeground(new java.awt.Color(255, 255, 255));
        bt_chatsend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.png"))); // NOI18N
        bt_chatsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_chatsendActionPerformed(evt);
            }
        });
        bt_chatsend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_chatsendKeyPressed(evt);
            }
        });
        getContentPane().add(bt_chatsend);
        bt_chatsend.setBounds(310, 217, 50, 40);

        jScrollPane2.setViewportView(ep_chat);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 60, 380, 140);

        bt1.setBackground(new java.awt.Color(0, 0, 0));
        bt1.setForeground(new java.awt.Color(255, 255, 255));
        bt1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture2.png"))); // NOI18N
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });
        getContentPane().add(bt1);
        bt1.setBounds(30, 280, 50, 40);

        bt2.setBackground(new java.awt.Color(0, 0, 0));
        bt2.setForeground(new java.awt.Color(255, 255, 255));
        bt2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture3.png"))); // NOI18N
        bt2.setToolTipText("");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt2ActionPerformed(evt);
            }
        });
        getContentPane().add(bt2);
        bt2.setBounds(100, 280, 50, 40);

        bt3.setBackground(new java.awt.Color(0, 0, 0));
        bt3.setForeground(new java.awt.Color(255, 255, 255));
        bt3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture4.png"))); // NOI18N
        bt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt3ActionPerformed(evt);
            }
        });
        getContentPane().add(bt3);
        bt3.setBounds(170, 280, 50, 40);

        bt4.setBackground(new java.awt.Color(0, 0, 0));
        bt4.setForeground(new java.awt.Color(255, 255, 255));
        bt4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture5.png"))); // NOI18N
        bt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt4ActionPerformed(evt);
            }
        });
        getContentPane().add(bt4);
        bt4.setBounds(240, 280, 50, 40);

        bt5.setBackground(new java.awt.Color(0, 0, 0));
        bt5.setForeground(new java.awt.Color(255, 255, 255));
        bt5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture6.png"))); // NOI18N
        bt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt5ActionPerformed(evt);
            }
        });
        getContentPane().add(bt5);
        bt5.setBounds(310, 280, 50, 40);

        bt_browse.setBackground(new java.awt.Color(0, 0, 0));
        bt_browse.setForeground(new java.awt.Color(255, 255, 255));
        bt_browse.setText("Browse...");
        bt_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_browseActionPerformed(evt);
            }
        });
        getContentPane().add(bt_browse);
        bt_browse.setBounds(20, 345, 150, 28);

        bt_fsend.setBackground(new java.awt.Color(0, 0, 0));
        bt_fsend.setForeground(new java.awt.Color(255, 255, 255));
        bt_fsend.setText("Send");
        bt_fsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_fsendActionPerformed(evt);
            }
        });
        getContentPane().add(bt_fsend);
        bt_fsend.setBounds(233, 345, 150, 28);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Path....");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 390, 380, 30);

        jpb.setBackground(new java.awt.Color(0, 0, 0));
        jpb.setForeground(new java.awt.Color(255, 153, 51));
        jpb.setRequestFocusEnabled(false);
        getContentPane().add(jpb);
        jpb.setBounds(16, 420, 380, 10);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 420, 440);

        pack();
    }// </editor-fold>                          
                    

        private void bt_chatsendActionPerformed(java.awt.event.ActionEvent evt) 
        {
            String message = tf_msg.getText();
            if (message.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter message");
            } else {
                try {
                    cl.dos.writeBytes("send message\r\n");
                    cl.dos.writeBytes("send text\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(fname + "\r\n");
                    cl.dos.writeBytes(message + "\r\n");
                    mychat += "<div align='right'>" + message + "</div>";
                    ep_chat.setText(mychat);
                    tf_msg.setText("");

                } catch (Exception ex) {
                   ex.printStackTrace();
                }
            }
        }

        private void tf_msgActionPerformed(java.awt.event.ActionEvent evt) {
             String message = tf_msg.getText();
            if (message.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter message");
            } else {
                try {
                    cl.dos.writeBytes("send message\r\n");
                    cl.dos.writeBytes("send text\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(fname + "\r\n");
                    cl.dos.writeBytes(message + "\r\n");
                    mychat += "<div align='right'>" + message + "</div>";
                    ep_chat.setText(mychat);
                    tf_msg.setText("");

                } catch (Exception ex) {
                   ex.printStackTrace();
                }
            }
        }
         private void bt_chatsendKeyPressed(java.awt.event.KeyEvent evt) {                                       
       if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
       String message = tf_msg.getText();
            if (message.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter message");
            } else {
                try {
                    cl.dos.writeBytes("send message\r\n");
                    cl.dos.writeBytes("send text\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                    cl.dos.writeBytes(fname + "\r\n");
                    cl.dos.writeBytes(message + "\r\n");
                    mychat += "<div align='right'>" + message + "</div>";
                    ep_chat.setText(mychat);
                    tf_msg.setText("");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }  
       }
    }  

        private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {
            try {
                cl.dos.writeBytes("send message\r\n");
                cl.dos.writeBytes("sending sticker\r\n");
                cl.dos.writeBytes(username+"\r\n");
                cl.dos.writeBytes(fname+"\r\n");
                cl.dos.writeBytes("//s2\r\n");
                 mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture3.png\" ></div>";
                ep_chat.setText(mychat);
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }

        private void bt1ActionPerformed(java.awt.event.ActionEvent evt)
        {
            try {
                cl.dos.writeBytes("send message\r\n");
                cl.dos.writeBytes("sending sticker\r\n");cl.dos.writeBytes(username+"\r\n");
                cl.dos.writeBytes(fname+"\r\n");
                cl.dos.writeBytes("//s1\r\n");
                 mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture2.png\" ></div>";
                ep_chat.setText(mychat);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
          
        }

        private void bt3ActionPerformed(java.awt.event.ActionEvent evt) {
          try {
                cl.dos.writeBytes("send message\r\n");
                cl.dos.writeBytes("sending sticker\r\n");
                cl.dos.writeBytes(username+"\r\n");
                cl.dos.writeBytes(fname+"\r\n");
                cl.dos.writeBytes("//s3\r\n");
                mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture4.png\" ></div>";
                ep_chat.setText(mychat);
            } catch (Exception ex) {
              ex.printStackTrace();
            }
        }

        private void bt4ActionPerformed(java.awt.event.ActionEvent evt) {
            try {
                cl.dos.writeBytes("send message\r\n");
                cl.dos.writeBytes("sending sticker\r\n");
                cl.dos.writeBytes(username+"\r\n");
                cl.dos.writeBytes(fname+"\r\n");
                cl.dos.writeBytes("//s4\r\n");
                 mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture5.png\" ></div>";
                ep_chat.setText(mychat);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void bt5ActionPerformed(java.awt.event.ActionEvent evt) {
             try {
                cl.dos.writeBytes("send message\r\n");
                cl.dos.writeBytes("sending sticker\r\n");
                cl.dos.writeBytes(username+"\r\n");
                cl.dos.writeBytes(fname+"\r\n");
                cl.dos.writeBytes("//s5\r\n");
                mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture6.png\" ></div>";
                ep_chat.setText(mychat);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        private void bt_browseActionPerformed(java.awt.event.ActionEvent evt) 
        {
        JFileChooser jfc=new JFileChooser("C:\\");
        int ans=jfc.showOpenDialog(this);
        if(ans==JFileChooser.APPROVE_OPTION)
        {
            f=jfc.getSelectedFile();
            jLabel2.setText(f.getPath());
        }
        bt_fsend.setEnabled(true);
        }                                         

    private void bt_fsendActionPerformed(java.awt.event.ActionEvent evt) 
        {                                         
            try {
                FileInputStream fis=new FileInputStream(f);
                cl.dos.writeBytes("recieve file\r\n");
                cl.dos.writeBytes(fname+"\r\n");
                cl.dos.writeBytes(f.getName()+"\r\n");
                cl.dos.writeLong(f.length());
                byte b[]=new byte[10000];
                long count=0;
                int per;
                while(count<f.length())
                {
                    int r=fis.read(b,0,10000);
                    count+=r;
                    cl.dos.write(b,0,r);
                    per=(int)((count/f.length())*100);
                    jpb.setValue(per);
                    jpb.setString((int)(count/f.length())+"% Sent");
                }
                cl.dos.writeBytes("send message\r\n");
                cl.dos.writeBytes("sending sticker\r\n");
                cl.dos.writeBytes(username+"\r\n");
                cl.dos.writeBytes(fname+"\r\n");
                cl.dos.writeBytes("//f\r\n");
                mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/file_icon.png\" ></div>";
                ep_chat.setText(mychat);
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        }

         // Variables declaration - do not modify                     
   private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JButton bt3;
    private javax.swing.JButton bt4;
    private javax.swing.JButton bt5;
    private javax.swing.JButton bt_browse;
    private javax.swing.JButton bt_chatsend;
    private javax.swing.JButton bt_fsend;
    private javax.swing.JEditorPane ep_chat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar jpb;
    private javax.swing.JLabel lb_friendname;
    private javax.swing.JTextField tf_msg;
    
    // End of variables declaration                  

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) 
        {
            al_chat.remove(this);
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }

    class search {

        String username, name, status;

        public search(String username, String name, String status) {
            this.username = username;
            this.name = name;
            this.status = status;
        }
    }

    class online_friends {

        String ip, name, status;

        online_friends(String ip, String name) {
            this.ip = ip;
            this.name = name;
        }
    }

    public class pendingfriends extends javax.swing.JFrame {
        public pendingfriends() {
            initComponents();
            setSize(418, 270);
                //---------------------------
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
            //---------------------------
            mtm_pending = new TableModel3();
            JT_pending.setModel(mtm_pending);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JT_pending = new javax.swing.JTable();
        bt_paccept = new javax.swing.JButton();
        bt_preject = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Gabriola", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Pending Requests");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 0, 280, 30);

        jScrollPane1.setViewportView(JT_pending);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 360, 100);

        bt_paccept.setBackground(new java.awt.Color(0, 0, 0));
        bt_paccept.setForeground(new java.awt.Color(255, 255, 255));
        bt_paccept.setText("Accept");
        bt_paccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pacceptActionPerformed(evt);
            }
        });
        bt_paccept.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_pacceptKeyPressed(evt);
            }
        });
        getContentPane().add(bt_paccept);
        bt_paccept.setBounds(70, 190, 100, 30);

        bt_preject.setBackground(new java.awt.Color(0, 0, 0));
        bt_preject.setForeground(new java.awt.Color(255, 255, 255));
        bt_preject.setText("Reject");
        bt_preject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_prejectActionPerformed(evt);
            }
        });
        getContentPane().add(bt_preject);
        bt_preject.setBounds(200, 190, 110, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 400, 300);

        pack();
    }// </editor-fold>                       

        private void bt_pacceptActionPerformed(java.awt.event.ActionEvent evt) {
            if (JT_pending.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please select a friend");
            } else {
                try {
                    String friend_name = al_pending.get(JT_pending.getSelectedRow()).pendingfriend;
                    cl.dos.writeBytes("accept friends\r\n");
                    cl.dos.writeBytes(friend_name + "\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                } catch (IOException ex) {
                    Logger.getLogger(clientgui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void bt_prejectActionPerformed(java.awt.event.ActionEvent evt) {
            if (JT_pending.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please select a friend");
            } else {
                try {
                    String friend_name = al_pending.get(JT_pending.getSelectedRow()).pendingfriend;
                    cl.dos.writeBytes("reject friends\r\n");
                    cl.dos.writeBytes(friend_name + "\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
         private void bt_pacceptKeyPressed(java.awt.event.KeyEvent evt) {                                      
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if (JT_pending.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please select a friend");
            } else {
                try {
                    String friend_name = al_pending.get(JT_pending.getSelectedRow()).pendingfriend;
                    cl.dos.writeBytes("reject friends\r\n");
                    cl.dos.writeBytes(friend_name + "\r\n");
                    cl.dos.writeBytes(username + "\r\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } 
        }
    }                                     


        // Variables declaration - do not modify                     
    private javax.swing.JTable JT_pending;
    private javax.swing.JButton bt_paccept;
    private javax.swing.JButton bt_preject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
    }

    class pending_friends {

        String pendingfriend;

        public pending_friends(String pendingfriend) {
            this.pendingfriend = pendingfriend;
        }
    }

    class TableModel2 extends AbstractTableModel {

        String title[] = {"online IP's", "Name"};

        @Override
        public String getColumnName(int a) {
            return title[a];
        }

        @Override
        public int getRowCount() {
            return al_onlinef.size();
        }

        @Override
        public int getColumnCount() {
            return title.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return al_onlinef.get(rowIndex).ip;
            } else {
                return al_onlinef.get(rowIndex).name;
            }
        }

    }

    class TableModel3 extends AbstractTableModel {

        String title[] = {"Username"};

        @Override
        public String getColumnName(int a) {
            return title[a];
        }

        @Override
        public int getRowCount() {
            return al_pending.size();
        }

        @Override
        public int getColumnCount() {
            return title.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return al_pending.get(rowIndex).pendingfriend;
        }

    }

    class TableModel extends AbstractTableModel {

        String title[] = {"Username", "Name", "status"};

        @Override
        public String getColumnName(int a) {
            return title[a];
        }

        @Override
        public int getRowCount() {
            return al_search.size();
        }

        @Override
        public int getColumnCount() {
            return title.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return al_search.get(rowIndex).username;
            } else if (columnIndex == 1) {
                return al_search.get(rowIndex).name;
            } else {
                return al_search.get(rowIndex).status;
            }
        }
    }

    class client implements Runnable {

        Socket sock;
        DataInputStream dis;
        DataOutputStream dos;

        @Override
        public void run() {
            try {
                sock = new Socket(server_ip, 9000);
                bt_connect.setEnabled(false);
                bt_signup.setEnabled(true);
                bt_login.setEnabled(true);
                dis = new DataInputStream(sock.getInputStream());
                dos = new DataOutputStream(sock.getOutputStream());
                dos.writeBytes("Hello server\r\n");
                while (true) {
                    String s = dis.readLine();
                    System.out.println("msg =" + s);
                    if (s.equals("user already exists")) {
                        JOptionPane.showMessageDialog(null, "User already exists");

                    } else if (s.equals("successfully signed up")) {

                        JOptionPane.showMessageDialog(null, "Welcome to Messenger");
                        su.dispose();
                    } else if (s.equals("successful")) {
                        JOptionPane.showMessageDialog(null, "Login successful,welcome "+username);
                        li.dispose();
                        bt_login.setEnabled(false);
                        bt_signup.setEnabled(false);
                        bt_getlist.setEnabled(true);
                        bt_start.setEnabled(true);
                        bt_change.setEnabled(true);
                        bt_pending.setEnabled(true);
                        bt_search.setEnabled(true);
                    } else if (s.equals("fails")) {
                        JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
                    } else if (s.equals("getfriends")) {
                        al_search.clear();
                        mtm.fireTableDataChanged();
                        int count = dis.readInt();
                        if (count == 0) {
                            JOptionPane.showMessageDialog(null, "Search didn't Match");
                        }
                        while (count != 0) {
                            String username = dis.readLine();
                            String name = dis.readLine();
                            String status = dis.readLine();
                            search s1 = new search(username, name, status);
                            al_search.add(s1);
                            mtm.fireTableDataChanged();
                            count--;
                        }
                    } else if (s.equals("getlist")) {
                        al_onlinef.clear();
                        while (true) {
                            String ip = dis.readLine();
                            if (ip.equals("ends")) {
                                break;
                            } else {
                                String name = dis.readLine();
                                al_onlinef.add(new online_friends(ip, name));
                                mtm_onlinef.fireTableDataChanged();
                            }
                        }
                    } else if (s.equals("changed password")) {
                        JOptionPane.showMessageDialog(null, "Passwords Successfully Updated");
                        cp.dispose();
                    } else if (s.equals("didnt changed")) {
                        JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
                    } else if (s.equals("getpendingrequests")) {
                        al_pending.clear();
                        int size = dis.readInt();
                        System.out.println("size" + size);
                        if (size == 0) {
                            JOptionPane.showMessageDialog(null, "No Pending Requests");
                            mtm_pending.fireTableDataChanged();
                            pf.dispose();
                        }
                        while (size != 0) {
                            String pending = dis.readLine();
                            System.out.println("pending" + pending);
                            al_pending.add(new pending_friends(pending));
                            mtm_pending.fireTableDataChanged();
                            size--;
                        }
                    } else if (s.equals("friend request send")) {
                        JOptionPane.showMessageDialog(null, "Friend Request sent");
                        try {
                            cl.dos.writeBytes("searchfriends\r\n");
                            cl.dos.writeBytes(search + "\r\n");
                            cl.dos.writeBytes(username + "\r\n");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else if (s.equals("friendaccepted")) {
                        JOptionPane.showMessageDialog(null, "Friend Request Accepted");
                        al_pending.clear();
                        cl.dos.writeBytes("getpendingfriends\r\n");
                        cl.dos.writeBytes(username + "\r\n");
                    } else if (s.equals("friendreject")) {
                        JOptionPane.showMessageDialog(null, "Friend Request rejected");
                        al_pending.clear();
                        cl.dos.writeBytes("getpendingfriends\r\n");
                        cl.dos.writeBytes(username + "\r\n");
                    } 
                    else if (s.equals("receive message")) 
                    {
                        String type = dis.readLine();
                        String username = dis.readLine();
                        String fname = dis.readLine();
                        String message = dis.readLine();
                        boolean flag = false;
                        for (int i = 0; i < al_chat.size(); i++) 
                        {
                            if (al_chat.get(i).fname.equals(username)) 
                            {
                                System.out.println("gggg");
                                al_chat.get(i).setState(JFrame.NORMAL);
                                al_chat.get(i).toFront();
                                if(type.equals("send text"))
                                {
                                al_chat.get(i).mychat += "<div align=\"left\">"+ message + "</div>";
                                
                                }
                                else
                                {
                                    if(message.equals("//s1"))
                                    {
                                         al_chat.get(i).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture2.png\" ></div>";
                                    }
                                    else if(message.equals("//s2"))
                                    {
                                         al_chat.get(i).mychat += " <div style=\"text-align: left;\"><img  src=File:\"src/Picture3.png\" ></div>";
                                    }
                                    else if(message.equals("//s3"))
                                    {
                                         al_chat.get(i).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture4.png\" ></div>";
                                    }
                                    else if(message.equals("//s4"))
                                    {
                                         al_chat.get(i).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture5.png\" ></div>";
                                    }
                                    else if(message.equals("//s5"))
                                    {
                                         al_chat.get(i).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture6.png\" ></div>";
                                    }
                                    else if(message.equals("//f"))
                                    {
                                         al_chat.get(i).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/file_icon.png\" ></div>";
                                    }
                                   
                                }
                                 flag=true;
                                al_chat.get(i).ep_chat.setText(al_chat.get(i).mychat);
                                Point p=al_chat.get(i).jScrollPane2.getViewport().getViewPosition();
                            p.y=p.y+1000000;
                            al_chat.get(i).jScrollPane2.getViewport().setViewPosition(p);
                                break;
                            }
                        }
                        if (flag == false) 
                        {
                            cf = new chatframe(username);
                            cf.setVisible(true);
                            al_chat.add(cf);
                            if(type.equals("send text"))
                            {
                            cf.mychat += "<div align=\"left\">" + message + "</div>";
                            }
                            else
                            {
                                    if(message.equals("//s1"))
                                    {
                                         cf.mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture2.png\" ></div>";
                                    }
                                    else if(message.equals("//s2"))
                                    {
                                         cf.mychat += " <div style=\"text-align: left;\"><img  src=File:\"src/Picture3.png\" ></div>";
                                    }
                                    else if(message.equals("//s3"))
                                    {
                                         cf.mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture4.png\" ></div>";
                                    }
                                    else if(message.equals("//s4"))
                                    {
                                         cf.mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture5.png\" ></div>";
                                    }
                                    else if(message.equals("//s5"))
                                    {
                                         cf.mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture6.png\" ></div>";
                                    } 
                                    else if(message.equals("//f"))
                                    {
                                         cf.mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/file_icon.png\" ></div>";
                                    }
                            }
                            cf.ep_chat.setText(cf.mychat);
                            Point p=cf.jScrollPane2.getViewport().getViewPosition();
                            p.y=p.y+1000000;
                            cf.jScrollPane2.getViewport().setViewPosition(p);
                        }

                    }
                    else if(s.equals("recieve chat"))
                            {
                            int count=dis.readInt();
                            String username=dis.readLine();
                            String fname =dis.readLine();
                            int index=-1; 
                            
                            for(int i=0;i<al_chat.size();i++)
                            {
                                if(al_chat.get(i).fname.equals(fname))
                                        {
                                        index=i;
                                        }
                            }
                            for(int i=0;i<count;i++)
                            {
                            String message_to=dis.readLine();
                            String message_from=dis.readLine();
                            String message=dis.readLine();
                            String type=dis.readLine();
                            if(type.equals("send text"))
                            {
                                if(message_from.equals(username))
                                {
                                    al_chat.get(index).mychat += "<div align='left'>" + message + "</div>";
                                }
                                else if(message_from.equals(fname))
                                {
                                     al_chat.get(index).mychat += "<div align=\"right\">" + message + "</div>";
                                }
                            }
                            else
                            {
                                if(message_from.equals(username))
                                {
                                  if(message.equals("//s1"))
                                  {
                                       al_chat.get(index).mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture2.png\" ></div>";
                                  }
                                  if(message.equals("//s2"))
                                  {
                                       al_chat.get(index).mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture3.png\" ></div>";
                                  }
                                  if(message.equals("//s3"))
                                  {
                                       al_chat.get(index).mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture4.png\" ></div>";
                                  }
                                  if(message.equals("//s4"))
                                  {
                                       al_chat.get(index).mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture5.png\" ></div>";
                                  }
                                  if(message.equals("//s5"))
                                  {
                                       al_chat.get(index).mychat += "<div style=\"text-align: right;\"><img  src=File:\"src/Picture6.png\" ></div>";
                                  }
                                  else if(message.equals("//f"))
                                    {
                                         al_chat.get(index).mychat +=  " <div style=\"text-align: right;\"><img  src=File:\"src/file_icon.png\" ></div>";
                                    }
                                }
                                else if(message_from.equals(fname))
                                {
                                   if(message.equals("//s1"))
                                    {
                                         al_chat.get(index).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture2.png\" ></div>";
                                    }
                                    else if(message.equals("//s2"))
                                    {
                                         al_chat.get(index).mychat += " <div style=\"text-align: left;\"><img  src=File:\"src/Picture3.png\" ></div>";
                                    }
                                    else if(message.equals("//s3"))
                                    {
                                         al_chat.get(index).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture4.png\" ></div>";
                                    }
                                    else if(message.equals("//s4"))
                                    {
                                         al_chat.get(index).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture5.png\" ></div>";
                                    }
                                    else if(message.equals("//s5"))
                                    {
                                         al_chat.get(index).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/Picture6.png\" ></div>";
                                    }
                                   else if(message.equals("//f"))
                                    {
                                         al_chat.get(index).mychat +=  " <div style=\"text-align: left;\"><img  src=File:\"src/file_icon.png\" ></div>";
                                    }
                                }
                            }
                            }
                                System.out.println(al_chat.get(index).mychat);
                            al_chat.get(index).ep_chat.setText(al_chat.get(index).mychat);
                            Point p=al_chat.get(index).jScrollPane2.getViewport().getViewPosition();
                            p.y=p.y+1000000;
                            al_chat.get(index).jScrollPane2.getViewport().setViewPosition(p);
                            }
                    else if(s.equals("recieve file"))
                    {
                       String fname=dis.readLine();
                       String filename=dis.readLine();
                       long fsize=dis.readLong();
                       byte b[]=new byte[10000];
                       long count=0;
                       FileOutputStream fos=new FileOutputStream("D:\\"+filename);
                       while(count<fsize)
                       {
                           int r=dis.read(b,0,10000);
                           count=count+r;
                           fos.write(b,0,r);
                       }
                       fos.close();
                       if(count==fsize)
                       {
                           JOptionPane.showMessageDialog(null,"A file "+filename+" is recieved");
                       }
                    }
                    else if(s.equals("unfriend"))
                    {
                        JOptionPane.showMessageDialog(null, "Unfriend Successful");
                        try {
                            cl.dos.writeBytes("searchfriends\r\n");
                            cl.dos.writeBytes(search + "\r\n");
                            cl.dos.writeBytes(username + "\r\n");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();    
                JOptionPane.showMessageDialog(null,"Server Not Found");            }
        }

    }

    private void bt_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_connectActionPerformed
       
        server_ip=JOptionPane.showInputDialog(null,"enter server ip");
         cl = new client();
        new Thread(cl).start();
    }//GEN-LAST:event_bt_connectActionPerformed

    private void bt_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_loginActionPerformed
        li = new LogIn();
        li.setVisible(true);
    }//GEN-LAST:event_bt_loginActionPerformed

    private void bt_signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_signupActionPerformed
        su = new SignUp();
        su.setVisible(true);
    }//GEN-LAST:event_bt_signupActionPerformed

    private void bt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_searchActionPerformed
        sf = new searchfriends();
        sf.setVisible(true);
    }//GEN-LAST:event_bt_searchActionPerformed

    private void bt_getlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_getlistActionPerformed
        try {
            cl.dos.writeBytes("sendlist\r\n");
            cl.dos.writeBytes(username + "\r\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bt_getlistActionPerformed

    private void bt_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_startActionPerformed

        if (JT_getlist.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Select a Friend to Start a chat");
        } else {
            boolean flag = false;
            String fname = al_onlinef.get(JT_getlist.getSelectedRow()).name;
            chat_with = fname;
            for (int i = 0; i < al_chat.size(); i++) {
                if (al_chat.get(i).fname.equals(chat_with)) {
                    al_chat.get(i).setState(JFrame.NORMAL);
                    al_chat.get(i).toFront();
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                cf = new chatframe(chat_with);
                cf.setVisible(true);
                al_chat.add(cf);
            }
        }
    }//GEN-LAST:event_bt_startActionPerformed

    private void bt_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_changeActionPerformed
        cp = new changepassword();
        cp.setVisible(true);
    }//GEN-LAST:event_bt_changeActionPerformed

    private void bt_pendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pendingActionPerformed
        try {
            pf = new pendingfriends();
            pf.setVisible(true);
            
            cl.dos.writeBytes("getpendingfriends\r\n");
            cl.dos.writeBytes(username + "\r\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bt_pendingActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        clientgui c1 = new clientgui();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JT_getlist;
    private javax.swing.JButton bt_change;
    private javax.swing.JButton bt_connect;
    private javax.swing.JButton bt_getlist;
    private javax.swing.JButton bt_login;
    private javax.swing.JButton bt_pending;
    private javax.swing.JButton bt_search;
    private javax.swing.JButton bt_signup;
    private javax.swing.JButton bt_start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
