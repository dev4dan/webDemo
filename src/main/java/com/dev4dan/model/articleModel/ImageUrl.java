package com.dev4dan.model.articleModel;

/**
 * Created by danielwood on 15/05/2017.
 */
public class ImageUrl {
    private long id;
    private long articleId; // 图片所在文章id
    private String imagelUrl; // 图片所在位置

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getImagelUrl() {
        return imagelUrl;
    }

    public void setImagelUrl(String imagelUrl) {
        this.imagelUrl = imagelUrl;
    }
}
