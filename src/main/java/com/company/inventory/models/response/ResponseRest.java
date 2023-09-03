package com.company.inventory.models.response;

import org.springframework.http.HttpStatus;

import java.util.*;

public abstract class ResponseRest {

    private List< Map<String, Object> > metadata = new ArrayList<>();

    public List< Map<String, Object> > getMetadata() {
        return metadata;
    }

    public void setMetadata(HttpStatus type, String message) {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("type", type.getReasonPhrase());
        map.put("code", type.value());
        map.put("date", new Date());
        map.put("message", message);

        metadata.add(map);
    }

}
