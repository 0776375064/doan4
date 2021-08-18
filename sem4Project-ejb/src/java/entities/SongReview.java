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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "SongReview")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SongReview.findAll", query = "SELECT s FROM SongReview s")
    , @NamedQuery(name = "SongReview.findByReviewID", query = "SELECT s FROM SongReview s WHERE s.reviewID = :reviewID")
    , @NamedQuery(name = "SongReview.findByMessage", query = "SELECT s FROM SongReview s WHERE s.message = :message")
    , @NamedQuery(name = "SongReview.findByCreatedAt", query = "SELECT s FROM SongReview s WHERE s.createdAt = :createdAt")})
public class SongReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReviewID")
    private Integer reviewID;
    @Size(max = 250)
    @Column(name = "Message")
    private String message;
    @Column(name = "CreatedAt")
    private Integer createdAt;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Accounts accountID;
    @JoinColumn(name = "SongID", referencedColumnName = "SongID")
    @ManyToOne
    private Songs songID;

    public SongReview() {
    }

    public SongReview(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
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
        hash += (reviewID != null ? reviewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SongReview)) {
            return false;
        }
        SongReview other = (SongReview) object;
        if ((this.reviewID == null && other.reviewID != null) || (this.reviewID != null && !this.reviewID.equals(other.reviewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SongReview[ reviewID=" + reviewID + " ]";
    }
    
}
