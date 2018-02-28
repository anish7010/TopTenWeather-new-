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
	//array of json objects
	JSONArray jarray = new JSONArray();
	
    public ParseJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setting response type
		response.setContentType("application/json");
		String city = request.getParameter("city");
		PrintWriter out = response.getWriter();
		//shows the city added
		out.print("Thank you for adding <b>"+city+"</b> to your favorite locations");
		String country = request.getParameter("country");
		String temperature = request.getParameter("temperature");
		String conditions = request.getParameter("conditions");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		//json object to store key value pairs
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
		//if length exceeds 10, warning message is issued
		if(jarray.length() < 10) {
			jarray.put(json);
		}
		else {
			System.out.println("You have exceeded your limit");
		}
		
		FileWriter jsonFile=null;
		
		try {
		jsonFile =  new FileWriter("/home/sapient/Documents/favorites.json");
		jsonFile.write(jarray.toString());
		System.out.println(json.toString());
		}catch(Exception e){
			System.out.println("Please enter a valid path where you want to store your json");
		}finally {
			jsonFile.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
