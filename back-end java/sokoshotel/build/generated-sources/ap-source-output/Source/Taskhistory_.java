package Source;

import Source.Tasks;
import Source.Taskstatus;
import Source.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-27T10:49:14")
@StaticMetamodel(Taskhistory.class)
public class Taskhistory_ { 

    public static volatile SingularAttribute<Taskhistory, Integer> historyID;
    public static volatile SingularAttribute<Taskhistory, Users> author;
    public static volatile SingularAttribute<Taskhistory, Taskstatus> changes;
    public static volatile SingularAttribute<Taskhistory, Date> time;
    public static volatile SingularAttribute<Taskhistory, Tasks> taskNumber;

}