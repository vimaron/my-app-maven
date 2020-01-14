package ar.com.ada.maven.model.dto;


import java.util.Objects;

public class CountryDTO {
     private int id;
     private String name;
     private ContinentDTO continent;
     private Integer isoCode;

     public CountryDTO(){}

     public CountryDTO(int id, String name, int iso_cod, ContinentDTO continent) {
          this.id=id;
          this.name=name;
          this.isoCode= iso_cod;
     }

     public int getId(){return id;}
     public void setId(int id){this.id=id;}
     public String getName(){return name;}
     public void setName(String name){this.name=name;}
     public ContinentDTO getContinent() { return continent; }
     public Integer getIsoCode() { return isoCode; }
     public  void setContinent(ContinentDTO continent) { this.continent = continent; }
     public void setIsoCode(Integer isoCod) { this.isoCode = isoCode; }

     public CountryDTO(int id, String name, ContinentDTO continent){
          this.id=id;
          this.name=name;
          this.continent=continent;
     }


     @Override
     public String toString() {
          return "CountryDTO{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  '}';
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          CountryDTO that = (CountryDTO) o;
          return id == that.id &&
                  Objects.equals(name, that.name);
     }

}
