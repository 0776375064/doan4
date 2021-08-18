/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "Accounts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a")
    , @NamedQuery(name = "Accounts.findByAccountID", query = "SELECT a FROM Accounts a WHERE a.accountID = :accountID")
    , @NamedQuery(name = "Accounts.findByUsername", query = "SELECT a FROM Accounts a WHERE a.username = :username")
    , @NamedQuery(name = "Accounts.findByPassword", query = "SELECT a FROM Accounts a WHERE a.password = :password")
    , @NamedQuery(name = "Accounts.findByFullname", query = "SELECT a FROM Accounts a WHERE a.fullname = :fullname")
    , @NamedQuery(name = "Accounts.findByEmail", query = "SELECT a FROM Accounts a WHERE a.email = :email")
    , @NamedQuery(name = "Accounts.findByAddress", query = "SELECT a FROM Accounts a WHERE a.address = :address")
    , @NamedQuery(name = "Accounts.findByFavourite", query = "SELECT a FROM Accounts a WHERE a.favourite = :favourite")
    , @NamedQuery(name = "Accounts.findByMoney", query = "SELECT a FROM Accounts a WHERE a.money = :money")
    , @NamedQuery(name = "Accounts.findByPhone", query = "SELECT a FROM Accounts a WHERE a.phone = :phone")
    , @NamedQuery(name = "Accounts.findByGender", query = "SELECT a FROM Accounts a WHERE a.gender = :gender")
    , @NamedQuery(name = "Accounts.findByAvatar", query = "SELECT a FROM Accounts a WHERE a.avatar = :avatar")
    , @NamedQuery(name = "Accounts.findByRole", query = "SELECT a FROM Accounts a WHERE a.role = :role")
    , @NamedQuery(name = "Accounts.findByStatus", query = "SELECT a FROM Accounts a WHERE a.status = :status")})
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AccountID")
    private Integer accountID;
    @Size(max = 20)
    @Column(name = "Username")
    private String username;
    @Size(max = 150)
    @Column(name = "Password")
    private String password;
    @Size(max = 70)
    @Column(name = "Fullname")
    private String fullname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Email")
    private String email;
    @Size(max = 50)
    @Column(name = "Address")
    private String address;
    @Size(max = 70)
    @Column(name = "Favourite")
    private String favourite;
    @Column(name = "Money")
    private Integer money;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "Phone")
    private String phone;
    @Size(max = 5)
    @Column(name = "Gender")
    private String gender;
    @Size(max = 50)
    @Column(name = "Avatar")
    private String avatar;
    @Size(max = 10)
    @Column(name = "Role")
    private String role;
    @Column(name = "Status")
    private Integer status;
    @OneToMany(mappedBy = "accountID")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<SongLikes> songLikesCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<FavouriteAlbum> favouriteAlbumCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<Advertise> advertiseCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<News> newsCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<Stories> storiesCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<ArtistFollow> artistFollowCollection;
    @OneToMany(mappedBy = "accountID")
    private Collection<SongReview> songReviewCollection;

    public Accounts() {
    }

    public Accounts(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<SongLikes> getSongLikesCollection() {
        return songLikesCollection;
    }

    public void setSongLikesCollection(Collection<SongLikes> songLikesCollection) {
        this.songLikesCollection = songLikesCollection;
    }

    @XmlTransient
    public Collection<FavouriteAlbum> getFavouriteAlbumCollection() {
        return favouriteAlbumCollection;
    }

    public void setFavouriteAlbumCollection(Collection<FavouriteAlbum> favouriteAlbumCollection) {
        this.favouriteAlbumCollection = favouriteAlbumCollection;
    }

    @XmlTransient
    public Collection<Advertise> getAdvertiseCollection() {
        return advertiseCollection;
    }

    public void setAdvertiseCollection(Collection<Advertise> advertiseCollection) {
        this.advertiseCollection = advertiseCollection;
    }

    @XmlTransient
    public Collection<News> getNewsCollection() {
        return newsCollection;
    }

    public void setNewsCollection(Collection<News> newsCollection) {
        this.newsCollection = newsCollection;
    }

    @XmlTransient
    public Collection<Stories> getStoriesCollection() {
        return storiesCollection;
    }

    public void setStoriesCollection(Collection<Stories> storiesCollection) {
        this.storiesCollection = storiesCollection;
    }

    @XmlTransient
    public Collection<ArtistFollow> getArtistFollowCollection() {
        return artistFollowCollection;
    }

    public void setArtistFollowCollection(Collection<ArtistFollow> artistFollowCollection) {
        this.artistFollowCollection = artistFollowCollection;
    }

    @XmlTransient
    public Collection<SongReview> getSongReviewCollection() {
        return songReviewCollection;
    }

    public void setSongReviewCollection(Collection<SongReview> songReviewCollection) {
        this.songReviewCollection = songReviewCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountID != null ? accountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.accountID == null && other.accountID != null) || (this.accountID != null && !this.accountID.equals(other.accountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Accounts[ accountID=" + accountID + " ]";
    }
    
}
