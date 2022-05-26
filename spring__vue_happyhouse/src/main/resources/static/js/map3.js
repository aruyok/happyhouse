var root = "";

// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById("map"); // 지도를 표시할 div	
var mapOption = {
	    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	    level: 3, // 지도의 확대 레벨
	};

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(map);


// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
// 커스텀 오버레이를 생성합니다
var customOverlay;
//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//init();


var house_arr=[]

//검색 결과 목록과 마커를 표출하는 함수입니다
function displayMarkers(places,x) {
	var fragment = document.createDocumentFragment();
    var bounds = new kakao.maps.LatLngBounds();
    var listStr = "";
	// 지도에 표시되고 있는 마커를 제거합니다
    
	removeMarker();
	
	for(var i=0; i<places.length; i++) {
		
		var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng);
		var marker = addMarker(placePosition, i);
		var itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
		
		//집위치
		var arr = [i,places[i].lat,places[i].lng];
		house_arr.push(arr)
//		console.log(places[i].lat+" "+places[i].lng);
		
		
		
		// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		// LatLngBounds 객체에 좌표를 추가합니다
		bounds.extend(placePosition);
		
		// 마커와 검색결과 항목에 mouseover 했을때
		// 해당 장소에 인포윈도우에 장소명을 표시합니다
		// mouseout 했을 때는 인포윈도우를 닫습니다
		(function (marker, title, code, place) {
			kakao.maps.event.addListener(marker, "click", function () {
				displayInfowindow(marker, title, place);
			});

		    kakao.maps.event.addListener(map, "click", function () {
		    	console.log(customOverlay);
		    	customOverlay.setMap(null);
		    });

		    itemEl.onmouseover = function () {
		    	displayInfowindow(marker, title);
		    };

		    itemEl.onmouseout = function () {
		    	customOverlay.setMap(null);
		    };
		})(marker, places[i].aptName, places[i].aptCode, places[i]);
		
		fragment.appendChild(itemEl);
	}

	// 마커를 생성하고 지도에 표시합니다 
	// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	remove_location();
	translation(x);
	map.setBounds(bounds);

}

//마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
	imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
	imgOptions = {
		spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
		spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
		offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	},
	markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	marker = new kakao.maps.Marker({
		position: position, // 마커의 위치
		image: markerImage,
	});

	marker.setMap(map); // 지도 위에 마커를 표출합니다
	markers.push(marker); // 배열에 생성된 마커를 추가합니다

	return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
}

//검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, place) {
	var el = document.createElement("li");
	var itemStr = `
		<span class="markerbg marker_${index + 1}></span>
		<div class="info"><h5>${place.aptName}</h5> <button>관심등록</button>
		<span>${place.sidoName} ${place.gugunName} ${place.dongName} ${place.jibun}</span>
	`;
	el.innerHTML = itemStr;
	el.className = "item";

	return el;
}

//검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
//인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title, place) {
	var content = `
		<div class="overlaybox">
			<div class="boxtitle">${title}</div>
			<div class="first"><img src="${root}/img/apt.png" style="width:247px; height:136px;" alt=""></div>
			<ul>
				<li class="up">
					<span class="title">건축년도</span>
					<span class="count">${place.buildYear}</span>
				</li>
				<li>
					<span class="title">주소</span>
					<span class="count">${place.sidoName} ${place.gugunName} ${place.dongName} ${place.jibun}</span>
				</li>
				<li>
					<span class="title">최신거래금액</span>
					<span class="count">${place.recentPrice}</span>
				</li>
				<li>
					<span class="last" id="recenthistor" data-toggle="modal" data-target="#myModal">아파트정보 update</span>
				</li>
			</ul>
		</div>
	`;
	var position = new kakao.maps.LatLng(marker.getPosition().getLat()+0.00033, marker.getPosition().getLng()-0.00003);
	customOverlay = new kakao.maps.CustomOverlay({
		position: position,
		content: content,
		xAnchor: 0.3,
		yAnchor: 0.91,
	});
	customOverlay.setMap(map);
}

//검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
	while (el.hasChildNodes()) {
		el.removeChild(el.lastChild);
	}
}


//----------------------------------------------map2-----------------------

var cs_arr=[];
var locations=[];

function remove_location(){
	for (var i = 0; i < locations.length; i++) {
		locations[i].setMap(null);
	}
}

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        for (var i=0; i<data.length; i++) {
        	var arr = [];
        	arr=[data[i].y,data[i].x];
        	cs_arr.push(arr);
        	console.log(data[i]);
        	console.log("placeSearchCB창입니다.");
            displayMarker(data[i]);    
        }       
    }
//    console.log(cs_arr);
}



// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x) 
    });
 
    

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
    locations.push(marker);
}

function init(Marker){
	house_arr=[];
	cs_arr=[];
//	flag=true;
//	map = new kakao.maps.Map(mapContainer, mapOption);
//	delete_marker();
//	cluster.clear();
};

destination=[];

function remove_dest(){
	for (var i = 0; i < destination.length; i++) {
		destination[i].setMap(null);
	}
}

function getDistance(j,lat1, lon1) {

	for (var i = 0; i < cs_arr.length; i++) {
		console.log(cs_arr[i][0]+":"+cs_arr[i][1]);
	    var radLat1 = Math.PI * lat1 / 180;
	    var radLat2 = Math.PI * cs_arr[i][0] / 180;
	    var theta = lon1 - cs_arr[i][1];
	    var radTheta = Math.PI * theta / 180;
	    var dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
	    if (dist > 1)
	        dist = 1;

	    dist = Math.acos(dist);
	    dist = dist * 180 / Math.PI;
	    dist = dist * 60 * 1.1515 * 1.609344 * 1000;
	    if (dist < 100) dist = Math.round(dist / 10) * 10;
	    else dist = Math.round(dist / 100) * 100;

//		    return dist;
	    console.log(++j, dist); //1000은 1km를 의미

  	    if(dist<=50){ //50m 이내의 경우 편세권!
	    	var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
	        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
	        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	          
		    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
		        markerPosition = new kakao.maps.LatLng(lat1, lon1); // 마커가 표시될 위치입니다

		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        position: markerPosition, 
		        image: markerImage // 마커이미지 설정 
		    });
		   
		    // 마커가 지도 위에 표시되도록 설정합니다.
		    remove_location();
		    marker.setMap(map);  
		    destination.push(marker);
//}
	    }else{
	    	continue;
	    }
	}
		    
		    
	
   
}


function translation(x){
	if(x==1){
		ps.categorySearch('CS2', placesSearchCB, {useMapBounds:true});
		remove_dest();
		
	}else if(x==2){
		ps.categorySearch('BK9', placesSearchCB, {useMapBounds:true});
		remove_dest();
	}
}

