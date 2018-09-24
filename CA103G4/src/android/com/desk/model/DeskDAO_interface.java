package android.com.desk.model;

import java.sql.Connection;
import java.util.*;
import android.com.branch.model.BranchVO;

public interface DeskDAO_interface {
	public void insert(DeskVO deskVO);
    public void update(DeskVO deskVO);
    public void updateDekStatus(String dek_no, Connection con);
    public DeskVO findByPrimaryKey(String dek_no);
    public List<DeskVO> getAll();
    public List<DeskVO> getByBranchNo(String branch_no);
    
}
