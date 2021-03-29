package com.DatabaseDao;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.tomcat.util.codec.binary.Base64;


import com.Model.CreateAccount;
import com.Model.DisplayAllData;
import com.Model.DisplayWeather;
import com.Model.LoginAccount;

import java.util.Date;

public class DatabaseHelper 
{
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	public DatabaseHelper()
	{
		
	}
	public Connection getConnection()
	{
		try 
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Prad@4520");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	public long generateCustomerIdFarm()
	{
		long custid=300001;
		int count=0;
		try 
		{
			con=getConnection();
			Statement s=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=s.executeQuery("select * from FarmerInfo");
			while(rs.next())
			{
				count++;
			}
			custid=custid+count;
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return custid;
	}
	
	public long generateCustomerIdDeal()
	{
		con=getConnection();
		long custid=900001;
		int count=0;
		try 
		{
			Statement s=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=s.executeQuery("select * from DealerInfo");
			while(rs.next())
			{
				count++;
			}
			custid=custid+count;
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return custid;
	}
	
	public long createAccountFarm(List<CreateAccount> list)
	{
		int cnt=0;
		CreateAccount Accountobj=list.get(0);
		long farmid=generateCustomerIdFarm();
		con=getConnection();
		System.out.println("hello5");
		try 
		{
			ps=con.prepareStatement("insert into FarmerInfo values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, farmid);
			ps.setString(2,Accountobj.getFirstName());
			ps.setString(3,Accountobj.getLastName());
			ps.setString(4,Accountobj.getDateOfBirth());
			ps.setString(5, Accountobj.getGender());
			ps.setString(6,Accountobj.getCity());
			ps.setString(7,Accountobj.getState());
			ps.setString(8,Accountobj.getNationality());
			cnt=ps.executeUpdate();
			if(cnt >0 ){
				
				ps=con.prepareStatement("insert into Farmer_vari_info values(?,?,?)");
				ps.setLong(1, farmid);
				ps.setString(2,Accountobj.getEmailId());
				ps.setLong(3, Accountobj.getMobileNo());
				cnt=ps.executeUpdate();
			}
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(cnt>0)
			return farmid;
		else
			return 0;
	}
	public int createLoginAccountFarm(List<LoginAccount> list, long id)
	{
		int cnt=0;
		LoginAccount Accountobj=list.get(0);
		con=getConnection();
		
		try 
		{
			ps=con.prepareStatement("insert into FarmerLogin values(?,?,?)");
			ps.setLong(1, id);
			ps.setString(2, Accountobj.getUname());
			ps.setString(3, Accountobj.getPass());
			cnt=ps.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return cnt;	
	}
	
	public int createLoginAccountDeal(List<LoginAccount> list, long id)
	{
		int cnt=0;
		LoginAccount Accountobj=list.get(0);
		con=getConnection();
		
		try 
		{
			ps=con.prepareStatement("insert into DealerLogin values(?,?,?)");
			ps.setLong(1, id);
			ps.setString(2, Accountobj.getUname());
			ps.setString(3, Accountobj.getPass());
			cnt=ps.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return cnt;	
	}
	
	public long createAccountDealer(List<CreateAccount> list)
	{
		int cnt=0;
		CreateAccount Accountobj=list.get(0);
		long dealid=generateCustomerIdDeal();
		con=getConnection();
		try 
		{
			ps=con.prepareStatement("insert into DealerInfo values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, dealid);
			ps.setString(2,Accountobj.getFirstName());
			ps.setString(3,Accountobj.getLastName());
			ps.setString(4,Accountobj.getDateOfBirth());
			ps.setString(5, Accountobj.getGender());
			ps.setString(6,Accountobj.getCity());
			ps.setString(7,Accountobj.getState());
			ps.setString(8,Accountobj.getNationality());
			cnt=ps.executeUpdate();
			if(cnt >0 ){
				ps=con.prepareStatement("insert into Dealer_vari_info values(?,?,?)");
				ps.setLong(1, dealid);
				ps.setString(2,Accountobj.getEmailId());
				ps.setLong(3, Accountobj.getMobileNo());
				cnt=ps.executeUpdate();
			}
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dealid;	
	}
	
	
	public String getMailIdByFarmid(String id)
	{
		con=getConnection();
		String Farmemail=null;

			try 
			{
				ps=con.prepareStatement("select * from Farmer_vari_info where F_id=?");
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next())
				{
					Farmemail = rs.getString(2);
				}
				else{
					Farmemail = "no";
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return Farmemail;
	
	}

	public List<DisplayAllData> getAllValidAcc()
	{
		List<DisplayAllData> lst=new ArrayList<DisplayAllData>();
		con=getConnection();
		try
		{
			Statement s=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=s.executeQuery("select *from FarmCropInfo");
			while(rs.next())
			{
				DisplayAllData obj=new DisplayAllData();
				obj.setF_id(rs.getString(1));
				obj.setCrop(rs.getString(2));
				obj.setWgt(rs.getString(3));
				lst.add(obj);
			}		
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return lst;
	}
	
	public int cropInput(long id, String crop, String quant)
	{
		int cnt=0;
		con=getConnection();
		try 
		{
			ps=con.prepareStatement("insert into FarmCropInfo values(?,?,?)");
			ps.setLong(1, id);
			ps.setString(2,crop);
			ps.setString(3,quant);
			cnt=ps.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return cnt;	
	}
	
	public  List<DisplayWeather> cropWeather(String CropeName, String weather)
	{
		List<DisplayWeather> lst=new ArrayList<DisplayWeather>();
		con=getConnection();
		try
		{
			ps=con.prepareStatement("select *from Crop_info where CropName=? and Weather=?");
			ps.setString(1, CropeName);
			ps.setString(2, weather);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				DisplayWeather obj=new DisplayWeather();
				obj.setCropInfo(rs.getString(3));
				obj.setCare(rs.getString(4));
				lst.add(obj);
			}		
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return lst;	
	}

	public int checkEmailDeal(String username,String emailid)
	{
		int cnt=0;
		String userid=null, email=null;
		try 
		{
				userid=getDealerId(username);
				email=findEmailId(userid);
				if(email.equals(emailid))
				{
					cnt=1;
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return cnt;
	}
	
	
	public int checkEmailFarm(String username,String emailid)
	{
		int cnt=0;
		String userid=null, email=null;
		try 
		{
			userid=getFarmId(username);
			email=findEmailId(userid);
			if(email.equals(emailid))
				{
					cnt=1;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return cnt;
	}
	
	public String findEmailId(String id)
	{
		con=getConnection();
		String userEmail=null;

			try 
			{
				ps=con.prepareStatement("select * from Farmer_vari_info where F_id=?");
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next())
				{
					userEmail = rs.getString(2);
				}
				else{
					ps=con.prepareStatement("select * from Dealer_vari_info where D_id=?");
					ps.setString(1, id);
					rs=ps.executeQuery();
					if(rs.next())
					{
						userEmail = rs.getString(2);
					}
					
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return userEmail;
	
	}

	public int changePass(String username,String epass)
	{
		int cnt=0;
		try 
		{
			con=getConnection();
			ps=con.prepareStatement("update FarmerLogin set Password=?  where F_Username=?");
			byte[] encode=Base64.encodeBase64(epass.getBytes());
			String epass1=new String(encode);
			ps.setString(1, epass1);
			ps.setString(2, username);
			cnt=ps.executeUpdate();
			con.close();
			if(cnt<= 0){
				con=getConnection();
				ps=con.prepareStatement("update DealerLogin set Password=?  where D_Username=?");
				byte[] encode1=Base64.encodeBase64(epass.getBytes());
				String epass2=new String(encode1);
				ps.setString(1, epass2);
				ps.setString(2, username);
				cnt=ps.executeUpdate();
				con.close();	
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public List<DisplayWeather> cropDispWeather()
	{
		List<DisplayWeather> lst=new ArrayList<DisplayWeather>();
		con=getConnection();
		try
		{
			Statement s=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=s.executeQuery("select *from Crop_info");
			while(rs.next())
			{
				DisplayWeather obj=new DisplayWeather();
				obj.setCrop(rs.getString(1));
				obj.setWeather(rs.getString(2));
				lst.add(obj);
			}		
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return lst;
	}
	public Long validateUserId(String Userid)
	{
		con=getConnection();
		long Farm_id=0, Dealer_id=0;

			try 
			{
				ps=con.prepareStatement("select * from FarmerLogin where F_Username=?");
				ps.setString(1, Userid);
				rs=ps.executeQuery();
				if(rs.next())
				{
					Farm_id = rs.getLong(2);
				}
				else{
					ps=con.prepareStatement("select * from DealerLogin where D_Username=?");
					ps.setString(1, Userid);
					rs=ps.executeQuery();
					if(rs.next())
					{
						Dealer_id = rs.getLong(2);
					}
					
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			if(Farm_id != 0){
				return Farm_id;
			}
			else if (Dealer_id != 0) {
				
			} {
				return Dealer_id;
			}
	
	}
	public int generatePassword()
	{
		int max=99999;
		int min=1000;
		Random obj=new Random();
		int randomNum = obj.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	public String validateAccount(List<LoginAccount> list){
		LoginAccount Accountobj=list.get(0);
		int id=0;
		String Farm_pass=null, Dealer_pass=null,pass1=null;
		try{
			
			String uname=Accountobj.getUname();
			String pass=Accountobj.getPass();
			con=getConnection();
			ps=con.prepareStatement("select * from FarmerLogin where F_Username=? and Password=?");
			ps.setString(1, uname);
			byte[] encode=Base64.encodeBase64(pass.getBytes());
			String pin1=new String(encode);
			ps.setString(2, pin1);
			rs=ps.executeQuery();
			if(rs.next())
			{
				id=1;
			}
			else{
				ps=con.prepareStatement("select * from DealerLogin where D_Username=? and Password=?");
				ps.setString(1, uname);
				byte[] encode1=Base64.encodeBase64(pass.getBytes());
				String pin2=new String(encode1);
				ps.setString(2, pin2);
				rs=ps.executeQuery();
				if(rs.next())
				{
					id=2;
				}
				
			}
			con.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		if(id==1)
		{
			return "Farmer";
		}
		else if (id==2){
			return "Dealer";
		}
		return " ";
	}
	
	public String getFarmId(String Userid)
	{
		con=getConnection();
		String Farm_id=null, Dealer_id=null;

			try 
			{
				ps=con.prepareStatement("select * from FarmerLogin where F_Username=?");
				ps.setString(1, Userid);
				rs=ps.executeQuery();
				if(rs.next())
				{
					Farm_id = rs.getString(1);
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return Farm_id;
	}

	public String getDealerId(String Userid)
	{
		con=getConnection();
		String Deal_id=null;

			try 
			{
				ps=con.prepareStatement("select * from DealerLogin where D_Username=?");
				ps.setString(1, Userid);
				rs=ps.executeQuery();
				if(rs.next())
				{
					Deal_id = rs.getString(1);
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return Deal_id;
	}


	public long getFarmIdl(String Userid)
	{
		con=getConnection();
		long Farm_id=0, Dealer_id=0;

			try 
			{
				ps=con.prepareStatement("select * from FarmerLogin where F_Username=?");
				ps.setString(1, Userid);
				rs=ps.executeQuery();
				if(rs.next())
				{
					Farm_id = rs.getLong(1);
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return Farm_id;
	}

	
	
}
