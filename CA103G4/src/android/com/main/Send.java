package android.com.main;
import java.io.*;

public class Send {

  public void sendMessage(String[] tel , String message)  {

  try {
      String server  = "203.66.172.131"; //Socket to Air Gateway IP
      int port	     = 8000;            //Socket to Air Gateway Port

      String user    = "85559671"; //帳號
      String passwd  = "2irioiai"; //密碼
      String messageBig5 = new String(message.getBytes(),"big5"); //簡訊內容

      //----建立連線 and 檢查帳號密碼是否錯誤
      sock2air mysms = new sock2air();
      int ret_code = mysms.create_conn(server,port,user,passwd) ;
      if( ret_code == 0 ) {
           System.out.println("帳號密碼Login OK!");
      } else {
      	   System.out.println("帳號密碼Login Fail!");
           System.out.println("ret_code="+ret_code + ",ret_content=" + mysms.get_message());
           //結束連線
           mysms.close_conn();
           return ;
      }

      //傳送文字簡訊
      //如需同時傳送多筆簡訊，請多次呼叫send_message()即可。
    for(int i=0 ; i<tel.length ; i++){  
      ret_code=mysms.send_message(tel[i],messageBig5);
      if( ret_code == 0 ) {
           System.out.println("簡訊已送到簡訊中心!");
           System.out.println("MessageID="+mysms.get_message()); //取得MessageID
           System.out.println("-----------------------------------------"); 
      } else {
           System.out.println("簡訊傳送發生錯誤!");
           System.out.print("ret_code="+ret_code+",");
           System.out.println("ret_content="+mysms.get_message());//取得錯誤的訊息
           //結束連線
           mysms.close_conn();
           return ;
      }
    }

      //結束連線
      mysms.close_conn();

  }catch (Exception e)  {

      System.out.println("I/O Exception : " + e);
   }
 }

 public static void main(String[] args) {
 	Send se = new Send();
 	String[] tel ={"09"};//,"0977777777","0988888888"};
 	String message = "排程訊息測試";
 	se.sendMessage(tel , message);
 }	

}//end of class
