package com.santatostada.restservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class GetUser {
    private final String id;
    private final String name;
}
