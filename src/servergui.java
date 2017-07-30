import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.*;

public class servergui extends javax.swing.JFrame {

    ArrayList<server.clienthandler> al_onlineIP = new ArrayList<>();
    TableModel mtm;

    public servergui() {
        initComponents();
            //---------------------------
          
            //---------------------------
            

        setSize(417,340);
        setVisible(true);
          int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width/2)-(this.getWidth()/2);
            int y = (height/2)-(this.getHeight()/2);
            setLocation(x, y);
        bt_startserver.setEnabled(true);
        mtm = new TableModel();
        JT_onlineIPs.setModel(mtm);

    }

    class TableModel extends AbstractTableModel {

        String title[] = {"online IP's", "Name"};

        @Override
        public String getColumnName(int a) {
            return title[a];
        }

        @Override
        public int getRowCount() {
            return al_onlineIP.size();
        }

        @Override
        public int getColumnCount() {
            return title.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return al_onlineIP.get(rowIndex).ip;
            } else {
                return al_onlineIP.get(rowIndex).name;
            }
        }
    }

    public class server implements Runnable {

        ServerSocket sersock;
        Socket sock;

        @Override
        public void run() {
            try {
                sersock = new ServerSocket(9000);
                bt_startserver.setEnabled(false);
                while (true) {
                    sock = sersock.accept();
                    clienthandler cl = new clienthandler(sock);
                    Thread t = new Thread(cl);
                    t.start();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        class clienthandler implements Runnable {

            Socket sock;
            DataInputStream dis;
            DataOutputStream dos;
            String ip, name;

            clienthandler(Socket sock) {
                try {
                    this.sock = sock;
                    dis = new DataInputStream(sock.getInputStream());
                    dos = new DataOutputStream(sock.getOutputStream());
                    ip = sock.getInetAddress().getHostAddress();

                    al_onlineIP.add(this);
                    mtm.fireTableDataChanged();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void run() {
                try {
                    dos.writeBytes("Welcome client\r\n");
                    while (true) {
                        String msg = dis.readLine();
                        System.out.println("msg in server-----" + msg);
                        if (msg.equals("signup")) {
                            String username = dis.readLine();
                            String name = dis.readLine();
                            String password = dis.readLine();
                            String mobile = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from users where username='" + username + "' ");
                            if (rs.next()) {
                                dos.writeBytes("user already exists\r\n");
                            } else {
                                rs.moveToInsertRow();
                                rs.updateString("Username", username);
                                rs.updateString("Name", name);
                                rs.updateString("Password", password);
                                rs.updateString("Mobile", mobile);
                                rs.insertRow();
                                dos.writeBytes("successfully signed up\r\n");
                            }
                        } else if (msg.equals("login")) {
                            String username = dis.readLine();
                            String password = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from users where username='" + username + "'and password='" + password + "' ");
                            if (rs.next()) {
                                dos.writeBytes("successful\r\n");
                                name = username;
                                mtm.fireTableDataChanged();
                            } else {
                                dos.writeBytes("fails\r\n");
                            }
                        } else if (msg.equals("searchfriends")) {
                            String searchtext = dis.readLine();
                            String username = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select count(*) as count from users where username like '%" + searchtext + "%'");
                            rs.next();
                            int count = rs.getInt("count");
                            System.out.println("Count--" + count);
                            rs = stmt.executeQuery("select * from users where username like '%" + searchtext + "%'");
                            dos.writeBytes("getfriends\r\n");
                            dos.writeInt(count);
                            while (rs.next()) {
                                boolean flag = false;
                                String f_uname = rs.getString("username");
                                String f_name = rs.getString("name");
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection conn1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                                Statement stmt2 = conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs2 = stmt2.executeQuery("select *  from friend where to_user='" + f_uname + "' and from_user='" + username + "'");
                                while (rs2.next()) {
                                    flag = true;
                                    String status = rs2.getString("status");
                                    if (status.equals("pending")) {
                                        dos.writeBytes(f_uname + "\r\n");
                                        dos.writeBytes(f_name + "\r\n");
                                        dos.writeBytes("pending\r\n");
                                    } else if (status.equals("friends")) {
                                        dos.writeBytes(f_uname + "\r\n");
                                        dos.writeBytes(f_name + "\r\n");
                                        dos.writeBytes("friends\r\n");
                                    }
                                }
                                if (flag == false) {
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection conn3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                                    Statement stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                                    ResultSet rs3 = stmt3.executeQuery("select *  from friend where to_user='" + username + "' and from_user='" + f_uname + "'");
                                    if (rs3.next()) {
                                        dos.writeBytes(f_uname + "\r\n");
                                        dos.writeBytes(f_name + "\r\n");
                                        dos.writeBytes("received request\r\n");
                                    } else {
                                        dos.writeBytes(f_uname + "\r\n");
                                        dos.writeBytes(f_name + "\r\n");
                                        dos.writeBytes("send request\r\n");
                                    }
                                }
                            }

                        } else if (msg.equals("sendlist")) {
                            String username = dis.readLine();
                            dos.writeBytes("getlist\r\n");
                            int size = al_onlineIP.size();
                            for (int i = 0; i < size; i++) {

                                try {
                                    String fname = al_onlineIP.get(i).name;
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                    ResultSet rs = stmt.executeQuery("select * from friend where to_user ='" + username + "'and from_user='" + fname + "' and status='friends'");
                                    if (rs.next()) {
                                        dos.writeBytes(al_onlineIP.get(i).ip + "\r\n");
                                        dos.writeBytes(al_onlineIP.get(i).name + "\r\n");
                                    }

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            dos.writeBytes("ends\r\n");
                        } else if (msg.equals("change")) {
                            String username = dis.readLine();
                            String oldpassword = dis.readLine();
                            String newpassword = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from users where username='" + username + "'and password='" + oldpassword + "' ");
                            if (rs.next()) {
                                rs.updateString("Password", newpassword);
                                rs.updateRow();
                                dos.writeBytes("changed password\r\n");
                            } else {
                                dos.writeBytes("didnt changed\r\n");
                            }
                        } else if (msg.equals("getpendingfriends")) {
                            String username = dis.readLine();

                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select count(*) as count from friend where to_user ='" + username + "'and status='pending'");
                            rs.next();
                            int count = rs.getInt("count");
                            dos.writeBytes("getpendingrequests\r\n");
                            dos.writeInt(count);
                            rs = stmt.executeQuery("select * from friend where to_user ='" + username + "'and status='pending'");
                            while (rs.next()) {
                                String friend = rs.getString("from_user");
                                dos.writeBytes(friend + "\r\n");
                            }
                        } else if (msg.equals("send request")) {
                            String from_user = dis.readLine();
                            String to_user = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friend");
                            rs.next();
                            rs.moveToInsertRow();
                            rs.updateString("from_user", from_user);
                            rs.updateString("to_user", to_user);
                            rs.updateString("status", "pending");
                            rs.insertRow();
                            dos.writeBytes("friend request send\r\n");
                        } else if (msg.equals("accept friends")) {
                            String F_name = dis.readLine();
                            String username = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friend where to_user='" + username + "' and from_user='" + F_name + "' and status='pending'");
                            if (rs.next()) {
                                rs.deleteRow();
                                rs.moveToInsertRow();
                                rs.updateString("to_user", F_name);
                                rs.updateString("from_user", username);
                                rs.updateString("status", "friends");
                                rs.insertRow();
                                rs.moveToInsertRow();
                                rs.updateString("to_user", username);
                                rs.updateString("from_user", F_name);
                                rs.updateString("status", "friends");
                                rs.insertRow();
                                dos.writeBytes("friendaccepted\r\n");
                            }

                        } else if (msg.equals("reject friends")) {
                            String F_name = dis.readLine();
                            String username = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friend where to_user='" + username + "' and from_user='" + F_name + "' and status='pending'");
                            if (rs.next()) {
                                rs.deleteRow();
                                dos.writeBytes("friendreject\r\n");
                            }
                        } else if (msg.equals("send message")) 
                        { 
                            String type = dis.readLine();
                            String username = dis.readLine();
                            String fname = dis.readLine();
                            String message = dis.readLine();
                            System.out.println(username + "  " + fname);
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from chat");
                            rs.moveToInsertRow();
                            rs.updateString("message_to",fname);
                            rs.updateString("message_from",username);
                            rs.updateString("message",message);
                            rs.updateString("type",type);
                            rs.insertRow();
                            for (int i = 0; i < al_onlineIP.size(); i++) 
                            {
                                if (al_onlineIP.get(i).name.equals(fname)) 
                                {
                                    al_onlineIP.get(i).dos.writeBytes("receive message\r\n");
                                    al_onlineIP.get(i).dos.writeBytes(type +"\r\n");
                                    al_onlineIP.get(i).dos.writeBytes(username +"\r\n");
                                    al_onlineIP.get(i).dos.writeBytes(fname +"\r\n");
                                    al_onlineIP.get(i).dos.writeBytes(message +"\r\n");
                                }
                            }
                        }
                        else if(msg.equals("send previous chat"))
                        {
                            String username=dis.readLine();
                            String fname=dis.readLine();
                            int index=-1;
                            for(int i=0;i<=al_onlineIP.size();i++)
                            {
                                if(al_onlineIP.get(i).name.equals(username))
                                {
                                    index=i;
                                    break;
                                }
                            }
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select count(*) as count from chat where (message_to='"+username+"' and message_from='"+fname+"')or (message_to='"+fname+"' and message_from='"+username+"')"); 
                            rs.next();
                            int count=rs.getInt("count");
                            al_onlineIP.get(index).dos.writeBytes("recieve chat\r\n");
                            al_onlineIP.get(index).dos.writeInt(count);
                            al_onlineIP.get(index).dos.writeBytes(username+"\r\n");
                            al_onlineIP.get(index).dos.writeBytes(fname+"\r\n");
                            rs = stmt.executeQuery("select *  from chat where (message_to='"+username+"' and message_from='"+fname+"')or (message_to='"+fname+"' and message_from='"+username+"')"); 
                            while(rs.next())
                            {   
                                String message_to=rs.getString("message_to");
                                String message_from=rs.getString("message_from");
                                String message=rs.getString("message");
                                String type=rs.getString("type");
                                al_onlineIP.get(index).dos.writeBytes(message_to+"\r\n");
                                al_onlineIP.get(index).dos.writeBytes(message_from+"\r\n");
                                al_onlineIP.get(index).dos.writeBytes(message+"\r\n");
                                al_onlineIP.get(index).dos.writeBytes(type+"\r\n");
                            }
                        }
                        else if(msg.equals("recieve file"))
                        {
                            int index=-1;
                            String fname=dis.readLine();
                            String filename=dis.readLine();
                            long fsize=dis.readLong();
                            for(int i=0;i<al_onlineIP.size();i++)
                            {
                                if(al_onlineIP.get(i).name.equals(fname))
                                {
                                    index=i;
                                }
                            }
                            al_onlineIP.get(index).dos.writeBytes("recieve file\r\n");
                            al_onlineIP.get(index).dos.writeBytes(name+"\r\n");
                            al_onlineIP.get(index).dos.writeBytes(filename+"\r\n");
                            al_onlineIP.get(index).dos.writeLong(fsize);
                            byte b[]=new byte[10000];
                            long count=0;
                            while(count<fsize)
                            {
                                int r=dis.read(b,0,10000);
                                count=count+r;
                                al_onlineIP.get(index).dos.write(b,0,r);
                            }
                        }
                        else if(msg.equals("unfriend"))
                        {
                            String from_user = dis.readLine();
                            String to_user = dis.readLine();
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/asa chat", "root","aman");
                            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from friend where (to_user='"+from_user+"' and from_user='"+to_user+"') or(to_user='"+to_user+"' and from_user='"+from_user+"')");
                            while(rs.next())
                            {
                                rs.deleteRow();
                            }
                            dos.writeBytes("unfriend\r\n");
                        }
                    }
                } catch (Exception ex) {
                    al_onlineIP.remove(this);
                    mtm.fireTableDataChanged();
                    ex.printStackTrace();
                }
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_startserver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JT_onlineIPs = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));
        getContentPane().setLayout(null);

        bt_startserver.setBackground(new java.awt.Color(0, 0, 0));
        bt_startserver.setForeground(new java.awt.Color(255, 255, 255));
        bt_startserver.setText("Start the Server");
        bt_startserver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_startserver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_startserverActionPerformed(evt);
            }
        });
        getContentPane().add(bt_startserver);
        bt_startserver.setBounds(20, 50, 360, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText(" Server");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 80, 30);

        JT_onlineIPs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(JT_onlineIPs);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 360, 160);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Reign_Of_Fire_1680 x 1050 widescreen.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 400, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_startserverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_startserverActionPerformed
        Thread t = new Thread(new server());
        t.start();


    }//GEN-LAST:event_bt_startserverActionPerformed

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
            java.util.logging.Logger.getLogger(servergui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(servergui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(servergui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(servergui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        servergui sgui = new servergui();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JT_onlineIPs;
    private javax.swing.JButton bt_startserver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
