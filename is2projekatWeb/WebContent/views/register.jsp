<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:if test="${!empty player || !empty club }">
			<div class="text-center">        
                <h2>You are already logged in!</h2>
            </div> 
</c:if>
<c:if test="${empty player && empty club }">
	<section class="contact-page">
        <div class="container">
            <div class="text-center">        
                <h2>Player Register</h2>
            </div> 
            <div class="row contact-wrap"> 
            	<c:if test="${!empty errorP }">
                	<div class="status alert alert-success" style="background-color: #ea7e7e; color: #42f44b;">${ errorP}</div>
                </c:if>
                <form:form modelAttribute="playerImage" id="main-contact-form" class="contact-form" name="contact-form" method="post" enctype="multipart/form-data" action="/is2projekatWeb/player/savePlayer">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Username</label>
                            <form:input path="username" type="text" name="username" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <form:input path="password" type="password" name="password" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <form:input path="email" type="text" name="email" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>First Name</label>
                            <form:input path="firstName" type="text" name="firstName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <form:input path="lastName" type="text" name="lastName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Avatar</label>
                            <form:input path="avatar" type="file" name="avatar" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Register" name="submit" class="btn btn-primary btn-lg" required="required"/>
                        </div>
                     </div>
                </form:form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
    <section class="contact-page">
        <div class="container">
            <div class="text-center">        
                <h2>Club Register</h2>
            </div> 
            <div class="row contact-wrap"> 
            	<c:if test="${!empty errorC }">
                	<div class="status alert alert-success" style="background-color: #ea7e7e; color: #42f44b;">${errorC }</div>
                </c:if>
                <form:form modelAttribute="clubReg" id="main-contact-form" class="contact-form" name="contact-form" method="post" action="/is2projekatWeb/club/saveClub">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Club Name</label>
                            <form:input path="clubName" type="text" name="clubName" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <form:input path="password" type="password" name="password" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <form:input path="email" type="text" name="email" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <form:input path="address" type="text" name="address" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Register" name="submit" class="btn btn-primary btn-lg" required="required">
                        </div>
                     </div>
                </form:form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
</c:if>