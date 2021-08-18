/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Comment;
import entities.News;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface NewsFacadeLocal {

    void create(News news);

    void edit(News news);

    void remove(News news);

    News find(Object id);

    List<News> findAll();

    List<News> findRange(int[] range);

    int count();

    public List<News> findNews(String name);

    public List<News> findByNames(String s);

    public List<Comment> getComment(int newID);

    public List<Object[]> getStatistic(String fdate, String tdate);

    public int countLikeNews(int newID);

    public List<News> getLatest();

    public int checkLike(int newID, int userID);

    public int deleteLike(int newID, int userID);

    public int countNewsInDay(String date);

    public List<String> getDateNews(String fdate, String tdate);
    
}
