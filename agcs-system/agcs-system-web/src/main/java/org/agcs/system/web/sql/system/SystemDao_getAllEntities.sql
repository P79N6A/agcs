SELECT * FROM glpt_user where 1=1
<#if glptUser.userCode ?exists && glptUser.userCode ?length gt 0>
	and user_code = :glptUser.userCode
</#if> 