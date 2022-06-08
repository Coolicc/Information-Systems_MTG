package app.domain;

import java.io.Serializable;

import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

public class CardImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cardName;

	private String cardSet;

	private String cardText;

	private String flavour;
	
	private MultipartFile image;

	private int loyalty;

	private String power;

	private String rarity;
	
	private MultipartFile titleImage;

	private String toughness;

	private String type;
	
	private String manas;

	public CardImage() {
		super();
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardSet() {
		return cardSet;
	}

	public void setCardSet(String cardSet) {
		this.cardSet = cardSet;
	}

	public String getCardText() {
		return cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public int getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public MultipartFile getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(MultipartFile titleImage) {
		this.titleImage = titleImage;
	}

	public String getToughness() {
		return toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManas() {
		return manas;
	}

	public void setManas(String manas) {
		this.manas = manas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
