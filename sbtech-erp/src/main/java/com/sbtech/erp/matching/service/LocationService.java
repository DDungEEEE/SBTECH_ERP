package com.sbtech.erp.matching.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.matching.adapter.out.dto.LocationInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
@Slf4j
public class LocationService {

    @Value("${GEOCODING_API_KEY")
    private String geocodingApiKey;

    public LocationInfo getLocationByAddress(String clientAddress){
        try{

            String encodedAddress = URLEncoder.encode(clientAddress, StandardCharsets.UTF_8)
                    .replace("+", "%20");

            String surl = "https://maps.googleapis.com/maps/api/geocode/json?address="
                    + encodedAddress + "&key=" + geocodingApiKey + "&language=ko";

            URL url = new URL(surl);
            InputStream is = url.openConnection().getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;

            while((inputStr = streamReader.readLine()) != null){
                responseStrBuilder.append(inputStr);
            }

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            if (!jsonObject.getString("status").equals("OK")) {
                log.error("Google API Error: " + jsonObject.toString());
                return null;
            }

            JSONArray results = jsonObject.getJSONArray("results");
            HashMap<String, String> ret = new HashMap<>();

            if(!results.isEmpty()){
                JSONObject jo = results.getJSONObject(0);

                // 위도 & 경도 추출
                Double lat = jo.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                Double lng = jo.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                JSONArray addressComponents = jo.getJSONArray("address_components");
                String province = "";
                String city = "";

                for(int i=0; i < addressComponents.length(); i++){
                    JSONObject component = addressComponents.getJSONObject(i);
                    JSONArray types = component.getJSONArray("types");
                    String longName = component.getString("long_name");
                    if(types.toString().contains("administrative_area_level_1")){
                        province = longName; // 도
                    }else if(types.toString().contains("locality")){
                        city = longName; // 시
                    }
                }

                ret.put("lat", lat.toString());
                ret.put("lng", lng.toString());
                ret.put("do", province);
                ret.put("si", city);

                return LocationInfo.builder()
                        .lat(lat.toString())
                        .lng(lng.toString())
                        .province(province)
                        .city(city)
                        .build();
            }
        }catch (Exception ex) {
            ErrorCode internalServerError = ErrorCode.INTERNAL_SERVER_ERROR;
            log.error(ex.getMessage());
        }
        return null;
    }
}
