package com.vsh8k.mushop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Comment {
    private int id;
    private String commentTitle;
    private String commentBody;
    private User owner;
    private List<Comment> replies;
    private float rating;
}
