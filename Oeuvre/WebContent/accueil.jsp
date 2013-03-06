<%-- 
    Document   : accueil
    Created on : 3 nov. 2010, 15:03:01
    Author     : alain
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Accueil</title>
	
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
		
		<article class="module width_full">
			<header><h3>Message d'accueil</h3></header>
				<div class="module_content">
					<h1>Bienvenue sur le site de gestion des oeuvres de Polytech'Lyon</h1>
					<p>Utilises le menu a gauche pour pouvoir modifier , ajouter des oeuvres ainsi que de confirmer une reservation</p>
				</div>
		</article><!-- end of styles article -->
		<div class="spacer"></div>
	</section>


</body>

</html>