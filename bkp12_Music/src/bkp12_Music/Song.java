package bkp12_Music;
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
	private DbUtilities db;
	
	//existing entry constructor
	public Song(String songID){
		this.songID = songID;
		db = new DbUtilities();
		String sql = "SELECT title, length, file_path, release_date, record_date FROM song WHERE song_id = " + this.songID + ";";
		try {
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
	}
	
	//new song constructor
	public Song(String title, int length, String filePath, String releaseDate, String recordDate){
		db = new DbUtilities();
		this.songID = "" + UUID.randomUUID();
		this.title = title;
		this.length = length;
		this.filePath = filePath;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		String sql = "INSERT INTO song (song_id, title, length, file_path, release_date, record_date) VALUES ('" + songID + "', '"+ title + "', '" + length + "', '" + filePath + "', '" + releaseDate + "', '" + recordDate + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	//makes all values null and removes from database
	public void deleteSong(String songID){
		String sql = "DELETE FROM song WHERE song_id='" + songID + "');";
		System.out.println(sql);
		db.executeQuery(sql);
		this.songID = null;
		this.title = null;
		this.length = (Integer) null;
		this.filePath = null;
		this.releaseDate = null;
		this.recordDate = null;
	}

	
	//getters and setters for variables besides id and dbulilities
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String sql = "Update song SET title = '" + title + "' WHERE song_id = " + this.songID + ";";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		String sql = "Update song SET length = '" + length + "' WHERE song_id = " + this.songID + ";";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		String sql = "Update song SET file_path = '" + filePath + "' WHERE song_id = " + this.songID + ";";
		this.filePath = filePath;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		String sql = "Update song SET release_date = '" + releaseDate + "' WHERE song_id = " + this.songID + ";";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		String sql = "Update song SET record_date = '" + recordDate + "' WHERE song_id = " + this.songID + ";";
		this.recordDate = recordDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}	
	
}
