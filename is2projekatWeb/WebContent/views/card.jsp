<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="gallery">
	<table style="color: #000;">
		<tr>
			<td rowspan="10"><img height="370" width="265" src="/is2projekatWeb/img/full_${card.cardName}.jpg"/></td>
			<td colspan="10">
				<div class="text-center">
					<h3>${card.cardName }</h3>
				</div>
			</td>
		</tr>
		<tr>
			<th style="padding-left: 15px; width: 150px;">Set:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.cardSet }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Text:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.cardText }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Flavour text:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.flavour }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Power:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.power }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Toughness:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.toughness }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Loyalty:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.loyalty }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Rarity:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.rarity }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Type:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${card.type }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Mana:</th>
			<td style="padding-right: 300px; padding-left: 15px;"> 
				<c:forEach var="mana" items="${card.manas }">
					${mana.shortName }
				</c:forEach> 
			</td>
		</tr>
	</table>
	<c:if test="${!empty player }">
		<a href="/is2projekatWeb/cards/addToCollection/${card.cardID }"><button class="btn btn-primary btn-lg">Add to collection</button></a>
	</c:if>
	<c:if test="${!empty player && !empty deck }">
		<a href="/is2projekatWeb/cards/addToDeck/${card.cardID }"><button class="btn btn-primary btn-lg">Add to deck</button></a>
	</c:if>
	<c:if test="${!empty player && player.admin == 1 }">
		<a href="/is2projekatWeb/cards/deleteCard/${card.cardID }"><button class="btn btn-primary btn-lg">Delete card</button></a>
	</c:if>
</div>