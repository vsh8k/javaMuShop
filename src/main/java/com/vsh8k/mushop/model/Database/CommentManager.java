package com.vsh8k.mushop.model.Database;

import com.vsh8k.mushop.model.AccountSystem.User;
import com.vsh8k.mushop.model.Shop.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {

    public List<Comment> getCommentsFromDB(DBConnector db, int productId) {
        try {
            System.out.println("getCommentsFromDB");
            ArrayList<Comment> comments = new ArrayList<>();
            db.connect();
            ResultSet maxDepthResultSet = db.query("SELECT MAX(reply_depth) FROM comments WHERE product_id = " + productId);
            int maxDepth = 0;
            if (maxDepthResultSet.next()) {
                maxDepth = maxDepthResultSet.getInt(1);
            }

            for (int i = 0; i <= maxDepth; i++) {
                ResultSet resultSet = db.query("SELECT * FROM comments WHERE product_id = " + productId + " AND reply_depth = " + i + " ORDER BY date_added DESC");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int authorId = resultSet.getInt("author_id"); // Assuming author_id column name
                    User author = UserManager.getUserFromDB(authorId, db); // Implement this method to retrieve User from DB
                    String commentText = resultSet.getString("body"); // Assuming column names
                    String commentTitle = resultSet.getString("title"); // Assuming column names
                    int parentCommId = resultSet.getInt("parent_comment_id"); // Assuming column names
                    float rating = resultSet.getFloat("rating"); // Assuming column names
                    LocalDate dateAdded = resultSet.getDate("date_added").toLocalDate(); // Assuming column names
                    Comment comment = new Comment(id, author, commentText, commentTitle, i, rating); // Assuming depth is 'i'
                    comments.add(comment);
                }
            }
            db.disconnect();
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
