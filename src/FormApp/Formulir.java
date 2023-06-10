package FormApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Formulir extends JFrame {
	
	public Connection con;
	public String sql="";
	public Statement stat;
	public ResultSet res;
	public ResultSetMetaData resmd;
	
	private JPanel contentPane;
	private JTextField namaField;
	private JTextField npmField;
	private JTextField umurField;
	private JTable table;
	private JTable tableData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulir frame = new Formulir();
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
	public Formulir() {
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 340);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Google Kickstart for Student Register Form");
		lblNewLabel.setBounds(133, 10, 267, 13);
		contentPane.add(lblNewLabel);
		
		//Field nama
		
		JLabel lblNama = new JLabel("Nama : ");
		lblNama.setBounds(10, 72, 45, 13);
		contentPane.add(lblNama);
		
		namaField = new JTextField();
		namaField.setBounds(49, 69, 351, 19);
		contentPane.add(namaField);
		namaField.setColumns(10);
		
		//Field NPM
		
		JLabel lblNPM = new JLabel("NPM  : ");
		lblNPM.setBounds(10, 46, 45, 13);
		contentPane.add(lblNPM);
		
		npmField = new JTextField();
		npmField.setBounds(49, 43, 351, 19);
		contentPane.add(npmField);
		npmField.setColumns(10);
		
		//Field Umur
		
		JLabel lblUmur = new JLabel("Umur : ");
		lblUmur.setBounds(10, 95, 45, 13);
		contentPane.add(lblUmur);
		
		umurField = new JTextField();
		umurField.setBounds(49, 92, 351, 19);
		contentPane.add(umurField);
		umurField.setColumns(10);
		
		//Memproses data masuk
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() { //Event Listener ketika user menekan tombol register
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud","root",""); //Buat koneksi ke localhost
					sql = "INSERT INTO peserta VALUES ('"+npmField.getText()+"','"+namaField.getText()+"','"+umurField.getText()+"')"; //Memasukkan perintah SQL
					stat = con.createStatement();
					stat.execute(sql);
					npmField.setText("");
					namaField.setText("");
					umurField.setText("");
					npmField.requestFocus();
					{
						javax.swing.JOptionPane.showMessageDialog(null, "Berhasil mendaftar!"); //Data berhasil masuk ke database
					}
				} catch(Exception e1) { 
					javax.swing.JOptionPane.showMessageDialog(null, "Gagal mendaftar!" + e1); //Data gagal masuk ke database
				}
			}
		});
		btnRegister.setBounds(49, 118, 85, 21);
		contentPane.add(btnRegister);
		
		//Tombol cari
		
		JButton btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud","root","");
					sql = "SELECT * FROM peserta WHERE npm = '"+npmField.getText()+"'";
					stat = con.createStatement();
					res = stat.executeQuery(sql);
					if (res.next()) {
						namaField.setText(res.getString("Nama"));
						umurField.setText(res.getString("Umur"));
					} else {
						javax.swing.JOptionPane.showMessageDialog(null,"Data tidak ada!");
					}
				} catch(Exception e2) {
					javax.swing.JOptionPane.showMessageDialog(null,"Gagal mencari data!" + e2);
				}
			}
		});
		btnCari.setBounds(144, 118, 71, 21);
		contentPane.add(btnCari);
		
		// Tombol edit
		JButton btnEdit = new JButton("Edit Info");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud","root","");
					sql = "UPDATE peserta SET nama = '"+namaField.getText()+"', umur = '"+umurField.getText()+"' WHERE npm = '"+npmField.getText()+"'";
					stat = con.createStatement();
					stat.execute(sql);
					namaField.setText("");
					umurField.setText("");
					{
						javax.swing.JOptionPane.showMessageDialog(null, "Berhasil mengedit data!"); //Data berhasil masuk ke database
					}
				} catch(Exception e3) {
					javax.swing.JOptionPane.showMessageDialog(null,"Gagal mengedit data!" + e3);
				}
			}
		});
		btnEdit.setBounds(225, 118, 85, 21);
		contentPane.add(btnEdit);
		
		
		//Tombol delete
		
		JButton btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud","root","");
					sql = "DELETE FROM peserta WHERE npm = '"+npmField.getText()+"'";
					stat = con.createStatement();
					stat.execute(sql);
					npmField.setText("");
					namaField.setText("");
					umurField.setText("");
					npmField.requestFocus();
					{
						javax.swing.JOptionPane.showMessageDialog(null,"Data berhasil dihapus!");
					}
				} catch(Exception e3) {
					javax.swing.JOptionPane.showMessageDialog(null, "Data gagal dihapus!" + e3);
				}
			}
		});
		btnDelete.setBounds(315, 118, 85, 21);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 149, 351, 79);
		contentPane.add(scrollPane);
		
		tableData = new JTable();
		scrollPane.setViewportView(tableData);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud","root","");
					sql = "SELECT * FROM peserta";
					stat = con.createStatement();
					res = stat.executeQuery(sql);
					java.sql.ResultSetMetaData resmd = res.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tableData.getModel();
					
					int cols = resmd.getColumnCount();
					String[] namaCol = new String [cols];
					for(int i=0;i < cols;i++)
						namaCol[i] = resmd.getColumnName(i+1);
					model.setColumnIdentifiers(namaCol);
					String npm,nama,umur;
					while(res.next()) {
						npm = res.getString(1);
						nama = res.getString(2);
						umur = res.getString(3);
						String[] row = {npm,nama,umur};
						model.addRow(row);
					}
					
				} catch (Exception e4) {
					javax.swing.JOptionPane.showMessageDialog(null, "Data gagal dihapus!" + e4);
				}
			}
		});
		btnRefresh.setBounds(315, 234, 85, 21);
		contentPane.add(btnRefresh);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableData.setModel(new DefaultTableModel());
			}
		});
		btnNewButton.setBounds(225, 234, 85, 21);
		contentPane.add(btnNewButton);
	}
}
