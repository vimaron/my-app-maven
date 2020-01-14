package ar.com.ada.maven.model.dto;


import java.util.Objects;

public class KindDTO {
    private int id;
    private String common_name;
    private String scientific_name;
    private boolean endangered;
    private FamilyDTO family;

    public KindDTO(){}

    public KindDTO(int id, String common_name, String scientific_name, boolean endangered, FamilyDTO family) {
        this.id=id;
        this.common_name=common_name;
        this.scientific_name=scientific_name;
        this.endangered=endangered;
        this.family=family;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getCommon_name(){return common_name;}
    public void setCommon_name(String common_name){this.common_name=common_name;}
    public String getScientific_name(){return scientific_name;}
    public void setScientific_name(String scientific_name){this.scientific_name=scientific_name;}
    public boolean getEndangered(){return endangered;}
    public void setEndangered(boolean endangered){this.endangered=endangered;}
    public FamilyDTO getFamily(){return family;}
    public void setFamily(){this.family=family;}


    @Override
    public String toString() {
        return "KindDTO{" +
                "id=" + id +
                ", common_name='" + common_name + '\'' +
                ", scientific_name='" + scientific_name + '\'' +
                ", endangered=" + endangered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KindDTO kindDTO = (KindDTO) o;
        return id == kindDTO.id &&
                endangered == kindDTO.endangered &&
                Objects.equals(common_name, kindDTO.common_name) &&
                Objects.equals(scientific_name, kindDTO.scientific_name);
    }

}
