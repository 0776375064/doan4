/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "News")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n")
    , @NamedQuery(name = "News.findByNewID", query = "SELECT n FROM News n WHERE n.newID = :newID")
    , @NamedQuery(name = "News.findByTitle", query = "SELECT n FROM News n WHERE n.title = :title")
    , @NamedQuery(name = "News.findByThumbnail", query = "SELECT n FROM News n WHERE n.thumbnail = :thumbnail")
    , @NamedQuery(name = "News.findByViews", query = "SELECT n FROM News n WHERE n.views = :views")
    , @NamedQuery(name = "News.findByCreatedAt", query = "SELECT n FROM News n WHERE n.createdAt = :createdAt")})
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NewID")
    private Integer newID;
    @Size(max = 250)
    @Column(name = "Title")
    private String title;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Size(max = 100)
    @Column(name = "Thumbnail")
    private String thumbnail;
    @Column(name = "Views")
    private Integer views;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "newID")
    private Collection<NewViews> newViewsCollection;
    @OneToMany(mappedBy = "newID")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "newID")
    private Collection<NewsLike> newsLikeCollection;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Accounts accountID;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    @ManyToOne
    private Categories categoryID;

    public News() {
    }

    public News(Integer newID) {
        this.newID = newID;
    }

    public Integer getNewID() {
        return newID;
    }

    public void setNewID(Integer newID) {
        this.newID = newID;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @XmlTransient
    public Collection<NewViews> getNewViewsCollection() {
        return newViewsCollection;
    }

    public void setNewViewsCollection(Collection<NewViews> newViewsCollection) {
        this.newViewsCollection = newViewsCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<NewsLike> getNewsLikeCollection() {
        return newsLikeCollection;
    }

    public void setNewsLikeCollection(Collection<NewsLike> newsLikeCollection) {
        this.newsLikeCollection = newsLikeCollection;
    }

    public Accounts getAccountID() {
        return accountID;
    }

    public void setAccountID(Accounts accountID) {
        this.accountID = accountID;
    }

    public Categories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newID != null ? newID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if ((this.newID == null && other.newID != null) || (this.newID != null && !this.newID.equals(other.newID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.News[ newID=" + newID + " ]";
    }
    
}
