package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metiers.Commande;
import metiers.Medicament;

public class CommandeDAO {

	public ArrayList<Commande> findByIdClient( String IdClient) {
		ArrayList<Commande> lstCmd= new ArrayList<Commande>();
		Connection cnx= Connexion.getInstance();
		Commande c= null;
		try {
		String req="select * from Commande where IdClient=?";
		PreparedStatement st=cnx.prepareStatement(req);
		st.setString(1, IdClient);
		ResultSet r= st.executeQuery();

		while (r.next()) {
		   c= new Commande(r.getInt(1),r.getString(2),r.getDate(3),r.getDouble(4));
		   lstCmd.add(c);
		   
		}
		for(Commande me : lstCmd) {
			System.out.println(me);
		}
		
		st.close();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		finally {
		Connexion.close();
		}
		return lstCmd;
		}

		public void addCommande(Commande c) {
		if(c==null)
		return;
		Connection cnx= Connexion.getInstance();
		String rq1 = "insert into commande (NumCmd,DateCmd,IdClient,PrixTot) values (?,?,?,?)";
		try {
		PreparedStatement stm2 = cnx.prepareStatement(rq1);
		stm2.setInt(1,c.getNumCmd());
		stm2.setObject(2,c.getDateCmd());
		stm2.setString(3,c.getIdClient());
		stm2.setDouble(4,c.getPrixTot());
		int n=stm2.executeUpdate();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}

		public void deleteCmd(int NumCmd) {
		Connection cnx= Connexion.getInstance();
		String rq3= "delete from commande where NumCmd=?";
		PreparedStatement st;
		int n=0;
		try {
		st = cnx.prepareStatement(rq3);
		st.setInt(1, NumCmd);
		n= st.executeUpdate();
		st.close();
		   System.out.println("La suppression du commande est effectue avec succe!");
		} catch (SQLException e) {
		System.out.println("Exception dans la suppression d'une commande !!");
		}
		}

		
}
