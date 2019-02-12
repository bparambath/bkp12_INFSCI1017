package bkp12_Music;
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
	private DbUtilities db;
	
	public Album(String albumID){
		this.albumID = albumID;
		db = new DbUtilities();
		String sql = "SELECT title, release_date, cover_image_path, recording_company, number_of_tracks, PMRC_rating, length FROM album WHERE album_id = " + this.albumID + ";";
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
	
	public Album(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length){
		this.albumID = "" + UUID.randomUUID();
		this.title = title;
		this.releaseDate = releaseDate;
		this.coverImagePath = coverImagePath;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		String sql = "INSERT INTO album (album_id, title, release_date, cover_image_path, recording_company, number_of_tracks, PMRC_rating, length  VALUES ('" + albumID + "', '" + title + "', '" + releaseDate + "', '" + coverImagePath + "', '" + recordingCompany + "', '" + numberOfTracks + "', '" + pmrcRating + "', '" + length + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public void deleteAlbum(String albumID){
		String sql = "DELETE FROM artist WHERE album_id='" + albumID + "');";
		System.out.println(sql);
		db.executeQuery(sql);
		this.albumID = null;
		this.title = null;
		this.releaseDate = null;
		this.coverImagePath = null;
		this.recordingCompany = null;
		this.numberOfTracks = (Integer) null;
		this.pmrcRating = null;
		this.length = (Integer) null;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String sql = "Update album SET title = '" + title + "' WHERE album_id = " + this.albumID + ";";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		String sql = "Update album SET release_date = '" + releaseDate + "' WHERE album_id = " + this.albumID + ";";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		String sql = "Update album SET cover_image_path = '" + coverImagePath + "' WHERE album_id = " + this.albumID + ";";
		this.coverImagePath = coverImagePath;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		String sql = "Update album SET recording_company = '" + recordingCompany + "' WHERE album_id = " + this.albumID + ";";
		this.recordingCompany = recordingCompany;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		String sql = "Update album SET number_of_tracks = '" + numberOfTracks + "' WHERE album_id = " + this.albumID + ";";
		this.numberOfTracks = numberOfTracks;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		String sql = "Update album SET PMRC_rating = '" + pmrcRating + "' WHERE album_id = " + this.albumID + ";";
		this.pmrcRating = pmrcRating;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		String sql = "Update album SET length = '" + length + "' WHERE album_id = " + this.albumID + ";";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	
}
