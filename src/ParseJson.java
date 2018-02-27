import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/favorite")
public class ParseJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray jarray = new JSONArray();
	
    public ParseJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String city = request.getParameter("city");
		PrintWriter out = response.getWriter();
		out.print("Thank you for adding <b>"+city+"</b> to your favorite locations");
		String country = request.getParameter("country");
		String temperature = request.getParameter("temperature");
		String conditions = request.getParameter("conditions");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		
		JSONObject json = new JSONObject();
		try {
			json.put("city", city);
			json.put("country", country);
			json.put("temperature", temperature);
			json.put("conditions", conditions);
			json.put("longitude", longitude);
			json.put("latitude", latitude);
			
		} catch (JSONException e) {
			System.out.println("Wrong element inserted");
		}
		
		if(jarray.length() < 10) {
			jarray.put(json);
		}
		else {
			System.out.println("You have exceeded your limit");
		}
		
		FileWriter jsonFile = new FileWriter("/home/anish7010/Documents/favorites.json");
		jsonFile.write(jarray.toString());
		System.out.println(json.toString());
		jsonFile.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
