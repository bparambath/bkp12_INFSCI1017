import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	private Map<String, Song> albumSongs = new HashMap<String, Song>();
	private DbUtilities db;
	
	
	//existing album constructor
	public Album(String albumID){
		this.albumID = albumID;
		db = new DbUtilities();
		//creates the resultset object fromt the sql query so we can create the Album Object
		String sql = "SELECT title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length FROM album WHERE album_id = '" + this.albumID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.title = rs.getString(1);
				this.releaseDate = rs.getString(2);
				this.coverImagePath = rs.getString(3);
				this.recordingCompany = rs.getString(4);
				this.numberOfTracks = rs.getInt(5);
				this.pmrcRating = rs.getString(6);
				this.length = rs.getInt(7);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql2 = "SELECT fk_song_id FROM album_song WHERE fk_album_id = '" + this.albumID + "';";
		try {
			//creates the resultset object fromt the sql query so we can create the Song Object
			ResultSet rs = db.getResultSet(sql2);
			if(rs.next()){
				this.albumSongs.put(rs.getString(1), new Song(rs.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//new album constructor
	public Album(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length){
		db = new DbUtilities();
		
		//setting all of the song object values from the parameters
		this.albumID = "" + UUID.randomUUID();
		this.title = title;
		this.releaseDate = releaseDate;
		this.coverImagePath = coverImagePath;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		String sql = "INSERT INTO album (album_id, title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length)  VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, albumID);
			stmt.setString(2, title);
			stmt.setString(3, releaseDate);
			stmt.setString(4, coverImagePath);
			stmt.setString(5, recordingCompany);
			stmt.setInt(6, numberOfTracks);
			stmt.setString(7, pmrcRating);
			stmt.setInt(8, length);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		
	}
	
	//removes from database garbage collector has the rest
	public void deleteAlbum(String albumID){
		String sql = "DELETE FROM album WHERE album_id= ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, albumID);
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
	
	public void addSong(Song song){
		albumSongs.put(song.getSongID(), song);
	}
	
	public void deleteSong(String songID){
		albumSongs.remove(songID);
	}
	
	public void deleteSong(Song song){
		albumSongs.remove(song.getSongID());
	}
		

	//getters and setters for variables besides id and dbulilities
	//setters update both in the object and in the database

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String sql = "Update album SET title = ? WHERE album_id = ?;";
		this.title = title;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}

	
	public String getAlbumID() {
		return albumID;
	}

	public void setAlbumID(String albumID1) {
		String temp = this.albumID;
		this.albumID = albumID1;
		String sql = "Update album SET album_id = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, albumID1);
			stmt.setString(2, temp);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		String sql = "Update album SET release_date = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, releaseDate);
			stmt.setString(2, albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		String sql = "Update album SET cover_image_path = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, coverImagePath);
			stmt.setString(2, albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		String sql = "Update album SET recording_company_name = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, recordingCompany);
			stmt.setString(2, albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		String sql = "Update album SET number_of_tracks = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setInt(1, numberOfTracks);
			stmt.setString(2, albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		String sql = "Update album SET PMRC_rating = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, pmrcRating);
			stmt.setString(2, albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		String sql = "Update album SET length = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setInt(1, length);
			stmt.setString(2, albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
