import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManageSongs {

	public void createSong(String songID, String title, int length, String filePath, 
							String releaseDate, String recordDate){
		
		EntityManagerFactory emFactory = 
				Persistence.createEntityManagerFactory("bkp12_Music2");
		
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Song a = new Song();
		
		// Song a = new Song();
		// a.setSongID(UUID.randomUUID().toString());
		
		a.setSongID(songID);
		a.setTitle(title);
		a.setLength(length);
		a.setFilePath(filePath);
		a.setReleaseDate(releaseDate);
		a.setRecordDate(recordDate);
		
		// Add the Song object to the ORM object grid
		em.persist(a);
		
		// Commit transaction
		em.getTransaction().commit();
		
		// Close connection to persistence manager
		em.close();
		emFactory.close();
	}
	
	public void updateSong(String songID, String title, int length, String filePath, 
			String releaseDate, String recordDate){
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song a = em.find(Song.class, songID);
		
		if(!songID.equals("")){
			a.setSongID(songID);;
		}
		if(!title.equals("")){
			a.setTitle(title);
		}
		if(!(length == 0)){
			a.setLength(length);
		}
		if(!filePath.equals("")){
			a.setFilePath(filePath);
		}
		if(!releaseDate.equals("")){
			a.setReleaseDate(releaseDate);
		}
		if(!recordDate.equals("")){
			a.setRecordDate(recordDate);
		}
		em.persist(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();	
	}
	
	public void deleteSong(String songID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song a = em.find(Song.class, songID);
		
		em.remove(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public Song findSong(String songID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
		return s;
	}
}