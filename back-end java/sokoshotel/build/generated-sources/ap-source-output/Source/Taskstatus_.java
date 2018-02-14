package Source;

import Source.Taskhistory;
import Source.Tasks;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T12:38:48")
@StaticMetamodel(Taskstatus.class)
public class Taskstatus_ { 

    public static volatile SingularAttribute<Taskstatus, Integer> taskStatusID;
    public static volatile SingularAttribute<Taskstatus, String> statusName;
    public static volatile CollectionAttribute<Taskstatus, Tasks> tasksCollection;
    public static volatile CollectionAttribute<Taskstatus, Taskhistory> taskhistoryCollection;

}