package app.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User information.
 * 
 * @author ground0state
 *
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    /**
     * Constructor.
     */
    public User() {}

    /**
     * Constructor.
     * 
     * @param id user id
     * @param name user name
     */
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * Getter for user ID.
     * 
     * @return user ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for user ID.
     * 
     * @param id user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for user name.
     * 
     * @return user name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for user name.
     * 
     * @param name user name
     */
    public void setName(String name) {
        this.name = name;
    }



}
