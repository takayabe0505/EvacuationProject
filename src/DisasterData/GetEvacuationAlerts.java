package DisasterData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetEvacuationAlerts {

	public static void main(String[] args) throws IOException, ParseException{
		File in = new File("c:/users/t-tyabe/Desktop/DisasterAlertData_20160224.csv");
		File out = new File("c:/users/t-tyabe/Desktop/DisasterAlertData_evacuation.csv");
		
		choosebyEvacuationbyDate(in,out,"2014-09-25","2015-10-31");
	}

	protected static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");//change time format

	public static File choosebyEvacuationbyDate(File in, File out, String startdat, String enddate) throws IOException, ParseException{

		Date startdate = YMD.parse(startdat);
		Date enddat    = YMD.parse(enddate);

		BufferedReader br = new BufferedReader(new FileReader(in));
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));
		String line = br.readLine();
		while((line = br.readLine())!= null){
			String[] tokens = line.split("\t");
			String type = tokens[1];
			if((type.equals("evac"))||(type.equals("warn"))||
					(type.equals("flood"))||(type.equals("emg2"))||
					(type.equals("dosha"))||(type.equals("volc"))){
				String[] ymd = tokens[0].split("/");
				String daytime = ymd[2];
				String[] d_t = daytime.split(" ");
				String dt = ymd[0]+"-"+ymd[1]+"-"+d_t[0];
				Date disdate = YMD.parse(dt);
				if((disdate.after(startdate))&&(disdate.before(enddat))){
					bw.write(line);
					bw.newLine();
				}
			}
		}
		br.close();
		bw.close();
		return out;
	}

}
