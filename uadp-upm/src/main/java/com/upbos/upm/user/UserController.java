package com.upbos.upm.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upbos.session.SessionManager;
import com.upbos.session.SessionUser;
import com.upbos.upm.dict.DictUtil;
import com.upbos.upm.entity.User;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <p>ClassName: UserController</p>
 * <p>Description: 用户管理控制层</p>
 * <p>Company： upbos.com</p>
 * @author wangjz
 * @date 2016年11月27日 下午5:14:09
 * @version v2.5.0
 */
@RestController
@RequestMapping("upm/user")
public class UserController {
	
	private static Logger log = LogManager.getLogger(UserController.class);	
	
	@Resource
	private UserService srv;
	
	@Resource
	private SessionManager session;
	
	/**
	 * @Method: queryUserList
	 * @Description: 根据机构ID查询所属的用户列表
	 * @param orgId 机构ID
	 * @return 用户列表
	 */
	@RequestMapping("queryUserListByOrgId")
	public List<User> queryUserListByOrgId(Integer orgId) {
		List<User> userList = srv.queryUserListByOrgId(orgId);
		try {
			DictUtil.tranlate(userList, "user_type:type->typeName");
		} catch (Exception e) {
			log.error("用户管理->翻译用户类型字典时发生异常", e);
		}
		return userList;
	}
	
	/**
	 * @Method: insertUser
	 * @Description: 新建用户
	 * @param user 用户
	 */
	@RequestMapping("insertUser")
	public Map<String, Object> insertUser(HttpServletRequest req, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!srv.checkLoginNameOnly(user.getLoginName())) {
			result.put("success", false);
			result.put("msg", "登录帐号已经存在！");
			return result;
		}
		
		SessionUser loginUser = session.getSession(req).getUser();
		user.setCreateUserId(loginUser.getUid());
		srv.insertUser(user);
		result.put("success", true);
		result.put("msg", "新增用户成功！");
		return result;
	}
	
	/**
	 * @Method: updateUser
	 * @Description: 更新用户
	 * @param user 用户
	 */
	@RequestMapping("updateUser")
	public void updateUser(User user) {
		srv.updateUser(user);
	}
	
	/**
	 * @Method: removeUser
	 * @Description: 删除用户
	 * @param uid 用户ID
	 */
	@RequestMapping("deleteUser")
	public void deleteUser(String uids) {
		String[] uidsArr = uids.split(",");
		for(String uid : uidsArr) {
			srv.deleteUser(uid);
		}
	}
	
	@RequestMapping("resetPassword")
	public void resetPassword(User user) {
		srv.resetPassword(user);
	}
	
	@RequestMapping("pinyin")
	public String pinyin(String userName) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer pinyinName = new StringBuffer();  
        char[] nameChar = userName.toCharArray();  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        for (int i = 0; i < nameChar.length; i++) {  
            if (nameChar[i] > 128) {  
                	pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);  
            }else{  
                pinyinName.append(nameChar[i]);  
            }  
        }
        return pinyinName.toString();  
	}
	
	@RequestMapping("ownerRole")
	public Map<String, Object> ownerRole(String orgId, String uid) {
		return srv.ownerRole(orgId, uid);
	}
	
	@RequestMapping("updateUserRole")
	public void updateUserRole(String uid, @RequestParam(value="roles[]") String[] roles) {
		srv.updateUserRole(uid, roles);
	}
	
	@RequestMapping("getCurrentUserType")
	public String getUserType(HttpServletRequest request) {
		SessionUser sessionUser = session.getSession().getUser();
		return sessionUser.getType();
	}
}