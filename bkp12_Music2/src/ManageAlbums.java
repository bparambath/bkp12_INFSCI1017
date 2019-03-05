import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManageAlbums {

	public void createAlbum(String albumID, String title, String releaseDate, String coverImagePath, 
							String recordingCompany, int numberOfTracks, String pmrcRating, int length){
		
		EntityManagerFactory emFactory = 
				Persistence.createEntityManagerFactory("bkp12_Music2");
		
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Album a = new Album();
		
		// Artist a = new Artist();
		// a.setArtistID(UUID.randomUUID().toString());
		
		a.setAlbumID(albumID);
		a.setTitle(title);
		a.setReleaseDate(releaseDate);
		a.setCoverImagePath(coverImagePath);
		a.setRecordingCompany(recordingCompany);
		a.setNumberOfTracks(numberOfTracks);
		a.setPmrcRating(pmrcRating);
		a.setLength(length);
		
		// Add the Album object to the ORM object grid
		em.persist(a);
		
		// Commit transaction
		em.getTransaction().commit();
		
		// Close connection to persistence manager
		em.close();
		emFactory.close();
	}
	
	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, 
			String recordingCompany, int numberOfTracks, String pmrcRating, int length){
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		if(!title.equals("")){
			a.setTitle(title);;
		}
		if(!releaseDate.equals("")){
			a.setReleaseDate(releaseDate);
		}
		if(!coverImagePath.equals("")){
			a.setCoverImagePath(coverImagePath);
		}
		if(!recordingCompany.equals("")){
			a.setRecordingCompany(recordingCompany);
		}
		if(!(numberOfTracks == 0)){
			a.setNumberOfTracks(numberOfTracks);
		}
		if(!pmrcRating.equals("")){
			a.setPmrcRating(pmrcRating);
		}
		if(!(length == 0)){
			a.setLength(length);
		}
		
		em.persist(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();	
	}
	
	public void deleteAlbum(String albumID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		em.remove(a);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public Album findAlbum(String albumID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bkp12_Music2");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
		return a;
		
	}
}