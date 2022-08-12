import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.awt.event.ActionEvent;

public class addingAdmin extends JFrame {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	private JPanel contentPane;
	private JTextField txtFieldID;
	private JTextField txtFieldFirstName;
	private JTextField txtFieldLastName;
	private JTextField txtFieldMajor;
	private JTextField txtFieldPassword;
	private DataOutputStream dataOutputStream;
	private static Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						socket = new Socket("192.168.1.67",1269);
					} catch (Exception e) {
						e.printStackTrace();
					}
					addingAdmin frame = new addingAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public addingAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblID = new JLabel("ID:");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 2;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		txtFieldID = new JTextField();
		GridBagConstraints gbc_txtFieldID = new GridBagConstraints();
		gbc_txtFieldID.anchor = GridBagConstraints.WEST;
		gbc_txtFieldID.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldID.gridx = 4;
		gbc_txtFieldID.gridy = 0;
		contentPane.add(txtFieldID, gbc_txtFieldID);
		txtFieldID.setColumns(20);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 2;
		gbc_lblFirstName.gridy = 2;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		txtFieldFirstName = new JTextField();
		GridBagConstraints gbc_txtFieldFirstName = new GridBagConstraints();
		gbc_txtFieldFirstName.anchor = GridBagConstraints.WEST;
		gbc_txtFieldFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldFirstName.gridx = 4;
		gbc_txtFieldFirstName.gridy = 2;
		contentPane.add(txtFieldFirstName, gbc_txtFieldFirstName);
		txtFieldFirstName.setColumns(20);
		
		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 4;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		txtFieldLastName = new JTextField();
		GridBagConstraints gbc_txtFieldLastName = new GridBagConstraints();
		gbc_txtFieldLastName.anchor = GridBagConstraints.WEST;
		gbc_txtFieldLastName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldLastName.gridx = 4;
		gbc_txtFieldLastName.gridy = 4;
		contentPane.add(txtFieldLastName, gbc_txtFieldLastName);
		txtFieldLastName.setColumns(20);
		
		JLabel lblMajor = new JLabel("Major:");
		GridBagConstraints gbc_lblMajor = new GridBagConstraints();
		gbc_lblMajor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMajor.insets = new Insets(0, 0, 5, 5);
		gbc_lblMajor.gridx = 2;
		gbc_lblMajor.gridy = 6;
		contentPane.add(lblMajor, gbc_lblMajor);
		
		JButton btnAdd = new JButton("ADD NEW ADM\u0130N");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtFieldID.getText();
				String firstName = txtFieldFirstName.getText();
				String lastName = txtFieldLastName.getText();
				String major = txtFieldMajor.getText();
		
				String password = txtFieldPassword.getText();
				
				password = md5(password);
				
				sentToClient(id, firstName, lastName, major,password);
				
				txtFieldID.setText("");
				txtFieldFirstName.setText("");
				txtFieldLastName.setText("");
				txtFieldMajor.setText("");
				txtFieldPassword.setText("");
				
			}
		});
		
		txtFieldMajor = new JTextField();
		GridBagConstraints gbc_txtFieldMajor = new GridBagConstraints();
		gbc_txtFieldMajor.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldMajor.anchor = GridBagConstraints.WEST;
		gbc_txtFieldMajor.gridx = 4;
		gbc_txtFieldMajor.gridy = 6;
		contentPane.add(txtFieldMajor, gbc_txtFieldMajor);
		txtFieldMajor.setColumns(20);
		
		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 2;
		gbc_passwordLabel.gridy = 8;
		contentPane.add(passwordLabel, gbc_passwordLabel);
		
		txtFieldPassword = new JTextField();
		GridBagConstraints gbc_txtFieldPassword = new GridBagConstraints();
		gbc_txtFieldPassword.anchor = GridBagConstraints.WEST;
		gbc_txtFieldPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldPassword.gridx = 4;
		gbc_txtFieldPassword.gridy = 8;
		contentPane.add(txtFieldPassword, gbc_txtFieldPassword);
		txtFieldPassword.setColumns(20);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 12;
		contentPane.add(btnAdd, gbc_btnAdd);
	}
	
	public void sentToClient(String id , String name , String lastName, String major , String password) {
		
		try {
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			dataOutputStream.writeBytes("ADMININSERT");
			dataOutputStream.writeBytes(id);
			dataOutputStream.writeBytes("^^");
			dataOutputStream.writeBytes(name);
			dataOutputStream.writeBytes("^^");
			dataOutputStream.writeBytes(lastName);
			dataOutputStream.writeBytes("^^");
			dataOutputStream.writeBytes(major);
			dataOutputStream.writeBytes("^^");
			dataOutputStream.writeBytes(password);
			dataOutputStream.writeBytes("_EOM");
			dataOutputStream.flush();
			dataOutputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static String md5(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = bytesToHex(digest);

            return myHash;

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
