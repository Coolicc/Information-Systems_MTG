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
                <h2>Player Login</h2>
            </div> 
            <div class="row contact-wrap"> 
            	<c:if test="${!empty errorP }">
                	<div class="status alert alert-success" style="background-color: #ea7e7e; color: #42f44b;">${errorP }</div>
                </c:if>
                <form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="/is2projekatWeb/player/login">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" name="username" class="form-control" required="required">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" required="required">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Log in" name="submit" class="btn btn-primary btn-lg" required="required">
                        </div>
                     </div>
                </form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
    <section class="contact-page">
        <div class="container">
            <div class="text-center">        
                <h2>Club Login</h2>
            </div> 
            <div class="row contact-wrap"> 
            	<c:if test="${!empty errorC }">
                	<div class="status alert alert-success" style="background-color: #ea7e7e; color: #42f44b;">${errorC }</div>
                </c:if>
                <form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="/is2projekatWeb/club/login">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="text" name="email" class="form-control" required="required">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" required="required">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Log in" name="submit" class="btn btn-primary btn-lg" required="required">
                        </div>
                    </div>
                </form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
</c:if>