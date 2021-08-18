/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh.servlet;

import entities.News;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import sessionbean.NewsFacadeLocal;

/**
 *
 * @author HP
 */
@Named(value="search")
@SessionScoped
public class ManageSearch implements Serializable{
    @EJB
    private NewsFacadeLocal newsFacade;
    
     private String s;
     private List<News> listNews;

    public ManageSearch() {
    }

    public NewsFacadeLocal getNewsFacade() {
        return newsFacade;
    }

    public void setNewsFacade(NewsFacadeLocal newsFacade) {
        this.newsFacade = newsFacade;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<News> getListNews() {
        return listNews;
    }

    public void setListNews(List<News> listNews) {
        this.listNews = listNews;
    }
      
    public void localeChanged(){
        System.out.println("searching..."+s);
        if(!s.equals("")){
            listNews = newsFacade.findNews(s);
        }
    }
}
