package app.reports;

import java.util.List;

import app.repository.CardJpaRepo;
import model.Card;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CardsDS extends JRAbstractBeanDataSourceProvider {

	List<Card> cards;
	CardJpaRepo cr;
	
	public CardsDS(CardJpaRepo cr) {
		super(Card.class);
		this.cr = cr;
	}
	
	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		cards = cr.findAllByOrderByCardSetAsc();
		return new JRBeanCollectionDataSource(cards);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		cards = null;
	}

}
