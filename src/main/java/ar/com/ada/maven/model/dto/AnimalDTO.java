package ar.com.ada.maven.model.dto;

import java.sql.Date;
import java.util.Objects;

public class AnimalDTO {
    private int id;
    private String sex;
    private Date birthday;
    private KindDTO kindID;
    private CountryDTO countryID;

    public AnimalDTO(){}

    public AnimalDTO(int id, String sex, Date birthday, KindDTO kindID, CountryDTO countryID) {
        this.id = id;
        this.sex = sex;
        this.birthday = birthday;
        this.kindID = kindID;
        this.countryID = countryID;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getSex(){return sex;}
    public void setSex(String sex){this.sex=sex;}
    public Date getBirthday(){return birthday;}
    public void setBirthday(Date birthday){this.birthday=birthday;}
    public KindDTO getKindID(){return kindID;}
    public void setKindID(){this.kindID=kindID;}
    public CountryDTO getCountryID(){return countryID;}
    public void setCountryID(){this.countryID=countryID;}


    @Override
    public String toString() {
        return "AnimalDTO{" +
                "id=" + id +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDTO animalDTO = (AnimalDTO) o;
        return id == animalDTO.id &&
                sex == animalDTO.sex &&
                Objects.equals(birthday, animalDTO.birthday);
    }

}
