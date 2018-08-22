package cn.ffcs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ffcs.model.TbDict;
import cn.ffcs.pojo.TreeView;
import cn.ffcs.service.TbDictService;
import cn.ffcs.util.ControllerUtils;
import cn.ffcs.util.DataTablePageUtil;
import cn.ffcs.util.MethodUtils;
import cn.ffcs.util.ResultUtils;
import cn.ffcs.util.TreeUtils;


@Controller
@RequestMapping("/dict")
public class DictController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private TbDictService tbDictService;
    
    
    /**
     * 分页列表
     * @param dict
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getPage")
    public void getPage(TbDict tbDict, 
    				HttpServletRequest request, HttpServletResponse response) {
    	
    	logger.debug("----->>>>>[getPage]:");
    	
    	// 设置父id
    	if(tbDict.getParentid()==null){
    		tbDict.setParentid(0L);
    	}

        //使用DataTables的属性接收分页数据
        DataTablePageUtil<TbDict> dataTable = new DataTablePageUtil<TbDict>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<TbDict> list = tbDictService.selectListByParam(tbDict);
        //用PageInfo对结果进行包装
        PageInfo<TbDict> pageInfo = new PageInfo<TbDict>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        ControllerUtils.writeJson(dataTable, response);
        
    }
    
    /**
     * 添加
     * @param parentid
     * @param response
     */
    @RequestMapping("/add")
    public void add(Long parentid, HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("parentid", parentid);
    	// 父级字典
    	TbDict parentDict = tbDictService.selectByKey(parentid);
    	
    	if( parentDict==null){
    		map.put("parentName", "");	//根目录
    	} else {
    		map.put("parentName", parentDict.getName());
    	}
    	
    	ControllerUtils.writeJson(map, response);
    }
    
    /**
     * 编辑
     * @param id
     * @param response
     * @throws Exception 
     */
    @RequestMapping("/edit")
    public void edit(Long id, HttpServletResponse response) throws Exception {
    	TbDict tbDict = tbDictService.selectByKey(id);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	// 转为map
    	if(tbDict!=null){
    		 map = MethodUtils.beanToMap(tbDict);
    	}
    	
    	// 父级字典
    	TbDict parentDict = new TbDict();
    	if( tbDict==null || tbDict.getParentid()==null || tbDict.getParentid()==0L ){
    		parentDict.setName("");	//根目录
    	} else {
    		parentDict = tbDictService.selectByKey(tbDict.getParentid());
    	}
    	map.put("parentName", parentDict.getName());
    	
    	ControllerUtils.writeJson(map, response);
    }
    @RequestMapping("/update")
    public void update(TbDict tbDict, HttpServletResponse response) {
    	logger.debug("----->>>>>[update]:");
    	
    	// 父菜单，默认为0
    	if( tbDict.getParentid()==null ){
    		tbDict.setParentid(0L);
    	}
    	// 创建时间
    	if ( tbDict.getCreatetime()==null ){
    		tbDict.setCreatetime(new Date());
    	}
    	
    	if( tbDict.getId() != null){
    		// 更新时间
    		tbDict.setUpdatetime(new Date());
    		
    		tbDictService.updateSelective(tbDict);
    	} else {
    		tbDictService.insert(tbDict);
    	}
    		
    	ResultUtils.renderSuccess("success", response);
    }
    
    
    /**
     * 删除
     * @param id
     * @param response
     */
    @RequestMapping("/delete")
    public void delete(Long id, HttpServletResponse response){
    	logger.debug("----->>>>>[delete]:");
    	
    	if( tbDictService.delete(id) ){
    		ResultUtils.renderSuccess("success", response);
    	} else {
    		ResultUtils.renderFail("fail", response);
    	}
        
    }
    
    /**
     * 根据父code取得子列表
     * @param parentCode
     * @param response
     */
    @RequestMapping("getListByParentCode")
    public void getListByParentCode(String parentCode, HttpServletResponse response){
    	ControllerUtils.writeJson(tbDictService.selectListByParentCode(parentCode), response);
    }
    
    /**
     * 判断code是否存在
     * @param code
     * @param id
     * @param response
     */
    @RequestMapping("valid")
    public void valid(String code, Long id, HttpServletResponse response){
    	Map<String, Boolean> map = new HashMap<String, Boolean>();
    	boolean valid = true;
    	
    	//判断当前id的code
		TbDict t = tbDictService.selectByKey(id);
		if( t==null || !t.getCode().equals(code) ){
	    	TbDict tbDict = new TbDict();
	    	tbDict.setCode(code);
	    	int count = tbDictService.selectCount(tbDict);
	    	if(count > 0){
	    		valid = false;
	    	}
		}
    	map.put("valid", valid);
    	ControllerUtils.writeJson(map, response);
    }
    
    /**
     * 获取树数据
     * 
     * @param response
     */
    @RequestMapping("/getTree")
    public void getTree(HttpServletResponse response){
    	
    	List<TreeView> treeViews = new ArrayList<TreeView>();
    	List<TbDict> dicts = tbDictService.selectListByParam(new TbDict());
    	
    	// 转化为树
    	for (TbDict dict : dicts){
    		TreeView tree = new TreeView();
    		tree.setId(dict.getId());
            tree.setText(dict.getName());
            tree.setParentId(dict.getParentid());
            tree.setStatus(dict.getStatus());
            
            treeViews.add(tree);
    	}
    	
    	ControllerUtils.writeJson(TreeUtils.formatNode(treeViews), response);
    }
    
}
