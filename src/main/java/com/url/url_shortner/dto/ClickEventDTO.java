package com.url.url_shortner.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Data
@Component
public class ClickEventDTO {
    private LocalDate clickDate;
    private Long count;

    public ClickEventDTO() {
        this.clickDate = clickDate;
        this.count = count;
    }

    public LocalDate getClickDate() {
        return clickDate;
    }

    public void setClickDate(LocalDate clickDate) {
        this.clickDate = clickDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }



}
