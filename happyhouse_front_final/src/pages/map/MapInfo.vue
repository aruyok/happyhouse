<template>
  <div>
    <div id="map" class="map"></div>
    <div id="clickLatlng"></div>
    <!-- <button @click="displayMarkers(markerPosition)"></button> -->
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";
export default {
  props: {
    select_wanted: String,
    info: Array,
  },
  computed: {
    // info_list() {
    //   // console.log("map_info_list: " + this.$store.state.info_list);
    //   return this.$store.state.info_list;
    // },
    ...mapState(houseStore, ["info_list"]),
  },
  watch: {
    info_list(val, OldValue) {
      // console.log("value: " + val);
      console.log("oldvalue: " + OldValue);
      console.log("current value: " + val);
      this.info = val;
      window.kakao && window.kakao.maps
        ? this.initMap()
        : this.addKakaoMapScript();
      // this.info = val;
      // window.kakao && window.kakao.maps
      //   ? this.initMap()
      //   : this.addKakaoMapScript();
    },
  },

  created() {
    // this.info = val;
  },
  data() {
    return {
      map: "",
      ps: "",
      root: "",
      marker: "",
      markers: [],
      cs_arr: [],
      location: [],
      house_arr: [],
      lat: 0,
      lng: 0,
      destination: [],
      overlay: [],
      category_marker: "",
      flag: 0,
      ss: "",
      infowindow: [],

      geocoder: "",
      // info: [],
      markerPosition: [
        {
          title: "카카오",
          latlng: new kakao.maps.LatLng(33.450705, 126.570677),
        },
      ],
    };
  },

  mounted() {
    // window.kakao && window.kakao.maps
    //   ? this.initMap()
    //   : this.addKakaoMapScript();
  },
  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4e1fb9b0abd6fa78647b5428d4a651ae&libraries=services";
      document.head.appendChild(script);
    },
    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      // this.resizeMap();
      // this.relayout();
      //------------------------------------------------------------------------
      // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      // var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      // // 커스텀 오버레이를 생성합니다
      // var customOverlay;
      // //주소-좌표 변환 객체를 생성합니다
      // var geocoder = new kakao.maps.services.Geocoder();
      // console.log("map: " + this.info_list);
      // console.log("val: " + this.info);
      this.geocoder = new kakao.maps.services.Geocoder();
      console.log("house_arr 리스트: " + this.info);
      this.house_arr = this.info_list;
      this.displayMarkers(this.info);
    },
    displayMarkers(positions) {
      // console.log("positions: " + positions);
      console.log("positions: " + positions);
      //현재 표시되어 있는 marker들을 초기화
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      //2,마커이미지 커스터마이징하기
      const imgSrc = require("@/assets/appartments.png");
      const imgSize = new kakao.maps.Size(24, 35);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      //3. 마커 표시하기
      positions.forEach((position) => {
        // console.log("position: " + position);

        const marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(position.lat, position.lng),
          // title: position.aptName,
          image: markerImage,
          // clickable: true,
          // removable: true,
        });
        marker.setMap(this.map);
        //----------------------------------------------------------------------------

        var content =
          '<div class="customoverlay">' +
          '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
          `    <span class="title">${position.aptCode}. ${position.aptName}</span>` +
          "  </a>" +
          "</div>";

        var overlay = new kakao.maps.CustomOverlay({
          content: content,
          // map: this.map,
          // position: new kakao.maps.LatLng(position.lat, position.lng),
          // removable: true,
        });
        // kakao.maps.event.addListener(marker, "click", () => {
        //   console.log("들어왔나?");
        //   overlay.setPosition(
        //     new kakao.maps.LatLng(position.lat, position.lng)
        //   );
        // });

        kakao.maps.event.addListener(
          marker,
          "mouseover",
          makeOverListener(this.map, marker, overlay)
        );

        kakao.maps.event.addListener(
          marker,
          "mouseout",
          makeOutListener(overlay)
        );

        function makeOverListener(map, marker, overlay) {
          return function() {
            overlay.setPosition(
              new kakao.maps.LatLng(position.lat, position.lng)
            );
            overlay.setMap(map);
          };
        }

        // 인포윈도우를 닫는 클로저를 만드는 함수입니다
        function makeOutListener(overlay) {
          return function() {
            overlay.setMap(null);
          };
        }

        //----------------------------------------------------------------------------
        // 커스텀 오버레이를 닫기 위해 호출되는 함수입니다

        this.markers.push(marker);
      }); //end of positions

      // positions.forEach((position) => {
      //   // customOverlay.setMap(this.map);
      // });

      //4. 지도를 이동시켜주기
      const bounds = positions.reduce(
        (bounds, position) =>
          bounds.extend(new kakao.maps.LatLng(position.lat, position.lng)),
        new kakao.maps.LatLngBounds()
      );
      // this.map = new kakao.maps.LatLngBounds();
      // console.log("bounds: " + bounds);

      this.map.setBounds(bounds);
      this.ps = new kakao.maps.services.Places(this.map);
      this.transaction(this.select_wanted);
    },
    closeOverlay() {
      this.overlay.setMap(null);
    },
    transaction(x) {
      if (x == 1) {
        this.ps.categorySearch("CS2", this.placesSearchCB, {
          useMapBounds: true,
        });
      } else if (x == 2) {
        this.ps.categorySearch("BK9", this.placesSearchCB, {
          useMapBounds: true,
        });
      } else if (x == 3) {
        this.ps.categorySearch("MT1", this.placesSearchCB, {
          useMapBounds: true,
        });
      } else if (x == 4) {
        this.ps.categorySearch("SW8", this.placesSearchCB, {
          useMapBounds: true,
        });
      } else if (x == 5) {
        this.ps.categorySearch("CT1", this.placesSearchCB, {
          useMapBounds: true,
        });
      } else if (x == 6) {
        this.ps.categorySearch("FD6", this.placesSearchCB, {
          useMapBounds: true,
        });
      } else if (x == 7) {
        this.ps.categorySearch("CE7", this.placesSearchCB, {
          useMapBounds: true,
        });
      }
    },
    placesSearchCB(data, status) {
      this.location = data;
      // console.log(this.location.length);
      console.log("선택한 옵션 오브젝트: " + JSON.stringify(data[0]));
      // console.log("선택한 옵션 오브젝트: " + data[0].);

      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          var arr = [];
          arr = [data[i].y, data[i].x];
          this.cs_arr.push(arr);
          // console.log(data[i]);
          console.log("placeSearchCB창입니다.");
          this.displayMarker(data[i]);
        }
      }
      if (this.select_wanted != "" || this.select_wanted != 0) {
        this.getDistance();
      }
    },
    displayMarker(place) {
      if (this.select_wanted == 1) {
        var imageSrc = require("@/assets/Convenience.png"); // 마커이미지의 주소입니다
      } else if (this.select_wanted == 2) {
        imageSrc = require("@/assets/bank.png"); // 마커이미지의 주소입니다
      } else if (this.select_wanted == 3) {
        imageSrc = require("@/assets/mart.png"); // 마커이미지의 주소입니다
      } else if (this.select_wanted == 4) {
        imageSrc = require("@/assets/subway.png"); // 마커이미지의 주소입니다
      } else if (this.select_wanted == 5) {
        imageSrc = require("@/assets/movie.png"); // 마커이미지의 주소입니다
      } else if (this.select_wanted == 6) {
        imageSrc = require("@/assets/restaurant.png"); // 마커이미지의 주소입니다
      } else if (this.select_wanted == 7) {
        imageSrc = require("@/assets/cafe.png"); // 마커이미지의 주소입니다
      }

      // }
      var imageSize = new kakao.maps.Size(38, 40);
      // 마커이미지의 크기입니다
      var imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다

      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );
      // markerPosition = new kakao.maps.LatLng(lat1, lon1); // 마커가 표시될 위치입니다

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage, // 마커이미지 설정
      });

      // 마커가 지도 위에 표시되도록 설정합니다.
      // remove_location();
      marker.setMap(this.map);

      var content =
        '<div class="customoverlay">' +
        `  <a href=${place.place_url} target="_blank">` +
        `    <span class="title">${place.place_name}</span>` +
        "  </a>" +
        "</div>";

      var overlay = new kakao.maps.CustomOverlay({
        content: content,
        // map: this.map,
        // position: new kakao.maps.LatLng(place.y, place.x),
        // map: this.map,
        // position: new kakao.maps.LatLng(position.lat, position.lng),
        // removable: true,
      });
      // kakao.maps.event.addListener(marker, "click", () => {
      //   console.log("들어왔나?");
      //   overlay.setPosition(new kakao.maps.LatLng(position.lat, position.lng));
      // });

      kakao.maps.event.addListener(
        marker,
        "mouseover",
        makeOverListener(this.map, marker, overlay)
      );

      kakao.maps.event.addListener(
        marker,
        "mouseout",
        makeOutListener(overlay)
      );

      function makeOverListener(map, marker, overlay) {
        return function() {
          overlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
          overlay.setMap(map);
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      function makeOutListener(overlay) {
        return function() {
          overlay.setMap(null);
        };
      }

      // const marker = new kakao.maps.Marker({
      //   imgSrc: require("@/assets/pngwing.png"),
      //   map: this.map,
      //   position: new kakao.maps.LatLng(place.y, place.x),
      // });

      // marker.setMap(this.map);
      // 마커에 클릭이벤트를 등록합니다
      // this.locations.push(marker);
    },
    getDistance() {
      // console.log("여기는 디스턴스입니다.");
      // console.log(this.location.length);
      // console.log("여기는 집 정보: " + this.house_arr[0].lat);
      console.log("안녕하세요");
      console.log("길이: " + this.house_arr.length);
      for (var j = 0; j < this.house_arr.length; j++) {
        this.lat = this.house_arr[j].lat;
        this.lng = this.house_arr[j].lng;
        for (var i = 0; i < this.location.length; i++) {
          // console.log("안녕하세요");
          // console.log(
          //   "거리:!!!!!!!: " + this.location[i].y + ":" + this.location[i].x
          // );
          const house_info = this.house_arr[j];
          // console.log(house_info);
          var lat1 = this.lat;
          var lon1 = this.lng;

          var radLat1 = (Math.PI * lat1) / 180;
          var radLat2 = (Math.PI * this.location[i].y) / 180;
          var theta = lon1 - this.location[i].x;
          var radTheta = (Math.PI * theta) / 180;
          var dist =
            Math.sin(radLat1) * Math.sin(radLat2) +
            Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
          if (dist > 1) dist = 1;

          dist = Math.acos(dist);
          dist = (dist * 180) / Math.PI;
          dist = dist * 60 * 1.1515 * 1.609344 * 1000;
          if (dist < 100) dist = Math.round(dist / 10) * 10;
          else dist = Math.round(dist / 100) * 100;

          //		    return dist;
          console.log(dist); //1000은 1km를 의미

          if (dist <= 100) {
            console.log("houseinfo: " + house_info);
            //300미터 이하 근처에 원하는 옵션이 있음.
            //50m 이내의 경우 편세권!
            // if (this.select_wanted == 1) {
            // this.imgSrc.require("@/assets/pngwing.png");
            // this.imageSrc2 =
            //   "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png";
            var imageSrc =
                "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png", // 마커이미지의 주소입니다
              imageSize = new kakao.maps.Size(64, 69),
              // imageSize = new kakao.maps.Size(30, 30),
              // 마커이미지의 크기입니다
              imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
            // }

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(
                imageSrc,
                imageSize,
                imageOption
              ),
              markerPosition = new kakao.maps.LatLng(lat1, lon1); // 마커가 표시될 위치입니다
            this.lat = lat1;
            this.lng = lon1;

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
              position: markerPosition,
              image: markerImage, // 마커이미지 설정
            });

            // 마커가 지도 위에 표시되도록 설정합니다.
            // remove_location();
            marker.setMap(this.map);

            this.destination.push(marker);
            console.log("house_arr: " + house_info);
            var content =
              '<div class="customoverlay">' +
              `  <a href="" target="_blank">` +
              `    <span class="title">${house_info.aptCode}. ${house_info.aptName}</span>` +
              "  </a>" +
              "</div>";

            var overlay = new kakao.maps.CustomOverlay({
              content: content,
              // map: this.map,
              // position: new kakao.maps.LatLng(position.lat, position.lng),
              // removable: true,
            });
            // kakao.maps.event.addListener(marker, "click", () => {
            //   console.log("들어왔나?");
            //   overlay.setPosition(
            //     new kakao.maps.LatLng(position.lat, position.lng)
            //   );
            // });

            kakao.maps.event.addListener(
              marker,
              "mouseover",
              makeOverListener(this.map, marker, overlay, this.lat, this.lng)
            );

            kakao.maps.event.addListener(
              marker,
              "mouseout",
              makeOutListener(overlay)
            );

            //}
          } else {
            continue;
          }
        }
      }
      function makeOverListener(map, marker, overlay, lat, lng) {
        return function() {
          overlay.setPosition(new kakao.maps.LatLng(lat, lng));
          overlay.setMap(map);
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      function makeOutListener(overlay) {
        return function() {
          overlay.setMap(null);
        };
      }
    },
  },
};
</script>

<style>
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
