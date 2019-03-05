import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManageArtists {

	public void createArtist(String artistID, String firstName, String lastName, String bandName, 
							String bio){
		
		EntityManagerFactory emFactory = 
				Persistence.createEntityManagerFactory("bkp12_Music2");
		
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Artist a = new Artist();
		
		// Artist a = new Artist();
		// a.setArtistID(UUID.randomUUID().toString());
		
		a.setArtistID(artistID);
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);
		
		// Add the Artist object to the ORM object grid
		em.persist(a);
		
		// Commit transaction
		em.getTransaction().commit();
		
		// Close connection to persistence manager
		em.close();
		emFactory.close();
	}
	
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, 
			String bio){
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		if(!artistID.equals("")){
			a.setArtistID(artistID);;
		}
		if(!firstName.equals("")){
			a.setFirstName(firstName);
		}
		if(!lastName.equals("")){
			a.setLastName(lastName);
		}
		if(!bandName.equals("")){
			a.setBandName(bandName);
		}
		if(!bio.equals("")){
			a.setBio(bio);
		}
		em.persist(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();	
	}
	
	public void deleteArtist(String artistID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		em.remove(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public Artist findArtist(String artistID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist ar = em.find(Artist.class, artistID);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
		return ar;		
	}
}