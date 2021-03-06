package com.doctor.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import com.doctor.bean.Module;
import com.doctor.persistance.Cfclient_view;

/**
 * Home object for domain model class Cfclient.
 * 
 * @see com.proddoctor.dao.Cfclient
 * @author Hibernate Tools
 */
public class Cfclient_viewHome {
	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(CfclientHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			// return (SessionFactory) new
			// InitialContext().lookup("SessionFactory");
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cfclient_view> findAll(boolean archive) {

		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Cfclient_view.class)
				.add(Restrictions.eq("archive", archive));

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Cfclient_view> findAllWithTri(boolean archive, String attribut,
			String ordre) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Cfclient_view.class);

		crit = crit.add(Restrictions.eq("archive", archive));

		if (attribut.equals("Nom")) {
			attribut = "nom";
		}
		if (attribut.equals("Prenom")) {
			attribut = "prenom";
		}
		if (attribut.equals("Patiente")) {
			attribut = "nomprenom";
		}
		if (attribut.equals("Nbr Consultation")) {
			attribut = "nbCons";
		}
		if (attribut.equals("Dernier Consultation")) {
			attribut = "dernierVisite";
		}
		if (attribut.equals("Par saisie")) {
			attribut = "code";
		}
		
		
		if (ordre.equals("Croissante"))
			crit = crit.addOrder(Property.forName(attribut).asc());
		if (ordre.equals("Décroissante"))
			crit = crit.addOrder(Property.forName(attribut).desc());

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Cfclient_view> findAllWithFilter(boolean archive,
			String attribut, String valeurRecherche) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Cfclient_view.class);

		crit = crit.add(Restrictions.eq("archive", archive));

		if (attribut.equals("Nom"))
			crit = crit.add(Restrictions.like("nom", valeurRecherche,
					MatchMode.START));

		if (attribut.equals("Prenom"))
			crit = crit.add(Restrictions.like("prenom", valeurRecherche,
					MatchMode.START));

		// if (attribut.equals("Patiente"))
		// crit = crit.add(Restrictions.eq("code",
		// Integer.parseInt(valeurRecherche)));

		if (attribut.equals("Patiente"))
			crit = crit.add(Restrictions.or(Restrictions.like("nomprenom",
					valeurRecherche, MatchMode.ANYWHERE), Restrictions.like(
					"prenomnom", valeurRecherche, MatchMode.ANYWHERE),
					(Restrictions.like("prenomNomConjoint", valeurRecherche,
							MatchMode.ANYWHERE))));

		if (attribut.equals("Conjoint"))
			crit = crit.add(Restrictions.or(Restrictions.like("nomC",
					valeurRecherche, MatchMode.ANYWHERE), Restrictions.like(
					"prenomC", valeurRecherche, MatchMode.ANYWHERE)));

		if (attribut.equals("Tel"))

		{
			String temp = valeurRecherche;
			String valeurRecherche1 = Module.AjoutSerpTel(2, temp, '.');
			String valeurRecherche2 = Module.AjoutSerpTel(2, temp, ' ');
			String valeurRecherche3 = valeurRecherche;
			String valeurRecherche4 = Module.AjoutSerpTel(3, temp, '.');
			String valeurRecherche5 = Module.AjoutSerpTel(3, temp, ' ');

			crit = crit
					.add(Restrictions.or(Restrictions.like("telephone",
							valeurRecherche1, MatchMode.ANYWHERE), Restrictions
							.like("telephone2", valeurRecherche1,
									MatchMode.ANYWHERE), Restrictions.like(
							"telC", valeurRecherche1, MatchMode.ANYWHERE),
							Restrictions.like("gsm1", valeurRecherche1,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm2", valeurRecherche1,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsmC", valeurRecherche1,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone", valeurRecherche2,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone2", valeurRecherche2,
									MatchMode.ANYWHERE), Restrictions.like(
									"telC", valeurRecherche2,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm1", valeurRecherche2,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm2", valeurRecherche2,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsmC", valeurRecherche2,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone", valeurRecherche4,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone2", valeurRecherche4,
									MatchMode.ANYWHERE), Restrictions.like(
									"telC", valeurRecherche4,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm1", valeurRecherche4,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm2", valeurRecherche4,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsmC", valeurRecherche4,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone", valeurRecherche5,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone2", valeurRecherche5,
									MatchMode.ANYWHERE), Restrictions.like(
									"telC", valeurRecherche5,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm1", valeurRecherche5,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm2", valeurRecherche5,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsmC", valeurRecherche5,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone", valeurRecherche3,
									MatchMode.ANYWHERE), Restrictions.like(
									"telephone2", valeurRecherche3,
									MatchMode.ANYWHERE), Restrictions.like(
									"telC", valeurRecherche3,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm1", valeurRecherche3,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsm2", valeurRecherche3,
									MatchMode.ANYWHERE), Restrictions.like(
									"gsmC", valeurRecherche3,
									MatchMode.ANYWHERE)));
		}
		crit.addOrder(Property.forName("code").desc());
		return crit.list();
	}
	public Integer Nbrtotal (){
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Cfclient_view.class);
				
		Integer total=crit.setProjection(Projections.rowCount()).uniqueResult().hashCode();
		  
		  return total;
	}
	
	public Integer NbrArchive (){
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Cfclient_view.class)
				.add(Restrictions.eq("archive", true));
		
		Integer totalArchive=crit.setProjection(Projections.rowCount()).uniqueResult().hashCode();
		  
		  return totalArchive;
	}
	
	public Integer NbrActive (){
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Cfclient_view.class)
				.add(Restrictions.eq("archive", false));
		
		Integer totalArchive=crit.setProjection(Projections.rowCount()).uniqueResult().hashCode();
		  
		  return totalArchive;
	}
}
