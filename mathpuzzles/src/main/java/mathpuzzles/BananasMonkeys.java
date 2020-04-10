package mathpuzzles;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BananasMonkeys {
	
    private static final Logger log = LoggerFactory.getLogger(BananasMonkeys.class);
    
	public static void main(String arg[]) {
		
		log.info("Starting..");
		
		List<Integer> winners = new ArrayList<Integer>();
		
		for(int i=1;i<=10000;i++) {
			
			Integer bananaCount = 4*i + 1;
			
			if(checkGameRule(bananaCount, false))
			{
				//log.info("Winner ==> {}", bananaCount);
				winners.add(bananaCount);
			}
			
		}
		
		

//		checkGameRule(winners.get(0), true);
//		checkGameRule(765, true);
		
		for(Integer winn:winners) {
			log.info(" Bananas = {}", winn);
			
			//log.info(" Run Game Rule ");
			checkGameRule(winn, true);
			//log.info("----------------------------------------------------------------------");
		}
		
		
		
		
	}
	
	
	private static Boolean checkGameRule(Integer bananaCount, Boolean showLog) {
		
		Boolean res = false;
		
		Integer remaining = bananaCount;
 
		//if(showLog) 	log.debug("Initital Count = {}", remaining);
		

		for(int i=1;i<=4;i++)
		{
			remaining = processPerson(i,remaining, showLog);
			
			if(remaining == null)
			{
				res = false;
				break;
			}
		}
		
		if (remaining != null && remaining%4 == 0) {
			
			if(showLog)
				log.info("Remaining Bananas for all 4 Persons {}, each person gets = {}",remaining , remaining/4);
			
			res = true;
		}
		
		
		
		return res;
	}

	
	
	private static Integer processPerson(int personSeq,Integer bananaCount, Boolean showLog) {
			
		Integer remaining = null;
		
		//if(showLog) log.info("Person Seq ==> {}", personSeq);
		
		
		
		if(bananaCount%4==1)
		{
			remaining = bananaCount - (bananaCount/4) - 1;
			
			if(showLog)
				log.debug("Person Seq: {}, Initial Count:{} Remaining: {}", personSeq,bananaCount,remaining);
		}
		
		return remaining ;
		
	}
	

}
