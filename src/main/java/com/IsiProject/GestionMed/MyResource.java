package com.IsiProject.GestionMed;
import metiers.*;


import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import DAO.CommandeDAO;
import DAO.MedicamentDAO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @GET
    @Path("findAllMedi")
    @Produces("application/xml")
    public ArrayList<Medicament> findAll() {
        MedicamentDAO med=new MedicamentDAO();
        return(med.findAll());
    }
    
    @DELETE
    @Path("deleteMedi/{id}")
    public void delete(@PathParam("id") String idMed) {
    	MedicamentDAO medi=new MedicamentDAO();
    	medi.delete(idMed);
    }
    
    @PUT
    @Path("saveMedi/{type}/{nom}/{date}/{qte}/{pxvente}")
    public void save (@PathParam("type") String Type,@PathParam("nom") String Nom ,
    		@PathParam("date") Date DateExpiration,@PathParam("qte") int Qte,
    		@PathParam("pxvente") Double Prixvente ) {
    	MedicamentDAO medi=new MedicamentDAO();
    	medi.save(Type, Nom, DateExpiration, Qte, Prixvente);
    }
    
    @GET
    @Path("findById/{idClient}")
    public ArrayList<Commande> findByIdClient(@PathParam("idClient") String IdClient){
    	CommandeDAO cmd=new CommandeDAO();
    	return(cmd.findByIdClient(IdClient));
    }
     
   @POST
   @Path("addCmd")
	public void AddCommande(@RequestBody Commande c1) {
    	Commande c =new Commande(c1.getNumCmd(),c1.getIdClient(),c1.getDateCmd(),c1.getPrixTot());
    	CommandeDAO cmd=new CommandeDAO();
    	cmd.addCommande(c);
    }

    @DELETE
    @Path("deleteCmd/{numCmd}")
    public void deleteCmd(@PathParam("numCmd") int NumCmd) {
    	CommandeDAO cmd=new CommandeDAO();
    	cmd.deleteCmd(NumCmd);
    }
}
