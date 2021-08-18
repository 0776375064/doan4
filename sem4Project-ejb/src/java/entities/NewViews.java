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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "NewViews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewViews.findAll", query = "SELECT n FROM NewViews n")
    , @NamedQuery(name = "NewViews.findByViewID", query = "SELECT n FROM NewViews n WHERE n.viewID = :viewID")
    , @NamedQuery(name = "NewViews.findByView", query = "SELECT n FROM NewViews n WHERE n.view = :view")
    , @NamedQuery(name = "NewViews.findByCreatedAt", query = "SELECT n FROM NewViews n WHERE n.createdAt = :createdAt")})
public class NewViews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ViewID")
    private Integer viewID;
    @Column(name = "View")
    private Integer view;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @JoinColumn(name = "NewID", referencedColumnName = "NewID")
    @ManyToOne
    private News newID;

    public NewViews() {
    }

    public NewViews(Integer viewID) {
        this.viewID = viewID;
    }

    public Integer getViewID() {
        return viewID;
    }

    public void setViewID(Integer viewID) {
        this.viewID = viewID;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        hash += (viewID != null ? viewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewViews)) {
            return false;
        }
        NewViews other = (NewViews) object;
        if ((this.viewID == null && other.viewID != null) || (this.viewID != null && !this.viewID.equals(other.viewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NewViews[ viewID=" + viewID + " ]";
    }
    
}
