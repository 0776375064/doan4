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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "SongLikes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SongLikes.findAll", query = "SELECT s FROM SongLikes s")
    , @NamedQuery(name = "SongLikes.findByLikeID", query = "SELECT s FROM SongLikes s WHERE s.likeID = :likeID")})
public class SongLikes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LikeID")
    private Integer likeID;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Accounts accountID;
    @JoinColumn(name = "SongID", referencedColumnName = "SongID")
    @ManyToOne
    private Songs songID;

    public SongLikes() {
    }

    public SongLikes(Integer likeID) {
        this.likeID = likeID;
    }

    public Integer getLikeID() {
        return likeID;
    }

    public void setLikeID(Integer likeID) {
        this.likeID = likeID;
    }

    public Accounts getAccountID() {
        return accountID;
    }

    public void setAccountID(Accounts accountID) {
        this.accountID = accountID;
    }

    public Songs getSongID() {
        return songID;
    }

    public void setSongID(Songs songID) {
        this.songID = songID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (likeID != null ? likeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SongLikes)) {
            return false;
        }
        SongLikes other = (SongLikes) object;
        if ((this.likeID == null && other.likeID != null) || (this.likeID != null && !this.likeID.equals(other.likeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SongLikes[ likeID=" + likeID + " ]";
    }
    
}
