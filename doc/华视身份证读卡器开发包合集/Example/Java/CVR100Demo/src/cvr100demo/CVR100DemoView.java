/*
 * CVR100DemoView.java
 */

package cvr100demo;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;




/**
 * The application's main frame.
 */
public class CVR100DemoView extends FrameView {

    String strTmp = "";
    int len = 0;
    int ret = 0;      
    
    public CVR100DemoView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
//                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
//        statusAnimationLabel.setIcon(idleIcon);
//        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
//                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
//                    statusAnimationLabel.setIcon(idleIcon);
//                    progressBar.setVisible(false);
//                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
//                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(false);
//                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = CVR100DemoApp.getApplication().getMainFrame();
            aboutBox = new CVR100DemoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        CVR100DemoApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton7 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(cvr100demo.CVR100DemoApp.class).getContext().getResourceMap(CVR100DemoView.class);
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText(resourceMap.getString("jRadioButton1.text")); // NOI18N
        jRadioButton1.setName("jRadioButton1"); // NOI18N

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(resourceMap.getString("jRadioButton2.text")); // NOI18N
        jRadioButton2.setName("jRadioButton2"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12", "COM13", "COM14", "COM15", "COM16" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "USB1001", "USB1002", "USB1003", "USB1004", "USB1005", "USB1006", "USB1007", "USB1008", "USB1009", "USB1010", "USB1011", "USB1012", "USB1013", "USB1014", "USB1015", "USB1016" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6))
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, 0, 121, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButton2)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton6))
                        .addGap(47, 47, 47)
                        .addComponent(jButton8)))
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(cvr100demo.CVR100DemoApp.class).getContext().getActionMap(CVR100DemoView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private  int CVR_InitComm(int Port) throws NativeException, IllegalAccessException, UnsupportedEncodingException 
    {          
          JNative n = null;
          try 
          {
             n = new JNative("Termb.dll", "CVR_InitComm");              
             n.setRetVal(Type.INT); // 指定返回参数的类型
             n.setParameter(0, Port);                   
             n.invoke(); // 调用方法
             return Integer.parseInt(n.getRetVal());
          } 
          finally 
          {
             
          }
     }
    
     private  int GetPeopleName() throws NativeException, IllegalAccessException 
    {
          JNative n = null;           
          try 
          {
             n = new JNative("Termb.dll", "GetPeopleName"); 
             n.setRetVal(Type.INT); // 指定返回参数的类型 
             Pointer a = new Pointer(MemoryBlockFactory.createMemoryBlock(4*10));
             Pointer b = new Pointer(MemoryBlockFactory.createMemoryBlock(4*30));             
             n.setParameter(0,b);
             n.setParameter(1,a);              
             n.invoke(); 
             byte[] by = new byte[120];             
             by = b.getMemory();
            try 
            {
                strTmp = new String(by,"gb2312");
            } 
            catch (UnsupportedEncodingException ex) 
            {
                Logger.getLogger(CVR100DemoView.class.getName()).log(Level.SEVERE, null, ex);
            }

            byte[] bt = new byte[40];  
            bt = a.getMemory();
             len = a.getAsInt(0);
             a.dispose();
             b.dispose();
             return Integer.parseInt(n.getRetVal());
          } 
          finally 
          {
             
          }
     }
     
      private  int GetPeopleAddress() throws NativeException, IllegalAccessException 
    {
          JNative n = null;           
          try 
          {
             n = new JNative("Termb.dll", "GetPeopleAddress"); 
             n.setRetVal(Type.INT); // 指定返回参数的类型 
             Pointer a = new Pointer(MemoryBlockFactory.createMemoryBlock(4*10));
             Pointer b = new Pointer(MemoryBlockFactory.createMemoryBlock(4*30));
             n.setParameter(0,b);
             n.setParameter(1,a);              
             n.invoke(); 
             byte[] by = new byte[120];             
             by = b.getMemory();
            try 
            {
                strTmp = new String(by,"gb2312");
            } 
            catch (UnsupportedEncodingException ex) 
            {
                Logger.getLogger(CVR100DemoView.class.getName()).log(Level.SEVERE, null, ex);
            }

             len = a.getAsInt(0);
             
             a.dispose();
             b.dispose();
             return Integer.parseInt(n.getRetVal());
          } 
          finally 
          {
             
          }
     }
      
       private  int GetPeopleIDCode() throws NativeException, IllegalAccessException 
    {
          JNative n = null;           
          try 
          {
             n = new JNative("Termb.dll", "GetPeopleIDCode"); 
             n.setRetVal(Type.INT); // 指定返回参数的类型 
             Pointer a = new Pointer(MemoryBlockFactory.createMemoryBlock(4*10));
             Pointer b = new Pointer(MemoryBlockFactory.createMemoryBlock(4*30));
             n.setParameter(0,b);
             n.setParameter(1,a);              
             n.invoke(); 
             byte[] by = new byte[120];             
             by = b.getMemory();
            try 
            {
                strTmp = new String(by,"gb2312");
            } 
            catch (UnsupportedEncodingException ex) 
            {
                Logger.getLogger(CVR100DemoView.class.getName()).log(Level.SEVERE, null, ex);
            }

             len = a.getAsInt(0);
             
             a.dispose();
             b.dispose();
             return Integer.parseInt(n.getRetVal());
          } 
          finally 
          {
             
          }
     }
    
    private  int CVR_Authenticate() throws NativeException, IllegalAccessException 
    {
          JNative n = null;
          try 
          {
             n = new JNative("Termb.dll", "CVR_Authenticate"); 
             n.setRetVal(Type.INT); // 指定返回参数的类型                              
             n.invoke(); // 调用方法
             return Integer.parseInt(n.getRetVal());
          } 
          finally 
          {
             
          }
     }
    
    private  int CVR_Read_Content(int Active) throws NativeException, IllegalAccessException 
    {
          JNative n = null;
          try 
          {
             n = new JNative("Termb.dll", "CVR_Read_Content"); 
             n.setRetVal(Type.INT); // 指定返回参数的类型
             n.setParameter(0, Active);                   
             n.invoke(); // 调用方法
             return Integer.parseInt(n.getRetVal());
          } 
          finally 
          {
             
          }
     }
    
    private  int CVR_CloseComm() throws NativeException,IllegalAccessException 
    {
          JNative n = null;
          try 
          {
             n = new JNative("Termb.dll", "CVR_CloseComm"); 
             n.setRetVal(Type.INT); // 指定返回参数的类型                               
             n.invoke(); // 调用方法
             return Integer.parseInt(n.getRetVal());
          } 
          finally 
          {
             
          }
       }

   
    
private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
// TODO add your handling code here:  
    String strport = "";
    int intport = 0;
    if(this.jRadioButton1.isSelected())
    {
        strport = this.jComboBox1.getModel().getSelectedItem().toString();
        strport = strport.replaceAll("COM", "");
        intport = Integer.parseInt(strport);
    }
    else
    {
        strport = this.jComboBox2.getModel().getSelectedItem().toString();
        strport = strport.replaceAll("USB", "");
        intport = Integer.parseInt(strport);
    }
   int ret = 0;   
   try
   {
        ret = CVR_InitComm(intport);         
        this.jTextArea1.append("返回值：" + String.valueOf(ret));
        this.jTextArea1.append("\n");
   }
   catch(Exception ex)
   {
       this.jTextArea1.append("调用异常！"+ ex.getMessage());
        this.jTextArea1.append("\n");
   }
}//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        int ret = 0;   
       try
       {
            ret = CVR_Authenticate();         
            this.jTextArea1.append("返回值：" + String.valueOf(ret));
            this.jTextArea1.append("\n");
       }
       catch(Exception ex)
       {
           this.jTextArea1.append("调用异常！"+ ex.getMessage());
            this.jTextArea1.append("\n");
       }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        int ret = 0;   
       try
       {
            ret = CVR_Read_Content(4);           
            this.jTextArea1.append("返回值：" + String.valueOf(ret));
            this.jTextArea1.append("\n");
       }
       catch(Exception ex)
       {
           this.jTextArea1.append("调用异常！"+ ex.getMessage());
            this.jTextArea1.append("\n");
       }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        int ret = 0;   
       try
       {
            ret = CVR_CloseComm();         
            this.jTextArea1.append("返回值：" + String.valueOf(ret));
            this.jTextArea1.append("\n");
       }
       catch(Exception ex)
       {
           this.jTextArea1.append("调用异常！"+ ex.getMessage());
            this.jTextArea1.append("\n");
       }
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:       
       try
       {
            ret = GetPeopleName();  
            this.jTextArea1.append("返回值：" + String.valueOf(ret));
            this.jTextArea1.append("\n");
            if(ret == 1)
            {               
                this.jTextArea1.append("姓名：" + strTmp.trim());
                this.jTextArea1.append("\n");  
            }           
       }
       catch(Exception ex)
       {
           this.jTextArea1.append("调用异常！"+ ex.getMessage());
            this.jTextArea1.append("\n");
       }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        try
       {
            ret = GetPeopleAddress();  
            this.jTextArea1.append("返回值：" + String.valueOf(ret));
            this.jTextArea1.append("\n");
            if(ret == 1)
            {               
                this.jTextArea1.append("地址：" + strTmp.trim());
                this.jTextArea1.append("\n");  
            }           
       }
       catch(Exception ex)
       {
           this.jTextArea1.append("调用异常！"+ ex.getMessage());
            this.jTextArea1.append("\n");
       }
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        try
       {
            ret = GetPeopleIDCode();  
            this.jTextArea1.append("返回值：" + String.valueOf(ret));
            this.jTextArea1.append("\n");
            if(ret == 1)
            {               
                this.jTextArea1.append("身份号码：" + strTmp.trim());
                this.jTextArea1.append("\n");  
            }           
       }
       catch(Exception ex)
       {
           this.jTextArea1.append("调用异常！"+ ex.getMessage());
            this.jTextArea1.append("\n");
       }
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
        this.jTextArea1.setText("");
    }//GEN-LAST:event_jButton7MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;

    
}
