/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "Feedbacks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedbacks.findAll", query = "SELECT f FROM Feedbacks f")
    , @NamedQuery(name = "Feedbacks.findByFeedbackID", query = "SELECT f FROM Feedbacks f WHERE f.feedbackID = :feedbackID")
    , @NamedQuery(name = "Feedbacks.findByTitle", query = "SELECT f FROM Feedbacks f WHERE f.title = :title")
    , @NamedQuery(name = "Feedbacks.findByEmail", query = "SELECT f FROM Feedbacks f WHERE f.email = :email")
    , @NamedQuery(name = "Feedbacks.findByCreatedAt", query = "SELECT f FROM Feedbacks f WHERE f.createdAt = :createdAt")})
public class Feedbacks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FeedbackID")
    private Integer feedbackID;
    @Size(max = 100)
    @Column(name = "Title")
    private String title;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 80)
    @Column(name = "Email")
    private String email;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Feedbacks() {
    }

    public Feedbacks(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Integer getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        this.feedbackID = feedbackID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackID != null ? feedbackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedbacks)) {
            return false;
        }
        Feedbacks other = (Feedbacks) object;
        if ((this.feedbackID == null && other.feedbackID != null) || (this.feedbackID != null && !this.feedbackID.equals(other.feedbackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Feedbacks[ feedbackID=" + feedbackID + " ]";
    }
    
}
