//global variables which shall be initialised in functions
var city,country,temperature,conditions,longitude,latitude;

function getData() {
	//getting value of the field in var name
	var name = document.getElementById("name").value;
	var xhttp = new XMLHttpRequest();
	//link to get json from api
	var link = "http://api.openweathermap.org/data/2.5/weather?q="+name+"&units=metric&APPID=1fece17149b11bf41a66f24302098d20";
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var result = JSON.parse(this.responseText);
			//values to be displayed
			city = result.name;
			country = result.sys.country;
			temperature = result.main.temp;
			conditions = result.weather[0].main;
			longitude = result.coord.lon;
			latitude = result.coord.lat;
			//html for making a card
			var html = "<div class=\"card\" style=\"width: 18rem;\">"+
			  			"<div class=\"card-body\">"+
			  			"<h5 class=\"card-title\">"+city+", "+country+"</h5>"+
			  			"<p class=\"card-text\">Temperature : "+temperature+"<br>Conditions : "+conditions+"<br>Longitude : "+longitude+"<br>Latitude : "+latitude+"</p>"+
			  			"<button class=\"btn btn-primary\" onclick = \"addFav()\" type = \"button\">Add to favorites</button>"+
			  			"</div>"+
			  			"</div>" ;
			  
			//content to be replaced
			document.getElementById("content").innerHTML = html;		
		}
	};
	xhttp.open("GET", link, true);
	xhttp.send();
}

function addFav(){
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			document.getElementById("favorites").innerHTML = xmlhttp.responseText;
		}
	};
	//values to be sent to servlet
	var values = "city=" +city+ "&country=" +country+ "&temperature=" +temperature+ "&conditions=" +conditions+ "&longitude=" +longitude+ "&latitude="+latitude;
	xmlhttp.open('GET',"http://localhost:8084/TopTen/favorite?"+values, true);
	xmlhttp.send();
}

function getFav(){
	var xmlhttp1 = new XMLHttpRequest();
	xmlhttp1.onreadystatechange = function(){
		if(xml1http.readyState == 4 && xml1http.status == 200){
			document.getElementById("favorites").innerHTML = xmlhttp1.responseText;
		}
	};
	//values to be sent to servlet
	var values = "city=" +city+ "&country=" +country+ "&temperature=" +temperature+ "&conditions=" +conditions+ "&longitude=" +longitude+ "&latitude="+latitude;
	xmlhttp1.open('GET',"http://localhost:8084/TopTen/readjson", true);
	xmlhttp1.send();
}

