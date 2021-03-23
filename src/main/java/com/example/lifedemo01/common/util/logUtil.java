package com.example.lifedemo01.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * 切面类
 */
//把切面类加入到IOC容器中
//@Component
//定义为一个切面类
//@Aspect
public class logUtil {
    private Logger log = LoggerFactory.getLogger(logUtil.class);
    /**
     * 定义切入点，切入点为 com.example.lifedemo01.controller下的所有函数
     */
    @Pointcut(value = "execution(* com.example.lifedemo01.controller.*.*(..))")
    public void pointOne(){}

    /**
     * 通知，(value=切点）
     * 注意：任何通知方法都可以将第一个参数定义为org.aspectj.lang.JoinPoint类型
     * 环绕通知需要定义第一个参数为ProceedingJoinPoint类型，
     * 它是 JoinPoint 的一个子类）。JoinPoint接口提供了一系列有用的方法，比
     * 如 getArgs()（返回方法参数）、getThis()（返回代理对象）、getTarget()
     * （返回目标）、getSignature()（返回正在被通知的方法相关信息）和 toString()（打印出正在被通知的方法的有用信息）。
     */
    //前置
    @Before(value = "pointOne()")
    public void doBefore(JoinPoint joinPoint){
        log.info("@Before-前置通知");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        log.info("代理的方法是："+signature.getName());
        //AOP代理类的名字
        log.info("AOP代理类的名字是："+signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        //获取请求参数
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = Maps.newHashMap();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
    }
    //后置（不论是正常返回还是异常退出）
    @After(value = "pointOne()")
    public void doAfter(JoinPoint joinPoint){
        log.info("@After-方法"+joinPoint.getSignature().getName()+"最终执行完了");
    }

    @AfterThrowing(value = "pointOne()",throwing = "exception")
    public void doThrows(JoinPoint joinPoint, Throwable exception){
        log.info("@AfterThrowing-异常通知");
        log.info("目标方法名称："+ joinPoint.getSignature().getName());
        log.info("exception:"+exception);
    }
    //结束后通知
    @AfterReturning(value = "pointOne()")
    public void doAfterReturn(){
        log.info("@AfterReturning-结束后通知");
    }


    /**
     * 环绕
     * 包围一个连接点的通知，如方法调用等。
     * 环绕通知可以在方法调用前后完成自定义的行为，它也会选择是否继续执行连接点或者直接返回它自己的返回值或抛出异常来结束执行。
     * 环绕通知最强大，也最麻烦，是一个对方法的环绕，具体方法会通过代理传递到切面中去，切面中可选择执行方法与否，执行几次方法等。
     * 环绕通知使用一个代理ProceedingJoinPoint类型的对象来管理目标对象，所以此通知的第一个参数必须是ProceedingJoinPoint类型。
     * 在通知体内调用ProceedingJoinPoint的proceed()方法会导致后台的连接点方法执行。
     * proceed()方法也可能会被调用并且传入一个Object[]对象，该数组中的值将被作为方法执行时的入参。
     * @param proceedingJoinPoint
     */
//    @Around(value = "pointOne()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        Long start=System.currentTimeMillis();
        try {
            //方法运行
            Object obj = proceedingJoinPoint.proceed();
            Long end=System.currentTimeMillis();
            log.info("方法"+proceedingJoinPoint.getSignature().getName()+"的具体运行时间是"+(end-start));
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

//        6、有时候我们定义切面的时候，切面中需要使用到目标对象的某个参数，如何使切面能得到目标对象的参数呢？
//        可以使用args来绑定。如果在一个args表达式中应该使用类型名字的地方使用一个参数名字，
//        那么当通知执行的时候对象的参数值将会被传递进来。

    @Before("execution(* findById*(..)) &&" + "args(id,..)")
    public void twiceAsOld1(Long id){
        System.err.println ("切面before执行了。。。。id==" + id);

    }


















//        /**
//         * 切入点配置
//         */
//        @Pointcut(value = "execution(* com.aaa.service.*.*(..))")
//        public void pointCutOne(){
//        }
//        /**
//         * 前置通知
//         * @param joinPoint
//         */
//        // @Before(value = "pointCutOne()")
//        public void beforeSaveLog(JoinPoint joinPoint){
//            System.out.println("在调用"+joinPoint.getSignature().getName()+"方法之前，打印。。。");
//        }
//
//        /**
//         * 后置通知
//         * @param joinPoint
//         */
//        @After(value = "pointCutOne()")
//        public void afterReturningSaveLog(JoinPoint joinPoint){
//            System.out.println("在调用"+joinPoint.getSignature().getName()+"方法之后，打印。。。");
//        }
//
//        /**
//         * 异常通知
//         * @param joinPoint
//         * @param e
//         */
//        @AfterThrowing(value = "pointCutOne()",throwing = "e")
//        public void afterThrowingSaveLog(JoinPoint joinPoint,Exception e){
//            System.out.println("在调用"+joinPoint.getSignature().getName()+"方法时出现了"+e.getClass().getName()+"异常，异常描述："+e.getMessage());
//        }
//        /**
//         * 最终通知
//         */
//        @AfterReturning(value = "pointCutOne()")
//        public void afterSaveLog(JoinPoint joinPoint){
//            System.out.println("在调用"+joinPoint.getSignature().getName()+"方法时，无论有没有异常都会打印。。。");
//        }
//        /**
//         * 环绕通知
//         */
//        public Object aroundSaveLog(ProceedingJoinPoint proceedingJoinPoint){
//            Object proceed = null;
//            System.out.println(System.currentTimeMillis()+"执行业务方法之前---");
//            try {
//                proceed =  proceedingJoinPoint.proceed();
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//            System.out.println(System.currentTimeMillis()+"执行业务方法之后---");
//            return proceed;
//        }
    }

