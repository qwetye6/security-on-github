

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Database {
	private HashMap<String, ArrayList<Location>> data = new HashMap<>();
	
	public Database()
	{
		Scanner x = null;
		try {
			
			x = new Scanner(new File("locations.txt"));
		}
			catch(Exception e){
				x = new Scanner(this.getClass().getResourceAsStream("locations.txt"));
			}
			while(x.hasNextLine())
			{
				String s = x.nextLine();
				String[] parts = s.split("\t");
				String place = parts[0];
				String address = parts[1];
				String type = parts[2];
				double lat = Double.parseDouble(parts[3]);
				double lon = Double.parseDouble(parts[4]);
				
					if(parts.length == 5)
					{
						Location a = new Location(place, address, type, lat, lon, "", 0);
						if(data.containsKey(type)== false)
						{
							ArrayList<Location> temp = new ArrayList<Location>();
							temp.add(a);
							data.put(type, temp);
						}
						else
						{
							ArrayList<Location> temp =data.get(type);
							temp.add(a);
							data.put(type, temp);
						}
					}
					else
					{
						String comment = parts[5];
						int rating = Integer.parseInt(parts[6]);
						Location a = new Location(place, address, type, lat, lon, comment, rating);
						if(data.containsKey(type)== false)
						{
							ArrayList<Location> temp = new ArrayList<Location>();
							temp.add(a);
							data.put(type, temp);
						}
						else
						{
							ArrayList<Location> temp =data.get(type);
							temp.add(a);
							data.put(type, temp);
						}
					
				}
			}
			
	}
	

	
public ArrayList<Location> getLiked(ArrayList<Location> q)
{
	ArrayList<Location> w = new ArrayList<>();
	for(Location e : q)
	{
		if(e.getRating() == 1)
			w.add(e);
	}
	return w;
}

public ArrayList<Location> getLocations(String t, double lat, double lon)
{
	ArrayList<Location> q1 = data.get(t);
	ArrayList<Location> q = new ArrayList<Location>();
	for (Location e: q1) {
		q.add(e);
	}
	
	ArrayList<Location> w = getLiked(q);
	if(q.size()<= 8)
	{
		return q;
	}
	else
	{
	while( w.size() > 8)
	{
		double d =0;
		double y = 0;
		int i = 0;
		int n =0;
		for(Location e : w)
		{
			y = Math.pow(lat - e.getLatitude(),2) + Math.pow(lon - e.getLongitude(),2);
			if(y > d)
			{
				i = n;
				d= y;
			}
			n++;
		}
		w.remove(i);
	}
	while( w.size() < 8)
	{
		double d = Integer.MAX_VALUE;
		double y = 0;
		int i = 0;
		int n =0;
		for(Location e : q)
		{
			if(e.getRating() == 0)
			{
				y = Math.pow(lat - e.getLatitude(),2) + Math.pow(lon - e.getLongitude(),2);
				if(y < d)
				{
					i = n;
					d =y;
				}
				n++;
			}
			else
			{
				n++;
			}
		}
		w.add(q.remove(i));
	}
	return w;
	}
}

public Set<String> list()
{
	return data.keySet();
}

public String p(String t)
{
	String r = "";
	ArrayList<Location> q = data.get(t);
	for(Location b : q)
	{
	r += b.getName() + "\n ";
	
	
	}
	return r;
}
/*
 * 
 */
public void s()
{
	try {
		PrintWriter writer = new PrintWriter(new FileWriter(new File("locations.txt")));
		
		for(ArrayList<Location> locations : data.values())
		{
			for(Location l : locations)
			{
				writer.println(l.getName()+"\t"+l.getAddress()+"\t"+l.getType()+"\t"+l.getLatitude()+"\t"+l.getLongitude()+"\t"+l.getComment()+"\t"+l.getRating());
				
			}
		}
		writer.close();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}

}
