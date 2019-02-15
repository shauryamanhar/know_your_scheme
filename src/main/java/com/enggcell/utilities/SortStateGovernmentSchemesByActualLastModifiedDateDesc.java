package com.enggcell.utilities;

import java.sql.Timestamp;
import java.util.Comparator;

import com.enggcell.entities.News;
import com.enggcell.entities.StatementGovernmentSchemes;

public class SortStateGovernmentSchemesByActualLastModifiedDateDesc implements Comparator<StatementGovernmentSchemes> {

	@Override
	public int compare(StatementGovernmentSchemes o1, StatementGovernmentSchemes o2) {
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
