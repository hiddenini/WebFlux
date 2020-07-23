package com.xz.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class City {
    /**
     * 城市编号
     */
    @Id
    private String id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    @NotBlank
    private String cityName;

    /**
     * 描述
     */
    private String description;

}
