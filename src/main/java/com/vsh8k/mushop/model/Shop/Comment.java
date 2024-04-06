package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.AccountSystem.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class Comment extends Message{
    public Comment(int id, User author, String content, String title, int reply_depth, float rating){
        super(id, author, content);
        this.title = title;
        this.reply_depth = reply_depth;
        this.rating = rating;
    }
    private String title;
    private List<Comment> replies;
    private int reply_depth;
    private float rating;
}

