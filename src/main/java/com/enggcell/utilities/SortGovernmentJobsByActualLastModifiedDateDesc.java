package com.enggcell.utilities;

import java.sql.Timestamp;
import java.util.Comparator;

import com.enggcell.entities.ApplicationForms;
import com.enggcell.entities.GovernmentJobs;
import com.enggcell.entities.News;
import com.enggcell.entities.StatementGovernmentSchemes;

public class SortGovernmentJobsByActualLastModifiedDateDesc implements Comparator<GovernmentJobs> {

	@Override
	public int compare(GovernmentJobs o1, GovernmentJobs o2) {
		Timestamp date1 = o1.getActualLastModifiedDate(); 
		Timestamp date2 = o2.getActualLastModifiedDate();
		if(date1.after(date2)){
			return -1;
		}else if(date1.before(date2)){
			return +1;
		}else{
			return 0;
		}
	}

}
