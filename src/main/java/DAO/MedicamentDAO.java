package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import metiers.Medicament;

public class MedicamentDAO {

	/*public void addMedicament(Medicament m) {
	if(m==null)
	return;
	Connection cnx= Connexion.getInstance();
	String rq1 = "insert into Medicament (IdMedicament,Type,Nom,DateExpiration,Qte,PrixVente) values (?,?,?,?,?,?)";
	try {
	PreparedStatement stm2 = cnx.prepareStatement(rq1);
	stm2.setString(1,m.getIdMedicament());
	stm2.setString(2,m.getType());
	stm2.setString(3,m.getNom());
	stm2.setObject(4, (m.getDateexpiration()));
	stm2.setInt(5,m.getQte());
	stm2.setDouble(6,m.getPrixvente());

	int n=stm2.executeUpdate();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	}*/

	public void delete(String IdMed) {
	Connection cnx= Connexion.getInstance();
	String rq3= "delete from medicament where IdMedicament=?";
	PreparedStatement st;
	int n=0;
	try {
	st = cnx.prepareStatement(rq3);
	st.setString(1, IdMed);
	n= st.executeUpdate();
	st.close();
	   System.out.println("La suppression du medicament est effectue avec succe!");
	} catch (SQLException e) {
	System.out.println("Exception dans la suppression d'un medicament !!");
	}
	}

	public ArrayList<Medicament> findAll() {
	ArrayList<Medicament> lstmed= new ArrayList<Medicament>();
	Connection cnx= Connexion.getInstance();
	Medicament m= null;
	try {
	String req="select * from medicament ";
	PreparedStatement st=cnx.prepareStatement(req);
	ResultSet r= st.executeQuery();

	while (r.next()) {
	   m= new Medicament (r.getString(1),r.getString(2),r.getString(3),r.getDate(4),r.getInt(5),r.getDouble(6));
	   lstmed.add(m);  
	}
	for(Medicament me : lstmed) {
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
	return lstmed;
	}

	public ArrayList<Medicament> findByNom( String Nom) {
	ArrayList lstmed= new ArrayList<Medicament>();
	Connection cnx= Connexion.getInstance();
	Medicament m= null;
	try {
	String req="select * from medicament where Nom=?";
	PreparedStatement st=cnx.prepareStatement(req);
	st.setString(1, Nom);
	ResultSet r= st.executeQuery();

	while (r.next()) {
	   m= new Medicament (r.getString(1),r.getString(2),r.getString(3),r.getDate(4),r.getInt(5),r.getDouble(6));
	   lstmed.add(m);   
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
	return lstmed;
	}

	public ArrayList<Medicament> findByType( String Type) {
	ArrayList lstmed= new ArrayList<Medicament>();
	Connection cnx= Connexion.getInstance();
	Medicament m= null;
	try {
	String req="select * from medicament where Type=?";
	PreparedStatement st=cnx.prepareStatement(req);
	st.setString(1, Type);
	ResultSet r= st.executeQuery();

	while (r.next()) {
	   m= new Medicament (r.getString(1),r.getString(2),r.getString(3),r.getDate(4),r.getInt(5),r.getDouble(6));
	   lstmed.add(m);   
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
	return lstmed;
	}

	public ArrayList<Medicament> findByIdMed( String IdMed) {
	ArrayList lstmed= new ArrayList<Medicament>();
	Connection cnx= Connexion.getInstance();
	Medicament m= null;
	try {
	String req="select * from medicament where IdMed=?";
	PreparedStatement st=cnx.prepareStatement(req);
	st.setString(1, IdMed);
	ResultSet r= st.executeQuery();

	while (r.next()) {
	   m= new Medicament (r.getString(1),r.getString(2),r.getString(3),r.getDate(4),r.getInt(5),r.getDouble(6));
	   lstmed.add(m);
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
	return lstmed;
	}

	/*public void update (Medicament m) {
	String rq= "update medicament set Qte=?, PrixVente=? where IdMedicament=?";
	Connection cnx= Connexion.getInstance();
	PreparedStatement st= null;
	int n=0;
	try {
	st = cnx.prepareStatement(rq);
	st.setInt(1, m.getQte());
	st.setDouble(2, m.getPrixvente());
	st.setString(3, m.getIdMedicament());
	n= st.executeUpdate();
	st.close();
	} catch (SQLException e) {
	System.out.println("Exception dans modification d'un medicament ");
	}
	}*/
	
	public void save (String Type, String Nom ,Date DateExpiration, int Qte,Double Prixvente ) {
	int y=DateExpiration.getYear();
	int m=DateExpiration.getMonth();

	String ID= Type+"_"+Nom+"_"+String.valueOf(m)+String.valueOf(y);

	Connection cnx= Connexion.getInstance();
	try {
	String req= "update medicament set Qte=? , PrixVente=? where IdMedicament=?";
	PreparedStatement st=cnx.prepareStatement(req);
	            st.setInt(1,Qte);
	            st.setDouble(2,Prixvente);
	            st.setString(3,ID);
	int n=st.executeUpdate();
	st.close();
	           
	if (n==1) {
	System.out.println("mise a jour avec succes");
	}
	else {
	String req1= "insert into medicament (IdMedicament, Type,Nom,DateExpiration,Qte,PrixVente) values (?,?,?,?,?,?)";
	st=cnx.prepareStatement(req1);
	st.setString(1,ID);
	           st.setString(2,Type);
	           st.setString(3,Nom);
	           st.setObject(4,DateExpiration);
	           st.setInt(5,Qte);
	           st.setDouble(6,Prixvente);
	           n=st.executeUpdate();         
	}
	st.close();
	}
	catch(SQLException e) {
	e.printStackTrace();
	}
	finally{
	Connexion.close();
	}
	}
}
