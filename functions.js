  /* We are trying to get you to read documentation and figure things
   * out from there.  Look for your Answer in the Google Maps
   * documentation, the answer is right there! Most of this code
   * is right from there.
   */
  
  // Create a base icon for all of our markers that specifies the
  // shadow, icon dimensions, etc.
  var baseIcon = new GIcon(G_DEFAULT_ICON);
  baseIcon.shadow = "http://www.google.com/mapfiles/shadow50.png";
  baseIcon.iconSize = new GSize(20, 34);
  baseIcon.shadowSize = new GSize(37, 34);
  baseIcon.iconAnchor = new GPoint(9, 34);
  baseIcon.infoWindowAnchor = new GPoint(9, 2);
  
  // Creates a marker whose info window displays the letter corresponding
  // to the given index.
  function createMarker(point, index, problem) {
	var letteredIcon = new GIcon(baseIcon);
  	var letter = String.fromCharCode("A".charCodeAt(0) + index/2);
	
//TYPE PROBLEM 1: 
if(problem == 1){	
  //Changes image based on which points
	if(index == 0){
		letteredIcon.image = "http://www.google.com/mapfiles/dd-start.png";
	}
	else if(index == points1.length-2){
		letteredIcon.image = "http://www.google.com/mapfiles/dd-end.png";
	}
	else{
		letteredIcon.image = "http://www.google.com/mapfiles/marker" + 
		letter + ".png";
	}
  
  //Make Object
	markerOptions = { icon:letteredIcon };
	var marker = new GMarker(point, markerOptions);
  
  //Pop up menu
	GEvent.addListener(marker, "click", function() {
	  if(index == 0 ){
			marker.openInfoWindowHtml("<b>Start Point</b><br>" + "Point " + letter + 
			"<br>Coordinates: (" + point.lat()+","+ point.lng() +")");
		}
		else if(index == points1.length-2){
			 marker.openInfoWindowHtml("<b>End Point</b><br>" + "Point " + letter + 
			"<br>Coordinates: (" + point.lat() +","+ point.lng() +")");
		}
		else{
			 marker.openInfoWindowHtml("Point " + letter + 
			"<br>Coordinates: (" + point.lat()+","+ point.lng() +")");
		  }    
	  
	});
}

//TYPE PROBLEM 2: 
if(problem == 2){	
  //Changes image based on which points
	letteredIcon.image = "http://www.google.com/mapfiles/marker.png";
  
  //Make Object
	markerOptions = { icon:letteredIcon };
	var marker = new GMarker(point, markerOptions);
  
  //Pop up menu
	GEvent.addListener(marker, "click", function() {
			 marker.openInfoWindowHtml(
			"<br>Coordinates: (" + point.lat()+","+ point.lng() +")"); 
	  
	});
}	
//TYPE PROBLEM 3: 
if(problem == 3){	
  //Changes image based on which points
	if(index == 0){
		letteredIcon.image = "http://www.google.com/mapfiles/dd-start.png";
	}
	else{
		letteredIcon.image = "http://www.google.com/mapfiles/marker.png";
	}
  
  //Make Object
	markerOptions = { icon:letteredIcon };
	var marker = new GMarker(point, markerOptions);
  
  //Pop up menu
	GEvent.addListener(marker, "click", function() {
	  if(index == 0 ){
			marker.openInfoWindowHtml("<b>Start Point</b><br>" +  
			"<br>Coordinates: (" + point.lat()+","+ point.lng() +")");
		}

		else{
			 marker.openInfoWindowHtml(
			"<br>Coordinates: (" + point.lat()+","+ point.lng() +")");
		}    
	  
	});
}	
	
	return marker;
  }
  
  
  function load() {
		if (GBrowserIsCompatible()) {
		
		//Problem 1
		  var map1 = new GMap2(document.getElementById("map1"));
		  
		  map1.setCenter(new GLatLng(points1[0], points1[1]), 16);
		  map1.addControl(new GSmallMapControl());
  
		  //points
		  for(var i = 0; i< points1.length; i+=2){
			  var point = new GLatLng(points1[i], points1[i+1]);
			  map1.addOverlay(createMarker(point, i, 1));
		  }

		  //connect
		  var vertices = new Array();
		  for(var i = 0; i< points1.length-2; i+=2){
			  vertices[0] = new GLatLng(points1[i], points1[i+1]);
			  vertices[1] = new GLatLng(points1[i+2], points1[i+3]);
			  
			  var polyline = new GPolyline(vertices, "#0000CD", 10);
			  map1.addOverlay(polyline);
		   }
		   
		   
		   
		  //Problem 2
		  var map2 = new GMap2(document.getElementById("map2"));
		  
		  map2.setCenter(new GLatLng(points2[0], points2[1]), 16);
		  map2.addControl(new GSmallMapControl());
  
		  //points
		  for(var i = 0; i< points2.length; i+=2){
			  var point = new GLatLng(points2[i], points2[i+1]);
			  map2.addOverlay(createMarker(point, i, 2));
		  }

		  //connect
		  var vertices = new Array();
		  for(var i = 0; i< points2.length-2; i+=4){
			  vertices[0] = new GLatLng(points2[i], points2[i+1]);
			  vertices[1] = new GLatLng(points2[i+2], points2[i+3]);
			  
			  var polyline = new GPolyline(vertices, "#0000CD", 10);
			  map2.addOverlay(polyline);
		   }

			
			
			//Problem 3
			var map3 = new GMap2(document.getElementById("map3"));

		  map3.setCenter(new GLatLng(points3[0], points3[1]), 16);
		  map3.addControl(new GSmallMapControl());
  		  
		  //points
		  for(var i = 0; i< points3.length; i+=2){
			  
			  var point = new GLatLng(points3[i], points3[i+1]);
			  map3.addOverlay(createMarker(point, i, 3));

		  }

		  //connect
		  var vertices = new Array();
		  for(var i = 0; i< points3.length-2; i+=4){
			  vertices[0] = new GLatLng(points3[i], points3[i+1]);
			  vertices[1] = new GLatLng(points3[i+2], points3[i+3]);
			  
			  var polyline = new GPolyline(vertices, "#0000CD", 10);
			  map3.addOverlay(polyline);
		   }

			
			



		 }  		
	}
	  
