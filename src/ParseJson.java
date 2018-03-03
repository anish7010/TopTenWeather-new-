import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@WebServlet("/favorite")
public class ParseJson extends HttpServlet {
	//global array
	JSONArray jarray = new JSONArray();
	private static final long serialVersionUID = 1L;
	//array of json objects
    public ParseJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//now first we will read the file and then check the count
		JSONObject json = new JSONObject();
		
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
		
			json.put("city", city);
			json.put("country", country);
			json.put("temperature", temperature);
			json.put("conditions", conditions);
			json.put("longitude", longitude);
			json.put("latitude", latitude);
						
		//if length exceeds 10, warning message is issued
		if(jarray.size() < 10) {
			jarray.add(json);
		}
		
		else {
			System.out.println("You have exceeded your limit");
		}
		
		FileWriter jsonFile=null;
		
		try {
		jsonFile =  new FileWriter("/home/anish7010/Documents/favorites.json");
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
