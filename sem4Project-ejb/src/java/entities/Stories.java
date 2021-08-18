/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "Stories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stories.findAll", query = "SELECT s FROM Stories s")
    , @NamedQuery(name = "Stories.findByStoryID", query = "SELECT s FROM Stories s WHERE s.storyID = :storyID")
    , @NamedQuery(name = "Stories.findByFeeling", query = "SELECT s FROM Stories s WHERE s.feeling = :feeling")
    , @NamedQuery(name = "Stories.findByCreated", query = "SELECT s FROM Stories s WHERE s.created = :created")
    , @NamedQuery(name = "Stories.findBySongID", query = "SELECT s FROM Stories s WHERE s.songID = :songID")})
public class Stories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "StoryID")
    private Integer storyID;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Message")
    private String message;
    @Column(name = "Feeling")
    private Integer feeling;
    @Column(name = "Created")
    private Integer created;
    @Column(name = "SongID")
    private Integer songID;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Accounts accountID;

    public Stories() {
    }

    public Stories(Integer storyID) {
        this.storyID = storyID;
    }

    public Integer getStoryID() {
        return storyID;
    }

    public void setStoryID(Integer storyID) {
        this.storyID = storyID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getFeeling() {
        return feeling;
    }

    public void setFeeling(Integer feeling) {
        this.feeling = feeling;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getSongID() {
        return songID;
    }

    public void setSongID(Integer songID) {
        this.songID = songID;
    }

    public Accounts getAccountID() {
        return accountID;
    }

    public void setAccountID(Accounts accountID) {
        this.accountID = accountID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storyID != null ? storyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stories)) {
            return false;
        }
        Stories other = (Stories) object;
        if ((this.storyID == null && other.storyID != null) || (this.storyID != null && !this.storyID.equals(other.storyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Stories[ storyID=" + storyID + " ]";
    }
    
}
