package c.sakshi.lab5;

public class Note {
    private String date;
    private String username;
    private String title;
    private String content;

    public Note(String date, String username, String title, String content){
        this.date = date;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }
}
