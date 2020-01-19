package br.com.stoom.backend.qualification.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapsResponse {

    @JsonProperty("results")
    private List<MapsResult> result;

    @JsonProperty("status")
    private String status;

    public List<MapsResult> getResult() {
        return result;
    }

    public void setResult(List<MapsResult> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
