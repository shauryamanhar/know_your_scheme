package com.enggcell.utilities;

import java.sql.Timestamp;
import java.util.Comparator;

import com.enggcell.entities.News;

public class SortTimelineByAddedDateDesc implements Comparator<News> {

	@Override
	public int compare(News o1, News o2) {
		Timestamp date1 = o1.getNewsDate(); 
		Timestamp date2 = o2.getNewsDate();
		if(date1.after(date2)){
			return -1;
		}else if(date1.before(date2)){
			return +1;
		}else{
			return 0;
		}
	}

}
