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
@Table (name="artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "artist_id")
	private String artistID;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "band_name")
	private String bandName;
	
	@Column(name = "bio")
	private String bio;
	
	@Transient
	private DbUtilities db;
	
	//default constructor
	public Artist(){}
	
	//existing artist constructor
	public Artist(String artistID){
		this.artistID = artistID;
		//creates the resultset object fromt the sql query so we can create the Artist Object
		String sql = "SELECT first_name, last_name, band_name, bio FROM artist WHERE artist_id = '" + this.artistID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.firstName = rs.getString(1);
				this.lastName = rs.getString(2);
				this.bandName = rs.getString(3);
				this.bio = rs.getString(4);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//new artist constructor
	public Artist(String firstName, String lastName, String bandName){
		//setting all of the song object values from the parameters
		this.artistID = "" + UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.bio = "";
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name, bio) VALUES ('" + artistID + "', '" + firstName + "', '" + lastName + "', '" + bandName + "', '" + bio + "');";
		System.out.println(sql);
		db.executeQuery(sql); //executes the sql query (actually adding it to the database)
	}
	
	//removes from database garbage collector has the rest
	public void deleteArtist(String artistID){
		String sql = "DELETE FROM artist WHERE artist_id='" + artistID + "';";
		System.out.println(sql);
		db.executeQuery(sql);
		//The object will be eligible for garbage collection (effectively deallocated) as soon as it is
		//not reachable from one of the root objects. Basically self-references doesn't matter. (so it would need to set to null in
		//the main method. https://stackoverflow.com/questions/12089961/delete-this-object-inside-the-class
	}

	public String getArtistID() {
		return artistID;
	}

	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public JSONObject toJSON(){
		JSONObject artistJSON = new JSONObject();
		try {
			artistJSON.put("artist_id", this.artistID);
			artistJSON.put("first_name", this.firstName);
			artistJSON.put("last_name", this.lastName);
			artistJSON.put("band_name", this.bandName);
			artistJSON.put("bio", this.bio);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return artistJSON;

	}
	
	
	//getters and setters for variables besides id and dbulilities
	//setters update both in the object and in the database


}
