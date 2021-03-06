package Music;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.*;

@Entity
@Table (name="song")
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "song_id")
	private String songID;

	@Column(name = "title")
	private String title;

	@Column(name = "length")
	private int length;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "record_date")
	private String recordDate;

	@Transient
	private DbUtilities db;

	//default constructor

	public Song(){}

	//existing entry constructor
	public Song(String songID){
		this.songID = songID;
		db = new DbUtilities();
		String sql = "SELECT title, length, file_path, release_date, record_date FROM song WHERE song_id = '" + this.songID + "';";
		try {
			//creates the resultset object fromt the sql query so we can create the Song Object
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
		//setting all of the song object values from the parameters
		this.songID = "" + UUID.randomUUID();
		this.title = title;
		this.length = length;
		this.filePath = filePath;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		String sql = "INSERT INTO song (song_id, title, length, file_path, release_date, record_date) VALUES ('" + songID + "', '"+ title + "', '" + length + "', '" + filePath + "', '" + releaseDate + "', '" + recordDate + "');";
		System.out.println(sql);
		db.executeQuery(sql); //executes the sql query (actually adding it to the database)
	}

	//removes from database garbage collector has the rest
	public void deleteSong(String songID){
		String sql = "DELETE FROM song WHERE song_id='" + songID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
		//The object will be eligible for garbage collection (effectively deallocated) as soon as it is
		//not reachable from one of the root objects. Basically self-references doesn't matter. (so it would need to set to null in
		//the main method. https://stackoverflow.com/questions/12089961/delete-this-object-inside-the-class
	}



	//getters and setters for variables besides id and dbulilities
	//setters update both in the object and in the database



	public String getSongID() {
		return songID;
	}

	public void setSongID(String songID) {
		this.songID = songID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public JSONObject toJSON(){
		JSONObject songJson = new JSONObject();
		try {
			songJson.put("song_id", this.songID);
			songJson.put("title", this.title);
			songJson.put("length", this.length);
			songJson.put("file_path", this.filePath);
			songJson.put("release_date", this.releaseDate);
			songJson.put("record_date", this.recordDate);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return songJson;

	}

}
