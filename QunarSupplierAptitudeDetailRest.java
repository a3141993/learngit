package com.usky.ctripoa.qunar.rest;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.usky.ctripoa.qunar.po.QunarSupplierAptitudeDetailDo;
import com.usky.ctripoa.qunar.po.qunarContractDO;
import com.usky.ctripoa.qunar.service.QunarContractService;
import com.usky.ctripoa.qunar.service.QunarSupplierAptitudeDetailService;

/**
 * 去哪儿供应商资质明细
 * @author liudongshuai
 *
 */
@RestController
@RequestMapping(value = "/qunarSupplierAptitude")
public class QunarSupplierAptitudeDetailRest {
	
	@Autowired
	private QunarSupplierAptitudeDetailService qunarSupplierAptitudeDetailService; 
	
	@Autowired
	private QunarContractService qunarContractService;

	/**
	 * 对供应商查重
	 * 
	 * @param ruleParameter
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/findQunarContract/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public boolean findQunarContract(@PathVariable  String name) throws ParseException {
		Conjunction con = Restrictions.and(Restrictions.eq("type", "supplier"));
		con.add(Restrictions.eq("name", name));
		List<qunarContractDO> qunarContractList = qunarContractService.findByCriterion(con);
		if(qunarContractList.size() > 0) {
			return true;
		}
		return false;				
	}
	/**
	 * 新建单条项目明细
	 * 
	 * @param ruleParameter
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addOne", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addOneOaItemDetail(@RequestBody  Map<String, Object> parameter) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Integer formId = (Integer) parameter.get("formId");
		String supplierName = (String) parameter.get("supplierName");
		String supplierNo = (String) parameter.get("supplierNo");
		String payee = (String) parameter.get("payee");
		String bank = (String) parameter.get("bank");
		String account = (String) parameter.get("account");
		String financialContacts = (String) parameter.get("financialContacts");
		String contactsTel = (String) parameter.get("contactsTel");
		String bankType = (String) parameter.get("bankType");
		String country = (String) parameter.get("country");
		String provincial = (String) parameter.get("provincial");
		String city = (String) parameter.get("city");

		if(formId!=null) {			
			Map<String, Object> addObjectParm = new HashMap<String, Object>();
			addObjectParm.put("formId", formId);
			addObjectParm.put("supplierName", supplierName);
			addObjectParm.put("supplierNo", supplierNo);
			addObjectParm.put("payee", payee);
			addObjectParm.put("bank", bank);
			addObjectParm.put("account", account);
			addObjectParm.put("financialContacts", financialContacts);
			addObjectParm.put("contactsTel", contactsTel);
			addObjectParm.put("bankType", bankType);
			addObjectParm.put("country", country);
			addObjectParm.put("provincial", provincial);
			addObjectParm.put("city", city);
			
			int id = qunarSupplierAptitudeDetailService.addObject(addObjectParm);
			resultMap.put("id", id);
		}
		
		String resultStr = JSON.toJSONString(resultMap);
		return resultStr;		
	}
	
	/**
	 * 查询资质账号明细
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> findOaItemDetail(@PathVariable Integer id) {
		return qunarSupplierAptitudeDetailService.findObjectMapById(id);
	}

	/**
	 * 修改资质账号明细
	 * 
	 * @param ruleParameter
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateOaItemDetail(@PathVariable Integer id,
			@RequestBody Map<String, Object> parameter) {
		qunarSupplierAptitudeDetailService.updateObject(id, parameter);
	}
	
	/**
	 * 删除资质账号明细
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteOaItemDetailById(@PathVariable Integer id) {
		qunarSupplierAptitudeDetailService.deleteObjectById(id);
	}
	
	/**
	 * select list
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/{id}/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public  List<QunarSupplierAptitudeDetailDo> listOaItemDetailByFormId(@PathVariable Integer id) {
		Conjunction con = Restrictions.and(Restrictions.eq("deleted", false));
		con.add(Restrictions.eq("formId", id));
		List<QunarSupplierAptitudeDetailDo> list = qunarSupplierAptitudeDetailService.findByCriterion(con);
		return list;
	}
	
}
