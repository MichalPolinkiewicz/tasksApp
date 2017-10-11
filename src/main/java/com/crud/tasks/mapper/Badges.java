package com.crud.tasks.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Lenovo on 11.10.2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badges {
    private int votes;
    private AttachmentsByType attachmentsByType;
}
