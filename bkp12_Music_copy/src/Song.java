import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtists = new HashMap<String, Artist>();
	private DbUtilities db;
	 
	
	//existing entry constructor
	public Song(String songID){
		this.songID = songID;
		db = new DbUtilities();
		String sql = "SELECT title, length, file_path, release_date, record_date FROM song WHERE song_id = '" + this.songID + "';";
		try {
			//creates the resultset object from the sql query so we can create the Song Object
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.title = rs.getString(1);
				this.length = rs.getInt(2);
				this.filePath = rs.getString(3);
				this.releaseDate = rs.getString(4);
				this.recordDate = rs.getString(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql2 = "SELECT fk_artist_id FROM song_artist WHERE fk_song_id = '" + this.songID + "';";
		try {
			//creates the resultset object fromt the sql query so we can create the Song Object
			ResultSet rs = db.getResultSet(sql2);
			if(rs.next()){
				this.songArtists.put(rs.getString(1), new Artist(rs.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//new song constructor
	public Song(String title, int length, String filePath, String releaseDate, String recordDate){
		db = new DbUtilities();
		//setting all of the song object values from the parameters
		this.songID = "" + UUID.randomUUID();
		this.title = title;
		this.length = length;
		this.filePath = filePath;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		String sql = "INSERT INTO song (song_id, title, length, file_path, release_date, record_date) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, songID);
			stmt.setString(2, title);
			stmt.setInt(3, length);
			stmt.setString(4, filePath);
			stmt.setString(5, releaseDate);
			stmt.setString(6, recordDate);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}
	
	//removes from database garbage collector has the rest
	public void deleteSong(String songID){
		String sql = "DELETE FROM song WHERE song_id= ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		//The object will be eligible for garbage collection (effectively deallocated) as soon as it is
		//not reachable from one of the root objects. Basically self-references doesn't matter. (so it would need to set to null in
		//the main method. https://stackoverflow.com/questions/12089961/delete-this-object-inside-the-class
	}
	
	public void addArtist(Artist artist){
		songArtists.put(artist.getArtistID(), artist);
	}
	
	public void deleteArtist(String artistID){
		songArtists.remove(artistID);
	}
	
	public void deleteArtist(Artist artist){
		songArtists.remove(artist.getArtistID());
	}
	
	//getters and setters for variables besides id and dbulilities
	//setters update both in the object and in the database

	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID1) {
		String temp = this.songID;
		this.songID = songID1;
		String sql = "Update artist SET artist_id = ? WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, songID1);
			stmt.setString(2, temp);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String sql = "Update song SET title = ? WHERE song_id = ?;";
		this.title = title;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		String sql = "Update song SET length = ? WHERE song_id = ?;";
		this.length = length;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setInt(1, length);
			stmt.setString(2, songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		String sql = "Update song SET file_path = ? WHERE song_id = ?;";
		this.filePath = filePath;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, filePath);
			stmt.setString(2, songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		String sql = "Update song SET release_date = ? WHERE song_id = ?;";
		this.releaseDate = releaseDate;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, releaseDate);
			stmt.setString(2, songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		String sql = "Update song SET record_date = ? WHERE song_id = ?;";
		this.recordDate = recordDate;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, recordDate);
			stmt.setString(2, songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}	
	
}
