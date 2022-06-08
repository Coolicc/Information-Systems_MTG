<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:if test="${empty player || pleyer.admin == 0 }">
	<div class="text-center">        
    	<h2>You don't have the permission to add cards! Please log in as an administrator.</h2>
    </div> 
</c:if>
<c:if test="${!empty player && player.admin == 1 }">
    <section class="contact-page">
        <div class="container">
            <div class="text-center">        
                <h2>Add a card</h2>
            </div> 
            <div class="row contact-wrap"> 
            	<c:if test="${!empty errorP }">
                	<div class="status alert alert-success" style="background-color: #ea7e7e; color: #42f44b;">${ errorP}</div>
                </c:if>
                <form:form modelAttribute="cardImage" id="main-contact-form" class="contact-form" name="contact-form" method="post" enctype="multipart/form-data" action="/is2projekatWeb/cards/saveCard">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Card Name</label>
                            <form:input path="cardName" type="text" name="cardName" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Set</label>
                            <form:input path="cardSet" type="text" name="cardSet" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Card Text</label>
                            <form:input path="cardText" type="text" name="cardText" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Card Flavour Text</label>
                            <form:input path="flavour" type="text" name="flavour" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Loyalty</label>
                            <form:input path="loyalty" type="number" name="loyalty" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Power</label>
                            <form:input path="power" type="text" name="power" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Toughness</label>
                            <form:input path="toughness" type="text" name="toughness" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Mana</label>
                            <form:input path="manas" type="text" name="manas" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Rarity</label>
                            <form:input path="rarity" type="text" name="rarity" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Type</label>
                            <form:input path="type" type="text" name="type" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Full Image</label>
                            <form:input path="image" type="file" name="image" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Title Image</label>
                            <form:input path="titleImage" type="file" name="titleImage" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Add Card" name="submit" class="btn btn-primary btn-lg" required="required"/>
                        </div>
                     </div>
                </form:form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
</c:if>