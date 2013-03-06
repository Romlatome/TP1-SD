package controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.Adherent;
import modeles.Oeuvre;
import modeles.Proprietaire;
import modeles.Reservation;


/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ACTION_TYPE = "action";
	private static final String SAISIE_OEUVRE = "saisieOeuvre";
	private static final String AFFICHER_OEUVRE = "afficheOeuvre";
	private static final String AJOUT_OEUVRE = "ajoutOeuvre";
	private static final String MODIFIER_OEUVRE = "Modifier";

	private static final String RESERVER_OEUVRE = "reserverOeuvre";
	private static final String CONFIRMER_RESERVER_OEUVRE = "confirmerOeuvre";
	private static final String RESERVER_FIN_OEUVRE = "reservationliste";
	private static final String LISTE_RESERVATION = "listereservation";
	private static final String FIN_MODIFIER_OEUVRE = "modifOeuvre";
	private static final String ERROR_PAGE = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			processusTraiteRequete(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processusTraiteRequete(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
		protected void processusTraiteRequete(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				Exception {
			String login, pwd, erreur;
			// Par dŽfaut si on ne rentre dans aucun des cas 
			// prŽvu la page de redirection sera login.jsp
			String pageReponse = "/index.jsp";
			erreur = "";
			String actionName = request.getParameter(ACTION_TYPE);
		
			System.out.println("action"+actionName);
			// execute l'action
	        // Saisie du stage :
			if (AJOUT_OEUVRE.equals(actionName)) {
				pageReponse = "/oeuvre.jsp";
				Proprietaire p = new Proprietaire ();
				ArrayList<Proprietaire> listeProprio = p.Liste();
				request.setAttribute("listeProprio",listeProprio);
				request.setAttribute("etat", "Ajouter");
				request.setAttribute("actionName", "saisieOeuvre");
	            //Atttributs utilisŽs pour savoir si il faut ajouter ou modifier le stage
			}
			else if(SAISIE_OEUVRE.equals(actionName))
			{
				pageReponse = "/accueil.jsp";
				String titre = request.getParameter("txtTitre");
				String prix = request.getParameter("txtPrix");
				String id_proprio = request.getParameter("lstProprietaires");
			
				Oeuvre o = new Oeuvre (Integer.parseInt(id_proprio));
				o.setPrix(Double.parseDouble(prix));
				o.setTitre(titre);
				o.Ajouter();
			}
			else if (AFFICHER_OEUVRE.equals(actionName)){
				Oeuvre o = new Oeuvre();
				ArrayList<Oeuvre> listeOeuvres = o.Liste();
				pageReponse = "/catalogue.jsp";
				request.setAttribute("listeOeuvres",listeOeuvres);
			}
			else if (RESERVER_OEUVRE.equals(actionName)){
				// quand on clique sur le bouton reserver on affiche l'oeuvre voulu
				String num = request.getParameter("num");
				
				Oeuvre o = new Oeuvre();
				o.Lire_Id(Integer.parseInt(num));
				// on verifie que l'oeuvre qu'il veut reserver est disponible
				if (o.getEtat_oeuvrevente().equals("L"))
				{
					pageReponse = "/reservation.jsp";
					System.out.println("titre"+o.getTitre());
					request.setAttribute("oeuvreReservee",o);
					// liste adherents
					Adherent a = new Adherent();
					ArrayList<Adherent> lAdherent = a.Liste(); 
					request.setAttribute("listeAdherent",lAdherent);
				}
				else
				{
					request.setAttribute("message","nonReservable"); // message d'erreur pour dire qu'elle n'est pas reservable
					pageReponse = "/userServlet?action=afficheOeuvre";
				}
			}
			else if (MODIFIER_OEUVRE.equals(actionName)){
				Proprietaire p = new Proprietaire ();
				ArrayList<Proprietaire> listeProprio = p.Liste();
				request.setAttribute("listeProprio",listeProprio);
				request.setAttribute("etat", "Modifier");
				request.setAttribute("actionName", "modifOeuvre");
				
				String num = request.getParameter("num");
				Oeuvre o = new Oeuvre();
				o.Lire_Id(Integer.parseInt(num));
				
				request.setAttribute("rOeuvre",o);
				pageReponse = "/oeuvre.jsp";
			}
			else if (FIN_MODIFIER_OEUVRE.equals(actionName)){
				String num = request.getParameter("txtNum");
				String titre = request.getParameter("txtTitre");
				String prix = request.getParameter("txtPrix");
				String id_proprio = request.getParameter("lstProprietaires");
				System.out.println(id_proprio);
				Oeuvre o = new Oeuvre (Integer.parseInt(id_proprio));
				o.setId_oeuvre(Integer.parseInt(num));
				o.setPrix(Double.parseDouble(prix));
				o.setTitre(titre);
				o.Modifier();
				pageReponse = "/userServlet?action=afficheOeuvre";
				request.setAttribute("message","modif");
				
			}
			else if (RESERVER_FIN_OEUVRE.equals(actionName)){
				String id_oeuvre = request.getParameter("num");
				pageReponse = "/userServlet?action=listereservation";
				String id_adherent = request.getParameter("lstAdherents");
				String date = request.getParameter("txtDate");
				System.out.println(date);
				Reservation r = new Reservation(Integer.parseInt(id_oeuvre),Integer.parseInt(id_adherent));
				r.setDate_reservation(date);
				r.Ajouter();
				
				// mettre à la lettre R
				Oeuvre o = new Oeuvre();
				o.OeuvreReservée(Integer.parseInt(id_oeuvre));
				
			}
			else if (LISTE_RESERVATION.equals(actionName)){
				pageReponse = "/listereservations.jsp";
				
				Reservation r = new Reservation();
				ArrayList <Reservation> lReservation = r.Liste();
				
				request.setAttribute("listeReserv",lReservation);
			}
			else if (CONFIRMER_RESERVER_OEUVRE.equals(actionName)){
				pageReponse = "/userServlet?action=listereservation";
				String num_oeuvre = request.getParameter("num");
				Reservation r = new Reservation();
				r.Lire(Integer.parseInt(num_oeuvre));
			
				// si elle n'est pas déjà confirmée
				if(!r.getStatut().equals("Confirmee")){
					request.setAttribute("message","oeuvreConfirmee");
				r.Modifier();}
				else
				{
					request.setAttribute("message","oeuvreDejaConfirmee");
				}
			}
			
			else
			{
			try{
			    login = request.getParameter("txtLogin");
			    pwd = request.getParameter("txtPwd");
			   
			    if (login.compareTo("user")==0 && pwd.compareTo("user")==0){
			       
			        pageReponse = "/accueil.jsp";
			    }else{
			        erreur = "Login ou mot de passe inconnus !";
			    }
			    request.setAttribute("erreur", erreur);
			
			}catch(Exception e){
			    request.setAttribute("erreur", erreur);
			} 
			}
			
			RequestDispatcher dsp = request.getRequestDispatcher(pageReponse);
		    dsp.forward(request, response);
			
		}
		
	}


