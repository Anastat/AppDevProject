package Source;

import Source.Departments;
import Source.Place;
import Source.Taskhistory;
import Source.Taskstatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T12:38:48")
@StaticMetamodel(Tasks.class)
public class Tasks_ { 

    public static volatile SingularAttribute<Tasks, String> attachment;
    public static volatile SingularAttribute<Tasks, Date> dueDate;
    public static volatile SingularAttribute<Tasks, String> detils;
    public static volatile SingularAttribute<Tasks, Place> place;
    public static volatile CollectionAttribute<Tasks, Taskhistory> taskhistoryCollection;
    public static volatile SingularAttribute<Tasks, Date> dueTime;
    public static volatile SingularAttribute<Tasks, String> title;
    public static volatile SingularAttribute<Tasks, Departments> department;
    public static volatile SingularAttribute<Tasks, Integer> taskID;
    public static volatile SingularAttribute<Tasks, Taskstatus> taskStatus;

}