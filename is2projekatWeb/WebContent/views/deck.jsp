<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="col-md-4" style="float: right; padding-bottom: 15px; border-color: #000; border-style: solid; border-width: 2px; width: 250px; background-color: #65AAF0; position: fixed; top: 208px; right: 0px;">
	<div class="text-center">
		<h3>${deck.deckName }</h3>
	</div>
	<c:forEach items="${deck.cards }" var="dc">
		<div>
			<a href="/is2projekatWeb/cards/removeFromDeck/${dc.cardID }">
				<img height="25" width="220" src="/is2projekatWeb/img/title_${dc.cardName}.jpg"/>
			</a>
		</div>
	</c:forEach>
	<a style="position: relative; left: 0; right: 0;" href="/is2projekatWeb/cards/saveDeck"><button class="btn btn-primary btn-lg">Save Deck</button></a>
</div>