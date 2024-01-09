package com.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    private String id;

    @NotNull
    @Size(min = 5, message = "Title should be atleast 5 characters")
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @NotNull
    @Size(min = 10, message = "Content should not be less than 10 words")
    @Column(name = "content", nullable = false)
    private String content;
}
