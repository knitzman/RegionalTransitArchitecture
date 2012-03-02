/**
Copyright 2012 University of South Florida

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

**/

package edu.usf.cutr.fdot7.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeLayer;
import com.esri.sde.sdk.client.SeTable;

import edu.usf.cutr.fdot7.io.FdotSchemaImpl;
import edu.usf.cutr.fdot7.main.Test;
import edu.usf.cutr.fdot7.object.GenericGtfsData;
import edu.usf.cutr.fdot7.sde.SdeConnection;

/**
 * @author Khoa Tran
 *
 */
public class SessionForm extends javax.swing.JDialog {

    private boolean clickedOKButton = false;
    private String _username;
    private String _password;
    
    private static Logger _log = LoggerFactory.getLogger(SessionForm.class);
    
    private BeanFactory factory;
	private FdotSchemaImpl schema;

    /** Creates new form OSMSessionForm */
    public SessionForm() {
    	factory = new XmlBeanFactory(new FileSystemResource(System.getProperty("user.dir")+System.getProperty("file.separator")+"data-source.xml"));
        initComponents();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter(){
        	public void windowClosing(WindowEvent we){
        		Test.mutex.release();
        	}
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FDOT7 Database Connection Login");
        setAlwaysOnTop(true);
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        setResizable(false);

        jLabel1.setText("Username:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Password:");
        jLabel2.setName("jLabel2"); // NOI18N

        usernameField.setName("tbUsername"); // NOI18N

        passwordField.setName("tbPassword"); // NOI18N

        jButton1.setText("OK");
        jButton1.setName("btnOK"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.setName("btnCancel"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(passwordField)
                                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("District 7  Login");

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        _username = usernameField.getText();
        _password = new String(passwordField.getPassword());
        if (!_username.isEmpty() && !_password.isEmpty()) {
        	clickedOKButton = true;
            setVisible(false);
            try {
            	verifyUser(_username, _password);
            } catch(Exception e){
            	this.showDialog();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields!");
        }
        Test.mutex.release();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        clickedOKButton= false;
        setVisible(false);
        Test.mutex.release();
    }                                        

    public boolean showDialog() {
        setVisible(true);
        return clickedOKButton;
    }
    
    /**
     * Verify the given user credentials
     * @param username
     * @param password
     * @throws Exception
     */
    private void verifyUser(String username, String password) throws Exception{    	
    	schema = (FdotSchemaImpl) factory.getBean("connection_specs");
    	SdeConnection conn = new SdeConnection(schema.getServer(),
    			schema.getInstance(),
    			schema.getDatabase(),
    			username,
    			password);
    	try{
    		try {
    			_log.info("Verifying credentials...");
    			conn.open_connection();
    		} catch (SeException se){
    			JOptionPane.showMessageDialog(this, "Failed to verify your credentials! Please try again.");
    			if(conn!=null) {
    				_log.info("Failed to verify your credentials! Please try again.");
    				conn.close_connection();
    			}
    			throw new Exception("Connection Failed!!");
    		}
    		
    		Test.mainUsername = username;
    		Test.mainPassword = password;

    		if(conn!=null){
//    			_log.info("Close connection");
    			conn.close_connection();
    		}
    		
    		_log.info("Complete verifying credentials!");
    	} catch (SeException e) {
    		_log.error(e.getMessage());
    		_log.error(e.getSeError().getErrDesc());
    		_log.error(e.getSeError().getSdeErrMsg());

    	}
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration                   

}