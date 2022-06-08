package app.reports;

import java.util.List;

import app.repository.PlayerJpaRepo;
import model.Player;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PlayersDS extends JRAbstractBeanDataSourceProvider {
	
	List<Player> players;
	PlayerJpaRepo pr;
	
	public PlayersDS(PlayerJpaRepo pr) {
		super(Player.class);
		this.pr = pr;
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		players = pr.findAllByOrderByUsernameAsc();
		return new JRBeanCollectionDataSource(players);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		players = null;
	}

}
