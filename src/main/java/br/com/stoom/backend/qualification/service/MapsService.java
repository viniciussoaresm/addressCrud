package br.com.stoom.backend.qualification.service;

import br.com.stoom.backend.qualification.pojo.dto.Geometry;
import br.com.stoom.backend.qualification.pojo.dto.Location;
import br.com.stoom.backend.qualification.pojo.dto.MapsResponse;
import br.com.stoom.backend.qualification.pojo.dto.MapsResult;
import br.com.stoom.backend.qualification.utils.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapsService extends RestTemplateConfig {

    @Value("${service.maps.host}")
    private String host;

    @Value("${service.maps.key}")
    private String key;

    private static String path = "/json";

   public Location getLocationByCep(String cep) {

       try {
           RestTemplate restTemplate = new RestTemplate();
           System.out.println(host+path+"?address="+cep+"&key="+key);
           MapsResponse response = restTemplate.getForObject(host+path+"?address="+cep+"&key="+key, MapsResponse.class);

           Geometry geometry = response.getResult().get(0).getGeometry();

           if (geometry != null) {
               return geometry.getLocation();
           }
       } catch (Exception ex) {
               System.out.println(ex.getMessage());
               return null;
       }
       return null;
   }
}
