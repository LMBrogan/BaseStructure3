package com.dailywear.base_structure.annotation;

import com.dailywear.base_structure.common.constant.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 记录管理日志和业务日志
 * @author Javacfox
 *
 */
@Slf4j
@Aspect
@Component
public class LogAopAction {

    /**
     * 保存请求时间
     */
    private static final ThreadLocal<Date> BEGIN_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal beginTime");


    @Pointcut("@annotation(com.dailywear.base_structure.annotation.LogAppender)")
    private void pointCutMethod(){}


    /**
     * 前置通知 (在方法执行之前返回)
     * @param joinPoint 切点
     */
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint) {
        //线程绑定变量（该数据只有当前请求的线程可见）
        Date dateTime = new Date();
        BEGIN_TIME_THREAD_LOCAL.set(dateTime);
    }

    /**
     * 后置通知 (在方法执行后返回)
     * @param joinPoint 切点
     */
    @After("pointCutMethod()")
    public void doAfter(JoinPoint joinPoint) {
        //线程绑定变量（该数据只有当前请求的线程可见）
        Date dateTime = new Date();
        BEGIN_TIME_THREAD_LOCAL.set(dateTime);
    }

    /**
     * 记录操作日志
     */
    @AfterThrowing(pointcut="pointCutMethod()",throwing = "e")
    public void throwss(JoinPoint jp, Exception e){
        //获取方法请求参数
        Object[] arg = jp.getArgs();
        System.out.println(arg);
    }

    /**
     * 获取注解中的值
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     */
    private Map<String, String> getLogMark(JoinPoint joinPoint) throws ClassNotFoundException {
        Map<String, String> map = new HashMap<>(6);
        String methodName = joinPoint.getSignature().getName();
        String targetName = joinPoint.getTarget().getClass().getName();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods){
            if(method.getName().equals(methodName)){
                LogAppender logAnnotation = method.getAnnotation(LogAppender.class);
                map.put(BaseConstant.LOG_TYPE,logAnnotation.logType());
                map.put(BaseConstant.OPERATE_TYPE,logAnnotation.operateType());
            }
        }
        return map;
    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(pointcut = "pointCutMethod()",returning = "ret")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        //获取方法请求参数
        Object[] arg = joinPoint.getArgs();
        System.out.println(arg);
    }

    /**
     * 环绕通知 (在方法执行前后返回)
     * @param joinPoint 切点
     */
    @Around("pointCutMethod()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("环绕通知执行前");
        proceedingJoinPoint.getArgs();
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕通知执行后");
    }
}
