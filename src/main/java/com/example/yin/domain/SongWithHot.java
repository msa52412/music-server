package com.example.yin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongWithHot {
    private String name;
    private Integer id;
    private Integer hot;
}
