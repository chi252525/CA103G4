package com.employee.model;

import java.util.*;

import com.empauthority.model.EmpauthorityVO;
import com.feature.model.FeatureVO;


public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public EmpVO findByPrimaryKey(String emp_no);
          public List<EmpVO> getAll();
          public void insertWithEmpauthorityVO(EmpVO empVO, List<EmpauthorityVO> empauthorlist);
          public EmpVO findByEmpAcnum(String emp_Acnum);
          public List<EmpVO> EmpVOfindByBranchNo(String branch_No);
}