package android.com.reservation.model;

import java.util.*;



public interface ResDAO_interface {
	public void insert(ResVO resVO);
    public void update(ResVO resVO);
    public void delete(String res_no);
    public ResVO findByPrimaryKey(String res_no);
    public List<ResVO> getAll();
    public String addWithBranchNo(String branchNo, ResVO resVO, String seatStr);
}
