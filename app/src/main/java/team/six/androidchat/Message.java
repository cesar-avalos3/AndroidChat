package team.six.androidchat;

/**
 * Created by Cesar on 4/1/2016.
 */
public class Message {

    private String author;
    private String content;

    public Message(String author, String content)
    {
        this.author = author;
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public String getAuthor()
    {
        return author;
    }

}
