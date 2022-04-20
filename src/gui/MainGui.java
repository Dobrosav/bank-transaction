package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import database.Funcionalities;
import database.TransferException;

import java.util.ArrayList;
import database.entities.*;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
public class MainGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
	}
	private JTextComponent textPane;
	private JComboBox comboBox;
	private JTextField textField;
	private JTextField address;
	private JTextField hn;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_1;
	private JTextField pc;
	private JTextPane textPane_1;
	private JTextField idrText;
	private JTextField sendertext;
	private JTextField cctext;
	private JTextField ammount;
	private JButton btnNewButton_2;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1179, 808);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ArrayList<Client> l=Funcionalities.getAllClients();
		String id[]=new String[l.size()];
		
		for(int i=0; i<l.size();i++) {
			id[i]=l.get(i).toString().split(":")[0];
		}
		JLabel lblNewLabel = new JLabel("All clients");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(120, 28, 117, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAllClient = new JButton("get all clients");
		btnAllClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ArrayList<Client> l=Funcionalities.getAllClients();
				for(Client c:l) {
					textPane.setText(textPane.getText()+c+"\n");
				}
			}
		});
		btnAllClient.setBounds(211, 26, 127, 21);
		frame.getContentPane().add(btnAllClient);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(90, 49, 597, 287);
		frame.getContentPane().add(textPane);
		
		comboBox = new JComboBox(id);
		comboBox.setBounds(90, 446, 100, 21);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("get client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String string=comboBox.getSelectedItem().toString();
				int id=Integer.parseInt(string);
				Client c=Funcionalities.getClientById(id);
				textField.setText(c.toString());
			}
		});
		btnNewButton.setBounds(706, 446, 100, 21);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(200, 446, 496, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		address = new JTextField();
		address.setBounds(94, 497, 96, 19);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		hn = new JTextField();
		hn.setBounds(294, 497, 96, 19);
		frame.getContentPane().add(hn);
		hn.setColumns(10);
		
		pc = new JTextField();
		pc.setBounds(461, 497, 96, 19);
		frame.getContentPane().add(pc);
		pc.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setBounds(22, 500, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Home number");
		lblNewLabel_2.setBounds(204, 500, 80, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Postal code");
		lblNewLabel_3.setBounds(392, 500, 59, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		btnNewButton_1 = new JButton("update address");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string=comboBox.getSelectedItem().toString();
				int id=Integer.parseInt(string);
				if(Funcionalities.updateClientAddress(id, address.getText(), Integer.parseInt(hn.getText()) , Integer.parseInt(pc.getText()))) {
					textField.setText("update succed");
				}
			}
		});
		btnNewButton_1.setBounds(567, 496, 130, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		textPane_1 = new JTextPane();
		textPane_1.setFocusTraversalPolicyProvider(true);
		textPane_1.setBounds(764, 58, 325, 209);
		frame.getContentPane().add(textPane_1);
		
		JLabel lblNewLabel_4 = new JLabel("id receiver");
		lblNewLabel_4.setBounds(177, 602, 92, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		idrText = new JTextField();
		idrText.setBounds(279, 599, 96, 19);
		frame.getContentPane().add(idrText);
		idrText.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("transfer");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_5.setBounds(211, 526, 363, 55);
		frame.getContentPane().add(lblNewLabel_5);
		
		sendertext = new JTextField();
		sendertext.setBounds(506, 599, 96, 19);
		frame.getContentPane().add(sendertext);
		sendertext.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("ld sender");
		lblNewLabel_6.setBounds(406, 602, 68, 13);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Currency code");
		lblNewLabel_7.setBounds(642, 602, 80, 13);
		frame.getContentPane().add(lblNewLabel_7);
		
		cctext = new JTextField();
		cctext.setBounds(764, 599, 96, 19);
		frame.getContentPane().add(cctext);
		cctext.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("ammount");
		lblNewLabel_8.setBounds(939, 602, 45, 13);
		frame.getContentPane().add(lblNewLabel_8);
		
		ammount = new JTextField();
		ammount.setBounds(1010, 599, 96, 19);
		frame.getContentPane().add(ammount);
		ammount.setColumns(10);
		
		JButton button = new JButton("New button");
		button.setBounds(625, 602, -9, -4);
		frame.getContentPane().add(button);
		
		btnNewButton_2 = new JButton("TRANSFER");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Funcionalities.transfer(Integer.parseInt(idrText.getText()), Integer.parseInt(sendertext.getText()), Integer.parseInt(ammount.getText()), Integer.parseInt(cctext.getText()));
					textPane.setText("TRANSFER SUCCED");
				} catch (TransferException e1) {
					// TODO Auto-generated catch block
					textPane.setText(e1.getMessage());
				}
			}
		});
		btnNewButton_2.setBounds(706, 669, 139, 55);
		frame.getContentPane().add(btnNewButton_2);
		ArrayList<Currency> lc=Funcionalities.getCurrencies();
		for(Currency c:lc)
			textPane_1.setText(textPane_1.getText()+c+"\n");
	}
}
