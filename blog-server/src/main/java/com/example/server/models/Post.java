package com.example.server.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("Post")
@Data
public class Post {
    @Id
    private UUID cuid;
    private UUID createdBy;
    private String name;
    private String title;
    private String content;
    private String slug;
    private LocalDateTime dateAdded = LocalDateTime.now();
}
