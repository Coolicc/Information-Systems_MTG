<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<header id="header">
        <nav class="navbar navbar-default navbar-static-top" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                   <div class="navbar-brand">
						<a href="/is2projekatWeb/"><h1>MTG:Collect</h1></a>
					</div>
                </div>				
                <div class="navbar-collapse collapse">							
					<div class="menu">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation"><a href="/is2projekatWeb/">Home</a></li>
							<li role="presentation"><a href="/is2projekatWeb/cards/viewCardsPage?search=&page=1">Cards</a></li>
							<c:if test="${!empty player }">
								<li role="presentation"><a href="/is2projekatWeb/cards/showCollection?search=&page=1">Collection</a></li>
								<li role="presentation"><a href="/is2projekatWeb/cards/showDecks">Decks</a></li>
								<li role="presentation"><a href="/is2projekatWeb/player/tournaments">Tournaments</a></li>
								<c:if test="${player.admin == 1 }">
									<li role="presentation"><a href="/is2projekatWeb/reports/players.pdf">Players Report</a></li>
									<li role="presentation"><a href="/is2projekatWeb/reports/cards.pdf">Cards Report</a></li>
								</c:if>
								<li role="presentation"><a href="/is2projekatWeb/logout">Logout</a></li>
							</c:if>
							<c:if test="${!empty club }">
								<li role="presentation"><a href="/is2projekatWeb/club/tournaments">Tournaments</a></li>
								<li role="presentation"><a href="/is2projekatWeb/logout">Logout</a></li>
							</c:if>
							<c:if test="${empty club && empty player}">
								<li role="presentation"><a href="/is2projekatWeb/login">Log In</a></li>
								<li role="presentation"><a href="/is2projekatWeb/register">Register</a></li>
							</c:if>					
						</ul>
					</div>
				</div>		
            </div><!--/.container-->
        </nav><!--/nav-->		
    </header><!--/header-->	
