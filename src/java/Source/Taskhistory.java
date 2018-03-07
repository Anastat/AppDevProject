/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kasperi
 */
@Entity
@Table(name = "taskhistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taskhistory.findAll", query = "SELECT t FROM Taskhistory t")
    , @NamedQuery(name = "Taskhistory.findByHistoryID", query = "SELECT t FROM Taskhistory t WHERE t.historyID = :historyID")
    , @NamedQuery(name = "Taskhistory.findByTime", query = "SELECT t FROM Taskhistory t WHERE t.time = :time")})
public class Taskhistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "historyID")
    private Integer historyID;
    @Basic(optional = false)
    
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "taskNumber", referencedColumnName = "taskID")
    @ManyToOne(optional = false)
    private Tasks taskNumber;
    @JoinColumn(name = "changes", referencedColumnName = "taskStatusID")
    @ManyToOne(optional = false)
    private Taskstatus changes;
    @JoinColumn(name = "author", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users author;

    public Taskhistory() {
    }

    public Taskhistory(Integer historyID) {
        this.historyID = historyID;
    }

    public Taskhistory(Integer historyID, Date time) {
        this.historyID = historyID;
        this.time = time;
    }

    public Integer getHistoryID() {
        return historyID;
    }

    public void setHistoryID(Integer historyID) {
        this.historyID = historyID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Tasks getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Tasks taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Taskstatus getChanges() {
        return changes;
    }

    public void setChanges(Taskstatus changes) {
        this.changes = changes;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyID != null ? historyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taskhistory)) {
            return false;
        }
        Taskhistory other = (Taskhistory) object;
        if ((this.historyID == null && other.historyID != null) || (this.historyID != null && !this.historyID.equals(other.historyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Source.Taskhistory[ historyID=" + historyID + " ]";
    }
    
}
