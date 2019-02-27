import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	private DbUtilities db;
	
	
	//existing artist constructor
	public Artist(String artistID){
		this.artistID = artistID;
		db = new DbUtilities();
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
		db = new DbUtilities();
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
	
	
	//getters and setters for variables besides id and dbulilities
	//setters update both in the object and in the database

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		String sql = "Update artist SET first_name = '" + firstName + "' WHERE artist_id = " + this.artistID + ";";
		this.firstName = firstName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		String sql = "Update artist SET last_name = '" + lastName + "' WHERE artist_id = " + this.artistID + ";";
		this.lastName = lastName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		String sql = "Update artist SET band_name = '" + bandName + "' WHERE artist_id = " + this.artistID + ";";
		this.bandName = bandName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		String sql = "Update artist SET bio = '" + bio + "' WHERE artist_id = " + this.artistID + ";";
		this.bio = bio;
		System.out.println(sql);
		db.executeQuery(sql);
	}

}
