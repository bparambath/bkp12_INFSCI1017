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

import org.json.JSONException;
import org.json.JSONObject;


@Entity
@Table (name="album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "album_id")
	private String albumID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "release_date")
	private String releaseDate;
	
	@Column(name = "cover_image_path")
	private String coverImagePath;
	
	@Column(name = "recording_company_name")
	private String recordingCompany;
	
	@Column(name = "number_of_tracks")
	private int numberOfTracks;
	
	@Column(name = "PMRC_rating")
	private String pmrcRating;
	
	@Column(name = "length")
	private int length;
	
	@Transient
	private DbUtilities db;
	
	//default constructor
	public Album(){}
	
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
		String sql = "INSERT INTO album (album_id, title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length)  VALUES ('" + albumID + "', '" + title + "', '" + releaseDate + "', '" + coverImagePath + "', '" + recordingCompany + "', '" + numberOfTracks + "', '" + pmrcRating + "', '" + length + "');";
		System.out.println(sql);
		db.executeQuery(sql); //executes the sql query (actually adding it to the database)
	}
	
	//removes from database garbage collector has the rest
	public void deleteAlbum(String albumID){
		String sql = "DELETE FROM album WHERE album_id='" + albumID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
		//The object will be eligible for garbage collection (effectively deallocated) as soon as it is
		//not reachable from one of the root objects. Basically self-references doesn't matter. (so it would need to set to null in
		//the main method. https://stackoverflow.com/questions/12089961/delete-this-object-inside-the-class
	}

	public String getAlbumID() {
		return albumID;
	}

	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public JSONObject toJSON(){
		JSONObject albumJSON = new JSONObject();
		try {
			albumJSON.put("album_id", this.albumID);
			albumJSON.put("title", this.title);
			albumJSON.put("release_date", this.releaseDate);
			albumJSON.put("cover_image_path", this.coverImagePath);
			albumJSON.put("recording_company", this.recordingCompany);
			albumJSON.put("number_of_tracks", this.numberOfTracks);
			albumJSON.put("PMRC_rating", this.pmrcRating);
			albumJSON.put("length", this.length);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return albumJSON;

	}

	//getters and setters for variables besides id and dbulilities
	//setters update both in the object and in the database
	
}
	