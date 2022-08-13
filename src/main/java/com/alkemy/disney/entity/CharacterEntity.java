package com.alkemy.disney.entity;

import com.alkemy.disney.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    private Integer age;

    private Double weight;

    private String story;

    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "cast", cascade = CascadeType.ALL)
    private List<MovieEntity> movies = new ArrayList<>() ;;

    public void addMovie(MovieEntity movie){this.movies.add(movie);}
    public void removeMovie(MovieEntity movie){this.movies.remove(movie);}

}
