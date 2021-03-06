import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	private DbUtilities db = new DbUtilities();
	
	
	//existing artist constructor
	public Artist(String artistID){
		this.setArtistID(artistID);
		//creates the resultset object fromt the sql query so we can create the Artist Object
		String sql = "SELECT first_name, last_name, band_name, bio FROM artist WHERE artist_id = '" + this.getArtistID() + "';";
		
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
		this.setArtistID("" + UUID.randomUUID());
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.bio = "";
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name, bio) VALUES (?,?,?,?,?);";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, artistID);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, bandName);
			stmt.setString(5, bio);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);

	}
	
	//removes from database garbage collector has the rest
	public void deleteArtist(String artistID){
		String sql = "DELETE FROM artist WHERE artist_id= ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, artistID);
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
	
	
	//getters and setters for variables besides id and dbulilities
	//setters update both in the object and in the database

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		String sql = "Update artist SET first_name = ? WHERE artist_id = ?;";
		this.firstName = firstName;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		String sql = "Update artist SET last_name = ? WHERE artist_id = ?;";
		this.lastName = lastName;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, lastName);
			stmt.setString(2, artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		String sql = "Update artist SET band_name = ? WHERE artist_id = ?;";
		this.bandName = bandName;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, bandName);
			stmt.setString(2, artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
		
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		String sql = "Update artist SET bio = ? WHERE artist_id = ?;";
		this.bio = bio;
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, bio);
			stmt.setString(2, artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sql);
	}

	public String getArtistID() {
		return artistID;
	}

	public void setArtistID(String artistID1) {
		String temp = this.artistID;
		this.artistID = artistID1;
		String sql = "Update artist SET artist_id = ? WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, artistID1);
			stmt.setString(2, temp);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
