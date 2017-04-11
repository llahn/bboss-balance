package com.hpe.bboss.balance.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.bboss.balance.demo.service.UserService;
import com.hpe.bboss.balance.demo.vo.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@RestController
public class DemoController {
	
	@Resource(name="userServiceImpl")
	private UserService user;
	/*
	 * 注解说明：
	 * @Api：用在类上，说明该类的作用
	 * @ApiOperation：用在方法上，说明方法的作用
	 * @ApiImplicitParams：用在方法上包含一组参数说明
	 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
	 * 		paramType：参数放在哪个地方
	 * 			header-->请求参数的获取：@RequestHeader
	 * 			query-->请求参数的获取：@RequestParam
	 * 			path（用于restful接口）-->请求参数的获取：@PathVariable
	 * 			body（不常用）
	 * 			form（不常用）
	 * 			name：参数名
	 * 			dataType：参数类型
	 * 			required：参数是否必须传
	 * 			value：参数的意思
	 * 			defaultValue：参数的默认值
	 * @ApiResponses：用于表示一组响应
	 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
	 * 		code：数字，例如400
	 * 		message：信息，例如"请求参数没填好"
	 * 		response：抛出异常的类
	 * @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
	 * @ApiModelProperty：描述一个model的属性
	 * 
	 * 以上这些就是最常用的几个注解了。
	 */
	@ApiOperation(value="查询用户信息", notes="根据UserId查询用户")
	@ApiImplicitParam(name = "uId", value = "具体用户ID", required = true, dataType = "String")
	//@RequestMapping(value = "/user", method = RequestMethod.POST)
	@RequestMapping(value = "/user")
	public User index() {
		User res=user.selectByPrimaryKey(1);
		return res;
	}
}
