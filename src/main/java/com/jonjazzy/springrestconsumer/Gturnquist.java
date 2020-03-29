package com.jonjazzy.springrestconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
*   The @JsonIgnoreProperties(ignoreUnknown = true)annotation instructs Jackson to not give any errors
*   if it encounters any value in JSON string that is not present in you POJO.
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gturnquist
{
    public String type;
    public String value;

    @Override
    public String toString() {
        return "Gturnquist{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
