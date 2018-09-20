<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reservation.model.*"%>





<%@ include file="/front_end/PostHeader.jsp" %> 

     		<div >
     			<b>所有訂位紀錄 - listOneRes.jsp</b>
     			<hr>
     		</div>
     		
              <div class="remove"  id="res2end">
                    <div class="col-md-12 text-center">
                            <div class="container" >
                                <table class="table table-striped">
	                                <thead>
	                                    <tr>
	                                        <th>訂位流水編號</th>
	                                        <th>會員編號</th>
	                                        <th>桌位流水編號</th>
	                                        <th>訂位成立日期</th>
	                                        <th>訂位日期時段起始時間</th>
	                                        <th>訂位日期時段結束時間</th>
	                                        <th>用餐人數</th>
	                                        <th>訂位使用狀態</th>           
	                                        <th>修改</th>           
	                                                   
	                                    </tr>
	                                </thead>
                                <tbody> 
					                      
                                    <tr>
                                        <td>${resVO.res_no}</td>
                                        <td>${resVO.mem_no}</td>
                                        <td>${resVO.dek_no}</td>
                                        <td>${resVO.res_submit}</td>
                                        <td>${resVO.res_timebg}</td>
                                        <td>${resVO.res_timefn}</td>
                                        <td>${resVO.res_people}</td>
                                        <td>${resVO.res_status}</td>
                                        
                                        <td>
										  <FORM METHOD="post" ACTION="" style="margin-bottom: 0px;">
										     <input type="submit" value="修改">
										     <input type="hidden" name="res_no"  value="${resVO.res_no}">
										     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
										</td>
				
                                    </tr>
                                  
                  
                   
                                </tbody>
                              
                                          
                            </table>
                            
                     </div>
                </div>
             </div>

	
<%@ include file="/front_end/PostFooter.jsp" %> 	
	