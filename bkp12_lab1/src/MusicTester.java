
public class MusicTester {

	public static void main(String[] args) {
		
		//In order to test, I commented all but small segments at a time separated by line breaks in my code. Also, I went into MySQL Workbench
		//to check for inserts/new songs
		
		//I also kept it in the same folder as the in-class work.
		
		//testing Song
		Song s = new Song("99");
		System.out.println(s.getTitle());
		
		s.setLength(119);
		s.setFilePath("songs/DONTcometogether.mp3");
		s.setRecordDate("1933-10-23 00:00:00");
		s.setReleaseDate("1954-10-23 00:00:00");
		s.setTitle("BigFlamingo");
		System.out.println(s.getFilePath());
		
		Song s2 = new Song("Flamingoo",432,"songs/cometogetherrrrr.mp3","1994-10-23 00:00:00","1995-10-23 00:00:00");
		s2.deleteSong("1");

		
		//testing Artist
		Artist a = new Artist("123");
		System.out.println(a.getBandName());
		
		a.setFirstName("Kero Kero");
		a.setLastName("Bonito");
		a.setBandName("Kero Kero Bonito");
		a.setBio("Funny song lol");
		System.out.println(a.getBio());
		
		Artist a2 = new Artist("Meek","Mill","Meek Mill");
		a2.deleteArtist("1");
		
		//testing Album
		Album al = new Album("2");
		System.out.println(al.getTitle());
		
		al.setTitle("Bonito Generation");
		al.setReleaseDate("1994-10-23 00:00:00");
		al.setCoverImagePath("images/bonito.jpg");
		al.setRecordingCompany("Bonitoo");
		al.setNumberOfTracks(5);
		al.setPmrcRating("R");
		al.setLength(234);
		
		Album al2 = new Album("Bonito Generationn", "1995-10-23 00:00:00", "images/bonito.jpg", "bonito", 3, "X", 752);
		al2.deleteAlbum("1");		
		
	}

}
