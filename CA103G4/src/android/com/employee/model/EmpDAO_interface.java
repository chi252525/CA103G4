package android.com.employee.model;

import java.util.*;


public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public EmpVO findByPrimaryKey(String emp_no);
          boolean isEmployee(String emp_Acnum, String emp_Psw);
          public List<EmpVO> getAll();
}