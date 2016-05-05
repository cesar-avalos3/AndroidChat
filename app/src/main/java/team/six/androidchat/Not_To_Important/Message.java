/**
 *
 * @author Cesar Avalos, Alec Knutsen
 * @date  4/3/2016
 * @filename Message.java
 *
 */



//Base package for all java files in the android app
package team.six.androidchat.Not_To_Important;


/**
 * Class that contains two private member variables <code>author</code> and <code>message
 * </code>to store authors and messages
 *
 * @author Cesar Avalos
 *
 */
public class Message {

    /**
     * Represents the author of whoever wrote the message
     */
    private String author;

    /**
     * Represents the content of a message
     */
    private String content;

    /**
     * Basic constructor that initializes the instance variables <code>author</code> and <code>content</code>
     *
     * @param author the string that represents the author of a message
     * @param content the string that represents the content of a message
     */
    public Message(String author, String content)
    {
        this.author = author;
        this.content = content;
    }

    /**
     * Basic getMethod for the instance variable <code>content</code>
     *
     *@return the instance variable <code>content</code>
     */
    public String getContent()
    {
        return content;
    }


    /**
     * Basic getMethod for the instance variable <code>author</code>
     *
     *@return the class variable <code>author</code>
     */
    public String getAuthor()
    {
        return author;
    }

}
