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
@Table(name = "NewsLike")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewsLike.findAll", query = "SELECT n FROM NewsLike n")
    , @NamedQuery(name = "NewsLike.findByNewLikeID", query = "SELECT n FROM NewsLike n WHERE n.newLikeID = :newLikeID")
    , @NamedQuery(name = "NewsLike.findByAccountID", query = "SELECT n FROM NewsLike n WHERE n.accountID = :accountID")})
public class NewsLike implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NewLikeID")
    private Integer newLikeID;
    @Column(name = "AccountID")
    private Integer accountID;
    @JoinColumn(name = "NewID", referencedColumnName = "NewID")
    @ManyToOne
    private News newID;

    public NewsLike() {
    }

    public NewsLike(Integer newLikeID) {
        this.newLikeID = newLikeID;
    }

    public Integer getNewLikeID() {
        return newLikeID;
    }

    public void setNewLikeID(Integer newLikeID) {
        this.newLikeID = newLikeID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public News getNewID() {
        return newID;
    }

    public void setNewID(News newID) {
        this.newID = newID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newLikeID != null ? newLikeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewsLike)) {
            return false;
        }
        NewsLike other = (NewsLike) object;
        if ((this.newLikeID == null && other.newLikeID != null) || (this.newLikeID != null && !this.newLikeID.equals(other.newLikeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NewsLike[ newLikeID=" + newLikeID + " ]";
    }
    
}
