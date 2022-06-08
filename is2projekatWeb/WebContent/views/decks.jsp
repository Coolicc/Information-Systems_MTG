<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:if test="${empty player}">
			<div class="text-center">        
                <h2>You must log in as a player first!</h2>
            </div> 
</c:if>
<c:if test="${!empty player}">
	<c:forEach var="d" items="${decks }">
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td colspan="2"><div class="text-center"><h3>${d.deckName }</h3></div></td>
			</tr>
			<tr>
				<th style="color: #000;">Description: </th>
				<td style="color: #000; padding-left: 15px;">${d.description }</td>
				
			</tr>
			<tr>
				<td colspan="2" class="text-center"><a href="/is2projekatWeb/cards/showDeck/${d.deckID }"><button class="btn btn-primary btn-lg">View Deck</button></a></td>
			</tr>
		</table>
	</c:forEach>
	<section class="contact-page">
        <div class="container">
            <div class="text-center">        
                <h2>Create Deck</h2>
            </div> 
            <div class="row contact-wrap"> 
            	<c:if test="${!empty errorC }">
                	<div class="status alert alert-success" style="background-color: #ea7e7e; color: #42f44b;">${errorC }</div>
                </c:if>
                <form:form modelAttribute="newDeck" id="main-contact-form" class="contact-form" name="contact-form" method="post" action="/is2projekatWeb/cards/addDeck">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Deck Name:</label>
                            <form:input path="deckName" type="text" name="deckName" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Short Description</label>
                            <form:input path="description" type="text" name="description" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Create" name="submit" class="btn btn-primary btn-lg" required="required">
                        </div>
                     </div>
                </form:form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
</c:if>