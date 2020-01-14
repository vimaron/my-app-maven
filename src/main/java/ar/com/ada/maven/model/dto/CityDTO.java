package ar.com.ada.maven.model.dto;

import java.util.Objects;

public class CityDTO {
    private int id;
    private String name;
    private CountryDTO countryID;

    public CityDTO(int id, String name, CountryDTO countryID) {
        this.id=id;
        this.name=name;
        this.countryID=countryID;
    }


    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public CountryDTO getCountryID(){return countryID;}
    public void setCountryID(){this.countryID=countryID;}


    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return id == cityDTO.id &&
                Objects.equals(name, cityDTO.name);
    }


}
