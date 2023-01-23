package com.io.qleoz12.api.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Schools")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private Integer status;
}
