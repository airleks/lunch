package com.test.lunch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * UserModel modelCreated by Алексей on 09.11.2015.
 */
@Entity
@Table(name = "users")
@JsonIgnoreProperties("password")
public class UserModel extends BasicModel<Long>
{
    private static final long serialVersionUID = 2803137463535188619L;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<VoteModel> votes;

    public UserModel()
    {
    }

    public UserModel(String login, String password, boolean isAdmin)
    {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Boolean getIsAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public Set<VoteModel> getVotes()
    {
        return votes;
    }

    public void setVotes(Set<VoteModel> votes)
    {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel user = (UserModel) o;

        return !(getId() != null ? !getId().equals(user.getId()) : user.getId() != null);

    }

    @Override
    public int hashCode()
    {
        return getId() != null ? getId().hashCode() : 0;
    }
}
