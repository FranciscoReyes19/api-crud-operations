package com.web.analizer.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class IdentifierRequest {

    @NotBlank
    @Size( min = 1)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
