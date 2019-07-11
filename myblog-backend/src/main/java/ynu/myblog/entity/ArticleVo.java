package ynu.myblog.entity;



public class ArticleVo {

    private String title;
    private String description;
    private String content;
    private String content_format;
    private int category_id;

    public ArticleVo(){

    }

    public ArticleVo(String title, String description, String content, String content_format, int category_id) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.content_format = content_format;
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_format() {
        return content_format;
    }

    public void setContent_format(String content_format) {
        this.content_format = content_format;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
