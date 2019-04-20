package Music;



import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SongServlet
 */
@WebServlet("/servlets/CreateSong")
public class CreateSong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSong() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("songTitle") != null && request.getParameter("songLength") != null
				&& request.getParameter("songFilePath") != null && request.getParameter("songReleaseDate") != null && request.getParameter("songRecordDate") != null){
			String songTitle = request.	getParameter("songTitle");
			String songLength = request.getParameter("songLength");
			String songFilePath = request.getParameter("songFilePath");
			String songReleaseDate = request.getParameter("songReleaseDate");
			String songRecordDate = request.getParameter("songRecordDate");
			
			ManageSongs gm = new ManageSongs();
			gm.createSong(UUID.randomUUID().toString(), songTitle, Integer.parseInt(songLength), songFilePath, songReleaseDate, songRecordDate);
			// response.getWriter().println("Genre Name: " + genreName);
			response.sendRedirect("../CreateSongForm.jsp");
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
