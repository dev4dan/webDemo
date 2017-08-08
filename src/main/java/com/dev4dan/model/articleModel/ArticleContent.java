package com.dev4dan.model.articleModel;

/**
 * Created by danielwood on 15/05/2017.
 */
public class ArticleContent {
    private int id;
    private long articleId; // 文章id
    private String content; // 文章内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
