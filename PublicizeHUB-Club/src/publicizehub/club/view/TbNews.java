/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 *
 * @author ImagineRabbits
 */
@Entity
@Table(name = "tb_news", catalog = "db_event", schema = "")
@NamedQueries({
    @NamedQuery(name = "TbNews.findAll", query = "SELECT t FROM TbNews t")
    , @NamedQuery(name = "TbNews.findByNewsId", query = "SELECT t FROM TbNews t WHERE t.newsId = :newsId")})
public class TbNews implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "newsId")
    private Integer newsId;
    @Basic(optional = false)
    @Lob
    @Column(name = "content")
    private String content;

    public TbNews() {
    }

    public TbNews(Integer newsId) {
        this.newsId = newsId;
    }

    public TbNews(Integer newsId, String content) {
        this.newsId = newsId;
        this.content = content;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        Integer oldNewsId = this.newsId;
        this.newsId = newsId;
        changeSupport.firePropertyChange("newsId", oldNewsId, newsId);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        String oldContent = this.content;
        this.content = content;
        changeSupport.firePropertyChange("content", oldContent, content);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newsId != null ? newsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbNews)) {
            return false;
        }
        TbNews other = (TbNews) object;
        if ((this.newsId == null && other.newsId != null) || (this.newsId != null && !this.newsId.equals(other.newsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publicizehub.club.view.TbNews[ newsId=" + newsId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
