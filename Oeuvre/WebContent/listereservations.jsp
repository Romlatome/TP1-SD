<%-- 
    Document   : listereservations
    Created on : 5 d�c. 2010, 17:12:15
    Author     : alain
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste r�servations</title>
	
	<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />
	<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
	<script src="js/hideshow.js" type="text/javascript"></script>
	<script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery.equalHeight.js"></script>
	<script type="text/javascript">
	$(document).ready(function() 
    	{ 
      	  $(".tablesorter").tablesorter(); 
   	 } 
	);
	$(document).ready(function() {

	//When page loads...
	$(".tab_content").hide(); //Hide all content
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab
	$(".tab_content:first").show(); //Show first tab content

	//On Click Event
	$("ul.tabs li").click(function() {

		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});

});
    </script>
    <script type="text/javascript">
    $(function(){
        $('.column').equalHeight();
    });
</script>

</head>


<body>

	<header id="header">
		<hgroup>
			<h1 class="site_title"><a href="index.html"></a>Polytech'Lyon</h1>
			<h2 class="section_title">Gestion des oeuvres</h2><div class="btn_view_site"></div>
		</hgroup>
	</header> <!-- end of header bar -->
	
	<section id="secondary_bar">
		<div class="user">
			
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs"><a href="accueil.jsp">Accueil</a> <div class="breadcrumb_divider"></div> <a class="current">Oeuvre</a></article>
		</div>
	</section><!-- end of secondary bar -->
	
	<aside id="sidebar" class="column">
		
		<hr/>
		<h3>Oeuvres</h3>
		<ul class="toggle">
			<li class="icn_new_article"><a href="userServlet?action=ajoutOeuvre">Nouvelle Oeuvre</a></li>
			<li class="icn_categories"><a href="userServlet?action=afficheOeuvre">Liste des oeuvres</a></li>
			<li class="icn_tags"><a href="userServlet?action=listereservation">Confirmer une reservation</a></li>
		</ul>
		<h3>Connexion</h3>
		<ul class="toggle">
			<li class="icn_jump_back"><a href="userServlet?action=deconnecter">Se deconnecter</a></li>
		</ul>
		
		<footer>
			<hr />
			<p><strong>TP1 ASSENDAL Abdessamed & WAILLE Romaric</strong></p>
			<p>Concu pour le cours de SD </a></p>
		</footer>
	</aside><!-- end of sidebar -->
	
	<section id="main" class="column">
		
		
		
		
		<article class="module width_3_quarter">
		<header><h3 class="tabs_involved">Confirmer une reservation</h3>
		
		</header>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   					<th>Titre </th> 
    				<th>Date</th> 
    				<th>Statut</th> 
    				<th>Adherent</th> 
    				<th>Actions</th> 
				</tr> 
			</thead> 
			<tbody> 
				<c:forEach items="${listeReserv}" var="liste">
									<tr>
										<td>${liste.oeuvre.titre}</td>
										<td>${liste.date_reservation}</td>
										<td>${liste.statut}</td>
										<td>${liste.adherent.prenom_adherent} ${liste.adherent.nom_adherent}</td>
										<td><a
											href="userServlet?action=confirmerOeuvre&num=${liste.id_oeuvre}">Confirmer</a></td>
									</tr>
								</c:forEach>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->
			
	


</body>

</html>
