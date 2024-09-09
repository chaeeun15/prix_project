package com.prix.user.DTO;

import com.prix.user.Entity.DatabaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@Builder
public class DatabaseDTO {
    private int id;
    private int data_id;
    private MultipartFile file;
    private String name;

    public DatabaseEntity toEntity(){
        DatabaseEntity build = DatabaseEntity.builder()
                .id(id)
                .data_id(data_id)
                .file(file.getOriginalFilename())
                .name(name)
                .build();
        return build;
    }

    @Builder
    public DatabaseDTO(int id, int data_id, MultipartFile file, String name){
        this.id = id;
        this.data_id = data_id;
        this.file = file;
        this.name = name;
    }
}
