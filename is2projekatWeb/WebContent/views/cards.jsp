<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:if test="${!empty player && player.admin == 1 }">
		<a style="padding-left: 53px;" href="/is2projekatWeb/cards/addCardInit"><button class="btn btn-primary btn-lg">Add a card</button></a>
	</c:if>
<div class="row" style="padding-top: 30px;">
	<form action="/is2projekatWeb/cards/viewCardsPage" method="get">
		<div style="padding-left: 70px;" class="form-group">
			<label style="color: #000;">Search cards by name:</label><br>
			<input type="text" value="${search }" style="color: #000;"  name="search">
		</div>
		<div style="padding-left: 70px;" class="form-group">
			<input type="hidden" name="page" value="1">
			<input  type="submit" value="Search" class="btn btn-primary btn-lg">
		</div>
	</form>
</div>
<div class="gallery">
		<c:forEach items="${cardsPage }" var="card">
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
				<img height="370" width="265" src="/is2projekatWeb/img/full_${card.cardName}.jpg"/>
				</div>
				<div class="col-md-4">
					<div class="text-center"><h3>${card.cardName }</h3></div>
					<form action="/is2projekatWeb/cards/showCard">
						<input type="hidden" name="id" value="${card.cardID }">
						<input style="margin-left: 168px;" type="submit" value="More Info" class="btn btn-primary btn-lg">
					</form>
				</div>
			</div>
		</c:forEach>
</div>
<div class="row">
	<c:if test="${empty collection}">
		<c:if test="${page > 1 }">
			<a style="margin-left: 65px;" href="/is2projekatWeb/cards/viewCardsPage?search=${search}&page=${prevPage}"><button class="btn btn-primary btn-lg">Previous page</button></a>
		</c:if>
		<c:if test="${page < maxPage }">
			<a style="margin-left: 250px;" href="/is2projekatWeb/cards/viewCardsPage?search=${search}&page=${nextPage}"><button class="btn btn-primary btn-lg">Next page</button></a>
		</c:if>
	</c:if>
	<c:if test="${!empty collection}">
		<c:if test="${page > 1 }">
			<a style="margin-left: 65px;" href="/is2projekatWeb/cards/showCollection?search=${search}&page=${prevPage}"><button class="btn btn-primary btn-lg">Previous page</button></a>
		</c:if>
		<c:if test="${page < maxPage }">
			<a style="margin-left: 250px;" href="/is2projekatWeb/cards/showCollection?search=${search}&page=${nextPage}"><button class="btn btn-primary btn-lg">Next page</button></a>
		</c:if>
	</c:if>
</div>