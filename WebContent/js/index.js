//global variables which shall be initialised in functions
var city,country,temperature,conditions,longitude,latitude;

function getData() {
	var name = document.getElementById("name").value;
	var xhttp = new XMLHttpRequest();
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
			var html = "<div class=\"card\" style=\"width: 18rem;\">"+
			  			"<div class=\"card-body\">"+
			  			"<h5 class=\"card-title\">"+city+", "+country+"</h5>"+
			  			"<p class=\"card-text\">Temperature : "+temperature+"<br>Conditions : "+conditions+"<br>Longitude : "+longitude+"<br>Latitude : "+latitude+"</p>"+
			  			"<button class=\"btn btn-primary\" onclick = \"addFav()\" type = \"button\">Add to favorites</button>"+
			  			"</div>"+
			  			"</div>" ;
			  			
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
	var values = "city=" +city+ "&country=" +country+ "&temperature=" +temperature+ "&conditions=" +conditions+ "&longitude=" +longitude+ "&latitude="+latitude;
	xmlhttp.open('GET',"http://localhost:8080/TopTen/favorite?"+values, true);
	xmlhttp.send();
}

function getFav() {
	
}

